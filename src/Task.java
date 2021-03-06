
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.io.*;

/**
 * Task object containing information on a single task.
 * @author SJHSStudent
 */
public class Task implements Comparable<Task> {

	public Task() {

	}

	/**
	 * comparing priorities in order to sort them
	 * @param t
	 * @return positive, negative, or zero
	 */
	public int compareTo(Task t) {
		if (priority < t.priority) {
			return -1;
		} else if (priority > t.priority) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * set priority of task
	 * @param userPriority
	 */
	public void setPriority(short userPriority) {
		if (userPriority < 0 || userPriority > 3) {
			throw new IllegalArgumentException("Invalid priority");
		}
		priority = userPriority;
	}

	/**
	 * set due date of task
	 * @param userDueDate
	 */
	public void setDueDate(Date userDueDate) {
		dueDate = userDueDate;
	}

	/**
	 * set category of task
	 * @param userCategory
	 */
	public void setCategory(short userCategory) {
		if (userCategory < 0 || userCategory > 5) {
			throw new IllegalArgumentException("Invalid category");
		}
		category = userCategory;
	}

	/**
	 * set description of task
	 * @param userDescription
	 */
	public void setDescription(String userDescription) {
		description = userDescription;
	}

	/**
	 * set location of task
	 * @param userLocation
	 */
	public void setLocation(String userLocation) {
		location = userLocation;
	}

	/**
	 * set if task is completed
	 * @param userCompleted
	 */
	public void setCompleted(boolean userCompleted) {
		completed = userCompleted;
	}

	/**
	 * get priority of task
	 * @return
	 */
	public short getPriority() {
		return priority;
	}

	/**
	 * get due date of task
	 * @return
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * get category of task
	 * @return
	 */
	public short getCategory() {
		return category;
	}

	/**
	 * get description of task
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * get location of task
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * get if task is completed
	 * @return
	 */
	public boolean getCompleted() {
		return completed;
	}

	/**
	 * tell user what priority a task is set at
	 * @return pName
	 */
	public String getPriorityName() {
		String pName;
		switch (priority) {
		case 0:
			pName = "Undefined";
			break;
		case 1:
			pName = "High";
			break;
		case 2:
			pName = "Medium";
			break;
		case 3:
			pName = "Low";
			break;
		default:
			pName = "Illegal Value";
		}
		return pName;
	}

	/**
	 * Write a task to the provided PrintWriter object
	 * @param writer
	 */
	public void write(PrintWriter writer) {
		// write data separated by tabs
		StringBuilder s = new StringBuilder();

		s.append(priority);
		s.append("\t");

		if (dueDate != null) {
			s.append(dueDate.toString());
		}

		// format the way the task will be written to disk
		s.append("\t");
		s.append(category);
		s.append("\t");
		s.append(description);
		s.append("\t");
		s.append(location);
		s.append("\t");
		s.append(completed);

		writer.println(s);
	}

	/**
	 * tell user what category (in words) a task is assigned to
	 * @return cName
	 */
	public String getCategoryName() {
		String cName;
		switch (category) {
		case 0:
			cName = "Undefined";
			break;
		case 1:
			cName = "Other";
			break;
		case 2:
			cName = "School";
			break;
		case 3:
			cName = "Personal";
			break;
		case 4:
			cName = "Chore";
			break;
		case 5:
			cName = "Work";
		default:
			cName = "Illegal Value";
		}
		return cName;
	}

	private short priority;
	private Date dueDate;
	private short category;
	private String description;
	private String location;
	private boolean completed;
	
	 //priorities
	public static short getPrioH() {
		return PRIO_HIGH;
	}
	public static short getPrioM() {
		return PRIO_MED;
	}
	public static short getPrioL() {
		return PRIO_LOW;
	}
	public static short getPrioU() {
		return PRIO_UNDEF;
	}

	public static final short PRIO_HIGH = 1;
	public static final short PRIO_MED = 2;
	public static final short PRIO_LOW = 3;
	public static final short PRIO_UNDEF = 0;

	 // categories
	public static short getCatU() {
		return CAT_UNDEF;
	}
	public static short getCatO() {
		return CAT_OTHER;
	}
	public static short getCatS() {
		return CAT_SCHOOL;
	}
	public static short getCatP() {
		return CAT_PERSONAL;
	}
	public static short getCatC() {
		return CAT_CHORE;
	}
	public static short getCatW() {
		return CAT_WORK;
	}
	public static final short CAT_UNDEF = 0;
	public static final short CAT_OTHER = 1;
	public static final short CAT_SCHOOL = 2;
	public static final short CAT_PERSONAL = 3;
	public static final short CAT_CHORE = 4;
	public static final short CAT_WORK = 5;

	/**
	 * Putting all the information for one task into one string
	 * @param
	 * @return string
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		//setting up the format in which the task will be printed out
		s.append("Priority: " + getPriorityName() + "\n");
		if (dueDate != null) {
			s.append("Due Date: " + dueDate.toString() + "\n");
		}
		s.append("Category: " + getCategoryName() + "\n");
		s.append("Description: " + description + "\n");
		s.append("Location: " + location + "\n");
		s.append("Completed? " + (completed ? "y" : "n") + "\n");
		return s.toString();
	}

	/**
	 * Read a task from disk using the provided BufferedReader
	 * @param reader BufferedReader to read from disk
	 * @return read task or null if not read
	 */
	public void read(BufferedReader reader) {
		String line = null;
		String[] results;

		try {
			line = reader.readLine();
		} catch (IOException e) {
			System.out.println("Cannot read file: " + e.getMessage());
			return;
		}

		results = line.split("\t");

		setPriority(Short.parseShort(results[0])); //setting what is at index 0 to priority

		//setting what is at index 1 to date
		try {
			setDueDate(DateFormat.getDateInstance().parse(results[1]));
		} catch (ParseException e) {
			System.out.println("Could not parse date. Setting to null");
		}

		setCategory(Short.parseShort(results[2])); //setting what is at index 2 to category
		setDescription(results[3]); //setting what is at index 3 to description
		setLocation(results[4]); //setting what is at index 4 to location
		setCompleted(Boolean.parseBoolean(results[5])); //setting what is at index 5 to completion

		return;
	}
}