package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfigurer {
    private final String url;

    public DatabaseConfigurer(String url)
    {
        this.url = url;
    }

    public void createTables()
    {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS User (" +
                    "userId INTEGER PRIMARY KEY," +
                    "age INTEGER," +
                    "gender CHAR," +
                    "name CHAR)");

            stmt.execute("CREATE TABLE IF NOT EXISTS ReadingHabit (" +
                    "habitID INTEGER PRIMARY KEY," +
                    "userId INTEGER," +
                    "book CHAR," +
                    "pagesRead INTEGER," +
                    "submissionMoment CHAR," +
                    "FOREIGN KEY (userId) REFERENCES User(userId))");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
