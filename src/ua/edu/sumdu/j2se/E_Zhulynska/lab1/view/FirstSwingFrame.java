package ua.edu.sumdu.j2se.E_Zhulynska.lab1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;

import ua.edu.sumdu.j2se.E_Zhulynska.lab1.controller.*;
import ua.edu.sumdu.j2se.E_Zhulynska.lab1.model.*;
import ua.edu.sumdu.j2se.E_Zhulynska.lab1.view.*;

public class FirstSwingFrame extends SwingFrame {
	public static String open_AddTaskFrame = "open_AddTaskFrame";
	public static String open_RemoveTaskFrame = "open_RemoveTaskFrame";
	public static String open_correctTaskFrame = "open_correctTaskFrame";
	public static String openFile = "openFile";
	public static String exit = "exit";
	public static String taskNotificationString = "taskNotificationString";
	private ArrayTaskList taskCollection = new ArrayTaskList();
	private String filename = new String();

	public FirstSwingFrame(TaskList taskCollection, String file) {

		super(taskCollection, file);
		this.taskCollection = (ArrayTaskList) taskCollection;
		this.filename = file;
	}

	static long count = 24 * 60 * 60 * 1000;
	static FirstSwingFrame window = null;
	NotificationFrame notificationFrameElement = new NotificationFrame();
	private JPanel panel = new JPanel();
	private JLabel monday = null;
	private JLabel tuesday = null;
	private JLabel wednesday = null;
	private JLabel thursday = null;
	private JLabel friday = null;
	private JLabel saturday = null;
	private JLabel sunday = null;

	JTextArea mondayTasks = null;
	private JTextArea tuesdayTasks = null;
	private JTextArea wednesdayTasks = null;
	private JTextArea thursdayTasks = null;
	private JTextArea fridayTasks = null;
	private JTextArea saturdayTasks = null;
	private JTextArea sundayTasks = null;
	private SortedMap<Date, Set<Task>> sm = null;

	JLabel tt = null;

	private static final SimpleDateFormat DATE_FORMAT0 = new SimpleDateFormat(
			"E, MMM d", Locale.ENGLISH);

