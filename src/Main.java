import model.ReadingHabit;
import model.User;
import services.DatabaseConfigurer;
import services.ReadingHabitServiceJDBC;
import services.UserServiceJDBC;

import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:sqlite:E:\\Java\\Java Projects\\Assignment 2\\resources\\data.sqlite";
    private static final Scanner scanner = new Scanner(System.in);
    private static UserServiceJDBC userServiceJDBC;
    private static ReadingHabitServiceJDBC readingHabitServiceJDBC;

    public static void main(String[] args)
    {

        DatabaseConfigurer dc = new DatabaseConfigurer(url);
        dc.createTables();
        userServiceJDBC = new UserServiceJDBC(url);
        readingHabitServiceJDBC = new ReadingHabitServiceJDBC(url);

        menu();
    }

    private static void menu()
    {
        while (true) {
            System.out.println("1. Add User");
            System.out.println("2. Show User Reading Habits");
            System.out.println("3. Change Book Title");
            System.out.println("4. Delete Reading Record");
            System.out.println("5. Show Mean User Age");
            System.out.println("6. Show Users Who Read Specific Book");
            System.out.println("7. Show Total Pages Read");
            System.out.println("8. Show Users Reading Multiple Books");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1 -> addUser();
                case 2 -> showUserReadingHabits();
                case 3 -> changeBookTitle();
                case 4 -> deleteReadingRecord();
                case 5 -> showMeanUserAge();
                case 6 -> showUsersReadSpecificBook();
                case 7 -> showTotalPagesRead();
                case 8 -> showUsersMultipleBooks();
                case 9 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void addUser()
    {
        User user = new User();

        System.out.print("Enter user ID: ");
        user.setUserID(scanner.nextInt());
        System.out.print("Enter age: ");
        user.setAge(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter gender: ");
        user.setGender(scanner.nextLine());
        System.out.print("Enter name: ");
        user.setName(scanner.nextLine());

        try {
            userServiceJDBC.saveUser(user);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void showUserReadingHabits()
    {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();

        try {
            for(ReadingHabit readingHabit : readingHabitServiceJDBC.getUserReadingHabits(userId)){
                System.out.println(readingHabit.toString());
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void changeBookTitle()
    {
        System.out.print("Enter habit ID: ");
        int habitId = scanner.nextInt();
        System.out.print("Enter new book title: ");
        String newBookTitle = scanner.next();

        try {
            readingHabitServiceJDBC.setNewBookTitle(habitId, newBookTitle);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void deleteReadingRecord()
    {
        System.out.print("Enter habit ID: ");
        int habitId = scanner.nextInt();

        try {
            readingHabitServiceJDBC.deleteHabit(habitId);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void showMeanUserAge()
    {
        try {
            System.out.println(userServiceJDBC.getMeanAge());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void showUsersReadSpecificBook()
    {
        System.out.print("Enter book title: ");
        String bookTitle = "";
        bookTitle += scanner.nextLine();

        try {
            System.out.println(readingHabitServiceJDBC.getNumberOfUsersReadSpecificBook(bookTitle));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void showTotalPagesRead()
    {
        try {
            System.out.println(readingHabitServiceJDBC.getTotalPagesRead());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void showUsersMultipleBooks()
    {
        try {
            System.out.println(readingHabitServiceJDBC.getTotalNumberOfUserWhoReadMoreThanOneBook());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}