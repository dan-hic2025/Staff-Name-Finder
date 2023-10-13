import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.util.Scanner;


public class NameFinder {
    public static void main (String[] args) throws IOException {
        System.out.println("Enter the individuals email id:");
        System.out.println(getName(getWebsite()));
    }


    private static String getName (URL givenWebsite) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(givenWebsite.openStream())); //creates a buffered reader object of which the raw text scraped from the website will be readable from
        String inputLine;
        String output = "";
        while ((inputLine = input.readLine()) != null) { //checking that the end of the website hasn't been found
            if (inputLine.contains("<title>")) { //website title contains the staff name
                if (inputLine.contains("People")) { //if the website has automatically redirected to have a title of "People" then the user does not exist
                    System.out.println("User has not been found!");
                } else {
                    int[] namePosition = {inputLine.indexOf(">"), inputLine.indexOf("|")}; //creates an array of size 2 containing the start and end of the string around the staffs name
                    output = inputLine.substring(namePosition[0] + 1, namePosition[1]); //uses substring and the namePosition array to create the output
                }
                break; //exit while loop after required data is found
            }
        }
        input.close();
        return output;
    }


    private static URL getWebsite () throws MalformedURLException {
        Scanner userInput = new Scanner(System.in); //creates a scanner object to enable to take a user input


        String id = userInput.nextLine(); //reads the users input

        return (new URL("https://www.ecs.soton.ac.uk/people/" + id)); //returns a URL object of the concatenated website based upon the user input
    }
}
