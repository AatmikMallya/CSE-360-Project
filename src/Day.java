

/**
 * Day class contains methods for day and time
 */
public class Day
{
	private String date;
	private int minutes;

	/**
	 * Constructors for Day initialize the date and minutes
	 * @param date, String
	 * @param minutes, integer
	 */
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

	/**
	 * Getter for date
	 * @return date in String format
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * Returns minutes
	 * @return minutes integer
	 */
	public int getMinutes()
	{
		return minutes;
	}

	/**
	 * Sets date
	 * @param d date in String format
	 */
	public void setDate(String d)
	{
		date = d;
	}

	/**
	 * Sets minutes
	 * @param min minutes integer
	 */
	public void setMinutes(int min)
	{
		minutes = min;
	}
}
