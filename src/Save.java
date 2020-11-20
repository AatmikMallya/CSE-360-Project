import java.io.IOException;
import java.io.FileWriter;

/**
 * Save defines a method for writing data stored in the JTable to a vsc file.
 */
public class Save {
    /**
     * Writes data from roster to a csv file
     * @param students array of Student objects
     */
    public static void saveRoster(Student[] students) {
        String fileName = "students.csv";

        try {
            FileWriter file = new FileWriter(fileName);
            // Write table headers first
            file.write("ID,First Name,Last Name,Program,Academic Level,ASURITE\n");

            // Write all data from students
            for (int iterator = 0; iterator < students.length; iterator++) {
                file.write(students[iterator].getCSV());
            }
            file.close();

            System.out.println("Successfully wrote to " + fileName + ".");
        }
        catch (IOException e) {
            System.out.println("An error occurred while writing to " + fileName + ".");
            e.printStackTrace();
        }
    }

    // TEMPORARY - just for testing saveRoster function
    public static void main(String[] args) {
        Student[] students = {
                new Student("1","sam","smith","cs","freshman","1234"),
                new Student("2","joe","roberts","history","soph","567"),
                new Student("3","anna","ssss","math","junior","89"),
        };

        saveRoster(students);
    }
}
