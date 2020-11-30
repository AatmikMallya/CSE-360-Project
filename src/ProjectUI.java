package FinalProject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;



public class ProjectUI extends JFrame implements ActionListener
{
    private JTextArea output;
    private JPanel panel;
    private ChartPanel chartPanel;
    private RosterTable rTable;
    private Student[] roster;
    private JFreeChart chart;
    private boolean hasRTable, hasTextOut, hasPlot;

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
        output.setText("testing outputs");
        panel.add(output);


        hasRTable = false;
        hasTextOut = true;

        setSize(500, 300);
        setLocationRelativeTo(null); //PUTS THE WINDOW IN THE CENTER OF THE SCREEN
        setVisible(true);
    }

    public static void main(String[] args)
    {
        ProjectUI UI = new ProjectUI("CSE360 Final Project");
    }

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
                            System.out.println("Attendance File not selected");
                        }
                    }
                    else
                        System.out.println("Date not selected");
                }
                else
                {
                    output.setText("Empty Roster");
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
                    if(!hasTextOut) {
                        //remove(rTable);
                        //add(output);
                        //panel.remove(rTable);
                        //panel.remove(rTable);
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

                String[] rTableHeader = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
                String[][] tableData = readRoster(rosterFile, lines, 6);

                rTable = new RosterTable(roster, tableData, rTableHeader);

                //remove(output);
                //add(rTable);
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

    public String[][] readRoster(File rosterFile, int rows, int cols) throws Exception
    {
        roster = new Student[rows];
        String[][] studentData = new String[rows][cols];
        Scanner in = new Scanner(rosterFile);
        in.useDelimiter(",|\\n");
        for(int line = 0; line < rows; line++)
        {
            String ID = in.next();
            //System.out.println(ID);
            String firstName = in.next();
            //System.out.println(firstName);
            String lastName = in.next();
           // System.out.println(lastName);
            String program = in.next();
            //System.out.println(program);
            String academicLevel = in.next();
            //System.out.println(academicLevel);
            String asurite = in.next();
            if (asurite.charAt(asurite.length()-1) == '\r')
            	asurite = asurite.substring(0,asurite.length()-1);
            //System.out.println(asurite);

            roster[line] = new Student(ID, firstName, lastName, program, academicLevel, asurite);
            //System.out.println("new student added");
            studentData[line][0] = ID;
            studentData[line][1] = firstName;
            studentData[line][2] = lastName;
            studentData[line][3] = program;
            studentData[line][4] = academicLevel;
            studentData[line][5] = asurite;
        }
        return studentData;
    }
}
