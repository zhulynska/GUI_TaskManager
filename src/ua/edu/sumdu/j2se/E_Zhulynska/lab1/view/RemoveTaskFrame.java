package ua.edu.sumdu.j2se.E_Zhulynska.lab1.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ua.edu.sumdu.j2se.E_Zhulynska.lab1.controller.TaskManagerController;
import ua.edu.sumdu.j2se.E_Zhulynska.lab1.model.*;




public class RemoveTaskFrame extends SwingFrame {
	
	
	public RemoveTaskFrame(TaskList taskCollection, String file) {
		super(taskCollection, file);
		this.taskCollection = (ArrayTaskList) taskCollection;
		this.fileName = file;
	}

	ArrayList<JCheckBox> checkBox = new ArrayList<JCheckBox>();
	private JDialog removeDialogFrame = new JDialog(frame,
			"Remove Task from Task Manager List", true);
	private JPanel centralPanel = new JPanel();
	private GridBagLayout gbl = new GridBagLayout();
	private static ArrayTaskList  taskCollection = new ArrayTaskList();
	public static String removeButtonString = "removeButtonString";
	public static String closeWindow = "closeWindow";
	private static String fileName = new String();
	private GridBagConstraints cell = new GridBagConstraints();
	
	
	@Override
	protected void createFrame() throws ParseException, IOException {
		removeDialogFrame.setLocationRelativeTo(null);
		JPanel northPanel = new JPanel();
		Label titleQuery = new Label("Select task for removing: ");
		northPanel.add(titleQuery);
		removeDialogFrame.add(northPanel, BorderLayout.NORTH);
		removeDialogFrame.setSize(500, 500);
		removeDialogFrame.add(centralPanel, BorderLayout.CENTER);
		centralPanel.setLayout(gbl);
		JPanel southPanel = new JPanel();
		JButton removeTaskButton = new JButton("Remove");
		southPanel.add(removeTaskButton);
		
		JButton closeTaskButton = new JButton("Return to Task Manager Window");
		southPanel.add(closeTaskButton);
		closeTaskButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				fireAction(RemoveTaskFrame.closeWindow);
			}
		});
		
		
		
		removeDialogFrame.add(southPanel, BorderLayout.SOUTH);
		removeTaskButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fireAction(RemoveTaskFrame.removeButtonString);
				
			}
		});
	}

	public class RemoveButtonClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (JCheckBox cb : checkBox) {

				for (Task t :RemoveTaskFrame.taskCollection) {
					if (cb.isSelected()
							&& t.toString().equals(cb.getText())) {

						RemoveTaskFrame.taskCollection.remove(t);
						cb.setEnabled(false);
					}
					try {
						TaskStorage.writeText(RemoveTaskFrame.taskCollection, RemoveTaskFrame.fileName);
					} catch (IOException e1) {
					}
				}
			}
		}
	}
	
	/**
	 * show JDialog removeDialogFrame with the range of tasks
	 */
	public void show() {
		cell.anchor = GridBagConstraints.NORTHWEST;
		cell.fill = GridBagConstraints.NORTHWEST;
		cell.gridheight = 0;
		cell.gridwidth = 0;
		// 1;
		cell.gridx = 0;
		cell.gridy = GridBagConstraints.RELATIVE;
		cell.weightx = 0.0;
		cell.weighty = 0.0;
		try {
			centralPanel.removeAll();

			for (JCheckBox checks : tasksInBox()) {
				checkBox.add(checks);
				
				gbl.setConstraints(checks, cell);
				centralPanel.add(checks);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		removeDialogFrame.setVisible(true);
	}
	
	/**
	 * close removeDialogFrame
	 */
	  public void close() {
		  removeDialogFrame.setVisible(false);
		  removeDialogFrame.dispose();
	    }

	/**
	 * 
	 * @param tasks
	 * @return tasksInCheckBox - collection of JCheckBox elements with text,
	 *         described tasks
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<JCheckBox> tasksInBox()
			throws ClassNotFoundException, IOException {
		if (RemoveTaskFrame.taskCollection == null)
			throw new IllegalArgumentException("Collection is empty");
		ArrayList<JCheckBox> tasksInCheckBox = new ArrayList<JCheckBox>();
		for (Task t : RemoveTaskFrame.taskCollection) {
			tasksInCheckBox.add(new JCheckBox(t.toString()));
		}
		return tasksInCheckBox;
	}

	@Override
	public void update(Observable source, Object arg) {
	}
}
