package com.anime.model.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLScriptRunner {

    public static void runScript(String filePath) throws IOException, SQLException {

        String script = Files.readString(Paths.get(filePath));

        String[] statements = script.split(";");

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            for (String raw : statements) {
                String sql = raw.trim();

                if (!sql.isEmpty()) {
                    stmt.execute(sql);
                }
            }
        }

        System.out.println("Executed SQL script: " + filePath);
    }
}


