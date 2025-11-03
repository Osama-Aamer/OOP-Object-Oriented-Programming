import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private double rating;
    private List<String> reviews;


    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.rating = 0.0;
        this.reviews = new ArrayList<>();
    }

    // === Task 4: Rating and Reviews ===
    public void setRating(double rating) {
        if (rating >= 0.0 && rating <= 5.0) {
            this.rating = rating;
        }
    }

    public void addReview(String review) {
        if (review != null && !review.trim().isEmpty()) {
            this.reviews.add(review.trim());
        }
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public double getRating() { return rating; }
    public int getReviewCount() { return reviews.size(); }

    @Override
    public String toString() {
        return "Title: \"" + title + "\", Author: \"" + author + "\", Year: " + publicationYear;
    }

    public void displayDetails() {
        System.out.println(toString());
        if (rating > 0.0) {
            System.out.printf("   Rating: %.1f/5.0 (%d reviews)%n", rating, reviews.size());
        }
    }
}