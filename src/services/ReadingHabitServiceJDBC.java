package services;

import model.ReadingHabit;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReadingHabitServiceJDBC {
    private final String url;

    public ReadingHabitServiceJDBC(String url)
    {
        this.url = url;
    }

    public List<ReadingHabit> getUserReadingHabits(int userId) throws Exception
    {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ReadingHabit WHERE userId=?"))
        {
            List<ReadingHabit> habits = new ArrayList<>();

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

            while (rs.next()) {
                ReadingHabit habit = new ReadingHabit(
                        rs.getInt("habitID"),
                        rs.getInt("userId"),
                        rs.getString("book"),
                        rs.getInt("pagesRead"),
                        LocalDateTime.parse(rs.getString("submissionMoment"), formatter)
                );
                habits.add(habit);
            }

            return habits;
        }
    }

    public void setNewBookTitle(int habitId, String newBookTitle) throws Exception
    {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE ReadingHabit SET book=? WHERE habitId=?"))
        {
            pstmt.setString(1, newBookTitle);
            pstmt.setInt(2, habitId);
            pstmt.executeUpdate();
        }
    }

    public void deleteHabit(int habitId) throws Exception
    {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ReadingHabit WHERE habitId=?"))
        {
            pstmt.setInt(1, habitId);
            pstmt.executeUpdate();
        }
    }

    public int getNumberOfUsersReadSpecificBook(String bookTitle) throws Exception
    {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(book) as numberOfUsers FROM ReadingHabit WHERE book=?"))
        {
            pstmt.setString(1, bookTitle);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt("numberOfUsers");
        }
    }

    public int getTotalPagesRead() throws Exception
    {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement())
        {
            ResultSet rs = stmt.executeQuery("SELECT SUM(pagesRead) as pagesRead FROM ReadingHabit");
            return rs.getInt("pagesRead");
        }
    }

    public int getTotalNumberOfUserWhoReadMoreThanOneBook() throws Exception
    {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement())
        {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(DISTINCT userID) as numberOfUsers FROM (SELECT userId FROM ReadingHabit GROUP BY userId HAVING COUNT(book) > 1)");
            return rs.getInt("numberOfUsers");
        }
    }

}
