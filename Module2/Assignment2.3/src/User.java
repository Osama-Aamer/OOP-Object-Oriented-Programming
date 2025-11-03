import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;
    private List<Book> borrowedBooks;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        if (book != null && !borrowedBooks.contains(book)) {
            borrowedBooks.add(book);
        }
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks); // Return copy
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", borrowed=" + borrowedBooks.size() + " books}";
    }
}