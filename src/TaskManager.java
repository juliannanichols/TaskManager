import java.io.*;
import java.util.Scanner;

/*
 * do these things:
 * - keep list of tasks
 * - allow user to add/remove tasks
 * - allow user to prioritize tasks
 * - print out lists of tasks
 * 
 * list of tasks:
 * - read and store on disk
 * - sort/search
 * 
 * reports:
 * - list high priority
 * - list due today/soon
 * - list all(by date OR by priority)
 */

/**
 * This program will assist the user to manage their tasks. (organize, sort, delete, add).
 * @author Julianna Nichols
 */

public class TaskManager {
	
	/**
	 * This is the main method for the TaskManager program.
	 * @param args
	 */

	public static void main(String[] args) {
		Scanner keyboard = new Scanner( System.in );
		TaskList newTaskList = new TaskList();
		String fileName = "Tasks.txt";
		String input;
		short set;
		boolean TrueFalse;
		
		System.out.println( "Welcome to the Task Manager!" );
		
		do {
			Task newTask = new Task();
			
			 System.out.print( "Enter description: " );
			 input = keyboard.nextLine();
			 newTask.setDescription( input );
			 
			 System.out.print( "Enter category (1-5): " );
			 set = keyboard.nextShort();
			 newTask.setCategory( set );
			 
			 System.out.print( "Enter priority (1-3): " );
			 set = keyboard.nextShort();
			 newTask.setPriority( set );
			 
			 keyboard.nextLine();
			 
			 System.out.print( "Enter location (return for none): " );
			 input = keyboard.nextLine();
			 newTask.setLocation( input );
			 
			 System.out.print( "Enter state of completion (type true or false): " );
			 TrueFalse = keyboard.nextBoolean();
			 newTask.setCompleted( TrueFalse );
			 
			 newTaskList.addTask( newTask );
			 
			 System.out.println( "Do you want to enter another task? (y for yes; n for no): " );
			 input = keyboard.nextLine();
			 
		} while (input.equalsIgnoreCase("y") );
		
		
		newTaskList.printTasks();
		
		try {
			newTaskList.writeFile( fileName );
		} catch( FileNotFoundException e ) {
			System.out.println( "File \"" + fileName + "\" not found");
			System.out.println( "Dying..." );
			e.printStackTrace();
			System.exit(-1);
		}
		
		newTaskList = new TaskList();
		
		System.out.println( "Before read: " );
		newTaskList.printTasks();
		
		try {
			newTaskList.readFile( fileName );
		} catch( FileNotFoundException e ) {
			System.out.println( "File \"" + fileName + "\" not found");
			System.out.println( "Dying..." );
			e.printStackTrace();
			System.exit(-1);
		}
		
		keyboard.close();
		
		System.exit(0);

	}

}
