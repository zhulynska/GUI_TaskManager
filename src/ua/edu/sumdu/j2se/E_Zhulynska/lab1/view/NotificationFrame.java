package ua.edu.sumdu.j2se.E_Zhulynska.lab1.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.lang.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import ua.edu.sumdu.j2se.E_Zhulynska.lab1.model.*;

/**
 * @author Zhulynska This class show current tasks in frame
 */
public class NotificationFrame implements NotificationShow,Runnable  {

	public void showCurrentTask(ArrayList<Task> collection) {
		// TODO Auto-generated method stub
		if (collection == null) throw new IllegalArgumentException();
		StringBuilder sb = new StringBuilder();
		
		for (Task t : collection) {
			sb.append(t.toString()).append("\n");
		}
		JOptionPane messageWindow = new JOptionPane();
		messageWindow.showMessageDialog(null, sb.toString(), "TIME OF FOLLOW TASK(S) OCCURED :", JOptionPane.INFORMATION_MESSAGE);
		
		//messageWindow.
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		NotificationFrame nf = new NotificationFrame();
		nf.showCurrentTask(TaskNotification.currentTask());
		
	}

}
