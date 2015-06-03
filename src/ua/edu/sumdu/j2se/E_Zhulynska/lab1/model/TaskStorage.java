package ua.edu.sumdu.j2se.E_Zhulynska.lab1.model;

//package ua.edu.sumdu.j2se.Zhulynska.pr7;
import java.util.*;
import java.io.*;
import java.text.*;
/**
* @author Zhulynska
* This class is for operation with binary and text data streams
*/
public  class TaskStorage {

   private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("E MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
   
   /**
    * @throws incorrect filename, empty tasks collection
   */
	public static void writeText(TaskList tasks, String filename)
			throws IOException {
       FileWriter fw = null;
       try {
           fw = new FileWriter(filename);
           write(tasks, fw);
       }
       finally {
           fw.close();
       }
   }        
	
	/**
    * @throws incorrect filename, read data
   */
	public static void readText(TaskList tasks, String filename)
			throws IOException, ClassNotFoundException {
       FileReader fr = null;
       try {
           fr = new FileReader(filename);
           read(tasks, fr);
       }
       finally {
           fr.close();
       }
   }    
      		    
	/**
	 * @throws empty tasks collection, incorrect data stream
	 */
	public static void write(TaskList tasks, Writer out) throws IOException {
       if (tasks == null)
				throw new IllegalArgumentException("Collection is empty");
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new BufferedWriter (out));
				for (Task t : tasks) {
					pw.write(t.getTitle());
					pw.append("\n");
					
					if (t.isRepeated()){
						pw.write("from");
						pw.append("\n");
						
						pw.write(DATE_FORMAT.format(t.getStartTime()));
						pw.append("\n");
						
						pw.write("to");
						pw.append("\n");
						
						pw.write(DATE_FORMAT.format(t.getStartTime()));
						pw.append("\n");
				
						pw.write("every");
						pw.append("\t");
						
						//pw.write(t.getRepeatInterval());
						//pw.write(String.valueOf(t.getRepeatInterval()));
                       pw.print(t.getRepeatInterval());
						pw.append("\t");
					}
					else {
						pw.write("at");
						pw.append("\n");
						
						pw.write(DATE_FORMAT.format(t.getTime()));
						pw.append("\n");
					}
					pw.write("is_active:"); pw.append("\t");
					pw.print(t.isActive());
				
				pw.append("\t");
				pw.write(";");
				pw.append("\n");
				}
				pw.append("collection_end").append('\n');
			}
			finally {
               pw.flush();
				}
			}  


	/**
	 * @throws  incorrect data stream, read data
	 */
	public static void read(TaskList tasks, Reader in)
			throws IOException {
       if (in == null)
           throw new IllegalArgumentException("Data stream  is empty");
		Scanner sc = null;
		Task t = null;
			try {
				sc = new Scanner(new BufferedReader(in));
				String title = null;
				while (!(title=sc.nextLine()).equals("collection_end")) {
					//System.out.println(title);
					
					String connect = sc.nextLine();
					//System.out.println(connect);
					
					if (connect.equals("from")){
						Date start = DATE_FORMAT.parse(sc.nextLine());
						//System.out.println(start);
						sc.nextLine();
						
						Date end = DATE_FORMAT.parse(sc.nextLine());
						//System.out.println(end);
						sc.next();
						
					
						long rep = sc.nextLong();
						//System.out.println(rep);
						t = new Task (title, start, end, rep);
					}
					else {
						Date time = DATE_FORMAT.parse(sc.nextLine());
						//System.out.println(time);
						t = new Task (title, time);
					}
					sc.next();
					
					t.setActive(sc.nextBoolean());
					
                   if ( sc.nextLine().equals(";"))                   
						tasks.add(t);
					
					}	
				//System.out.println(tasks);
			}
       catch (ParseException e) {
           throw (IOException) new IOException().initCause(e);
		}
   }
   
       
   /**
	 * @throws incorrect filename
	 */
public static void writeBinary(TaskList tasks, String filename) throws IOException {
       FileOutputStream out1 = null;
       try {
           out1 = new FileOutputStream(filename);
           write(tasks, out1);
       }
       finally {    
           out1.close();
       }    
	}
	
	/**
	 * @throws incorrect filename, read data
	 */
	public static void readBinary(TaskList tasks, String filename) throws IOException,
			ClassNotFoundException, EOFException {
       FileInputStream bis1 = new FileInputStream(filename);
       try {    
           read(tasks, bis1);
       }
       finally {
           bis1.close();
       }    
	}


       /** @throws nonexistent or incorrect OutputStream or 
		 * @return string, made with read bytes
		 */   
		public static void write(TaskList tasks, OutputStream out) throws IOException {
       if (tasks == null)
           throw new IllegalArgumentException("Collection is empty");
       DataOutputStream out1 = new DataOutputStream(out);
       try {
           for (Task t : tasks) {
               out1.writeUTF(t.getTitle());
               if (t.isRepeated()) {
                   out1.writeByte(1);
                   // start
                   out1.writeLong(t.getStartTime().getTime());
                   // end
                   out1.writeLong(t.getEndTime().getTime());
                   // repeat
                   out1.writeLong(t.getRepeatInterval());
               } else {
                   out1.writeByte(0);
                   out1.writeLong(t.getTime().getTime());
               }
               // write active
               if (t.isActive())
                   out1.writeByte(1);
               else
                   out1.writeByte(0);
               // separator
               out1.writeChar('$');
           }
           out1.writeUTF("%%");
       } 
       finally {
           out1.flush();
       }
   }
   
   /**
	 * @throws  incorrect data stream
	 */
	public static void read(TaskList tasks, InputStream in) throws IOException {
       if (in == null)
               throw new IllegalArgumentException("InputStream  is empty");
       Task task = null;
       DataInputStream bis1 = new DataInputStream(in);
       try {
       	String title = null;
       	while (!(title = bis1.readUTF()).equals("%%")) {
               //System.out.println(title);
               byte connect = bis1.readByte();
               //System.out.println(connect);
               if (connect == 1) {
                   Date start = new Date(bis1.readLong());
                   //System.out.println(start);
                   Date end = new Date(bis1.readLong());
                   //System.out.println(end);
                   int rep = bis1.readInt();
                   task = new Task(title, start, end, rep);
               } else {
                   Date time = new Date(bis1.readLong());
                   //System.out.println(time);
                   task = new Task(title, time);
               }
               if (bis1.readByte() == 1)
                   task.setActive(true);
               if (bis1.readChar() == '$') {
                   tasks.add(task);
               }
               
               
           }
       }    
       finally {
           //bis1.close();
       }

}	
}