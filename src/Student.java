

import java.util.ArrayList;

/**
 * Student class defines the data structure used for storing student data
 */
public class Student
{
    private String ID, firstName, lastName, program, academicLevel, asurite;
    private ArrayList<Day> attendance;

    /**
     * Constructor for Student sets data members to each of the parameters
     * @param ID student ID, String
     * @param firstName student's first name, String
     * @param lastName student's last name, String
     * @param program University program, String
     * @param academicLevel either freshman, sophomore, junior, or senior, String
     * @param asurite ASU username, String
     */
    public Student(String ID, String firstName, String lastName, String program, String academicLevel, String asurite)
    {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.program = program;
        this.academicLevel = academicLevel;
        this.asurite = asurite;
        attendance = new ArrayList<Day>();
    }

    /**
     * Getter for ID
     * @return student ID, String
     */
    public String getID() {
        return ID;
    }

    /**
     * Getter for first name
     * @return student's first name, String
     */
    public String getFirst()
    {
        return firstName;
    }

    /**
     * Getter for last name
     * @return student's last name, String
     */
    public String getLast()
    {
        return lastName;
    }

    /**
     * Getter for program
     * @return student's program, String
     */
    public String getProgram()
    {
        return program;
    }

    /**
     * Getter for academic level
     * @return student's academic level, String
     */
    public String getLevel()
    {
        return academicLevel;
    }

    /**
     * Getter for Asurite
     * @return student's Asurite, String
     */
    public String getAsurite()
    {
        return asurite;
    }

    /**
     * Sets minutes to values in attendance object
     * @param day Day object
     */
    public void addAttendance(Day day)
    {
    	attendance.add(day);
    }
    public int getAttendance(String date)
    {
    	int minutes = 0;
    	for (int x = 0; x < attendance.size(); x++)
    	{
    		if (attendance.get(x).getDate().equals(date))
    		{
    			minutes = attendance.get(x).getMinutes();
    		}
    	}
    	return minutes;
    }
    public String getAttendanceDate(int index)
    {
    	return attendance.get(index).getDate();
    }
    public int getAttendanceCount()
    {
    	return attendance.size();
    }
    public void updateAttendance(int index,int min)
    {
    	attendance.get(index).setMinutes(min);
    }

    /**
     * Converts student's data members to CSV format
     * @return comma separated values of student values
     */
    public String getCSV() {

        String output = this.getID() + "," +
                this.getFirst() + "," +
                this.getLast() + "," +
                this.getProgram() + "," +
                this.getLevel() + "," +
                this.getAsurite();

        if(getAttendanceCount() != 0)
        {
            for(int pos = 0; pos < getAttendanceCount(); pos++)
            {
                output = output + "," + getAttendance(getAttendanceDate(pos));
            }
        }

        output = output + "\n";

        return output;
    }

}
