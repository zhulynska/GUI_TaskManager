package ua.edu.sumdu.j2se.E_Zhulynska.lab1.model;

//package ua.edu.sumdu.j2se.Zhulynska.pr7;
import java.util.*;
import java.io.*;
/**
* @author Zhulynska
* This class describes the massive of tasks, saved likes linked list
*/
public class LinkedTaskList extends TaskList implements Cloneable, Iterable <Task>, Serializable {
   
   private int count = 0;
   private Node first = null;
   private Node current = null;
 
   /**
    * add new task to the linked list
    * @throws add null task
   */    
   public void add(Task task) {
       if (task == null) 
           throw new NullPointerException("task is null");
       // если первый элемент пустой, значит первым будет добавляемая задача
       else {
           if (first == null) { 
           first = new Node(task);
           
       }    
       // в противном случае элементу current, под которым подразумеваем текущий элемент присваиваем имя first
           else {
               Node current = first;
           // пока следующий за текущим элемент не равен 0, 
           // мы текущим называем следующий за ним и следующему за ним присваиваем добавляемую задачу
               while (current.getNext() != null) {
                   current = current.getNext();
               }
               current.setNext(new Node(task));
           } 
           count++;
       }
       setChanged();
       notifyObservers();
   }
   
   /**
   * remove the task from the linked list
   * @throws remove null task
   */  
   public void remove(Task task) {
       if (task == null) 
           throw new NullPointerException("task is null");
       if (count > 0)
       {
           if (!first.getNodeTask().equals(task)) 
           {
               Node prev = first;
               // пока текущий и следующий узлы существуют
               while (prev != null && prev.getNext() != null) {
                   if (prev.getNext().getNodeTask().equals(task)){
                      // текущий узел устанавливаем как следующий
                      prev.setNext(prev.getNext().getNext());
                      count--;                    
                   }
                   else {
                       prev = prev.getNext();
                   }
               }

           }
           else {
               first = first.getNext();
               count--;
           }
       }
       setChanged();
       notifyObservers();
   }
   
   /**
   * @return Return linked list size
   */  
   public int size() {
       return count;
   } 
   
   /**
    * @return a deep clone of element
    */
   @Override
   public LinkedTaskList clone() {
       try{
           LinkedTaskList cloned = (LinkedTaskList) super.clone();
           if (size() == 0)
               return cloned;
           cloned.first = (Node) first.clone();
           Node current = cloned.first;
           while (current.getNext() != null) {
               current.setNext((Node) current.clone().getNext());
               current = current.getNext();
           }
           return cloned;
       }
       catch (CloneNotSupportedException e) {
           throw new Error (e.getMessage());
   }        
   }

   
   /**
   * @return Return the iterator, realized in LinkedTaskListIterator class
   */  
   public Iterator <Task> iterator() {
       return new LinkedIterator(this);
   }
   
/**
* @author Zhulynska
* This class describes the Iterator methods
*/   
public class LinkedIterator implements Iterator <Task> {
   private LinkedTaskList ltl;
   private int index;
   private boolean nextCalled = false;
   
   /**
   * @return construct the element of LinkedIterator class, based on LinkedTaskList class
   */
   public LinkedIterator(LinkedTaskList object) {
       ltl = object;
   }
   
   /**
   * @return Return true if index < size() 
   */
   public boolean hasNext() {
       if (index < ltl.size())
           return true;
       else return false;
   }

   /**
   * @return Return next element of collection when  hasNext() = true
   */
   public Task next() {
       if (!hasNext()) throw new NoSuchElementException ("There no elements in the collection");
   	Node node_ = ltl.first;
       nextCalled = true;
       for(int i = 0; i < index; i++) 
           node_ = node_.getNext(); 
       index++;
  		return node_.getNodeTask(); 
   }   
           
  
 
 
   /**
   * @return remove the element, called before
   */
   public void remove() {
       if (nextCalled)  {
           int ii = 0;
           Node current_ = ltl.first;
           while (ii < index-1) {
               current_ = current_.getNext();
               ii++;
           } 
           ltl.remove(current_.getNodeTask());
           nextCalled = false;
           setChanged();
           notifyObservers();
	    }
	    else  throw new IllegalStateException("Next element wasn't called");
	    }
}
 
}

   
   

  


