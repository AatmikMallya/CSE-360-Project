

import java.awt.Color;
import javax.swing.JFrame;  
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;  
import org.jfree.data.xy.XYSeriesCollection; 

/**
 * Scatterplot class contains a method to create a dataset and initializes the plot
 */
public class ScatterPlot {
	private static final long serialVersionUID = 6294689542092367723L;
	private JFreeChart chart;

	/**
	 * Constructor for ScatterPlot class initializes dataset, chart, plot, and panel
	 * @param title
	 * @param roster
	 */
	public ScatterPlot(String title, Student[] roster) {  
	    //super(title);
	  
	    // Create dataset
		XYDataset dataset = null;
	    dataset = createDataset(roster);

	    // Create chart
	    chart = ChartFactory.createScatterPlot(
	        title,
	        "Hours", "Student Count", dataset,
	        PlotOrientation.VERTICAL, true, true, false);


	    //Changes background color
	    XYPlot plot = (XYPlot)chart.getPlot();
	    plot.setBackgroundPaint(new Color(255,228,196));
	    
	 // Create Panel  
	    //ChartPanel panel = new ChartPanel(chart);
	    //setContentPane(panel);
	}

	/**
	 * Creates a set of data based on the input roster
	 * @param roster
	 * @return returns a set of XY points based on the student data
	 */
	private XYDataset createDataset(Student[] roster) {  
	    XYSeriesCollection dataset = new XYSeriesCollection();  
	    int attendanceLength = roster[0].getAttendanceCount();
	    for(int j = 0; j < attendanceLength; j++)
	    {
	    	String date = roster[0].getAttendanceDate(j);
	    	//Students Series (Attendance,Population)
		    XYSeries series1 = new XYSeries(date);  
		    int att100 = 0; //each variable corresponds to the value we should plot for attendance
		    int att90 = 0;
		    int att80 = 0;
		    int att70 = 0;
		    int att60 = 0;
		    int att50 = 0;
		    int att40 = 0;
		    int att30 = 0;
		    int att20 = 0;
		    int att10 = 0;
		    int att0 = 0;
		    
		    for(int x = 0; x < roster.length; x++) //increment based on attendance numbers
		    {
		    	Student examinedStudent = roster[x];
		    	int dayAttendance = examinedStudent.getAttendance(date);
		    	if(dayAttendance/7.5 <= 1) //divide to find percent attendance
			    	att0++;
		    	else if(dayAttendance/7.5 <= 2) //divide to find percent attendance
			    	att10++;
		    	else if(dayAttendance/7.5 <= 3) //divide to find percent attendance
			    	att20++;
		    	else if(dayAttendance/7.5 <= 4) //divide to find percent attendance
			    	att30++;
		    	else if(dayAttendance/7.5 <= 5) //divide to find percent attendance
			    	att40++;
		    	else if(dayAttendance/7.5 <= 6) //divide to find percent attendance
			    	att50++;
		    	else if(dayAttendance/7.5 <= 7) //divide to find percent attendance
			    	att60++;
		    	else if(dayAttendance/7.5 <= 8) //divide to find percent attendance
		    		att70++;
		    	else if(dayAttendance/7.5 <= 9) //divide to find percent attendance
			    		att80++;
		    	else if(dayAttendance/7.5 <= 10) //divide to find percent attendance
			    	att90++;
		    	else att100++; //100% attendance if other conditions not met
		    }
		    series1.add(0, att0);
		    series1.add(10, att10);  
		    series1.add(20, att20);  
		    series1.add(30, att30);  
		    series1.add(40, att40);  
		    series1.add(50, att50);  
		    series1.add(60, att60);  
		    series1.add(70, att70);  
		    series1.add(80, att80);  
		    series1.add(90, att90);  
		    series1.add(100, att100);  
		  
		    dataset.addSeries(series1);
	    }
	  
	       
	  
	    return dataset;  
	  }

	  /**
	   * Getter for chart
	   * @return current JFreeChart
	   */
	  public JFreeChart getChart()
	  {
		return chart;
	  }

}
