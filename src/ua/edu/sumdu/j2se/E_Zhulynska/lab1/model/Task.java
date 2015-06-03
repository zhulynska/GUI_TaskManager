package ua.edu.sumdu.j2se.E_Zhulynska.lab1.model;

//package ua.edu.sumdu.j2se.Zhulynska.pr7;

import java.util.*;
import java.io.*;
/**
 * @author Zhulynska
 * This class provides description of the task and time, when it should be done
 */
public class Task extends Observable implements Cloneable, Serializable {
    
    public String title;
    public Date start = null;
    public Date end = null;
    public long repeat = 0;
    public Date time = null;
    public boolean active = false;
    //private int hash;
      
  
     /**
     * @return Construction of non repeatable task
     * @throws null title
     * @throws incorrect time
     */
    
	public Task(String title, Date time) { 
        if (title == null) throw new NullPointerException("wrong title");
        if ( time.getTime() < 0 ) throw new IllegalArgumentException("wrong time");
        this.title = title;
        this.time = time;
        this.active = false;
        this.repeat = 0;
    }
	
	
	 /**
     * @return Construction of repeatable task
     * @throws null title
     * @throws incorrect start, end time or repeat intervals
     */
    public Task(String title, Date s, Date e, long repeat) { 
        if (title == null) throw new NullPointerException("wrong title");
      if (s.getTime() < 0) throw new IllegalArgumentException("wrong start: " + s);
       if ((e.getTime() < 0) || (e.before(s))) throw new IllegalArgumentException("wrong end: " + e);
        if (repeat < 0) throw new IllegalArgumentException("wrong interval: " + repeat);
        this.title = title;
        this.start = s;
       // this.time = s;
        this.end = e;
        this.repeat = repeat;
        this.active = false;
    }
	
	
    
    /**
     * @return clone of task
     */
     @Override
    public Task clone()   {
        try {
             Task cloned = (Task) super.clone();
            if (isRepeated()) {
                cloned.start = (Date) start.clone();
                cloned.end = (Date) end.clone();
            }
            else
                cloned.time = (Date) time.clone();
            return cloned;
            
        }
        catch (CloneNotSupportedException e) {
            throw new Error (e.getMessage());
        }
    }
    
    /**
     * @return This method compares tasks
     */
    @Override 
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if((getClass() != obj.getClass()))
            return false;
        if (obj.hashCode() != this.hashCode())
            return false;
        else
            return true;
    }
    
    /**
     * @return hashCode of the elements
    */
    @Override 
    public int hashCode() {
        long hash = 7;
        hash = 11 * hash + getTitle().hashCode();
       // hash = 11 * hash + (getTitle() != null ? getTitle().hashCode() : 0);
        hash = 11 * hash + (getTime().getTime() > 0 ? getTime().getTime() : 0);
        hash = 11 * hash + (isRepeated() ? getStartTime().getTime() : 0);
        hash = 11 * hash + (isRepeated() ? getEndTime().getTime() : 0);
        hash = 11 * hash + (isRepeated() ? getRepeatInterval() : 0);
        hash = 11 * hash + (isActive() ? 3 : 0);
        return (int)hash;
    }
    
    /**
     * @return Task title  
     */
    public String getTitle(){
        return this.title;
    }
    
    public boolean isActive() {
        return this.active;
    }
        
    /**
     * @return Set title's method 
     * @throws null title     
     */
    public void setTitle(String title){
        if (title == null) throw new NullPointerException("title is null!");
        this.title = title;
        setChanged();
        notifyObservers();
    }
    
    /**
     * @return Check task's status method   
     */
    public void setActive(boolean active) {
        this.active = active;
        setChanged();
        notifyObservers();
    }
    
    /**
     * @return Method, which sets the time of non repeatable task 
     * @throws illegal time    
     */
    public void setTime(Date time) {
		if (time.getTime() < 0) throw new IllegalArgumentException("wrong time");
        this.time = (Date)time.clone();
		//this.start = (Date)time.clone();
		//this.end = (Date)time.clone();
		this.repeat = 0;
        setChanged();
        notifyObservers();
	}
    
    /**
     * @return Method, which sets the time of repeatable task 
     * @throws incorrect start, end time or repeat intervals
     */
    public void setTime(Date s, Date e, long repeat) {
//if (s.getTime() < 0) throw new IllegalArgumentException("wrong start" + s);
        if (e.before(s)) throw new IllegalArgumentException("wrong time" + e + "or " + s);
        if (repeat < 0) throw new IllegalArgumentException("wrong interval" + repeat);
        this.start = (Date)s.clone();
        this.end = (Date)e.clone();
        this.repeat = repeat;
        setChanged();
        notifyObservers();
    }
    
    /**
     * @return Method, which gets the time of the first or a single task appearance   
     */
    public Date getTime() {
    		if (repeat <= 0)
			return this.time;
		return this.start;
    }   
        
    /**
     * @return If it is non repeatable task, return event time   
     */
    public Date getStartTime() {
		return getTime();
    }
    
    /**
     * @return If it is non repeatable task, return event time
     */
    public Date getEndTime() {                          
        if (repeat <= 0)
            return this.time;
        else return this.end;
    }
    
    /**
     * @return If it is non repeatable task, return 0   
     */
    public long getRepeatInterval() {                   
         if (repeat <= 0)
            return 0;
        else 
            return this.repeat;
    }
    
    /**
     * @return Does the event repeat?
     */
    public boolean isRepeated() {                      
        if (repeat > 0)
            return true;
        else return false;
    }
    
    /**
     * @return Method, which gets the task discription
     */
    public String toString() { 
        String active = "inactive";
	    if (isRepeated()) {
	        if (isActive())
	        	active = "active";
	        return "Task " + this.title + " " +active+ " from " + this.start + 
	                " to " + this.end + " every " + this.repeat/1000 + " sec";
	    }
        if (isActive()) 
	        active = "active";
	    return "Task " + this.title + " " +active+ " at "+ this.time;
    }
   
    /**
     * @return Method, which gets next time event after current, excepting current time
     * @throws incorrect time
     */
    public  Date nextTimeAfter(Date time) {   
	    if (time == null) throw new IllegalArgumentException("wrong time: "+time);
	    if ((! isActive()) || (time.getTime() >= getEndTime().getTime()))
	        return null;
	    else if  (time.before(getTime()))
	        return getTime();
        //if  ((time.getTime() >= getTime().getTime()) && (!isRepeated()))
	     //   return null;    
	    Date startTime = (Date) getStartTime().clone();
	    Date nextTime = new Date();
	    while ((startTime.getTime()) <= end.getTime()) {
	        if ((time.getTime() >= startTime.getTime()) && (time.getTime() < (startTime.getTime() + getRepeatInterval()*1000)))
	            nextTime.setTime(startTime.getTime() + getRepeatInterval()*1000);
	        startTime.setTime(startTime.getTime() + getRepeatInterval()*1000);      
	    }
	    if ((time.getTime() >= (startTime.getTime()-getRepeatInterval()*1000) ) && (time.before(startTime)))
	         nextTime.setTime(startTime.getTime());
	    return nextTime;    
    }
       
    
    public Observable observable() {
        return this;
    }
}
 