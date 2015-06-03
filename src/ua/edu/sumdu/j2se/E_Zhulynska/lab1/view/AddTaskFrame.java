package ua.edu.sumdu.j2se.E_Zhulynska.lab1.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;

import java.io.IOException;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import ua.edu.sumdu.j2se.E_Zhulynska.lab1.controller.TaskManagerController;
import ua.edu.sumdu.j2se.E_Zhulynska.lab1.model.*;

import com.toedter.calendar.JDateChooser;

/**
 * @author E.Zhulynska This class describes the action after changing the
 *         editItemAdd
 * @param <JCalendar>
 */
public class AddTaskFrame extends SwingFrame {

	public AddTaskFrame(TaskList taskCollection, String file) {
		super(taskCollection, closeWindow);
		this.taskCollection = (ArrayTaskList) taskCollection;
		this.fileName = file;
		// TODO Auto-generated constructor stub
	}

	public static final SimpleDateFormat DateFormat = new SimpleDateFormat(
			"dd:MM:yyyy");
	public static final SimpleDateFormat TimeFormat = new SimpleDateFormat(
			"HH:mm:ss");
	public static String nonRepeatedString = "nonRepeatedString";
	public static String repeatedString = "repeatedString";
	public static String closeWindow = "closeWindow";
	//public String fileAddName = "fileAddName.txt";
	public static String saveTaskString = "saveTaskString";

	private JTextArea taskTitle = null;
	private JFormattedTextField timeOrStartTime = null;
	private JDateChooser dateOrStartdate = new JDateChooser();
	private JRadioButton nonRepeated = null;
	private JRadioButton repeated = null;
	private Label endDateQuery = null;
	private JDateChooser endDate = new JDateChooser();
	private Label endTimeQuery = null;
	private JFormattedTextField endTime = null;
	private Label repeatIntervalQuery = null;
	private JTextField repeatInterval = null;
	private Label timeQuery = null;
	private Label dateQuery = null;
	private static String fileName = new String();

	private JDialog addDialogFrame = new JDialog(frame,
			"Add Task into Task Manager List", true);
	private static ArrayTaskList taskCollection = new ArrayTaskList();
	private JLabel a = new JLabel("task");
	private Task task = null;

