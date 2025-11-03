import java.util.ArrayList;
import java.util.List;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>(); // Task 6

    // === Task 1: Add book ===
    public void addBook(Book book) {
        if (book != null && !books.contains(book)) {
            books.add(book);
        }
    }

    // === Task 1: Display all books ===
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
            return;
        }
        System.out.println("Library Catalog:");
        for (int i = 0; i < books.size(); i++) {
            System.out.print((i + 1) + ". ");
            books.get(i).displayDetails();
        }
    }

    // === Task 1: Find books by author ===
    public void findBooksByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) return;

        System.out.println("Books by Author \"" + author + "\":");
        boolean found = false;
        for (Book book : books) {
            if (author.equalsIgnoreCase(book.getAuthor())) {
                System.out.println(book.getTitle() + ", Year: " + book.getPublicationYear());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found by this author.");
        }
    }

    // === Task 2: Borrow book by title ===
    public boolean borrowBook(String title) {
        for (Book book : books) {
            if (title.equalsIgnoreCase(book.getTitle())) {
                books.remove(book);
                return true;
            }
        }
        System.out.println("Book \"" + title + "\" not available.");
        return false;
    }

    // === Task 2: Return book ===
    public void returnBook(Book book) {
        if (book != null && !books.contains(book)) {
            books.add(book);
        }
    }

    // === Task 3: Check availability ===
    public boolean isBookAvailable(String title) {
        if (title == null) return false;
        for (Book book : books) {
            if (title.equalsIgnoreCase(book.getTitle())) {
                return true;
            }
        }
        return false;
    }

    // === Task 5: Average rating ===
    public double getAverageBookRating() {
        if (books.isEmpty()) return 0.0;
        double sum = 0.0;
        int count = 0;
        for (Book book : books) {
            if (book.getRating() > 0.0) {
                sum += book.getRating();
                count++;
            }
        }
        return count > 0 ? sum / count : 0.0;
    }

    // === Task 5: Most reviewed book ===
    public Book getMostReviewedBook() {
        if (books.isEmpty()) return null;
        Book mostReviewed = books.get(0);
        for (Book book : books) {
            if (book.getReviewCount() > mostReviewed.getReviewCount()) {
                mostReviewed = book;
            }
        }
        return mostReviewed;
    }

    // === Task 6: User Management ===
    public void registerUser(User user) {
        if (user != null && !users.contains(user)) {
            users.add(user);
        }
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public boolean lendBookToUser(String title, User user) {
        if (user == null) return false;
        if (borrowBook(title)) {
            for (Book book : books) {
                if (title.equalsIgnoreCase(book.getTitle())) {
                    user.borrowBook(book);
                    return true;
                }
            }
        }
        return false;
    }

    public void returnBookFromUser(Book book, User user) {
        if (user != null && book != null) {
            user.returnBook(book);
            returnBook(book);
        }
    }
}