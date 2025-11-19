import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.anime.model.dao.SQLScriptRunner;
import com.anime.controller.*;
import com.anime.model.dao.AppModel;
import com.anime.view.*;
import com.anime.model.*;

import javax.swing.*;

public class Takoroll {
    
    private final String DB_URL = "jdbc:mysql://localhost:3306/takoroll"; // ???
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "to be filled";
    private static Connection connection = null;
//    private AnimeFrame mainFrame = new AnimeFrame();
//    private AppModel model = new AppModel();

    public static void main(String[] args) {
        try (InputStream in = Takoroll.class.getResourceAsStream("/sql/db_anime1.sql")){
            if(in==null){
                System.err.println("Resource not found: /sql/db_anime1.sql (make sure it's in `src/main/resources/sql/)");
                return;
            }
            Path tmp = Files.createTempFile("db_anime1", ".sql");
            try{
                Files.copy(in, tmp, StandardCopyOption.REPLACE_EXISTING);
                SQLScriptRunner.runScript(tmp.toString());
            } finally{
                Files.deleteIfExists(tmp);
            }
            System.out.println("Database Initialized");
        } catch (IOException e) {
            System.err.println("Error executing IO script: " + e.getMessage());
            return;
        } catch (SQLException e) {
            System.err.println("Error executing SQL script: " + e.getMessage());
            return;
        }

        SwingUtilities.invokeLater(()->{
            AppModel model = new AppModel();
            AnimeFrame view = new AnimeFrame();
            AppController controller = new AppController(view, model);

//            view.remove(view.getHeaderPanel());
            view.setVisible(true);
            System.out.println("Application started...");
        });
    }
}
