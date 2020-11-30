

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

/**
 * Attendance class defines a method for loading attendance
 */
public class Attendance extends JFrame
{
	/**
	 * Creates file chooser for the user to enter a file
	 * @param roster array of Students
	 * @param date String format
	 * @return boolean: true if succeeded, false otherwise
	 */
	public boolean loadAttendance(Student[] roster, String date)
	{
		System.out.println();
		// Create the FileChooser and open the FileChooser Window
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int response = chooser.showOpenDialog(null);
		
		if (response ==  JFileChooser.APPROVE_OPTION) //if the a file is selected
		{
			File file = chooser.getSelectedFile();
			String ext = file.getName().split("[.]")[1];
			if(ext.equals("csv"))
			{
				Scanner scanner;
				try
				{

					Student studentCheck = roster[0];
					int existingDate;
					for(existingDate = 0; existingDate < studentCheck.getAttendanceCount(); existingDate++)
					{
						if(date.equals(studentCheck.getAttendanceDate(existingDate)))
						{
							break;
						}
					}

					if(existingDate >= studentCheck.getAttendanceCount())
					{
						// Set every student in the roster's minutes as "0" before looking at attendance
						for (int x = 0; x < roster.length; x++)
						{
							Day day = new Day(date, 0);
							Student student = roster[x];
							student.addAttendance(day);
						}
					}


					// Input data from file
					scanner = new Scanner(file);
					ArrayList<Extra> extras = new ArrayList<Extra>();
					int user = 0;
					while (scanner.hasNextLine())
					{
						// Read in next line
						String line = scanner.nextLine();

						// Place data from attendance in correct variables (asurite and min)
						String asurite = line.split(",")[0];

						int min = Integer.parseInt((line.split(",")[1]));

						// Insert the attendance data (update)
						int rosterIndex = 0; //index traverse through roster
						boolean studentFound = false; //find the student asurite?
						while (!studentFound && rosterIndex < roster.length)
						{
							Student student = roster[rosterIndex];

							// You find the correct student by comparing name from file and name in roster
							if (student.getAsurite().equals(asurite))
							{
								studentFound = true;

								int attendanceIndex = 0; //traverse through student attendance
								boolean dateFound = false; //find the correct date

								while (!dateFound && attendanceIndex < student.getAttendanceCount())
								{
									if (student.getAttendanceDate(attendanceIndex).equals(date)) //duplicate date
									{
										dateFound = true;
										if (student.getAttendance(date) == 0)
										{
											user++;
										}
										min = min + student.getAttendance(date);
										student.updateAttendance(attendanceIndex, min);
									}
									attendanceIndex++; //increment
								}
							}
							rosterIndex++;
						}
						Extra extra;
						if (!studentFound) //a student is not on the roster
						{
							extra = new Extra(asurite,min);
							extras.add(extra);
						}
					}

					// Display Attendees Report Pop-Up
					String message = "";
					message = message + "Data loaded for " + user + " user(s) in the roster." + '\n';
					message = message + extras.size() + " additional attendees were found:" + '\n';
					for (int x = 0; x < extras.size(); x++)
					{
						message = message + extras.get(x).getAsurite() + ", connected for ";
						message = message + extras.get(x).getMinutes() + " minute(s)" + '\n';
					}
					JOptionPane pane = new JOptionPane();
					pane.setMessage(message);
					JDialog dialog = pane.createDialog(null);
					dialog.setVisible(true);
					
					return true;
				}
				// For when Scanner fails
				catch (FileNotFoundException e) {
					e.printStackTrace();
					return false;
				}
			}
			else
			{
				JOptionPane pane = new JOptionPane();
				pane.setMessage("Error: not a .csv file");
				JDialog dialog = pane.createDialog(null);
				dialog.setVisible(true);
				return false;
			}
		}
		else
		{
			JOptionPane pane = new JOptionPane();
			pane.setMessage("Error: no file chosen");
			JDialog dialog = pane.createDialog(null);
			dialog.setVisible(true);
			return false;
		}

	}
}
