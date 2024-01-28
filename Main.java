package project;

import java.io.*; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import project.Meeting;




class Meeting {
    private String topic;
    private String date;
    private String time;
    private String location;
    private String description;
    private boolean isPrivate;
    private String notes;
    private List<String> participants;
    private String password;

    public Meeting() {
        this.participants = new ArrayList<>();
        participants.add("Munner");
    	participants.add("Afzal");
    	participants.add("Asma");
    	participants.add("Fatima");
    	participants.add("Hafsa");
    	participants.add("Nashrah");
    	participants.add("Muniza");
    	participants.add("Zubair");
    	participants.add("Zeshan");
    	participants.add("Tariq");
    	participants.add("Faisal");
    	participants.add("Wasi");
    	participants.add("Sunny");
    	participants.add("Kamran");
    	participants.add("Payal");
    	participants.add("Ankita");
    	participants.add("Jameel");
    	participants.add("Mark");
    	participants.add("Pooja");
    }

  
    public void setTopic(String topic) {
       this.topic = topic;
    }

    public void setDate(String date) {
       this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
       this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public boolean getIsPrivate() {
        return isPrivate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

 
    public void addParticipant(String name) {
        participants.add(name);
    }

    public void removeParticipant(String name) {
        Iterator<String> iterator = participants.iterator();
        while (iterator.hasNext()) {
            String participant = iterator.next();
            if (name.equalsIgnoreCase(participant)) {
                iterator.remove();
                System.out.println("\t\t\t\tParticipant Removed Successfully");
            }
        }
    }

    public List<String> getParticipants() {
        return participants;
    }
    
    public void setNotes(String notes) {
    	this.notes=notes;
    }
    
    public String getNotes() {
    	return notes;
    }   
    public boolean isValid() {
        // Check that all required fields (e.g., topic, date, time) are not empty
        if (topic != null && !topic.isEmpty() &&
            date != null && !date.isEmpty() &&
            time != null && !time.isEmpty()) {
            return true;
        }
        return false;
    }
}


class Schedule {
     List<Meeting> meetings;
     static String yellow="\u001B[33m";
     static String white="\u001B[37m";
     static String green="\u001B[32m";
 

    Schedule() {
        this.meetings = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void displayMeetingDetails() {
        for (Meeting meeting : meetings) {
            System.out.println(white+"\n\t\t\t\tMeeting Topic :  " + meeting.getTopic());
            System.out.println("\n\t\t\tMeeting Date :\t\t" + meeting.getDate());
            System.out.println("\t\t\tMeeting Time :\t\t" + meeting.getTime());
            System.out.println("\t\t\tMeeting Description :\t" + meeting.getDescription());
            System.out.println("\t\t\tMeeting Location :\t" +meeting.getLocation());
            System.out.println("\t\t\tIs Private :\t\t" + (meeting.getIsPrivate() ? "Yes" : "No"));
            
        }
    }    
    

    public void deleteMeeting(String topic) {
    
    	Iterator<Meeting> iterator = meetings.iterator();
        while (iterator.hasNext()) {
            Meeting meeting = iterator.next();
            if (meeting.getTopic().equalsIgnoreCase(topic)) {
                iterator.remove(); // remove the meeting from the list
                System.out.println("\t\t\t\tMeeting with topic '" + topic + "' has been deleted.");
                saveMeetingToFile();
                return; // Exit the method after deleting the meeting
            }
           
        }
        
        System.out.println("\t\t\t\tMeeting with topic '" + topic + "' not found.");
    }
    
    public void saveMeetingToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/lenovo/eclipse-workspace/paperpractice/src/paperpractice/meetings.txt", false))) {
        	for(Meeting m:meetings) {
            bw.write("Topic: " + m.getTopic());
            bw.newLine();
            bw.write("Date: " + m.getDate());
            bw.newLine();
            bw.write("Time: " + m.getTime());
            bw.newLine();
            bw.write("Location: " + m.getLocation());
            bw.newLine();
            bw.write("Description: " + m.getDescription());
            bw.flush();
            bw.newLine();
            bw.newLine(); // Add an empty line to separate meetings
        }
        }catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving meetings: " + e.getMessage());
        }
    }
    
