package library.system;

import library.model.Book;
import library.model.LibraryMember;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<LibraryMember> members = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added: " + book);
    }

    public void addMember(LibraryMember member) {
        members.add(member);
        System.out.println("Added member: " + member.getName());
    }

    public void borrowBook(LibraryMember member, Book book) {
        if (books.contains(book) && !book.isReserved()) {
            member.borrowBook(book);
            System.out.println(member.getName() + " borrowed: " + book.getTitle());
        } else {
            System.out.println("Cannot borrow: unavailable or reserved.");
        }
    }

    public void returnBook(LibraryMember member, Book book) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            System.out.println(member.getName() + " returned: " + book.getTitle());
        }
    }

    public void reserveBook(LibraryMember member, Book book) {
        if (books.contains(book) && !book.isReserved()) {
            book.setReserved(true);
            member.addReservedBook(book);
            System.out.println(member.getName() + " reserved: " + book.getTitle());
        } else {
            System.out.println("Cannot reserve: already reserved.");
        }
    }

    public void cancelReservation(LibraryMember member, Book book) {
        if (book.isReserved() && member.hasReservedBook(book)) {
            book.setReserved(false);
            member.removeReservedBook(book);
            System.out.println("Reservation canceled.");
        }
    }

    public void displayReservedBooks(LibraryMember member) {
        System.out.println("Reserved by " + member.getName() + ":");
        for (Book b : member.getReservedBooks()) {
            System.out.println("  → " + b.getTitle());
        }
    }
}