	@Override
	protected void createFrame() throws ParseException, IOException {
		addDialogFrame.setLocationRelativeTo(null);
		addDialogFrame.setSize(500, 300);
		JPanel northPanel = new JPanel();
		addDialogFrame.add(northPanel, BorderLayout.NORTH);

		GridBagLayout gbl = new GridBagLayout();

		northPanel.setLayout(gbl);

		GridBagConstraints c00 = new GridBagConstraints();
		c00.anchor = GridBagConstraints.WEST;
		c00.gridheight = 1;
		c00.gridwidth = 1;
		c00.gridx = 0;
		c00.gridy = 0;
		c00.weightx = 0.0;
		c00.weighty = 0.0;
		Label titleQuery = new Label("Enter task title: ");
		gbl.setConstraints(titleQuery, c00);
		northPanel.add(titleQuery);

		GridBagConstraints c10 = new GridBagConstraints();
		c10.anchor = GridBagConstraints.WEST;
		c10.gridheight = 1;
		c10.gridwidth = 2;
		c10.gridx = 1;
		c10.gridy = 0;
		c10.weightx = 0.0;
		c10.weighty = 0.0;
		taskTitle = new JTextArea(2, 20);
		taskTitle.setText(null);
		taskTitle.setLineWrap(true);
		gbl.setConstraints(taskTitle, c10);
		northPanel.add(taskTitle);

		GridBagConstraints c01 = new GridBagConstraints();
		c01.anchor = GridBagConstraints.WEST;
		c01.gridheight = 1;
		c01.gridwidth = 1;
		c01.gridx = 0;
		c01.gridy = 1;
		c01.weightx = 0.0;
		c01.weighty = 0.0;
		Label type = new Label("Select task type: ");
		gbl.setConstraints(type, c01);
		northPanel.add(type);

		GridBagConstraints c02 = new GridBagConstraints();
		c02.anchor = GridBagConstraints.WEST;
		c02.gridheight = 1;
		c02.gridwidth = 1;
		c02.gridx = 0;
		c02.gridy = 2;
		c02.weightx = 0.0;
		c02.weighty = 0.0;

		GridBagConstraints c021 = new GridBagConstraints();
		c021.anchor = GridBagConstraints.WEST;
		c021.gridheight = GridBagConstraints.RELATIVE;
		c021.gridwidth = 1; // 1
		c021.gridx = 0;
		c021.gridy = GridBagConstraints.RELATIVE; // 2
		c021.weightx = 0.0;
		c021.weighty = 0.0;

		ButtonGroup bg = new ButtonGroup();
		nonRepeated = new JRadioButton("Non-repeated", true);
		repeated = new JRadioButton("Repeated", false);
		bg.add(nonRepeated);
		bg.add(repeated);
		gbl.setConstraints(nonRepeated, c02);
		gbl.setConstraints(repeated, c021);
		northPanel.add(nonRepeated);
		northPanel.add(repeated);

		GridBagConstraints c12 = new GridBagConstraints();
		c12.anchor = GridBagConstraints.WEST;
		c12.gridheight = 1;
		c12.gridwidth = 1;
		c12.gridx = 1;
		c12.gridy = 2;
		c12.weightx = 0.0;
		c12.weighty = 0.0;
		dateQuery = new Label("Enter the date: ");
		gbl.setConstraints(dateQuery, c12);
		northPanel.add(dateQuery);

		GridBagConstraints c13 = new GridBagConstraints();
		c13.anchor = GridBagConstraints.WEST;
		c13.gridheight = 1;
		c13.gridwidth = 1;
		c13.gridx = 1;
		c13.gridy = 3;
		c13.weightx = 0.0;
		c13.weighty = 0.0;
		timeQuery = new Label("Enter the time: ");

		gbl.setConstraints(timeQuery, c13);
		northPanel.add(timeQuery);

		GridBagConstraints c22 = new GridBagConstraints();
		c22.anchor = GridBagConstraints.WEST;
		c22.gridheight = 1;
		c22.gridwidth = 1;
		c22.gridx = 2;
		c22.gridy = 2;
		c22.weightx = 0.0;
		c22.weighty = 0.0;
		dateOrStartdate.setLocale(Locale.ENGLISH);
		dateOrStartdate.setDateFormatString("dd:MM:yyyy");
		gbl.setConstraints(dateOrStartdate, c22);
		northPanel.add(dateOrStartdate);

		GridBagConstraints c23 = new GridBagConstraints();
		c23.anchor = GridBagConstraints.WEST;
		c23.gridheight = 1;
		c23.gridwidth = 1;
		c23.gridx = 2;
		c23.gridy = 3;
		c23.weightx = 0.0;
		c23.weighty = 0.0;

		timeOrStartTime = new JFormattedTextField(new SimpleDateFormat(
				"HH:mm:ss"));
		// timeOrStartTime.setText(new
		// SimpleDateFormat("HH:mm:ss").format((Date)this.currentDate.clone()));
		// timeOrStartTime.setText(new SimpleDateFormat("HH:mm:ss").format(new
		// Date()));
		// (Date)this.currentDate.clone()
		/*
		 * timeOrStartTime = new JTextField( (new
		 * SimpleDateFormat("HH:mm:ss").format(new Date())) .toString(), 10);
		 */
		timeOrStartTime.setToolTipText("Use format HH:mm:ss ");
		gbl.setConstraints(timeOrStartTime, c23);
		northPanel.add(timeOrStartTime);

		GridBagConstraints c14 = new GridBagConstraints();
		c14.anchor = GridBagConstraints.WEST;
		c14.gridheight = 1;
		c14.gridwidth = 1;
		c14.gridx = 1;
		c14.gridy = 4;
		c14.weightx = 0.0;
		c14.weighty = 0.0;
		endDateQuery = new Label("Enter the end date: ");
		gbl.setConstraints(endDateQuery, c14);
		northPanel.add(endDateQuery);

		GridBagConstraints c15 = new GridBagConstraints();
		c15.anchor = GridBagConstraints.WEST;
		c15.gridheight = 1;
		c15.gridwidth = 1;
		c15.gridx = 1;
		c15.gridy = 5;
		c15.weightx = 0.0;
		c15.weighty = 0.0;
		endTimeQuery = new Label("Enter end time: ");

		gbl.setConstraints(endTimeQuery, c15);
		northPanel.add(endTimeQuery);

		GridBagConstraints c24 = new GridBagConstraints();
		c24.anchor = GridBagConstraints.WEST;
		c24.gridheight = 1;
		c24.gridwidth = 1;
		c24.gridx = 2;
		c24.gridy = 4;
		c24.weightx = 0.0;
		c24.weighty = 0.0;
		endDate.setLocale(Locale.ENGLISH);
		endDate.setDateFormatString("dd:MM:yyyy");
		gbl.setConstraints(endDate, c24);
		northPanel.add(endDate);

		GridBagConstraints c25 = new GridBagConstraints();
		c25.anchor = GridBagConstraints.WEST;
		c25.gridheight = 1;
		c25.gridwidth = 1;
		c25.gridx = 2;
		c25.gridy = 5;
		c25.weightx = 0.0;
		c25.weighty = 0.0;
		endTime = new JFormattedTextField(TimeFormat);
		endTime.setToolTipText("Use format HH:mm:ss ");

		gbl.setConstraints(endTime, c25);
		northPanel.add(endTime);

		GridBagConstraints c16 = new GridBagConstraints();
		c16.anchor = GridBagConstraints.WEST;
		c16.gridheight = 1;
		c16.gridwidth = 1;
		c16.gridx = 1;
		c16.gridy = 6;
		c16.weightx = 0.0;
		c16.weighty = 0.0;
		repeatIntervalQuery = new Label(
				"Enter repeat interval of task (minutes): ");

		gbl.setConstraints(repeatIntervalQuery, c16);
		northPanel.add(repeatIntervalQuery);

		GridBagConstraints c26 = new GridBagConstraints();
		c26.anchor = GridBagConstraints.WEST;
		c26.gridheight = 1;
		c26.gridwidth = 1;
		c26.gridx = 2;
		c26.gridy = 6;
		c26.weightx = 0.0;
		c26.weighty = 0.0;
		repeatInterval = new JTextField(10);
		gbl.setConstraints(repeatInterval, c26);
		northPanel.add(repeatInterval);
		// because non-repeated task indicator is selected default
		endDateQuery.setEnabled(false);
		endDate.setEnabled(false);
		endTimeQuery.setEnabled(false);
		endTime.setEnabled(false);
		repeatIntervalQuery.setEnabled(false);
		repeatInterval.setEnabled(false);

		nonRepeated.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(AddTaskFrame.nonRepeatedString);
			}
		});
		repeated.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(AddTaskFrame.repeatedString);
			}
		});
		JPanel southPanel = new JPanel();
		JButton addTaskButton = new JButton("Add task");
		southPanel.add(addTaskButton);

		JButton returnButton = new JButton("Return to Task Manager window");
		southPanel.add(returnButton);

		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(AddTaskFrame.closeWindow);
			}
		});

		addDialogFrame.add(southPanel, BorderLayout.SOUTH);
		addTaskButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(AddTaskFrame.saveTaskString);
			}
		});
	}

	/**
	 * open JDialog addDialogFrame
	 * set default values in addDialogFrame components
	 */
	public void show() {
		dateOrStartdate.setDate(new Date());
		timeOrStartTime.setText(new SimpleDateFormat("HH:mm:ss")
				.format(new Date()));
		endDate.setDate(new Date(new Date().getTime() + 24 * 60 * 60 * 1000));
		endTime.setText(TimeFormat.format(new Date(new Date().getTime())));
		repeatInterval.setText("10");
		this.addDialogFrame.setVisible(true);
	}

	/**
	 * close JDialog addDialogFrame
	 */
	public void close() {
		addDialogFrame.setVisible(false);
		addDialogFrame.dispose();
	}

	@Override
	protected void fireAction(String command) {
		ActionEvent event = new ActionEvent(this, 0, command);
		for (Iterator listener = SwingFrame.listeners.iterator(); listener
				.hasNext();) {
			((ActionListener) listener.next()).actionPerformed(event);
		}
	}

	/**
	 * describes the reaction on the non-repeated indicator selection
	 */
	public class NonRepeatedClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			endDateQuery.setEnabled(false);
			endDate.setEnabled(false);
			endTimeQuery.setEnabled(false);
			endTime.setEnabled(false);
			repeatIntervalQuery.setEnabled(false);
			repeatInterval.setEnabled(false);
			timeQuery.setText("Enter the time: ");
			dateQuery.setText("Enter the date: ");
		}
	}

	/**
	 * describes the reaction on the repeated indicator selection
	 */
	public class RepeatedClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			endDateQuery.setEnabled(true);
			endDate.setEnabled(true);
			endTimeQuery.setEnabled(true);
			endTime.setEnabled(true);
			repeatIntervalQuery.setEnabled(true);
			repeatInterval.setEnabled(true);

			timeQuery.setText("Enter start time: ");
			dateQuery.setText("Enter start date: ");
		}

	}

	/**
	 * add the task with entered parameters
	 */
	public class AddTaskAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			StringBuilder startDateTime = null;
			StringBuilder endDateTime = null;

			if (taskTitle.getText() == null
					|| timeOrStartTime.getText() == null
					|| dateOrStartdate.getDate().equals(null))
				throw new IllegalArgumentException("Enter parameter of task!");

			try {
				if (nonRepeated.isSelected() && taskTitle.getText() != null
						&& timeOrStartTime.getText() != null
						&& !(dateOrStartdate.getDate().equals(null))) {
					startDateTime = new StringBuilder();

					startDateTime
							.append(DateFormat.format(dateOrStartdate.getDate()))
							.append(" ").append(timeOrStartTime.getText());
					// .append("\t").append(timeOrStartTime.getText());

					Date time = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss")
							.parse(startDateTime.toString());
					task = new Task(taskTitle.getText(), time);
				}
				if (repeated.isSelected() && taskTitle.getText() != null
						&& timeOrStartTime.getText() != null
						&& !(dateOrStartdate.getDate().equals(null))
						&& !endDate.getDate().equals(null)
						&& endTime.getText() != null
						&& repeatInterval.getText() != null) {
					startDateTime = new StringBuilder();

					startDateTime
							.append(DateFormat.format(dateOrStartdate.getDate()))
							.append(" ").append(timeOrStartTime.getText());
					// .append("\t").append(timeOrStartTime.getText());

					Date startTime = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss")
							.parse(startDateTime.toString());

					endDateTime = new StringBuilder();
					endDateTime.append(DateFormat.format(endDate.getDate()))
							.append(" ").append(endTime.getText());

					Date endTime1 = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss")
							.parse(endDateTime.toString());

					long rep = Integer.parseInt(repeatInterval.getText()) * 60 * 1000;

					if (endTime1.after(startTime))
						task = new Task(taskTitle.getText(), startTime,
								endTime1, rep);

				}

				task.setActive(true);

				if (!AddTaskFrame.taskCollection.toString().contains(
						task.toString())) {
					AddTaskFrame.taskCollection.add(task);
					TaskStorage.writeText(AddTaskFrame.taskCollection,
							AddTaskFrame.fileName);
					JOptionPane.showMessageDialog(null, task.toString(),
							"FOLLOW TASK IS ADDED :", 1);

					taskTitle.setText(null);

				}
			} catch (ParseException | IOException e1) {
				try {
					throw (IOException) new IOException().initCause(e1);
				} catch (IOException e2) {
					JOptionPane.showMessageDialog(null, "IOException!!!");
				}

			} catch (NullPointerException e3) {
				JOptionPane.showMessageDialog(addDialogFrame,
						"Set correct date!", "FORMAT ERROR :",
						JOptionPane.ERROR_MESSAGE);

			}

			catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(addDialogFrame,
						"Set correct task interval!", "FORMAT ERROR :",
						JOptionPane.ERROR_MESSAGE);
			}

			catch (IllegalArgumentException e4) {
				JOptionPane.showMessageDialog(addDialogFrame,
						"Set correct task interval Illegal!", "FORMAT ERROR :",
						JOptionPane.ERROR_MESSAGE);

			}
		}
	}

	@Override
	public void update(Observable source, Object arg) {

	}

}