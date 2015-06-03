package ua.edu.sumdu.j2se.E_Zhulynska.lab1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ua.edu.sumdu.j2se.E_Zhulynska.lab1.controller.TaskManagerController;
import ua.edu.sumdu.j2se.E_Zhulynska.lab1.model.*;

import com.toedter.calendar.JDateChooser;

public class CorrectTaskFrame extends SwingFrame {

	public static String CorrectNonRepeated = "CorrectNonRepeated";
	public static String CorrectRepeated = "CorrectRepeated";
	public static String ComboBoxTasksString = "ComboBoxTasksString";
	public static String saveButtonString = "saveButtonString";
	public static String closeCorrectWindow = "closeCorrectWindow";

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
	private Label titleQuery = null;
	private Label type = null;
	private Task currentTask = null;
	private GridBagLayout gblNorth = new GridBagLayout();
	private final JComboBox<String> comboBoxTasks = new JComboBox<String>();
	private GridBagConstraints cell = new GridBagConstraints();

	private static ArrayTaskList taskCollection = new ArrayTaskList();
	private ArrayList<JRadioButton> radioButton = new ArrayList<JRadioButton>();
	private JDialog correctDialogFrame = new JDialog(frame,
			"Correct Task in Task Manager List", true);
	private static String filename = new String();
	
	
	public CorrectTaskFrame(TaskList taskCollection, String file) {
		super(taskCollection, file);
		this.taskCollection = (ArrayTaskList) taskCollection;
		this.filename = file;
		
	}

