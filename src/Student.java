//package FinalProject;

public class Student
{
    private String ID, firstName, lastName, program, academicLevel, asurite;

    public Student(String ID, String firstName, String lastName, String program, String academicLevel, String asurite)
    {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.program = program;
        this.academicLevel = academicLevel;
        this.asurite = asurite;
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

}
