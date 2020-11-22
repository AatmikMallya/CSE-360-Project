package FinalProject;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.BorderLayout;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class ProjectUI extends JFrame implements ActionListener
{
    private JTextArea output;
    private JScrollPane scrollPane;
    private JPanel contentPane;
    private RosterTable rTable;
    private Student[] roster;

    public ProjectUI(String title)
    {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setJMenuBar(createMenuBar());
        //setContentPane(createContentPane());
        output = new JTextArea(5,30);
        output.setEditable(false);
        output.setText("testing outputs");
        add(output);


        setSize(450, 260);
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
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

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


    public Container createContentPane() {
        contentPane = new JPanel(new BorderLayout());
        //contentPane.setOpaque(true);

        output = new JTextArea(5,30);
        output.setEditable(false);
        output.setText("testing outputs");
        scrollPane = new JScrollPane(output);

        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
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
                output.setText("Add Attendance to be implemented");
                break;

            case "Save":
                if (roster != null)
                {
                    Save save = new Save();
                    save.saveRoster(roster);
                }
                else
                {
                    output.setText("Empty Roster");
                }
                break;

            case "Plot Data":
                output.setText("Plot Data to be implemented");
                break;

            case "About":
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
        repaint();
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
                //scrollPane.remove(output);
                //scrollPane.add(rTable);
                remove(output);
                add(rTable);

            }
            else
            {
                output.setText("not a csv file");
            }
        }
        else
        {
            output.setText("Pick a file u fukr");
        }

        repaint();
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
            //System.out.println(asurite);

            roster[line] = new Student(ID, firstName, lastName, program, academicLevel, asurite);
            System.out.println("new student added");
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
