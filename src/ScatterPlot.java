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

public class ScatterPlot extends JFrame {
	private static final long serialVersionUID = 6294689542092367723L;
	
	public ScatterPlot(String title, Student[] roster) {  
	    super(title);  
	  
	    // Create dataset  
	    XYDataset dataset = createDataset(roster);  
	  
	    // Create chart  
	    JFreeChart chart = ChartFactory.createScatterPlot(  
	        "Boys VS Girls weight comparison chart",   
	        "X-Axis", "Y-Axis", dataset,
	        PlotOrientation.VERTICAL, true, true, false);  
	  
	      
	    //Changes background color  
	    XYPlot plot = (XYPlot)chart.getPlot();  
	    plot.setBackgroundPaint(new Color(255,228,196));  
	    
	 // Create Panel  
	    ChartPanel panel = new ChartPanel(chart);  
	    setContentPane(panel);  
	}
	private XYDataset createDataset(Student[] roster) {  
	    XYSeriesCollection dataset = new XYSeriesCollection();  
	  
	    //Students Series (Attendance,Population)
	    XYSeries series1 = new XYSeries("Attendance");  
	    int att100 = 0; //each variable corresponds to the value wwe should plot for attendance
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
	    	if(Student.getAttendance()/75 <= 1) //divide to find percent attendance
	    		att0++;
	    	if(Student.getAttendance()/75 <= 2) //divide to find percent attendance
	    		att10++;
	    	if(Student.getAttendance()/75 <= 3) //divide to find percent attendance
	    		att20++;
	    	if(Student.getAttendance()/75 <= 4) //divide to find percent attendance
	    		att30++;
	    	if(Student.getAttendance()/75 <= 5) //divide to find percent attendance
	    		att40++;
	    	if(Student.getAttendance()/75 <= 6) //divide to find percent attendance
	    		att50++;
	    	if(Student.getAttendance()/75 <= 7) //divide to find percent attendance
	    		att60++;
	    	if(Student.getAttendance()/75 <= 8) //divide to find percent attendance
	    		att70++;
	    	if(Student.getAttendance()/75 <= 9) //divide to find percent attendance
	    		att80++;
	    	if(Student.getAttendance()/75 <= 10) //divide to find percent attendance
	    		att90++;
	    	att100++;
	    	
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
	  
	    return dataset;  
	  }  

}
