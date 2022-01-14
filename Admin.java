import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    String email;
    private String password;
    ArrayList<Event> eventList;
    ArrayList<BookedEvent> eventRequestList,eventScheduledList;
    Scanner input = new Scanner(System.in);

    Admin(){
        email = "admin@gmail.com";
        password = "12345";
        eventList = new ArrayList<Event>();
        eventRequestList = new ArrayList<BookedEvent>();
        eventScheduledList = new ArrayList<BookedEvent>();
        eventList.add(new Event("Wedding", "A wedding ceremony consists of many events. They are starting from wedding rehearsal dinner, wedding to wedding reception. All these involve guests, friends and family.", 5000,300000));
        eventList.add(new Event("Birthday", "Whether you are celebrating a kid's birthday party, Sweet 16, surprise party, or milestone birthday, we are here to organize the best birthdays for you.", 5000,30000));
        eventList.add(new Event("Themed Party", "Everyone loves a themed party! From a Bookworm Bash to a Jewel Heist Happy Hour, themed events allow our imaginations to run wild.", 5000,30000));
    }

    void line(){
        System.out.println("\n-----------------------------------------------------------\n");
    }

    public String getPassword() {
        return password;
    }

    void viewEvents(){
        line();
        for(int i=0; i<eventList.size(); i++){
            System.out.println(eventList.get(i).name);
            System.out.println("\tID: "+(i+1));
            System.out.println("\tDescription: "+eventList.get(i).description);
            System.out.println("\tBooking Amount: Rs. "+eventList.get(i).price);
            System.out.println("\tAverage Budget: Rs. "+eventList.get(i).minBudget+"\n\n");
        }
    }
    
    void addEvent(){
        line();
        while(true){
            try{
                System.out.print("Event Name: ");
                String name = input.nextLine();
                System.out.print("Event Description: ");
                String description = input.nextLine();
                System.out.print("Event Booking Price: ");
                double price = Double.parseDouble(input.nextLine());
                System.out.print("Minimum Required Budget: ");
                double budget = Double.parseDouble(input.nextLine());
                eventList.add(new Event(name, description, budget, price));
                System.out.println("\nEvent added Successfully!");
                line();
                break;
            }catch(NumberFormatException e){
                System.out.println("Wrong Inputs! Enter again.");
            }
        }

        
    }

    void removeEvent(int i){
        eventList.remove(i);
        System.out.println("\nEvent removed Successfully!");
        line();
    }

    void eventRequests(){
        line();
        if(eventRequestList.size()==0){
            System.out.println("No Requests Currently\n");
            return;
        }
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("|\tID\t|\tCustomer Name\t|\tEvent Name\t|\tEvent Date\t|\tDuration\t|");
        System.out.println("----------------------------------------------------------------------------------------------");
        for(int i=0; i<eventRequestList.size(); i++){
            System.out.printf("|\t%d\t|\t%s\t|\t%s\t\t|\t%s\t|\t%s\t|\n",(i+1),eventRequestList.get(i).customer.getName(),eventRequestList.get(i).event.name,eventRequestList.get(i).date,eventRequestList.get(i).duration);
        }
        System.out.println("----------------------------------------------------------------------------------------------");

        while(true){
            try{
                System.out.println("\n\nEnter 1 to confirm any event OR 0 to Go Back");
                int choice = Integer.parseInt(input.nextLine());
                if(choice==1){
                    System.out.println("Enter Event ID: ");
                    int id = Integer.parseInt(input.nextLine());
                    while(true){
                        if(id > 0 && id <= eventRequestList.size()){
                            eventRequestList.get(id-1).status = "Approved";
                            for(int i=0; i<eventRequestList.get(id-1).customer.bookedEvents.size(); i++){
                                if(eventRequestList.get(id-1) == eventRequestList.get(id-1).customer.bookedEvents.get(i)){
                                    eventRequestList.get(id-1).customer.bookedEvents.get(i).status = "Approved";
                                }
                            }
                            eventScheduledList.add(eventRequestList.get(id-1));
                            eventRequestList.remove(id-1);
                            break;
                        }else{
                            System.out.print("Wrong ID!\nEnter correct ID or 0 to EXIT");
                            id = input.nextInt();
                            if(id == 0){
                                break;
                            }
                        }
                    }
                    break;
                }else if(choice == 0){
                    return;
                }
            }catch(NumberFormatException e){
                
            }
        }
    }

    void assignEvents(){

    }

    void onGoingEvents(){
        line();
        if(eventScheduledList.size()==0){
            System.out.println("No Ongoing Events Currently\n");
            return;
        }
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("|\tID\t|\tCustomer Name\t|\tEvent Name\t|\tEvent Date\t|\tDuration\t|");
        System.out.println("----------------------------------------------------------------------------------------------");
        for(int i=0; i<eventScheduledList.size(); i++){
            System.out.printf("|\t%d\t|\t%s\t|\t%s\t\t|\t%s\t|\t%s\t|\n",(i+1),eventScheduledList.get(i).customer.getName(),eventScheduledList.get(i).event.name,eventScheduledList.get(i).date,eventScheduledList.get(i).duration);
        }
        System.out.println("----------------------------------------------------------------------------------------------");
    }

    void updateEvents(BookedEvent event){
        for(int i=0; i<eventRequestList.size(); i++){
            if(eventRequestList.get(i) == event){
                eventRequestList.remove(i);
            }
        }
        for(int i=0; i<eventScheduledList.size(); i++){
            if(eventScheduledList.get(i) == event){
                eventScheduledList.remove(i);
            }
        }
    }
    void payment(){

    }
}
