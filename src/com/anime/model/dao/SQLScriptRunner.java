package com.anime.model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLScriptRunner {

    public static void runScript(String filePath) throws IOException, SQLException {
        String script = null;
        Path path = Paths.get(filePath);
//        String[] statements = script.split(";");
        if(Files.exists(path)){
            script = Files.readString(path, StandardCharsets.UTF_8);
        } else{
            String resourcePath = filePath.startsWith("/") ? filePath.substring(1) : filePath;
            InputStream in = SQLScriptRunner.class.getClassLoader().getResourceAsStream(resourcePath);
            if (in == null) {
                in = SQLScriptRunner.class.getResourceAsStream(filePath.startsWith("/") ? filePath : "/" + filePath);
            }
            if (in == null) {
                in = SQLScriptRunner.class.getClassLoader().getResourceAsStream("sql/" + resourcePath);
            }
            if (in == null) {
                throw new IOException("SQL script file not found: " + filePath + " (tried filesystem and classpath)");
            }
            script = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
        List<String> statements = parseSqlStatements(script);
        try (Connection conn = DBConnection.getServerConnection();
             Statement stmt = conn.createStatement()) {
            for (String raw : statements) {
                String sql = raw.trim();

                if (sql.trim().isEmpty()) {
                    continue;
                }
//                if(sql.contains("--")){
//                    sql=sql.substring(0,sql.indexOf("--")).trim();
//                }
//                if(!sql.isEmpty()){
                    stmt.execute(sql);
//                }
            }
        }

        System.out.println("Executed SQL script: " + filePath);
    }

    private static String readScript(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            return Files.readString(path, StandardCharsets.UTF_8);
        }

        // classpath variants
        String[] variants = new String[] {
                filePath.startsWith("/") ? filePath.substring(1) : filePath,
                filePath.startsWith("/") ? filePath : "/" + filePath,
                "sql/" + (filePath.startsWith("/") ? filePath.substring(1) : filePath)
        };

        for (String res : variants) {
            InputStream in = SQLScriptRunner.class.getClassLoader().getResourceAsStream(res);
            if (in == null) {
                // try with leading slash via Class.getResourceAsStream
                in = SQLScriptRunner.class.getResourceAsStream(res.startsWith("/") ? res : "/" + res);
            }
            if (in != null) {
                try (InputStream is = in) {
                    return new String(is.readAllBytes(), StandardCharsets.UTF_8);
                }
            }
        }

        throw new IOException("SQL script file not found: " + filePath + " (tried filesystem and classpath)");
    }

    // Parse SQL into statements: respect single-quoted strings, line comments (-- and #), and block comments (/* */).
    private static List<String> parseSqlStatements(String script) {
        List<String> statements = new ArrayList<>();
        StringBuilder cur = new StringBuilder();

        boolean inSingleQuote = false;
        boolean inDoubleQuote = false; // some SQL may use " for identifiers
        boolean inLineComment = false;
        boolean inBlockComment = false;

        int len = script.length();
        for (int i = 0; i < len; i++) {
            char c = script.charAt(i);
            char next = (i + 1 < len) ? script.charAt(i + 1) : '\0';

            if (inLineComment) {
                if (c == '\n' || c == '\r') {
                    inLineComment = false;
                    cur.append(c); // keep newline (not strictly necessary)
                } else {
                    // skip character (inside line comment)
                }
                continue;
            }

            if (inBlockComment) {
                if (c == '*' && next == '/') {
                    inBlockComment = false;
                    i++; // skip '/'
                }
                // skip everything inside block comment
                continue;
            }

            // detect start of line comment (-- or #) when not inside quotes
            if (!inSingleQuote && !inDoubleQuote) {
                if (c == '-' && next == '-') {
                    inLineComment = true;
                    i++; // skip next '-'
                    continue;
                }
                if (c == '#') {
                    inLineComment = true;
                    continue;
                }
                if (c == '/' && next == '*') {
                    inBlockComment = true;
                    i++; // skip '*'
                    continue;
                }
            }

            // handle quotes (allow escaped quotes by doubling '')
            if (c == '\'' && !inDoubleQuote) {
                cur.append(c);
                // check for escaped single-quote: SQL uses two single quotes to escape
                if (inSingleQuote && next == '\'') {
                    cur.append(next);
                    i++; // consume escaped quote
                } else {
                    inSingleQuote = !inSingleQuote;
                }
                continue;
            }

            if (c == '"' && !inSingleQuote) {
                cur.append(c);
                // handle doubled double quotes as escape
                if (inDoubleQuote && next == '"') {
                    cur.append(next);
                    i++;
                } else {
                    inDoubleQuote = !inDoubleQuote;
                }
                continue;
            }

            // statement delimiter: semicolon outside quotes and comments
            if (c == ';' && !inSingleQuote && !inDoubleQuote) {
                String statement = cur.toString().trim();
                if (!statement.isEmpty()) {
                    statements.add(statement);
                }
                cur.setLength(0);
                continue;
            }

            cur.append(c);
        }

        String last = cur.toString().trim();
        if (!last.isEmpty()) {
            statements.add(last);
        }
        return statements;
    }
}


