import java.io.File;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
public class Who {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("who.dat"));
        doIt(input);
    }

    public static void doIt(Scanner input) {
        //Do your code here
        int limit = input.nextInt();
        input.nextLine();
        for (int i = 0; i < limit; i++) {
            Person person = new Person(input.nextLine());
            int lines = input.nextInt();
            input.nextLine();
            for (int j = 0; j < lines; j++) {
                System.out.println(person.query(input.nextLine()) ? "yes" : "no");
            }
        }
    }
}
class Person{
    String name;
    String gender;
    String hair;
    String eye;
    boolean glass;
    boolean hat;

    public Person(String statement) {
        String[] attributes = statement.split(" ");
        name = attributes[0].toLowerCase();
        gender = attributes[1].equals("M") ? "male" : "female";
        hair = attributes[2];
        eye = attributes[3];
        glass = attributes[4].equals("Y");
        hat = attributes[5].equals("Y");

        switch (hair){
            case "W":
                hair = "white";
                break;
            case "BR":
                hair = "brown";
                break;
            case "BL":
                hair = "blonde";
                break;
            case "BLK":
                hair = "black";
                break;
            case "R":
                hair = "red";
                break;
        }

        switch (eye){
            case "BL":
                eye = "blue";
                break;
            case "BR":
                eye = "brown";
                break;
            case "G":
                eye = "green";
                break;
        }
    }

    public boolean query(String query){
        String[] aQuery = query.split(" ");
        if(query.contains("Is your person a "))
            return gender.equals(aQuery[4].toLowerCase().replaceAll("\\W",""));
        if(query.contains("Is your person"))
            return name.equals(aQuery[3].toLowerCase().replaceAll("\\W", ""));
        if(query.contains("hair"))
            return hair.equals(aQuery[4].toLowerCase());
        if(query.contains("eyes"))
            return eye.equals(aQuery[4].toLowerCase());
        if(query.contains("glasses"))
            return glass;
        if(query.contains("hat"))
            return hat;
        return false;
    }
}