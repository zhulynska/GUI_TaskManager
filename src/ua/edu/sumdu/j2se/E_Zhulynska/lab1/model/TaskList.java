package ua.edu.sumdu.j2se.E_Zhulynska.lab1.model;

//package ua.edu.sumdu.j2se.Zhulynska.pr7;
import java.util.*;
import java.util.Observer;
import java.io.*;
/**
* @author Zhulynska
* This class involves the abstract methods for working with massive of tasks
*/
public abstract class TaskList extends Observable implements Cloneable, Iterable <Task>, Serializable{


   /**
    * @return Add new element
    * @throws NullPointerException("add null task")
    */
	 public abstract void add(Task task); 
		
   /**
    * @return Remove the element
    * @throws NullPointerException("remove null task");
    */
    public abstract void remove(Task task); 
  
  /**
    * @return Task count in the current list
    */  
     public abstract int size(); 
     
     
     
     public Observable observable() {
         return this;
     }
     
   
   /**
    * @return override clone() in TaskList class
    * @throws the class doesn't implement interface Cloneable
    */
   @Override
   public TaskList clone() throws CloneNotSupportedException {
       TaskList cloned = (TaskList) super.clone();
       return cloned;
   }
   
    @Override
   public String toString() {
       if (size() == 0) return this.getClass().getName() + "[null]";
       StringBuilder s = new StringBuilder(); 
       s.append(this.getClass().getName()).append(" [");
       for (Task t : this)
           s.append(t).append(",");

       s.deleteCharAt(s.length()-1);
       s.append("]");	        
       return s.toString();
	}
   
   @Override
   /**
    * @return true if class name, hashcodes, order and name of elements in collections will be similar
    */
   public boolean equals(Object obj) {
       //нужно проверить порядок, hash код, и класс.
	    if (obj == null) 
           return false;
	    if ((obj.getClass() != this.getClass())) 
	        	return false;
           boolean rez = true;
           // приводим obj к типу TaskList и cоздаем его итератор
           TaskList obj1 = (TaskList) obj;
	        Iterator iter = obj1.iterator();
	        // создаем итератор создаваемых элементов в классах-наследниках
	        Iterator iter_this = this.iterator();
	        	while (iter.hasNext() && iter_this.hasNext()) { 
                   Task t_this = (Task)iter_this.next();
                   Task t = (Task)iter.next();
                   if(!t.equals(t_this))
	        		 	rez = false;
	        	}
               return rez;                
	    }

   
   /**
    * @return hashCode of the elements
   */
   @Override 
   public int hashCode() {
       long hash = 7;
		for (Task t : this) 
           hash = t.hashCode();
       return (int)hash;    
   }  
   
      
   
}


  
   
   



       
