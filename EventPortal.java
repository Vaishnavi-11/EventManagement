import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class EventPortal{

    Authentication authenticate;
    Customer customer;
    Admin admin;
    Scanner input = new Scanner(System.in);

    EventPortal(){
        authenticate = new Authentication();
        admin = authenticate.admin;
        dashboard();
    }

    void line(){
        System.out.println("\n-----------------------------------------------------------\n");
    }

    void dashboard(){
        int choice=0;

        line();
        System.out.println("Welcome to the Event Management Portal!!");
        do{
            line();
            System.out.println("\n\n => Press 1 if you are a client\n => Press 2 if you are the admin\n => Press 3 if you an employee\n => Press 4 to Exit\n");
            try{
                choice = Integer.parseInt(input.nextLine());
                switch(choice){
                    case 1:
                    customer = authenticate.authenticateCustomer();
                    if(customer!=null){
                        customerPortal();
                    }
                    break;

                    case 2:
                    admin = authenticate.authenticateAdmin();
                    if(admin!=null){
                        adminPortal();
                    }
                    break;

                    case 3:
                    System.out.println("Feature will be added soon!");
                    break;

                    case 4:
                    line();
                    System.out.println("\t\tTHANK YOU");
                    line();
                    break;

                    default:
                    System.out.println("Wrong Input! Enter the right choice:");
                }
            }catch(NumberFormatException e){
                System.out.println("Wrong Input! Enter the right choice:");
            }
        }while(choice!=4);
    }

    void adminPortal(){
        line();
        System.out.println("User: Admin");
        int choice=0;
        do{
            try{
                System.out.println("\n\nWhat do you wanna do?");
                System.out.println("\t1. View Events\n\t2. Add Events\n\t3. Remove Events\n\t4. Check Event Requests\n\t5. Check Ongoing Events\n\t6. View Team\n\t7. Log Out\n");
                choice = Integer.parseInt(input.nextLine());
                switch(choice){
                    case 1:
                    admin.viewEvents();
                    break;
                    
                    case 2:
                    admin.addEvent();
                    break;
                    
                    case 3:
                    System.out.print("Enter Event ID to be removed: ");
                    int i = input.nextInt();
                    admin.removeEvent(i-1);
                    break;
                    
                    case 4:
                    admin.eventRequests();
                    break;
                    
                    case 5:
                    admin.onGoingEvents();
                    break;
                    
                    case 6:
                    System.out.println("Feature will be available soon!\n");
                    break;
                    
                    case 7:
                    System.out.println("Logged Out\n");
                    authenticate.admin = admin;
                    break;
                    
                    default:
                    System.out.println("Wrong Choice!");
                }
            }catch(NumberFormatException e){
                System.out.println("Wrong Choice!");
            }
        }while(choice!=7);
    }

    void customerPortal(){
        line();
        System.out.println("User: " + customer.getName());
        int choice=0;
        do{
            try{
                System.out.println("\n\nWhat do you wanna do?");
                System.out.println("\t1. View Events\n\t2. Check Booked Events\n\t3. Cancel Booking\n\t4. Logout\n");
                choice = Integer.parseInt(input.nextLine());
                switch(choice){
                    case 1:
                    viewEvents();
                    break;

                    case 2:
                    checkEventStatus();
                    break;

                    case 3:
                    cancelEvent();
                    break;

                    case 4:
                    System.out.println("Logged Out\n");
                    authenticate.registeredUsers.put(customer.getEmail(),customer);
                    break;

                    default:
                    System.out.println("Wrong Choice!");
                }
            }catch(NumberFormatException e){
                System.out.println("Wrong Choice!");
            }
        }while(choice!=4);
    }

    void viewEvents(){
        line();
        System.out.println("Events Available :-\n\n");
        for(int i=0; i<admin.eventList.size(); i++){
            System.out.println(admin.eventList.get(i).name);
            System.out.println("\tID: "+(i+1));
            System.out.println("\tDescription: "+admin.eventList.get(i).description);
            System.out.println("\tBooking Amount: Rs. "+admin.eventList.get(i).price);
            System.out.println("\tAverage Budget: Rs. "+admin.eventList.get(i).minBudget+"\n\n");
        }
        System.out.println("\n\nEnter 1 to book any event OR 0 to Go Back");
        int choice = Integer.parseInt(input.nextLine());
        if(choice==1){
            System.out.print("Enter Event ID: ");
            int id = Integer.parseInt(input.nextLine());
            while(true){
                try{
                    if(id > 0 && id <= admin.eventList.size()){
                        System.out.print("Enter Event date (DD/MM/YYYY): ");
                        String date = input.nextLine();
                        System.out.print("Enter Event duration: ");
                        String duration = input.nextLine();
                        DateFormat sdfrmt = new SimpleDateFormat("dd/mm/yyyy");
	                    sdfrmt.setLenient(false);
                        Date eDate = sdfrmt.parse(date);
                        Date today = sdfrmt.parse(sdfrmt.format(new Date()));
                        if(Integer.parseInt(duration)<=0 || date.equals("") || eDate.compareTo(today)>0){
                            System.out.println("Invalid Event Details Provided! Enter again.\n");
                            continue;
                        }
                        BookedEvent book = new BookedEvent(admin.eventList.get(id-1),customer,date,duration,"Requested");
                        customer.bookedEvents.add(book);
                        admin.eventRequestList.add(book);
                        break;
                    }else{
                        System.out.println("Wrong ID!\nEnter correct ID or 0 to EXIT");
                        id = input.nextInt();
                        if(id == 0){
                            break;
                        }
                    }
                }catch(NumberFormatException e){
                        System.out.println("Invalid Event Details Provided! Enter again.\n");
                }catch(ParseException e){
                    System.out.println("Invalid Event Details Provided! Enter again.\n");
                }
            }
        }else if(choice == 0){
            return;
        }
    }

    void checkEventStatus(){
        line();
        customer.displayBookedEvents();
    }

    void cancelEvent(){
        System.out.print("Enter Event ID: ");
        int id = Integer.parseInt(input.nextLine());
        BookedEvent event = customer.bookedEvents.get(id-1);
        admin.updateEvents(event);
        customer.bookedEvents.remove(id-1);
        System.out.println("Event Cancelled Successfully!");
    }

    public static void main(String[] args) {
        new EventPortal();
    }
}