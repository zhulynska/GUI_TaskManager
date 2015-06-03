package ua.edu.sumdu.j2se.E_Zhulynska.lab1.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ua.edu.sumdu.j2se.E_Zhulynska.lab1.model.*;

public abstract class SwingFrame implements Observer {

	public SwingFrame(TaskList taskCollection, String file) {
		// subscribe to changes in the model

		this.taskCollection = (ArrayTaskList) taskCollection;
		this.fileName = file;
		taskCollection.observable().addObserver(this);

		for (Task task : this.taskCollection) {
			task.observable().addObserver(this);
		}
		// run Swing in a separate thread
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createFrame();
				} catch (ParseException e) {

				} catch (IOException e) {

				}
				update();
			}
		});
	}

	protected abstract void createFrame() throws ParseException, IOException;

	/**
	 * read the changes in TaskList
	 */
	protected void update() {
		update(null, null);
	}

	/**
	 * update the changes in frame components
	 */
	public abstract void update(Observable source, Object arg);

	/**
	 * add ActionListener
	 * 
	 * @param listener
	 */
	public void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}

	/**
	 * remove ActionListener
	 * 
	 * @param listener
	 */
	public void removeActionListener(ActionListener listener) {
		listeners.remove(listener);
	}

	/**
	 * add the 
	 * @param sign the listener on the event 
	 */
	protected void fireAction(String command) {
		ActionEvent event = new ActionEvent(this, 0, command);
		for (Iterator listener = listeners.iterator(); listener.hasNext();) {
			((ActionListener) listener.next()).actionPerformed(event);
		}
	}

	private ArrayTaskList taskCollection = new ArrayTaskList();
	private String fileName = new String();
	protected static ArrayList listeners = new ArrayList();
	public String text = null;
	public Task task = null;

	JFrame frame = new JFrame("Task Manager");
}
