/* This includes all of the imports requried to have tools avalible for further
coding, and imports the method from MD5Digest to allow for password hasing
*/

/* import java.security.MessageDigest; //all of the required imports for the tools
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class ValidateCredentials { //this class containts the password hash

	public static String hashPassword(String password) throws Exception {
      //Copy and paste this section of code
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
                StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
   return sb.toString(); //returns the password after it's been encrypted
	}
    static boolean loginSuccess = false; //used to determine if credentials are vaild
    static String userUserName = ""; //created the variable to store the username
    static String userPassword = ""; //created the variable to store the password
    static int counter = 0; //limits the user to three attempts
    static Scanner scnr = new Scanner(System.in); //adds a scanner to allow the user for input
    public static boolean login() {
        try {
        ValidateCredentials.Array(); // stores the values from credentials.txt for later use
        String hashedPassword = ValidateCredentials.hashPassword(userPassword);
            while (counter <= 3) {
                if ((hashedPassword.equals(CredentialsArray[1][2])) && (userUserName.equals(CredentialsArray[1][1]))) {
                    loginSuccess = true;
                    return loginSuccess;
                }
                else if ((hashedPassword.equals(CredentialsArray[2][2])) && (userUserName.equals(CredentialsArray[2][1]))) {
                    loginSuccess = true;
                    return loginSuccess;
                }
                else if ((hashedPassword.equals(CredentialsArray[3][2])) && (userUserName.equals(CredentialsArray[3][1]))) {
                    loginSuccess = true;
                    return loginSuccess;
                }
                else if ((hashedPassword.equals(CredentialsArray[4][2])) && (userUserName.equals(CredentialsArray[4][1]))) {
                    loginSuccess = true;
                    return loginSuccess;
                }
                else if ((hashedPassword.equals(CredentialsArray[5][2])) && (userUserName.equals(CredentialsArray[5][1]))) {
                    loginSuccess = true;
                    return loginSuccess;
                }
                else if ((hashedPassword.equals(CredentialsArray[6][2])) && (userUserName.equals(CredentialsArray[6][1]))) {
                    loginSuccess = true;
                    return loginSuccess;
                }
                else {
                    counter = counter + 1; 
                }
        }
        }
        catch (Exception e) {
        }
        return loginSuccess; // returns the boolean 
    }
    static String CredentialsArray[][]; //creates the array
    static String[][] Array() {
        CredentialsArray = new String[6][4];
        int Rowc = 0;
        int Row = 0;
        int Colc = 0;
        int Col = 0;
        double xnum = 0;
        
        try {
            Scanner scanIn = new Scanner(new BufferedReader(new FileReader("./credentials.txt"))); // reads values from credentials.txt
            while (scanIn.hasNextLine()) { //commands the code to read all values from credentials.txt
                String InputLine = scanIn.nextLine();
                String[] InArray = InputLine.split(" ");
                for (int x = 0; x < InArray.length; x++) {
                    CredentialsArray[Rowc][x] = (InArray[x]);
                }
                Rowc++;
            }
        }
        catch (Exception e) {
        }
    return new String[6][4]; //returns the value of the array
    }
} */

import java.security.MessageDigest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ValidateCredentials {

    private boolean isValid;
    private String filePath;
    private String credentialsFileName;

    public ValidateCredentials() {
       /* Note: 
          
          If you place your .txt datafiles on the same
          level as your .java files are at then you will
          not need to specify a 'filePath' like:
          
          filePath = "C:\\Users\\...\\Authentication\\";
       */
        filePath = "";
        
        isValid = false;
        credentialsFileName = "credentials";
    }

    public boolean isCredentialsValid(String userName, String passWord) throws Exception {
        String original = passWord;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        System.out.println("");
        System.out.println("original:" + original);
        System.out.println("digested:" + sb.toString()); //sb.toString() is what you'll need to compare password strings

        isValid = readDataFiles(userName, sb.toString());

        return isValid;
    }

    public boolean readDataFiles(String userName, String passWord) throws IOException {
        FileInputStream fileByteStream1 = null; // File input stream
        FileInputStream fileByteStream2 = null; // File input stream

        Scanner inFS1 = null;                   // Scanner object
        Scanner inFS2 = null;                   // Scanner object

        String textLine = null;
        String textFileName = null;
        
        boolean foundCredentials = false;

        // Try to open file
        System.out.println("");
        System.out.println("Opening file " + credentialsFileName + ".txt");
        fileByteStream1 = new FileInputStream(filePath + "credentials.txt");
        inFS1 = new Scanner(fileByteStream1);

        System.out.println("");
        System.out.println("Reading lines of text.");

        while (inFS1.hasNextLine()) {
            textLine = inFS1.nextLine();
            //System.out.println(textLine);

            String[] words = textLine.split("\\s");//splits the string based on whitespace

            if (words[0].equals(userName) && textLine.contains(passWord)) {
                foundCredentials = true;
                int last = words.length - 1;
                textFileName = words[last];
                break;
            }
        }

        // Done with file, so try to close it
        System.out.println("");
        System.out.println("Closing file " + credentialsFileName + ".txt");

        if (textLine != null) {
            fileByteStream1.close(); // close() may throw IOException if fails
        }

        if (foundCredentials == true) {
            // Try to open file
            System.out.println("");
            System.out.println("Opening file " + textFileName + ".txt");
            
            fileByteStream2 = new FileInputStream(filePath + textFileName + ".txt");
            inFS2 = new Scanner(fileByteStream2);

            System.out.println("");

            while (inFS2.hasNextLine()) {
                textLine = inFS2.nextLine();
                System.out.println(textLine);
            }

            // Done with file, so try to close it
            System.out.println("");
            System.out.println("Closing file " + textFileName + ".txt");

            if (textLine != null) {
                fileByteStream2.close(); // close() may throw IOException if fails
            }
        }

        return foundCredentials;
    }

}
