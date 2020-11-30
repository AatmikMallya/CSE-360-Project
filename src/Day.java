package FinalProject;


public class Day
{
	private String date;
	private int minutes;
	
	//Constructors
	public Day(String date, int minutes)
	{
		this.date = date;
		this.minutes = minutes;
	}
	public Day()
	{
		date = "01/11";
		minutes = 0;
	}
	
	//Getters
	public String getDate()
	{
		return date;
	}
	public int getMinutes()
	{
		return minutes;
	}
	
	//Setters
	public void setDate(String d)
	{
		date = d;
	}
	public void setMinutes(int min)
	{
		minutes = min;
	}
}