	@Override
	protected void createFrame() throws ParseException, IOException {
		correctDialogFrame.setSize(500, 500);
		correctDialogFrame.setLocationRelativeTo(null);
		JPanel centerPanel = new JPanel();
		correctDialogFrame.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setEnabled(false);
		GridBagLayout gbl = new GridBagLayout();
		centerPanel.setLayout(gbl);
		GridBagConstraints c00 = new GridBagConstraints();
		c00.anchor = GridBagConstraints.WEST;
		c00.gridheight = 1;
		c00.gridwidth = 1;
		c00.gridx = 0;
		c00.gridy = 0;
		c00.weightx = 0.0;
		c00.weighty = 0.0;
		titleQuery = new Label("Enter task title: ");
		gbl.setConstraints(titleQuery, c00);
		centerPanel.add(titleQuery);

		GridBagConstraints c10 = new GridBagConstraints();
		c10.anchor = GridBagConstraints.WEST;
		c10.gridheight = 1;
		c10.gridwidth = 2;
		c10.gridx = 1;
		c10.gridy = 0;
		c10.weightx = 0.0;
		c10.weighty = 0.0;
		taskTitle = new JTextArea(2, 20);
		taskTitle.setLineWrap(true);

		gbl.setConstraints(taskTitle, c10);
		centerPanel.add(taskTitle);

		GridBagConstraints c01 = new GridBagConstraints();
		c01.anchor = GridBagConstraints.WEST;
		c01.gridheight = 1;
		c01.gridwidth = 1;
		c01.gridx = 0;
		c01.gridy = 1;
		c01.weightx = 0.0;
		c01.weighty = 0.0;
		type = new Label("Select task type: ");
		type.setEnabled(false);
		gbl.setConstraints(type, c01);
		centerPanel.add(type);

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
		c021.gridwidth = 1;
		c021.gridx = 0;
		c021.gridy = GridBagConstraints.RELATIVE;
		c021.weightx = 0.0;
		c021.weighty = 0.0;

		ButtonGroup bg = new ButtonGroup();
		nonRepeated = new JRadioButton("Non-repeated", false);
		nonRepeated.setEnabled(false);
		repeated = new JRadioButton("Repeated", false);
		repeated.setEnabled(false);
		bg.add(nonRepeated);
		bg.add(repeated);
		gbl.setConstraints(nonRepeated, c02);
		gbl.setConstraints(repeated, c021);
		centerPanel.add(nonRepeated);
		centerPanel.add(repeated);

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
		centerPanel.add(dateQuery);

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
		centerPanel.add(timeQuery);

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
		centerPanel.add(dateOrStartdate);

		GridBagConstraints c23 = new GridBagConstraints();
		c23.anchor = GridBagConstraints.WEST;
		c23.gridheight = 1;
		c23.gridwidth = 1;
		c23.gridx = 2;
		c23.gridy = 3;
		c23.weightx = 0.0;
		c23.weighty = 0.0;
		timeOrStartTime = new JFormattedTextField(AddTaskFrame.TimeFormat);
		timeOrStartTime.setToolTipText("Use format HH:mm:ss ");
		gbl.setConstraints(timeOrStartTime, c23);
		centerPanel.add(timeOrStartTime);

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
		centerPanel.add(endDateQuery);

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
		centerPanel.add(endTimeQuery);

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

		endDate.setToolTipText("Use format dd:MM:yyyy");

		gbl.setConstraints(endDate, c24);
		centerPanel.add(endDate);

		GridBagConstraints c25 = new GridBagConstraints();
		c25.anchor = GridBagConstraints.WEST;
		c25.gridheight = 1;
		c25.gridwidth = 1;
		c25.gridx = 2;
		c25.gridy = 5;
		c25.weightx = 0.0;
		c25.weighty = 0.0;
		endTime = new JFormattedTextField(AddTaskFrame.TimeFormat);
		endTime.setSize(10, 10);

		endTime.setToolTipText("Use format HH:mm:ss ");
		gbl.setConstraints(endTime, c25);
		centerPanel.add(endTime);

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
		centerPanel.add(repeatIntervalQuery);

		GridBagConstraints c26 = new GridBagConstraints();
		c26.anchor = GridBagConstraints.WEST;
		c26.gridheight = 1;
		c26.gridwidth = 1;
		c26.gridx = 2;
		c26.gridy = 6;
		c26.weightx = 0.0;
		c26.weighty = 0.0;
		repeatInterval = new JTextField("60", 10);
		gbl.setConstraints(repeatInterval, c26);
		centerPanel.add(repeatInterval);

		titleQuery.setEnabled(false);
		taskTitle.setEnabled(false);
		timeOrStartTime.setEnabled(false);
		timeQuery.setEnabled(false);
		dateOrStartdate.setEnabled(false);
		dateQuery.setEnabled(false);
		endDateQuery.setEnabled(false);
		endDate.setEnabled(false);
		endTimeQuery.setEnabled(false);
		endTime.setEnabled(false);
		repeatIntervalQuery.setEnabled(false);
		repeatInterval.setEnabled(false);

		nonRepeated.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(CorrectTaskFrame.CorrectNonRepeated);
			}
		});

		repeated.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(CorrectTaskFrame.CorrectRepeated);
			}
		});
		JPanel southPanel = new JPanel();
		JButton saveTaskButton = new JButton("Save task");
		southPanel.add(saveTaskButton);

		JButton closeTaskButton = new JButton("Return to Task Manager Window");
		southPanel.add(closeTaskButton);

		closeTaskButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(CorrectTaskFrame.closeCorrectWindow);
			}
		});

		correctDialogFrame.add(southPanel, BorderLayout.SOUTH);
		saveTaskButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(CorrectTaskFrame.saveButtonString);

			}
		});

		JPanel northPanel = new JPanel();
		correctDialogFrame.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(gblNorth);

		GridBagConstraints c00North = new GridBagConstraints();
		c00North.anchor = GridBagConstraints.CENTER;
		c00North.gridheight = 1;
		c00North.gridwidth = 1;
		c00North.gridx = 0;
		c00North.gridy = 0;
		c00North.weightx = 0.0;
		c00North.weighty = 0.0;
		Label titleQueryWest = new Label("Select the task for correction: ");
		gblNorth.setConstraints(titleQueryWest, c00North);
		northPanel.add(titleQueryWest);
		comboBoxTasks.setEditable(false);
		comboBoxTasks.addItem(null);

		for (Task tasks : CorrectTaskFrame.taskCollection) {

			comboBoxTasks.addItem(tasks.toString());
		}

		cell.anchor = GridBagConstraints.WEST;
		cell.gridheight = GridBagConstraints.RELATIVE;
		cell.gridwidth = GridBagConstraints.RELATIVE;
		// 1;
		cell.gridx = 0;
		cell.gridy = GridBagConstraints.RELATIVE;
		cell.weightx = 0.0;
		cell.weighty = 0.0;
		gblNorth.setConstraints(comboBoxTasks, cell);
		northPanel.add(comboBoxTasks);
		comboBoxTasks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(CorrectTaskFrame.ComboBoxTasksString);
			}
		});
		correctDialogFrame.pack();

	}

	/**
	 * fill in window components according selected task parametres
	 */
	public class ComboBoxTasksClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (Task t : CorrectTaskFrame.taskCollection) {
				if (t.toString().equals(comboBoxTasks.getSelectedItem())) {

					currentTask = t;
					t = currentTask;
					type.setEnabled(true);
					nonRepeated.setEnabled(true);
					repeated.setEnabled(true);

					if (!t.isRepeated()) {
						nonRepeated.setSelected(true);
					}

					titleQuery.setEnabled(true);
					taskTitle.setText(t.getTitle());
					taskTitle.setEnabled(true);
					timeQuery.setEnabled(true);
					timeOrStartTime.setEnabled(true);
					timeOrStartTime.setText(new SimpleDateFormat("HH:mm:ss")
							.format(t.getTime()));

					dateQuery.setEnabled(true);
					dateOrStartdate.setEnabled(true);
					dateOrStartdate.setDate(t.getTime());

					if (t.isRepeated()) {
						repeated.setSelected(true);

						endDateQuery.setEnabled(true);
						endDate.setEnabled(true);
						endDate.setDate(t.getEndTime());

						endTimeQuery.setEnabled(true);
						endTime.setEnabled(true);
						endTime.setText(new SimpleDateFormat("HH:mm:ss")
								.format(t.getEndTime()));

						repeatIntervalQuery.setEnabled(true);
						repeatInterval.setEnabled(true);
						repeatInterval.setText(String.valueOf(t
								.getRepeatInterval() / (1000 * 60)));
					}
				}
			}
		}
	}

	/**
	 * open JDialog correctDialogFrame
	 * set default values in JDialog components
	 */
	public void show() {
		dateOrStartdate.setDate(new Date());
		timeOrStartTime.setText(AddTaskFrame.TimeFormat.format(new Date()));
		Date endDate_ = (Date) dateOrStartdate.getDate().clone();
		endDate.setDate(new Date(endDate_.getTime() + 24 * 60 * 60 * 1000));
		endTime.setText(AddTaskFrame.TimeFormat.format(new Date()));
		correctDialogFrame.setVisible(true);
	}

	/**
	 * close JDialog correctDialogFrame
	 */
	public void close() {
		correctDialogFrame.setVisible(false);
		correctDialogFrame.dispose();
	}

	/**
	 * describes the reaction on the non-repeated indicator selection
	 */
	public class NonRepeatedClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			timeOrStartTime.setEnabled(true);
			timeQuery.setEnabled(true);
			dateOrStartdate.setEnabled(true);
			dateQuery.setEnabled(true);

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
	 * set the task with entered parameters used for selected radioButtons
	 */
	public class CorrectTaskAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			StringBuilder startDateTime = null;
			StringBuilder endDateTime = null;
			Task task = null;
			try {
				if (nonRepeated.isSelected()
						&& !taskTitle.getText().equals(null)
						&& timeOrStartTime.getText() != null
						&& !dateOrStartdate.getDate().equals(null)) {
					startDateTime = new StringBuilder();

					startDateTime
							.append(AddTaskFrame.DateFormat
									.format(dateOrStartdate.getDate()))
							.append(" ").append(timeOrStartTime.getText());

					Date time = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss")
							.parse(startDateTime.toString());

					currentTask.setTitle(taskTitle.getText());
					currentTask.setTime(time);
				}

				if (repeated.isSelected() && !taskTitle.getText().equals(null)
						&& timeOrStartTime.getText() != null
						&& !dateOrStartdate.getDate().equals(null)
						&& !endDate.getDate().equals(null)
						&& endTime.getText() != null
						&& repeatInterval.getText() != null) {
					startDateTime = new StringBuilder();

					startDateTime
							.append(AddTaskFrame.DateFormat
									.format(dateOrStartdate.getDate()))
							.append(" ").append(timeOrStartTime.getText());

					Date startTime = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss")
							.parse(startDateTime.toString());

					endDateTime = new StringBuilder();
					endDateTime
							.append(AddTaskFrame.DateFormat.format(endDate
									.getDate())).append(" ")
							.append(endTime.getText());

					Date endTime = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss")
							.parse(endDateTime.toString());

					int rep = Integer.parseInt(repeatInterval.getText()) * 60 * 1000;

					if (endTime.after(startTime)) {
						currentTask.setTitle(taskTitle.getText());
						currentTask.setTime(startTime, endTime, rep);
					}
				}
				currentTask.setActive(true);
				CorrectTaskFrame.taskCollection.remove(currentTask);
				CorrectTaskFrame.taskCollection.add(currentTask);

				TaskStorage.writeText(taskCollection,
						CorrectTaskFrame.filename);
				taskTitle.setText(null);

				update();

			} catch (NullPointerException e3) {
				JOptionPane.showMessageDialog(correctDialogFrame,
						"Set correct date!", "FORMAT ERROR :",
						JOptionPane.ERROR_MESSAGE);

			}

			catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(correctDialogFrame,
						"Set correct task interval!", "FORMAT ERROR :",
						JOptionPane.ERROR_MESSAGE);

			}

			catch (IllegalArgumentException e4) {
				JOptionPane.showMessageDialog(correctDialogFrame,
						"Set correct task interval!", "FORMAT ERROR :",
						JOptionPane.ERROR_MESSAGE);

			}

			catch (ParseException e1) {
				try {
					throw (IOException) new IOException().initCause(e1);
				} catch (IOException e2) {

				}
			} catch (IOException e1) {
			}

		}
	}

	@Override
	public void update(Observable source, Object arg) {
		comboBoxTasks.removeAllItems();
		comboBoxTasks.addItem(null);
		for (Task tasks : CorrectTaskFrame.taskCollection) {
			comboBoxTasks.addItem(tasks.toString());
		}
		correctDialogFrame.pack();
	}
}