    public void loadMeetings(List<Meeting> meetings) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/lenovo/eclipse-workspace/paperpractice/src/paperpractice/meetings.txt"))) {
            String line;
            
            Meeting currentMeeting = new Meeting(); 
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Topic: ")) {
                    currentMeeting.setTopic(line.substring("Topic: ".length())); // Extract and set the topic
                } else if (line.startsWith("Date: ")) {
                    currentMeeting.setDate(line.substring("Date: ".length())); // Extract and set the date
                } else if (line.startsWith("Time: ")) {
                    currentMeeting.setTime(line.substring("Time: ".length())); // Extract and set the time
                } else if (line.startsWith("Location: ")) {
                    currentMeeting.setLocation(line.substring("Location: ".length())); // Extract and set the location
                } else if (line.startsWith("Description: ")) {
                    currentMeeting.setDescription(line.substring("Description: ".length())); // Extract and set the description
                } else if (line.isEmpty()) {
                    if (currentMeeting.isValid()) { // Check if the meeting is valid (e.g., all required fields are set)
                        meetings.add(currentMeeting); // Add the meeting to the list
                    }
                    currentMeeting = new Meeting(); // Start a new meeting object for the next entry
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error Loading Meetings");
        }
    }
    
    
}



class User {
    private String Apassword ="admin123";
    static String yellow="\u001B[33m";
    static String white="\u001B[37m";
    static String green="\u001B[32m";

    public boolean isAdmin(String password) {
        return this.Apassword.equals(password);
  
    }

    public void menu() {
        System.out.println("\n\t\t\t\t"+yellow+"1."+white+"  List All Meetings");
        System.out.println("\t\t\t\t"+yellow+"2."+white+"  Show Participants ");
        System.out.println("\t\t\t\t"+yellow+"3."+white+"  Find Meeting by Topic");
        System.out.println("\t\t\t\t"+yellow+"4."+white+"  Exit");
    }
}

class Admin extends User {
    public void menu() {
    	 System.out.println("\n\t\t\t\t"+yellow+"1."+white+"  Schedule Meeting");
         System.out.println("\t\t\t\t"+yellow+"2."+white+"  List All Meetings");
         System.out.println("\t\t\t\t"+yellow+"3."+white+"  Show Participants");
         System.out.println("\t\t\t\t"+yellow+"4."+white+"  Find Meeting by Topic");
         System.out.println("\t\t\t\t"+yellow+"5."+white+"  Delete Meeting");
         System.out.println("\t\t\t\t"+yellow+"6."+white+"  Exit");
    }
    
    
}


class MeetingScheduler extends Thread {
	
   
        static Schedule s = new Schedule();
        static Meeting meeting=new Meeting();
        static Scanner scan = new Scanner(System.in);
        static User user=new User();
        static User admin =new Admin();
        static String yellow="\u001B[33m";
        static String white="\u001B[37m";
        static String green="\u001B[32m";
        
       
        public void run() {
        System.out.println("\t\t\t  *******************************************");
        System.out.println("\t\t\t  *"+yellow+"     Welcome to the Meeting Scheduler    "+white+"*");
        System.out.println("\t\t\t  *******************************************");
        System.out.println();

        System.out.print("\t\t\t\t Enter Your Name: "+yellow);
        String name = scan.nextLine();

        System.out.print("\n\t\t\t\t"+white+" Enter Password: "+yellow);
        String password = scan.nextLine();

        if (admin.isAdmin(password)) {
            System.out.println("\n\t\t\t\t"+white+"    Welcome Admin!");
            adminMenu();
        } 
        
        else {
            System.out.println("\n\t\t\t\t"+white+"   Welcome Regular User");
            userMenu();
        }
    }

