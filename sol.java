import java.io.*;
import java.util.*;

class Student implements Comparable<Student> {
    private final int id;
    private final String name;
    private final float grade;

    public Student(int id, String name, float grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + grade;
    }
}

public class StudentGradeSorter {
    public static void main(String[] args) {
        String inputFile = "studentGrades.csv";
        String outputFile = "sortedStudentGrades.csv";
        List<Student> students = new ArrayList<>();

        // Step 1: Read from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                float grade = Float.parseFloat(parts[2].trim());
                students.add(new Student(id, name, grade));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Step 2: Sort by student ID
        Collections.sort(students);

        // Step 3: Write to the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }

        System.out.println("Sorting complete. Output written to " + outputFile);
    }
}