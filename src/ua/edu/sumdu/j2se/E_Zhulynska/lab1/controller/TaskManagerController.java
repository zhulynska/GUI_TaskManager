package ua.edu.sumdu.j2se.E_Zhulynska.lab1.controller;

import java.awt.Desktop;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import ua.edu.sumdu.j2se.E_Zhulynska.lab1.model.*;
import ua.edu.sumdu.j2se.E_Zhulynska.lab1.view.*;

/**
 * 
 * @author user Zhulynska
 *realize the logics of application
 */
public class TaskManagerController implements ActionListener {

	FirstSwingFrame firstFrame = null;
	public static ua.edu.sumdu.j2se.E_Zhulynska.lab1.view.AddTaskFrame add = null;
	public static CorrectTaskFrame correct = null;
	public static RemoveTaskFrame removeFrame1 = null;
	TaskManagerController controller = null;
	public static ArrayTaskList taskCollection = new ArrayTaskList();
	public static String filenameController = null;
			//"fileFrame.txt";
	private List views = new ArrayList();
	
	
	public TaskManagerController(TaskList collection, String filename) {
		this.taskCollection = (ArrayTaskList) collection;
		this.filenameController = filename;
		new TaskNotification(collection);
	}

	public static void main(String[] args) {
		TaskManagerController controller = new TaskManagerController(
				taskCollection, "fileFrame.txt");
		controller.createView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingFrame view = (SwingFrame) e.getSource();
		if (e.getActionCommand().equals(FirstSwingFrame.open_AddTaskFrame)) {
			if (this.add == null) {
				this.add = new AddTaskFrame(this.taskCollection, this.filenameController);
			}
			this.add.show();

		}

		if (e.getActionCommand().equals(FirstSwingFrame.exit)) {
			System.exit(0);
		}

		if (e.getActionCommand().equals(AddTaskFrame.saveTaskString)) {
			if (this.add == null) {
				this.add = new AddTaskFrame(this.taskCollection, this.filenameController);
			}
			ua.edu.sumdu.j2se.E_Zhulynska.lab1.view.AddTaskFrame.AddTaskAction a = add.new AddTaskAction();
			a.actionPerformed(e);
		}

		if (e.getActionCommand().equals(AddTaskFrame.nonRepeatedString)) {
			if (this.add == null) {
				this.add = new AddTaskFrame(this.taskCollection, this.filenameController);
			}
			ua.edu.sumdu.j2se.E_Zhulynska.lab1.view.AddTaskFrame.NonRepeatedClass a = this.add.new NonRepeatedClass();
			a.actionPerformed(e);
		}

		if (e.getActionCommand().equals(AddTaskFrame.repeatedString)) {
			if (this.add == null) {
				this.add = new AddTaskFrame(this.taskCollection, this.filenameController);
			}
			ua.edu.sumdu.j2se.E_Zhulynska.lab1.view.AddTaskFrame.RepeatedClass a = this.add.new RepeatedClass();
			a.actionPerformed(e);
		}

		if (e.getActionCommand().equals(AddTaskFrame.closeWindow)) {
			if (this.add == null) {
				this.add = new AddTaskFrame(this.taskCollection, this.filenameController);
			}
			this.add.close();
		}

		if (e.getActionCommand().equals(FirstSwingFrame.open_correctTaskFrame)) {
			this.correct.show();

		}

		if (e.getActionCommand().equals(CorrectTaskFrame.CorrectNonRepeated)) {
			if (this.correct == null) {
				this.correct = new CorrectTaskFrame(taskCollection, this.filenameController);
			}
			CorrectTaskFrame.NonRepeatedClass a = this.correct.new NonRepeatedClass();
			a.actionPerformed(e);

		}

		if (e.getActionCommand().equals(CorrectTaskFrame.CorrectRepeated)) {
			if (this.correct == null) {
				this.correct = new CorrectTaskFrame(taskCollection, this.filenameController);
			}
			CorrectTaskFrame.RepeatedClass a = this.correct.new RepeatedClass();
			a.actionPerformed(e);

		}

		if (e.getActionCommand().equals(CorrectTaskFrame.ComboBoxTasksString)) {
			if (this.correct == null) {
				this.correct = new CorrectTaskFrame(taskCollection, this.filenameController);
			}
			CorrectTaskFrame.ComboBoxTasksClass a = this.correct.new ComboBoxTasksClass();
			a.actionPerformed(e);
		}

		if (e.getActionCommand().equals(CorrectTaskFrame.saveButtonString)) {
			if (this.correct == null) {
				this.correct = new CorrectTaskFrame(taskCollection, this.filenameController);
			}
			CorrectTaskFrame.CorrectTaskAction a = this.correct.new CorrectTaskAction();
			a.actionPerformed(e);
		}

		if (e.getActionCommand().equals(CorrectTaskFrame.closeCorrectWindow)) {
			if (this.correct == null) {
				this.correct = new CorrectTaskFrame(taskCollection, this.filenameController);
			}
			this.correct.close();
		}

		if (e.getActionCommand().equals(FirstSwingFrame.open_RemoveTaskFrame)) {
			this.removeFrame1.show();

		}

		if (e.getActionCommand().equals(RemoveTaskFrame.removeButtonString)) {
			if (this.removeFrame1 == null) {
				this.removeFrame1 = new RemoveTaskFrame(taskCollection, this.filenameController);
			}
			RemoveTaskFrame.RemoveButtonClass a = this.removeFrame1.new RemoveButtonClass();
			a.actionPerformed(e);
		}

		if (e.getActionCommand().equals(RemoveTaskFrame.closeWindow)) {
			if (this.removeFrame1 == null) {
				this.removeFrame1 = new RemoveTaskFrame(taskCollection, this.filenameController);
			}
			removeFrame1.close();

		}
		// the mechanism of task notification
		if (e.getActionCommand().equals(FirstSwingFrame.taskNotificationString)) {
			if (TaskNotification.currentTask().size() > 0) {
				Runnable r = new NotificationFrame();
				Thread t = new Thread(r);
				t.start();
			}
		}

		if (e.getActionCommand().equals(FirstSwingFrame.openFile)) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			chooser.setSelectedFile(new File(this.filenameController));
			int ret = chooser.showDialog(null, "Open");
			// set the title of dialog window
			chooser.setDialogTitle("Select file");
			// set the tipe of dialog Open or Save
			chooser.setDialogType(JFileChooser.OPEN_DIALOG);
			File file1 = null;
			// JFileChooser.APPROVE_OPTION - noticed, thet user selected to open file
			if (ret == JFileChooser.APPROVE_OPTION) {
				file1 = new File(chooser.getSelectedFile().getPath());
				// Desktop is about a platform to open file
				try {
					Desktop.getDesktop().open(file1);
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * add the actionListener (object of this class) to SwingFrame objects
	 */
	public void createView() {
		SwingFrame view = new FirstSwingFrame(this.taskCollection, this.filenameController);
		view.addActionListener(this);
	}
}
