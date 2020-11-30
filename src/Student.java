package FinalProject;

import java.util.ArrayList;

public class Student
{
    private String ID, firstName, lastName, program, academicLevel, asurite;
    private ArrayList<Day> attendance;

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

    public String getID()
    {
        return ID;
    }

    public String getFirst()
    {
        return firstName;
    }

    public String getLast()
    {
        return lastName;
    }
    
    public String getProgram()
    {
        return program;
    }

    public String getLevel()
    {
        return academicLevel;
    }

    public String getAsurite()
    {
        return asurite;
    }
    
    //Student Attendance methods
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

    // Returns all data members separated by commas
    public String getCSV() {
        return  this.getID() + "," +
                this.getFirst() + "," +
                this.getLast() + "," +
                this.getProgram() + "," +
                this.getLevel() + "," +
                this.getAsurite() + "\n";
    }

}