    private static void userMenu() {
        User user = new User();
        int choice;

        do {
            user.menu();
            System.out.print("\t\t\tEnter Your Choice: "+yellow);
            choice = scan.nextInt();
            scan.nextLine(); 

            switch (choice) {
                case 1:
                	s.meetings.clear();
                	s.loadMeetings(s.meetings);
                    s.displayMeetingDetails();
                    break;
                case 2:
                	showParticipants();
                    break;
                case 3:
                	 System.out.print(white+"\t\t\tEnter Meeting Topic: "+yellow);
                   	 String topic=scan.nextLine();
                   	 Meeting foundMeeting=findMeeting(topic);
                   	 if(foundMeeting!=null) {
                   		 System.out.println(white+"\n\t\t\t\tMeeting Topic :  " + foundMeeting.getTopic());
                   		 System.out.println(white+"\n\t\t\tMeeting Date :\t\t" + foundMeeting.getDate());
                   		 System.out.println(white+"\t\t\t\tMeeting Time :\t\t" + foundMeeting.getTime());
                   		 System.out.println(white+"\t\t\t\tMeeting Location\t:  " + foundMeeting.getLocation());
                   		 System.out.println(white+"\t\t\t\tMeeting Description :  " + foundMeeting.getDescription());
                   		 System.out.println(white+"\t\t\t\tMeeting Time\t\t:  " + foundMeeting.getTime());
                   		 System.out.println(white+"\t\t\t\tIs Private :\t\t" + (foundMeeting.getIsPrivate() ? "Yes" : "No"));
                   	 }
            	         break;
                case 4:
                    System.out.println("\n\t\t\tThanks For Using Meeting Scheduler App. Goodbye!");
                    break;
                default:
                    System.out.println("\n\t\t\tInvalid Choice. Please try again.");
            }
        } while (choice != 3);
    }
    
    
   
    

    private static void adminMenu() {
        int choice;

        do {
            admin.menu();
            System.out.print("\t\t\tEnter Your Choice: "+yellow);
            choice = scan.nextInt();
            scan.nextLine();
            System.out.println(white);

            switch (choice) {
                case 1:
                    scheduleMeeting();
                    break;
                case 2:
                	s.meetings.clear();
                	s.loadMeetings(s.meetings);
                    s.displayMeetingDetails();
                    break;
                case 3:
                    showParticipants();
                    break;
                case 4:
                	System.out.print(white+"\t\t\tEnter Meeting Topic: "+yellow);
                  	 String topic=scan.nextLine();
                  	 Meeting foundMeeting=findMeeting(topic);
                  	 if(foundMeeting!=null) {
                  		 System.out.println(white+"\n\t\t\t\tMeeting Topic :  " + foundMeeting.getTopic());
                  		 System.out.println(white+"\n\t\t\tMeeting Date :\t\t" + foundMeeting.getDate());
                  		 System.out.println(white+"\t\t\t\tMeeting Time :\t\t" + foundMeeting.getTime());
                  		 System.out.println(white+"\t\t\t\tMeeting Location\t:  " + foundMeeting.getLocation());
                  		 System.out.println(white+"\t\t\t\tMeeting Description :  " + foundMeeting.getDescription());
                  		 System.out.println(white+"\t\t\t\tMeeting Time\t\t:  " + foundMeeting.getTime());
                  		 System.out.println(white+"\t\t\t\tIs Private :\t\t" + (foundMeeting.getIsPrivate() ? "Yes" : "No"));
                  	 }
           	         break;
                case 5:
                	System.out.print("\t\t\tEnter Topic Of Meeting To Delete: ");
                	String dMeeting=scan.nextLine();
                	s.deleteMeeting(dMeeting);
                	break;
                case 6:
                    System.out.println("\n\t\t\t"+yellow+"Thanks For Using Meeting Scheduler App. Goodbye!"+white);
                    break;
                default:
                    System.out.println("\n\t\t\t\t"+yellow+"Invalid Choice. Please try again."+white);
            }
        } while (choice != 6);
    }
    
    
    

