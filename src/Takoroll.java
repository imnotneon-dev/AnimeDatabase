import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.anime.model.dao.SQLScriptRunner;
import com.anime.*;
import com.anime.controller.*;
import com.anime.model.dao.AppModel;
import com.anime.view.*;
import com.anime.model.*;

public class Takoroll {
    
    private final String DB_URL = "jdbc:mysql://localhost:3306/takoroll"; // ???
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "to be filled";
    private static Connection connection = null;
    private AnimeFrame mainFrame = new AnimeFrame();
    private AppModel model = new AppModel();

    public static void main(String[] args) {
        try {
            SQLScriptRunner.runScript("AnimeDatabase/resources/sql/02_add_constraints.sql");
            SQLScriptRunner.runScript("AnimeDatabase/resources/sql/data_FavoriteSeries.sql");
            SQLScriptRunner.runScript("AnimeDatabase/resources/sql/data_accounts.sql");
            SQLScriptRunner.runScript("AnimeDatabase/resources/sql/data_actors.sql");
            SQLScriptRunner.runScript("AnimeDatabase/resources/sql/data_episodes.sql");
            SQLScriptRunner.runScript("AnimeDatabase/resources/sql/data_likedepisodes.sql");
            SQLScriptRunner.runScript("AnimeDatabase/resources/sql/data_series.sql");
            SQLScriptRunner.runScript("AnimeDatabase/resources/sql/db_anime.sql");
            SQLScriptRunner.runScript("AnimeDatabase/resources/sql/getFavoriteSeriesByUser.sql");
            SQLScriptRunner.runScript("AnimeDatabase/resources/sql/report_reviewlog.sql");
            System.out.println("Database Initialized");
        } catch (Exception e) {
            e.printStackTrace();
        }

        AppController controller = new AppController(mainFrame, model);
    }

    public Connection connectToDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to Takoroll database successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            System.getLogger(Takoroll.class.getName()).log(System.Logger.Level.ERROR, "Database connection error", e);
        }
        return connection;
    }

    public Connection geConnection() {
        return connection;
    }
}