	@Override
	protected void createFrame() throws ParseException, IOException {
		frame.setLocationRelativeTo(null);

		// set the text in format "Day of week, date"
		monday = new JLabel(DATE_FORMAT0.format(dateIndification().get(0)));
		monday.setBorder(BorderFactory.createLineBorder(Color.black));

		tuesday = new JLabel((DATE_FORMAT0.format(new Date(dateIndification()
				.get(0).getTime() + 1 * count))));
		tuesday.setBorder(BorderFactory.createLineBorder(Color.black));

		wednesday = new JLabel((DATE_FORMAT0.format(new Date(dateIndification()
				.get(0).getTime() + 2 * count))));
		wednesday.setBorder(BorderFactory.createLineBorder(Color.black));

		thursday = new JLabel((DATE_FORMAT0.format(new Date(dateIndification()
				.get(0).getTime() + 3 * count))));
		thursday.setBorder(BorderFactory.createLineBorder(Color.black));

		friday = new JLabel((DATE_FORMAT0.format(new Date(dateIndification()
				.get(0).getTime() + 4 * count))));
		friday.setBorder(BorderFactory.createLineBorder(Color.black));

		saturday = new JLabel((DATE_FORMAT0.format(new Date(dateIndification()
				.get(0).getTime() + 5 * count))));
		saturday.setBorder(BorderFactory.createLineBorder(Color.black));

		sunday = new JLabel((DATE_FORMAT0.format(dateIndification().get(1))));
		sunday.setBorder(BorderFactory.createLineBorder(Color.black));

		mondayTasks = new JTextArea(1, 10);
		mondayTasks.setLineWrap(true);
		mondayTasks.setEditable(false);
		mondayTasks.setBorder(BorderFactory.createLineBorder(Color.black));

		tuesdayTasks = new JTextArea(1, 10);
		tuesdayTasks.setLineWrap(true);
		tuesdayTasks.setEditable(false);
		tuesdayTasks.setBorder(BorderFactory.createLineBorder(Color.black));

		wednesdayTasks = new JTextArea(1, 10);
		wednesdayTasks.setLineWrap(true);
		wednesdayTasks.setEditable(false);
		wednesdayTasks.setBorder(BorderFactory.createLineBorder(Color.black));

		thursdayTasks = new JTextArea(1, 10);
		thursdayTasks.setBorder(BorderFactory.createLineBorder(Color.black));
		thursdayTasks.setLineWrap(true);
		thursdayTasks.setEditable(false);

		fridayTasks = new JTextArea(1, 10);
		fridayTasks.setLineWrap(true);
		fridayTasks.setEditable(false);
		fridayTasks.setBorder(BorderFactory.createLineBorder(Color.black));

		saturdayTasks = new JTextArea(1, 10);
		saturdayTasks.setLineWrap(true);
		saturdayTasks.setEditable(false);
		saturdayTasks.setBorder(BorderFactory.createLineBorder(Color.black));

		sundayTasks = new JTextArea(1, 10);
		sundayTasks.setLineWrap(true);
		sundayTasks.setEditable(false);
		sundayTasks.setBorder(BorderFactory.createLineBorder(Color.black));

		this.addMenu();
		frame.add(panel, BorderLayout.NORTH);

		panel.setSize(100, 100);
		GridLayout gr = new GridLayout(7, 2);
		panel.setLayout(gr);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(monday);
		panel.add(mondayTasks);
		panel.add(tuesday);
		panel.add(tuesdayTasks);

		panel.add(wednesday);
		panel.add(wednesdayTasks);

		panel.add(thursday);
		panel.add(thursdayTasks);

		panel.add(friday);
		panel.add(fridayTasks);

		panel.add(saturday);
		panel.add(saturdayTasks);

		panel.add(sunday);
		panel.add(sundayTasks);

		TaskManagerController.add = new AddTaskFrame(this.taskCollection, this.filename);
		TaskManagerController.correct = new CorrectTaskFrame(
				this.taskCollection, this.filename);
		TaskManagerController.removeFrame1 = new RemoveTaskFrame(
				this.taskCollection, this.filename);

		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(FirstSwingFrame.taskNotificationString);
			}
		});
		timer.start();

		JPanel southPanel = new JPanel();
		southPanel.setVisible(true);

		JButton addTaskButton = new JButton("Add new task");

		JButton correctTaskButton = new JButton("Correct task");
		JButton removeTaskButton = new JButton("Remove task");
		JButton exitButton = new JButton("Exit");
		southPanel.add(addTaskButton);
		southPanel.add(correctTaskButton);
		southPanel.add(removeTaskButton);
		southPanel.add(exitButton);
		addTaskButton.setVisible(true);
		frame.add(southPanel, BorderLayout.SOUTH);

		addTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(open_AddTaskFrame);

			}
		});

		correctTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(open_correctTaskFrame);

			}
		});

		removeTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(open_RemoveTaskFrame);

			}
		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(exit);

			}
		});

		frame.pack();
		// frame.setSize(300, frame.getHeight());

		frame.setVisible(true);

		

	}

	/*public ua.edu.sumdu.j2se.E_Zhulynska.lab1.view.AddTaskFrame createAddDialogFrame(
			TaskList tasks) {
		return new ua.edu.sumdu.j2se.E_Zhulynska.lab1.view.AddTaskFrame(tasks);
	}
	*/

	/**
	 * 
	 * @param day
	 * @param set
	 * @return the time and tasks of this time
	 */
	public static String calendarCreator(JLabel day,
			SortedMap<Date, Set<Task>> set) {
		if (set == null)
			throw new IllegalArgumentException(set + "is empty!");

		StringBuilder sb = new StringBuilder();
		for (Date key : set.keySet()) {
			// if the key contains first 3 symbols from day.getText(), it will
			// be done the following...
			if (key.toString().contains(
					day.getText().toString().substring(0, 2)))
				sb.append(
						(new SimpleDateFormat("HH:mm:ss").format(key))
								.toString()).append(" - ")
						.append(toTitleString(set.get(key))).append("\n");
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param tasks
	 *            - collection of tasks
	 * @return a list of task titles separated with "," (will be used in task's
	 *         calendar)
	 */
	public static String toTitleString(Set<Task> tasks) {
		if (tasks == null)
			throw new IllegalArgumentException();
		StringBuilder s = new StringBuilder();
		for (Task t : tasks)
			s.append(t.getTitle()).append(", ");
		s.deleteCharAt(s.length() - 2);
		return s.toString();
	}

	public void addMenu() {
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		JMenu edit = new JMenu("Edit");
		JMenu view = new JMenu("View");
		menu.add(edit);
		menu.add(view);
		JMenuItem editItemAdd = new JMenuItem("Add task");
		JMenuItem editItemCorrect = new JMenuItem("Correct task");
		JMenuItem editItemRemove = new JMenuItem("Remove task");

		editItemAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireAction(open_AddTaskFrame);
			}
		});

		editItemRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireAction(open_RemoveTaskFrame);
			}
		});

		editItemCorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireAction(open_correctTaskFrame);
			}
		});

		JMenuItem viewItemFile = new JMenuItem("All tasks in the file");
		viewItemFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(FirstSwingFrame.openFile);
			}
		});

		edit.add(editItemAdd);
		edit.add(editItemCorrect);
		edit.add(editItemRemove);
		view.add(viewItemFile);
	}

	/**
	 * 
	 * @return such values as startDate - Monday of current week and endDate -
	 *         Sunday of current week and identify the date range of current
	 *         week these values will be used for such methods as incoming
	 *         (tasks, start, end), timeline(tasks, start, end)
	 * @throws ParseException
	 * @throws IOException
	 */
	public static ArrayList<Date> dateIndification() throws ParseException,
			IOException {
		ArrayList<Date> dateList = new ArrayList<Date>();
		Date startDate = new Date();
		Date endDate = new Date();

		Date now = new Date();
		if (now.toString().contains("Mon")) {
			startDate.setTime(now.getTime());
			endDate.setTime(now.getTime() + 6 * count);

		} else if (now.toString().contains("Tue")) {
			startDate.setTime(now.getTime() - 1 * count);
			endDate.setTime(now.getTime() + 5 * count);
		} else if (now.toString().contains("Wed")) {
			startDate.setTime(now.getTime() - 2 * count);
			endDate.setTime(now.getTime() + 4 * count);
		} else if (now.toString().contains("Thu")) {
			startDate.setTime(now.getTime() - 3 * count);
			endDate.setTime(now.getTime() + 3 * count);
		} else if (now.toString().contains("Fri")) {
			startDate.setTime(now.getTime() - 4 * count);
			endDate.setTime(now.getTime() + 2 * count);
		} else if (now.toString().contains("Sat")) {
			startDate.setTime(now.getTime() - 5 * count);
			endDate.setTime(now.getTime() + 1 * count);
		} else {
			startDate.setTime(now.getTime() - 6 * count);
			endDate.setTime(now.getTime());
		}

		// set startDate from monday of curent week with time 00:00:00
		dateList.add(startTimeDate(startDate));

		// set endtDate from sunday of curent week with time 23:59:59
		dateList.add(endTimeDate(endDate));
		return dateList;
	}

	/**
	 * @return start time of date 00:00:00
	 * @throws IOException
	 */
	static Date startTimeDate(Date date) throws IOException {
		Date start00 = null;
		StringBuilder startOfDay = new StringBuilder(new SimpleDateFormat(
				"E dd:MMM:yyyy").format(date));
		try {
			startOfDay.append(" 00:00:00");
			start00 = new SimpleDateFormat("E dd:MMM:yyyy HH:mm:ss")
					.parse(startOfDay.toString());

		} catch (ParseException e) {
			throw (IOException) new IOException().initCause(e);
		}

		return start00;
	}

	/**
	 * @return end time of date 23:59:59
	 * @throws IOException
	 */
	static Date endTimeDate(Date date) throws IOException {
		Date end24 = null;
		StringBuilder startOfDay = new StringBuilder(new SimpleDateFormat(
				"E dd:MMM:yyyy").format(date));
		try {
			startOfDay.append(" 23:59:59");
			end24 = new SimpleDateFormat("E dd:MMM:yyyy HH:mm:ss")
					.parse(startOfDay.toString());

		} catch (ParseException e) {
			throw (IOException) new IOException().initCause(e);
		}

		return end24;
	}

	@Override
	public void update(Observable source, Object arg) {
		// update
		// currentWeekTasks - collection of tasks for current week

		try {
			sm = Tasks.timeline(this.taskCollection, dateIndification().get(0),
					dateIndification().get(1));
		} catch (ParseException e) {
		} catch (IOException e) {

		}

		if (calendarCreator(monday, sm) != null) {
			mondayTasks.setText(calendarCreator(monday, sm).toString());
		}

		if (calendarCreator(tuesday, sm) != null) {
			tuesdayTasks.setText(calendarCreator(tuesday, sm).toString());
		}

		if (calendarCreator(wednesday, sm) != null) {
			wednesdayTasks.setText(calendarCreator(wednesday, sm).toString());
		}

		if (calendarCreator(thursday, sm) != null) {
			// if (!thursdayTasks.getText().equals(null))
			// thursdayTasks.setText(null);
			thursdayTasks.setText(calendarCreator(thursday, sm).toString());
		}

		if (calendarCreator(friday, sm) != null) {
			fridayTasks.setText(calendarCreator(friday, sm).toString());

		}

		if (calendarCreator(saturday, sm) != null) {
			saturdayTasks.setText(calendarCreator(saturday, sm).toString());
		}

		if (calendarCreator(sunday, sm) != null) {
			sundayTasks.setText(calendarCreator(sunday, sm).toString());
		}
		frame.pack();

	}

}
