/**
 * This file contains the driver method for the application, which provides
 * an interface for loading student data, displaying it to a plot, and saving
 * it a file.
 * @author Michaela Chen
 * @author Daniel Hsu
 * @author Nicholas Ngo
 * @author Aatmik Mallya
 * @author Dion (Marco) Pimentel
 * @version 1.0
 * @since 11/21/2020
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableColumn;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * ProjectUI class creates an interface for the application and contains the
 * main method
 */
public class ProjectUI extends JFrame implements ActionListener
{
    private JTextArea output;
    private JPanel panel;
    private ChartPanel chartPanel;
    private RosterTable rTable;
    private Student[] roster;
    private JFreeChart chart;
    private int tableCols;
    private String[] rTableHeader;
    private String[][] rTableData;
    private boolean hasRTable, hasTextOut, hasPlot;

    /**
     * Constructor for ProjectUI initializes interface with title
     * @param title
     */
    public ProjectUI(String title)
    {

        super(title);

        //setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //setPreferredSize(new Dimension(500, 300));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 1000));

        setContentPane(panel);
        //add(panel);

        setJMenuBar(createMenuBar());

        output = new JTextArea(2,10);
        output.setEditable(false);
        output.setText(mull);
        panel.add(output);


        hasRTable = false;
        hasTextOut = true;

        setSize(500, 300);
        setLocationRelativeTo(null); //PUTS THE WINDOW IN THE CENTER OF THE SCREEN
        setVisible(true);
    }

    /**
     * Main function for application, creates a ProjectUI object
     * @param args
     */
    public static void main(String[] args)
    {
        ProjectUI UI = new ProjectUI("CSE360 Final Project");
    }

    /**
     * Creates a menu bar for the setJMenuBar method
     * @return JMenuBar object
     */
    public JMenuBar createMenuBar()
    {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem load, add, save, plot, about;

        menuBar = new JMenuBar();

        menu = new JMenu("File");

        load = new JMenuItem("Load a Roster");
        load.addActionListener(this);

        add = new JMenuItem("Add Attendance");
        add.addActionListener(this);

        save = new JMenuItem("Save");
        save.addActionListener(this);

        plot = new JMenuItem("Plot Data");
        plot.addActionListener(this);

        about = new JMenuItem("About");
        about.addActionListener(this);

        menu.add(load);
        menu.add(add);
        menu.add(save);
        menu.add(plot);
        menuBar.add(menu);
        menuBar.add(about);

        return menuBar;

    }

    /**
     * Performs appropriate action based on user input
     * @param e ActionEvent input event from user
     */
    public void actionPerformed(ActionEvent e)
    {
        String button = e.getActionCommand();
        switch(button)
        {
            case "Load a Roster":
                try
                {
                    loadRoster();
                }
                catch (Exception ex)
                {
                    System.out.println("An Error Has Occurred.");
                }
                break;

            case "Add Attendance":
                if (roster != null)
                {
                    Attendance attendance = new Attendance();
                    DateSelector dateSelector = new DateSelector();

                    dateSelector.selectDate();
                    String date = dateSelector.getDate();
                    if (date != null)
                    {
                        if (!attendance.loadAttendance(roster,date))
                        {
                            System.out.println("Attendance File not uploaded");
                        }
                    }
                    else
                    {
                        System.out.println("Date not selected");
                    }

                    panel.remove(rTable);

                    tableCols++;
                    String[] temp = new String[tableCols];
                    String[][] temp2D = new String[roster.length + 1][tableCols];

                    System.arraycopy(rTableHeader, 0, temp, 0, temp.length - 1);
                    temp[temp.length - 1] = date;
                    rTableHeader = temp;

                    for(int pos = 0; pos < roster.length + 1; pos++)
                    {
                        if(pos == 0)
                        {
                            System.arraycopy(rTableData[pos], 0, temp2D[pos], 0, tableCols - 1);
                            temp2D[pos][tableCols - 1] = date;
                        }
                        else
                        {
                            System.arraycopy(rTableData[pos],0, temp2D[pos], 0 , tableCols - 1);
                            temp2D[pos][tableCols - 1] = Integer.toString(roster[pos-1].getAttendance(date));
                        }

                    }

                    rTableHeader = temp;
                    rTableData = temp2D;

                    rTable = new RosterTable(roster, rTableData, rTableHeader);

                    panel.add(rTable);

                }
                else
                {
                    output.setText("Error: Empty Roster");
                }
                break;

            case "Save":
                if(hasPlot)
                {
                    hasPlot = false;
                    setContentPane(panel);
                }
                if (roster != null)
                {
                    Save save = new Save();
                    String filename = save.saveRoster(roster);
                    if(!hasTextOut)
                    {
                        panel.add(output);
                        hasTextOut = true;
                    }
                    output.setText("Roster successfully saved to " + filename);
                }
                else
                {
                    output.setText("Error: Empty Roster");
                }
                break;

            case "Plot Data":
                if(rTable != null)
                {
                    ScatterPlot plot = new ScatterPlot("Student Attendance", roster);
                    chart = plot.getChart();

                    chartPanel = new ChartPanel(chart);
                    setContentPane(chartPanel);
                    hasPlot = true;



                }
                else
                {
                    output.setText("Error: Empty Roster");
                }
                break;

            case "About":
                if(hasPlot)
                {
                    hasPlot = false;
                    setContentPane(panel);
                }
                if(hasRTable)
                {
                    //remove(rTable);
                    panel.remove(rTable);
                    hasRTable = false;
                    if(!hasTextOut)
                    {
                        //add(output);
                        panel.add(output);
                        hasTextOut = true;
                    }
                }

                output.setText("Team Members:" + "\n" +
                                "Aatmik Mallya" + "\n" +
                                "Daniel Hsu" + "\n" +
                                "Dion (Marco) Pimentel" + "\n" +
                                "Michaela Chen" + "\n" +
                                "Nicholas Ngo");
                break;

            default:
                output.setText("how did you add another button lol");
                break;
        }
        revalidate();
    }