    //Scheduling meeting
    private static void scheduleMeeting() {
        Meeting meeting = new Meeting();

        System.out.print("\t\t\tEnter Meeting Topic: "+yellow);
        meeting.setTopic(scan.nextLine());

        System.out.print("\t\t\t"+white+"Enter Meeting Date (YYYY-MM-DD): "+yellow);
        meeting.setDate(scan.nextLine());

        System.out.print("\t\t\t"+white+"Enter Meeting Time (HH:MM AM/PM): "+yellow);
        meeting.setTime(scan.nextLine());

        System.out.print("\t\t\t"+white+"Enter Location: "+yellow);
        meeting.setLocation(scan.nextLine());

        System.out.print("\t\t\t"+white+"Enter Description: "+yellow);
        meeting.setDescription(scan.nextLine());

        System.out.print("\t\t\t"+white+"Is the meeting private? (1 for Yes, 0 for No): "+yellow);
        int isPrivate = scan.nextInt();
        scan.nextLine();
        meeting.setIsPrivate(isPrivate == 1);
    
        System.out.print("\t\t\t"+white+"Enter Password (if private, otherwise press Enter): "+yellow);
        String password = scan.nextLine();
        meeting.setPassword(password);

        int choice;
        do {
            System.out.println("\n\t\t\t\t"+white+"****Participants:****");
            displayParticipants(meeting.getParticipants());

            System.out.println("\n\t\t\t\t"+yellow+"1."+white+" Add Participant");
            System.out.println("\t\t\t\t"+yellow+"2."+white+" Remove Participant");
            System.out.println("\t\t\t\t"+yellow+"3."+white+" Done");
            System.out.print("\t\t\tEnter Your Choice: "+yellow);
            choice = scan.nextInt();
            scan.nextLine(); 

            if (choice == 1) {
                System.out.print("\n\t\t\t"+white+"Enter Name Of Participant: "+yellow);
                String name = scan.nextLine();
                meeting.addParticipant(name);
                System.out.println("\n\t\t\tParticipant Added Successfully!");
            } else if (choice == 2) {
                System.out.print("\n\t\t\t"+white+"Enter Name Of Participant: "+yellow);
                String name = scan.nextLine();
                meeting.removeParticipant(name);
            } else if (choice != 3) {
                System.out.println("\t\t\t\t"+white+"Invalid Choice");
            }
        } while (choice != 3);

        s.addMeeting(meeting);
        s.saveMeetingToFile();
        System.out.println(yellow+"\n\t\t\t\tMeeting scheduled successfully!"+white);
    }

    
    //Display Participants (6 names in one line)Logic
    private static void displayParticipants(List<String> participants) {
        int namesPerLine = 6;
        int count = 0;
        
        for (String participant : participants) {
            System.out.print("\t"+participant + "\t");
            count++;
            if (count == namesPerLine) {
                System.out.println();
                count = 0;
            }
        }
        System.out.println();
    }

  
    private static void showParticipants() {
        System.out.print("\n\t\t\t"+white+"Enter Meeting Topic: "+yellow);
        String topic = scan.nextLine();
        Meeting meeting = findMeeting( topic);

        if (meeting != null) {
            System.out.println("\n\t\t\t\t"+white+"Participants for Meeting: " + topic);
            displayParticipants(meeting.getParticipants());
        } else {
            System.out.println("\n\t\t\t\tMeeting not found!");
        }
    }
    
    //Searching Meeting by topic
    private static Meeting findMeeting(String topic) {
        for (Meeting meeting : s.getMeetings()) {
            if (meeting.getTopic().equalsIgnoreCase(topic)) {
                return meeting;
            }
        }
        return null;
    }
}




public class Main{
	public static void main(String[] args) {
	   MeetingScheduler t1=new MeetingScheduler();
	   t1.start();
	
	}
	
}




