package ua.edu.sumdu.j2se.E_Zhulynska.lab1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author E.Zhulynska This class used to determine the current tasks
 */
public class TaskNotification {

	public TaskNotification(TaskList tasks) {
		this.taskCollection = (ArrayTaskList) tasks;
	}

	private static ArrayTaskList taskCollection = new ArrayTaskList();

	/**
	 * 
	 * @return array of current tasks
	 */
	public static ArrayList<Task> currentTask() {

		if (TaskNotification.taskCollection == null)
			throw new IllegalArgumentException("Collection is empty");
		ArrayList<Task> currentTaskArray = new ArrayList();
		for (Task t : TaskNotification.taskCollection) {
			if ((t.getTime().toString().equals(new Date().toString()))
					|| (t.isRepeated() && t
							.nextTimeAfter(
									new Date(new Date().getTime() - 1000))
							.toString().equals(new Date())))
				currentTaskArray.add(t);
		}
		return currentTaskArray;

	}
}
