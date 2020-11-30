package FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Attendance extends JFrame
{
	public boolean loadAttendance(Student[] roster, String date)
	{
		System.out.println();
		//create the FileChooser and open the FileChooser Window
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
					//set every student in the roster's minutes as "0" before looking at attendance
					for (int x = 0; x < roster.length; x++)
					{
						Day day = new Day(date, 0);
						Student student = roster[x];
						student.addAttendance(day);
					}

					//input data from file
					scanner = new Scanner(file);
					ArrayList<Extra> extras = new ArrayList<Extra>();
					int user = 0;
					while (scanner.hasNextLine())
					{
						//read in next line
						String line = scanner.nextLine();

						//place data from attendance in correct variables (asurite and min)
						String asurite = line.split(",")[0];
						//System.out.println(asurite);
						//String min = line.split(",")[1];
						//System.out.println(min);
						int min = Integer.parseInt((line.split(",")[1]));

						//insert the attendance data (update)
						int rosterIndex = 0; //index traverse through roster
						boolean studentFound = false; //find the student asurite?
						while (!studentFound && rosterIndex < roster.length)
						{
							//System.out.println();
							Student student = roster[rosterIndex];
							//System.out.println("Looking for: " + asurite);
							//System.out.println("student.getAsurite(): " + student.getAsurite());

							//you find the correct student by comparing name from file and name in roster
							if (student.getAsurite().equals(asurite))
							{
								//System.out.println("Student Found");
								studentFound = true;

								int attendanceIndex = 0; //traverse through student attendance
								boolean dateFound = false; //find the correct date

								while (!dateFound && attendanceIndex < student.getAttendanceCount())
								{
									//System.out.println("provided date: " + date);
									//System.out.println("Student date: " + student.getAttendanceDate(attendanceIndex));
									//System.out.println("Attendance Index: " + attendanceIndex);
									if (student.getAttendanceDate(attendanceIndex).equals(date)) //duplicate date
									{
										//System.out.println("Date Found");
										dateFound = true;
										if (student.getAttendance(date) == 0)
											user++;
										//System.out.println("Date: " + student.getAttendanceDate(attendanceIndex));
										//System.out.println("Minutes: " + student.getAttendance(date));
										min = min + student.getAttendance(date);
										student.updateAttendance(attendanceIndex, min);
										//System.out.println("Date: " + student.getAttendanceDate(attendanceIndex));
										//System.out.println("Minutes: " + student.getAttendance(date));
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

					//Display Attendees Report Pop-Up
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

					//Check inputs

					System.out.println('\n'+ "Finished everything. Check Inputs.");
					System.out.println("roster length: " + roster.length + '\n');
					for (int x = 0; x < roster.length ; x++)
					{
						Student student = roster[x];
						System.out.println("Student asurite: " + student.getAsurite());
						System.out.println("attendance count: " + student.getAttendanceCount());
						for (int y = 0; y < student.getAttendanceCount(); y++)
						{
							System.out.println("Date: " + student.getAttendanceDate(y));
							System.out.println("Minutes: " + student.getAttendance(student.getAttendanceDate(y)));
							System.out.println();
						}
					}
					return true;
				}
				//for when Scanner fails
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
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
			System.out.println("Error: no file chosen");
			return false;
		}

	}
}
