import java.util.ArrayList;

public class Customer{
    private String name;
    private String email;
    private String password;
    private String phno;
    ArrayList<BookedEvent> bookedEvents;

    Customer(String name, String email, String password, String phno){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phno = phno;
        bookedEvents = new ArrayList<BookedEvent>();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhno() {
        return phno;
    }

    void displayBookedEvents(){
        if(bookedEvents.size()==0){
            System.out.println("You have not booked any event. Go to 'View Events' to book.\n");
            return;
        }
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.printf("|\t%s\t|\t%s\t|\t%s\t|\t%s\t\t|\n","ID","Event Name","Event Date","Status");
        System.out.println("----------------------------------------------------------------------------------------------");
        for(int i=0; i<bookedEvents.size(); i++){
            System.out.printf("|\t%d\t|\t%s\t\t|\t%s\t|\t%s\t|\n",(i+1),bookedEvents.get(i).event.name,bookedEvents.get(i).date,bookedEvents.get(i).status);
        }
        System.out.println("----------------------------------------------------------------------------------------------");
    }
}