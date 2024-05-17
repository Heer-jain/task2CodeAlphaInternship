import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Task2{
    private static Map<String, String> userCredentials = new HashMap<>();
    private static Map<String, Double> userBalances = new HashMap<>();

    public static void main(String[] args){

        // Pre-populated users
        userCredentials.put("user1", "password1");
        userCredentials.put("user2", "password2");
        userBalances.put("user1", 1000.0);
        userBalances.put("user2", 500.0);

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Simple Banking Application!");

        boolean authenticated = false;
        while (!authenticated){
            System.out.print("Do you have an existing account? (yes/no): ");
            String response = sc.next().toLowerCase();

            if(response.equals("no")){
                createNewAccount(sc);
            }
            else if(response.equals("yes")){
                authenticated = authenticateUser(sc);
            }
            else{
                System.out.println("Invalid option. Please type 'yes' or 'no'.");
            }
        }

        boolean running = true;
        while(running){
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");

            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit(sc);
                    break;
                case 3:
                    withdraw(sc);
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:                 
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createNewAccount(Scanner sc){
        System.out.print("Enter a new username: ");
        String newUsername = sc.next();

        while(userCredentials.containsKey(newUsername)){
            System.out.print("Username already exists. Enter a different username: ");
            newUsername = sc.next();
        }

        System.out.print("Enter a password: ");
        String newPassword = sc.next();

        userCredentials.put(newUsername, newPassword);
        userBalances.put(newUsername, 0.0);

        System.out.println("Account created successfully. Please log in with your new credentials.");
    }

    private static boolean authenticateUser(Scanner sc){
        System.out.print("Enter your username: ");
        String username = sc.next();

        System.out.print("Enter your password: ");
        String password = sc.next();

        if(userCredentials.containsKey(username) && userCredentials.get(username).equals(password)){
            System.out.println("Authentication sucessful. Welcome, " + username + "!");
            return true;
        }
        else{
            System.out.println("Invalid username and password.");
            return false;
        }
    }

    private static void checkBalance(){
        System.out.print("Enter your username: ");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();

        if(userBalances.containsKey(username)){
            System.out.println("Your balance is: Rs. " + userBalances.get(username));
        }
        else{
            System.out.println("Account not found.");
        }
    }

    private static void deposit(Scanner sc){
        System.out.print("Enter your username: ");
        String username = sc.next();

        if(userBalances.containsKey(username)){
            System.out.print("Enter amount to deposit: ");
            double amount = sc.nextDouble();
            userBalances.put(username, userBalances.get(username) + amount);
            System.out.println("Deposit successfully. New balance: Rs. " + userBalances.get(username));
        }
        else{
            System.out.println("Account not found.");
        }
    }

    private static void withdraw(Scanner sc){
        System.out.print("Enter your username: ");
        String username = sc.next();

        if(userBalances.containsKey(username)){
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            if(amount <= userBalances.get(username)){
                userBalances.put(username, userBalances.get(username) - amount);
                System.out.println("Withdrawal successfully. New balance: Rs. " + userBalances.get(username));
            }
            else{
                System.out.println("Insufficient funds.");
            }
        }
        else{
            System.out.println("Account not found.");
        }
    }
}