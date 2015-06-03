package ua.edu.sumdu.j2se.E_Zhulynska.lab1.model;

//package ua.edu.sumdu.j2se.Zhulynska.pr7;
import java.util.*;
/**
* @author Zhulynska
* This class for working with collections
*/
public  class Tasks {

   /**
    * @return a set of tasks from start to end
    * @throws incorrect time or/and empty initial collection
   */
   public static Set<Task> incoming( Iterable<Task> tasks, Date start, Date end) {
		 if (tasks == null) throw new NullPointerException("empty collection " + tasks );
		 if ((start.after(end))) throw new IllegalArgumentException("illegal argument" + start + " or "+ end);
	        Iterator <Task> iter = tasks.iterator();
	        Set <Task> collect = new HashSet <Task> ();
	        while (iter.hasNext()) {
               Task t = iter.next();
	        	if ((t.nextTimeAfter(start) != null) && (t.nextTimeAfter(start).getTime() <= end.getTime()))
                  collect.add(t);
	        }
          return collect;    
	}

   /**
    * @return a SortedMap of tasks from start to end
    * @throws incorrect time or/and empty initial collection
   */
   public static SortedMap<Date, Set<Task>> timeline(Iterable<Task> tasks, Date start, Date end) {
		if (tasks == null) throw new NullPointerException("tasks is empty");
		if (start.after(end)) throw new IllegalArgumentException("illegal argument" + end + "or" + start);

       	Iterator <Task> iter = incoming(tasks, start, end).iterator();
		SortedMap <Date, Set <Task>> sm = new TreeMap <Date, Set <Task>>();
		while (iter.hasNext()){
			Task t = iter.next();
			Date time = t.nextTimeAfter(start);
           while ((time != null) && (!time.after(end))) {
               if (!sm.containsKey(time)) {
               	
               	Set <Task> task = new HashSet<Task>();
               	task.add(t);
               	sm.put(time, task);
               }
		else {
			sm.get(time).add(t);				
		}
               time = t.nextTimeAfter(time);
           }
		}

       return sm;
	}
}


  
   
   



       
