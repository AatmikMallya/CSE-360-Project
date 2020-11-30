//package FinalProject;

import javax.swing.*;
import java.io.File;


public class RosterTable extends JTable
{
    private Student[] rosterData;
    private Student[] attendanceData;
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
