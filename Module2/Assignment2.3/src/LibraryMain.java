import java.util.List;

public class LibraryMain {
    public static void main(String[] args) {
        // === Task 1: Create books ===
        Book b1 = new Book("Introduction to Java Programming", "John Smith", 2020);
        Book b2 = new Book("Data Structures and Algorithms", "Jane Doe", 2018);
        Book b3 = new Book("The Art of Fiction", "Alice Johnson", 2019);
        Book b4 = new Book("Advanced Java", "Jane Doe", 2021);

        // === Task 1: Create library and add books ===
        Library library = new Library();
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);

        // Display all books
        library.displayBooks();

        // Find by author
        System.out.println();
        library.findBooksByAuthor("Jane Doe");

        // === Task 2 & 3: Borrowing & Availability ===
        System.out.println("\nBorrowing \"Advanced Java\"...");
        boolean borrowed = library.borrowBook("Advanced Java");
        System.out.println("Available? " + library.isBookAvailable("Advanced Java")); // false

        // === Task 4: Ratings & Reviews ===
        b1.setRating(4.5);
        b1.addReview("Great intro!");
        b1.addReview("Clear examples.");

        b2.setRating(5.0);
        b2.addReview("Must-read for CS students.");

        // === Task 5: Statistics ===
        System.out.printf("\nAverage rating: %.2f%n", library.getAverageBookRating());
        Book mostReviewed = library.getMostReviewedBook();
        if (mostReviewed != null) {
            System.out.println("Most reviewed: " + mostReviewed.getTitle() + " (" + mostReviewed.getReviewCount() + " reviews)");
        }

        // === Task 6: Users & Borrowing ===
        System.out.println("\n--- User System ---");
        User user1 = new User("Alice", 25);
        User user2 = new User("Bob", 30);
        library.registerUser(user1);
        library.registerUser(user2);

        // Lend a book that is still available
        library.lendBookToUser("Introduction to Java Programming", user1);

        System.out.println(user1 + " borrowed books: " + user1.getBorrowedBooks().size());

        // === Return book safely ===
        List<Book> borrowedBooks = user1.getBorrowedBooks();
        if (!borrowedBooks.isEmpty()) {
            Book returnedBook = borrowedBooks.get(0);
            library.returnBookFromUser(returnedBook, user1);
            System.out.println("After return, available: " + library.isBookAvailable("Introduction to Java Programming"));
        } else {
            System.out.println("No book to return.");
        }
    }
}