
import javax.swing.*;
import java.io.File;

/**
 * RosterTable class defines the table used for displaying the roster
 */
public class RosterTable extends JTable
{
    private Student[] rosterData;
    private Student[] attendanceData;

    /**
     * Constructor for RosterTable initializes the roster data for the table
     * @param studentData, array of Students
     * @param tableData, 2d array of table data
     * @param tableHeaders, table headers in String format
     */
    public RosterTable(Student[] studentData, String[][] tableData, String[] tableHeaders)
    {
        super(tableData, tableHeaders);
        rosterData = studentData;
        isCellEditable(0,0);
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

}
