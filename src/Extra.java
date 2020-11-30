/**
 * Extra class for extra students in student list
 */
public class Extra
{
	private String asurite;
	private int minutes;

	/**
	 * Constructor sets the data members
	 * @param asurite student's asurite
	 * @param minutes integer of minutes
	 */
	public Extra(String asurite, int minutes)
	{
		this.asurite = asurite;
		this.minutes = minutes;
	}

	/**
	 * Getter for minutes
	 * @return minutes integer
	 */
	public int getMinutes()
	{
		return minutes;
	}

	/**
	 * Getter for Asurite
	 * @return asurite String
	 */
	public String getAsurite()
	{
		return asurite;
	}
}
