package services;

import model.User;
import java.sql.*;

public class UserServiceJDBC {
    private final String url;

    public UserServiceJDBC(String url)
    {
        this.url = url;
    }

    public void saveUser(User user) throws Exception {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO User(userId, age, gender, name) VALUES(?,?,?,?)"))
        {

            pstmt.setInt(1, user.getUserID());
            pstmt.setInt(2, user.getAge());
            pstmt.setString(3, user.getGender());
            pstmt.setString(4, user.getName());
            pstmt.executeUpdate();
        }
    }

    public double getMeanAge() throws Exception
    {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement())
        {

            ResultSet rs = stmt.executeQuery("SELECT AVG(age) as meanAge FROM User");
            return rs.getDouble("meanAge");
        }
    }
}
