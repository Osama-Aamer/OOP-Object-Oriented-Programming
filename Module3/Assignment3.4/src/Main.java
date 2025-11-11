// Task3
import java.io.*;

public class Main {
    public static void main(String[] args) {

        Student student = new Student(1, "Alice Johnson", 20);
        Course course = new Course("CS101", "Introduction to Programming", "Dr. Smith");
        Enrollment enrollment = new Enrollment(student, course, "2023-01-15");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("enrollments.ser"))) {
            oos.writeObject(enrollment);
            System.out.println("Serialized: " + enrollment);
        } catch (IOException e) {
            System.err.println("Serialization error: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("enrollments.ser"))) {
            Enrollment deserialized = (Enrollment) ois.readObject();
            System.out.println("Deserialized: " + deserialized);
            System.out.println("Student: " + deserialized.getStudent());
            System.out.println("Course: " + deserialized.getCourse());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization error: " + e.getMessage());
        }
    }
}