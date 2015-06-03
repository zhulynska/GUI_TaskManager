package ua.edu.sumdu.j2se.E_Zhulynska.lab1.model;

//package ua.edu.sumdu.j2se.Zhulynska.pr7;
import java.util.*;
import java.io.*;
/**
* @author Zhulynska
* This class describes the massive of tasks
*/


public class Node implements Cloneable, Serializable {
   
   private Task task;
 
   public Node first = null;
   public Node current = null;
   public Node next = null;

   /**
   * This method determines the task and next element after task 
   */
   public Node(Task task) {
		setTask(task);
		setNext(null);
	}
	
   /**
   * @return Return the task  
   */
   public Task getNodeTask(){
       return task;
   }
   
   /**
   * Setting the task  
   */
   public void setTask(Task task){
       this.task = task;
   }
   
   /**
   * @return Return next element  
   */ 
   public Node getNext() {
       return next;
   }
   
   /**
   * Setting the task  
   */
   public void setNext(Node next) {
		this.next = next;
   }
   
   /**
    * @return a deep clone of element
    */
    @Override
   public Node clone() {
       try {
           Node cloned = (Node) super.clone();
           cloned.task = (Task) task.clone();
           return cloned;
       }
       catch (CloneNotSupportedException e) {
               throw new Error (e.getMessage());
       }
   }    
   
}