public class BookedEvent {
    Event event;
    Customer customer;
    OrganisingTeam team;
    String status;
    String date;
    String duration;

    BookedEvent(Event event, Customer customer, String date, String duration, String status){
        this.event = event;
        this.customer = customer;
        this.date = date;
        this.duration = duration;
        this.status = status;
    }

    void generateInvoice(){

    }
}
