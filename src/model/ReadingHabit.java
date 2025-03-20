package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ReadingHabit {
    private int habitId;
    private int userId;
    private String book;
    private int pagesRead;
    private LocalDateTime submissionMoment;

    public ReadingHabit() {
    }

    public ReadingHabit(int habitId, int userId, String book, int pagesRead, LocalDateTime submissionMoment) {
        this.habitId = habitId;
        this.userId = userId;
        this.book = book;
        this.pagesRead = pagesRead;
        this.submissionMoment = submissionMoment;
    }

    public int getHabitId() {
        return habitId;
    }

    public int getUserId() {
        return userId;
    }

    public String getBook() {
        return book;
    }

    public int getPagesRead() {
        return pagesRead;
    }

    public LocalDateTime getSubmissionMoment() {
        return submissionMoment;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    public void setSubmissionMoment(LocalDateTime submissionMoment) {
        this.submissionMoment = submissionMoment;
    }

    @Override
    public String toString() {
        return  "habitId=" + habitId +
                ", userId=" + userId +
                ", book='" + book +
                ", pagesRead=" + pagesRead +
                ", submissionMoment=" + submissionMoment;
    }
}
