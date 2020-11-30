
import java.io.IOException;
import java.io.FileWriter;

/**
 * Save defines a method for writing data stored in the JTable to a vsc file.
 */
public class Save
{
    /**
     * Writes data from roster to a csv file
     * @param students array of Student objects
     */
    public static String saveRoster(Student[] students)
    {
        String fileName = "students.csv";

        try
        {
            FileWriter file = new FileWriter(fileName);
            // Write table headers first
            String header = "ID,First Name,Last Name,Program,Academic Level,ASURITE";

            if(students[0].getAttendanceCount() != 0)
            {
                for(int pos = 0; pos < students[0].getAttendanceCount(); pos++)
                {
                    header = header + "," + students[0].getAttendanceDate(pos);
                }
            }
            header = header + "\n";

            file.write(header);

            // Write all data from students
            for (int iterator = 0; iterator < students.length; iterator++)
            {
                file.write(students[iterator].getCSV());
            }
            file.close();


        }
        catch (IOException e)
        {
            System.out.println("An error occurred while writing to " + fileName + ".");
            e.printStackTrace();
        }
        return fileName;
    }
}
