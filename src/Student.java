package FinalProject;

public class Student
{
    private String ID, firstName, lastName, academicLevel, asurite;

    public Student(String ID, String firstName, String lastName, String academicLevel, String asurite)
    {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getLevel()
    {
        return academicLevel;
    }

    public String getAsurite()
    {
        return asurite;
    }

}
