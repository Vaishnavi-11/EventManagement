import java.util.HashMap;
import java.util.Scanner;

public class Authentication {
    HashMap<String,Customer>  registeredUsers;
    Admin admin;
    Scanner input = new Scanner(System.in);

    Authentication(){
        registeredUsers = new HashMap<String,Customer>();
        admin = new Admin();
    }

    Customer authenticateCustomer(){
        int choice;
        do{
            System.out.println("Enter 1 is you are a new user\nEnter 2 if you are an existing user\nEnter 3 to GO BACK\n");
            choice = Integer.parseInt(input.nextLine());
            if(choice == 1){
                String email,password,name,phno;
                System.out.print("Enter Email ID: ");
                email = input.nextLine();
                System.out.print("Enter Name: ");
                name = input.nextLine();
                System.out.print("Enter Password: ");
                password = input.nextLine();
                System.out.print("Enter Phone Number: ");
                phno = input.nextLine();

                if(!registeredUsers.containsKey(email)){
                    registeredUsers.put(email, new Customer(name, email, password, phno));
                    return registeredUsers.get(email);
                }else{
                    System.out.println("User Already Exists\n");
                    choice = 0;
                }
            }else if(choice == 2){
                int i = 0;
                while(i<3){
                    System.out.print("E-mail ID: ");
                    String email = input.nextLine();
                    System.out.print("Password: ");
                    String password = input.nextLine();
        
                    if(registeredUsers.containsKey(email) && password.equals(registeredUsers.get(email).getPassword())){
                        System.out.println("Successful Login!\n");
                        return registeredUsers.get(email);
                    }
                    System.out.println("Invalid Credentials!Try Again\n");
                    i++;
                }
                System.out.println("Exceeded Number of tries. Try Again Later!\n");
                return null;
            }else if(choice == 3){
               return null;
            }else{
                System.out.println("Invalid Operation!\n");
                choice = input.nextInt();
            }
        }while(choice!=1 || choice!=2 || choice!=3);
        return null;   
    }

    Admin authenticateAdmin(){
        int i = 0;
        while(i<3){
            System.out.print("E-mail ID: ");
            String email = input.nextLine();
            System.out.print("Password: ");
            String password = input.nextLine();

            if(email.equals(admin.email) && password.equals(admin.getPassword())){
                System.out.println("Successful Login!\n");
                return admin;
            }
            System.out.println("Invalid Credentials!Try Again\n");
            i++;
        }
        System.out.println("Exceeded Number of tries. Try Again Later!\n");
        return null;
    }

    // void updateCustomer(Customer customer){

    // }

}
