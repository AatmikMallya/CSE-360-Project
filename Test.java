import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.Container;
import java.awt.*;

public class Test {
    JTextArea output;
    JScrollPane scrollPane;

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        menuBar = new JMenuBar();

        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);

        menuItem = new JMenuItem("Load a Roster");
        menu.add(menuItem);

        menuItem = new JMenuItem("Add Attendance");
        menu.add(menuItem);

        menuItem = new JMenuItem("Save");
        menu.add(menuItem);

        menuItem = new JMenuItem("Plot Data");
        menu.add(menuItem);



        menu  = new JMenu("About");
        menuBar.add(menu);

        return menuBar;
    }

    public Container createContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        output = new JTextArea(5,30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CSE360 Final Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Test test = new Test();
        frame.setJMenuBar(test.createMenuBar());
        frame.setContentPane(test.createContentPane());

        frame.setSize(450, 260);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
