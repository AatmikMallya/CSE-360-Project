

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * DateSelector class defines a user interface for picking a data
 */
public class DateSelector extends JDialog implements ActionListener
{
	JDatePickerImpl datePicker;
	JButton button;
	String date;

	/**
	 * Constructor for DataSelector
	 */
	public DateSelector()
	{
		super();
		setModal(true);
	}

	/**
	 * Method to create the UI by displaying the DatePicker and adding a button
	 */
	public void selectDate()
	{
		setLayout(new FlowLayout());
		
		// Create the DatePicker
		UtilDateModel model = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model,properties);
		datePicker = new JDatePickerImpl(datePanel, new DateFormatter());
		
		// Add confirmation button
		button = new JButton("OK");
		button.addActionListener(this);
		
		// Display the DatePicker
		add(datePicker);
		add(button);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
	}

	/**
	 * Sets the date based on user input
	 * @param e user input
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == button)
		{
			//Get information from calendar
			if (datePicker.getModel().getValue() != null)
			{
				Date calendar = (Date) datePicker.getModel().getValue();
				int month = calendar.getMonth()+1;
				int day = calendar.getDate();
				date = month + "/" + day;
				dispose();
			}
			else
			{
				dispose();
				JOptionPane pane = new JOptionPane();
				pane.setMessage("Error: no date chosen");
				JDialog dialog = pane.createDialog(null);
				dialog.setVisible(true);
			}
		}
		else
		{
			dispose();
			JOptionPane pane = new JOptionPane();
			pane.setMessage("Error: no input");
			JDialog dialog = pane.createDialog(null);
			dialog.setVisible(true);
		}

	}

	/**
	 * Getter for date
	 * @return date in String format
	 */
	public String getDate()
	{
		return date;
	}
}
