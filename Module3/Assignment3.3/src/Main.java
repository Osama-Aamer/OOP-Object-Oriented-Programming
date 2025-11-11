import library.model.Book;
import library.model.LibraryMember;
import library.system.Library;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();

        Book b1 = new Book("Java", "Doe", "111");
        Book b2 = new Book("Python", "Smith", "222");
        lib.addBook(b1);
        lib.addBook(b2);

        LibraryMember m1 = new LibraryMember("Alice", 1);
        LibraryMember m2 = new LibraryMember("Bob", 2);
        lib.addMember(m1);
        lib.addMember(m2);

        // === Task 3: Reserve & Cancel ===
        System.out.println();
        lib.borrowBook(m1, b1);
        lib.reserveBook(m2, b1);  // Should fail
        lib.reserveBook(m1, b2);
        lib.displayReservedBooks(m1);
        lib.cancelReservation(m1, b2);
        lib.displayReservedBooks(m1);
    }
}