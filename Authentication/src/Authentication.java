/* This program is designed to prompt the user for a login and password, and if 
they correctly input a specific password and username it will display relevant
information for each given role.
*/

/*
import java.io.BufferedReader; // created for reading txt files
import java.util.Scanner; // allows for the use of a scanner further in the code
import java.io.FileReader; // created for reading txt files

public class Authentication { //creates the main class
    static boolean SuccessfulLogin = false;
    public static void main (String[] args) { 
        Scanner scnr = new Scanner(System.in); //creates the scanner
        String userChar = (""); //creates the variable that controlls the main loop
        while (!userChar.equals("Yes")) { //enters the loop
            System.out.print("Login? (Yes / No)"); //prompts the user for the ability to exit the progam
            String userChar2 = scnr.next();
            if (userChar2.equals("No")) { //allows the program to exit while in the loop
                System.out.println("Goodbye");
                System.exit(0); //leaves the loop
            }
            else if (userChar2.equals("Yes")) { //continutes the loop to the next stage
                try { //added to fix bug with reading txt file
                SuccessfulLogin = ValidateCredentials.login(); //boolean returned from ValidateCredentials
                System.out.println("Please enter a Username.");
                String userUserName = scnr.nextLine(); //inputs the username
                System.out.println("Please enter a Password.");
                String userPassword = scnr.nextLine(); //inputs the scanner
                    if (SuccessfulLogin = true) { //if the login boolean is true from credentials class the login credentials are valid
                        if (userUserName.equals(ValidateCredentials.CredentialsArray[1][1])) { //if the username matches the username form the txt file
                            System.out.println("Welcome zookeeper!");
                                Scanner txtfile = new Scanner(new BufferedReader(new FileReader("./zookeeper.txt"))); //loads message from specific txt file
                                while (txtfile.hasNextLine()) { //allows the reader to read the entire txt file
                                    System.out.println(txtfile.nextLine()); //prints the entire txt file (zookeeper)
                            }
                        }
                        if (userUserName.equals(ValidateCredentials.CredentialsArray[2][1])) {  //reference first loop for notes
                            System.out.println("Welcome administrator!");
                                Scanner txtfile = new Scanner(new BufferedReader(new FileReader("./admin.txt")));
                                while (txtfile.hasNextLine()) {
                                    System.out.println(txtfile.nextLine());
                            }
                        }
                        if (userUserName.equals(ValidateCredentials.CredentialsArray[3][1])) { //reference first loop for notes
                            System.out.println("Welcome administrator!");
                                Scanner txtfile = new Scanner(new BufferedReader(new FileReader("./veterinarian.txt"))); //scanes in the veterinarian.txt file
                                while (txtfile.hasNextLine()) {
                                    System.out.println(txtfile.nextLine());
                            }
                        }
                        if (userUserName.equals(ValidateCredentials.CredentialsArray[4][1])) { //refence first loop for notes
                            System.out.println("Welcome zookeeper!");
                                Scanner txtfile = new Scanner(new BufferedReader(new FileReader("./zookeeper.txt"))); //scans in the zookeeper.txt file
                                while (txtfile.hasNextLine()) {
                                    System.out.println(txtfile.nextLine());
                            }
                        }
                        if (userUserName.equals(ValidateCredentials.CredentialsArray[5][1])) { 
                            System.out.println("Welcome veterinarian!");
                                Scanner txtfile = new Scanner(new BufferedReader(new FileReader("./veterinarian.txt")));
                                while (txtfile.hasNextLine()) {
                                    System.out.println(txtfile.nextLine());
                            }
                        }
                        if (userUserName.equals(ValidateCredentials.CredentialsArray[6][1])) { 
                            System.out.println("Welcome veterinarian!");
                                Scanner txtfile = new Scanner(new BufferedReader(new FileReader("./veterinarian.txt")));
                                while (txtfile.hasNextLine()) {
                                    System.out.println(txtfile.nextLine());
                            }
                        }
                    }
                }
                
                catch (Exception e) { //added to fix bug with inputing txt file
                } 
            }
            else { //restarts the loop and directs the user for a correct input
            System.out.println("Please enter a acceptable answer!");
            userChar = userChar2; //restarts the loop to allow the user to log out if they decide 
            
            }
        }
    }
}
*/
import java.util.Scanner; // allows for the use of a scanner further in the code

public class Authentication { //creates the main class
    public static void main (String[] args) { 
        int counter = 0;
        Scanner scnr = new Scanner(System.in); //creates the scanner
        String userChar = (""); //creates the variable that controlls the main loop
        while (!userChar.equals("Yes")) { //enters the loop
            System.out.print("Login? (Yes / No)"); //prompts the user for the ability to exit the progam
            String userChar2 = scnr.next();
            if (userChar2.equals("No")) { //allows the program to exit while in the loop
                System.out.println("Goodbye");
                System.exit(0); //leaves the loop
            }
            else if (userChar2.equals("Yes")) { //continutes the loop to the next stage
                System.out.println("Please enter a Username.");
                String userUserName = scnr.next(); //inputs the username
                System.out.println("Please enter a Password.");
                String userPassword = scnr.next(); //inputs the scanner
                try{
                    while (counter < 3) {
                        ValidateCredentials password = new ValidateCredentials();
                        ValidateCredentials readfiles = new ValidateCredentials();
                    password.isCredentialsValid(userPassword, userPassword);
                    readfiles.readDataFiles(userUserName, userPassword);
                    Boolean valid = password.isCredentialsValid(userPassword, userUserName);
                    
                    System.out.println(readfiles);
                        if (valid.equals(true)) {
                            break;
                        }
                        else if (counter >= 3) {
                        System.exit(0);
                        }
                        else {
                            counter = counter + 1;
                        }
                    }
                }
                catch(Exception e){
                }
            }
            else { //restarts the loop and directs the user for a correct input
            System.out.println("Please enter a acceptable answer!");
            userChar = userChar2; //restarts the loop to allow the user to log out if they decide 
            }
        }
    //System.out.println(e.printStackTrace());
    }
}