    /**
     * Loads the CSV file containing student roster
     * @throws Exception
     */
    public void loadRoster() throws Exception
    {
        JFileChooser fileLoader = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = fileLoader.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION)
        {
            File rosterFile = fileLoader.getSelectedFile();
            String ext = rosterFile.getName().split("[.]")[1];
            if(ext.equals("csv"))
            {

                Path path = Paths.get(rosterFile.getAbsolutePath());
                int lines = (int) Files.lines(path).count();

                String[] tableHeader = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
                String[][] tableData = readRoster(tableHeader, rosterFile, lines, 6);

                rTable = new RosterTable(roster, tableData, tableHeader);

                rTableHeader = tableHeader;
                rTableData = tableData;
                tableCols = tableHeader.length;

                panel.remove(output);
                panel.add(rTable);

                hasRTable = true;
                hasTextOut = false;

            }
            else if(hasRTable && hasTextOut)
            {
                output.setText("Error: not a csv file");
            }
            else
            {
                panel.add(output);
                output.setText("Error: not a csv file");
                hasTextOut = true;
            }
        }
        else if (rTable == null)
        {
            output.setText("Error: No file chosen");
        }

    }

    /**
     * Reads the data in the CSV file and stores in into Student array
     * @param header first line of file
     * @param rosterFile CSV file of the roster
     * @param rows of the Student array
     * @param cols of the Student array
     * @return String[][] of student data
     * @throws Exception
     */
    public String[][] readRoster(String[] header, File rosterFile, int rows, int cols) throws Exception
    {
        roster = new Student[rows];
        String[][] studentData = new String[rows + 1][cols];
        Scanner in = new Scanner(rosterFile);
        in.useDelimiter(",|\\n");
        for(int line = 0; line < rows + 1; line++)
        {
            if(line == 0)
            {
                studentData[line] = header;
            }
            else
            {
                String ID = in.next();
                String firstName = in.next();
                String lastName = in.next();
                String program = in.next();
                String academicLevel = in.next();
                String asurite = in.next();
                if (asurite.charAt(asurite.length()-1) == '\r')
                    asurite = asurite.substring(0,asurite.length()-1);

                roster[line-1] = new Student(ID, firstName, lastName, program, academicLevel, asurite);

                studentData[line][0] = ID;
                studentData[line][1] = firstName;
                studentData[line][2] = lastName;
                studentData[line][3] = program;
                studentData[line][4] = academicLevel;
                studentData[line][5] = asurite;
            }

        }
        return studentData;
    }
}
