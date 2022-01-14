public class Event {
    String name;
    String description;
    double price;
    double minBudget;

    Event(String name, String description, double minBudget, double price){
        this.name = name;
        this.description = description;
        this.minBudget = minBudget;
        this.price = price;
    }
}
