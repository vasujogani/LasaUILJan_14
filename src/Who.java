import java.io.File;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
class Who {

	private static Scanner input;

	public static void main(String[] args) throws Exception {
		input = new Scanner(new File("who.dat"));
		doIt();
	}

	//Do your code here
	private static void doIt() {
		//# of games to play
		int limit = nextIntLine();
		for (int i = 0; i < limit; i++) {
			//Instantiate a new person with the next line as the description
			Person person = new Person(input.nextLine());
			//# of questions
			int lines = nextIntLine();
			//Query each question
			for (int j = 0; j < lines; j++)
				System.out.println(person.query(input.nextLine()) ? "yes" : "no");
		}
	}

	//Return the next line as an int
	private static int nextIntLine() {
		return Integer.parseInt(input.nextLine());
	}

	//Person class with its attributes
	private static class Person {

		private final String name;
		private final String gender;
		private String hair;
		private String eye;
		private final boolean glass;
		private final boolean hat;

		//Constructor taking a person description
		Person(String description) {
			//Split description into each attribute
			String[] attributes = description.split(" ");
			//Translates attributes, all lowercase, or change to boolean
			name = attributes[0].toLowerCase();
			gender = attributes[1].equals("M") ? "male" : "female";
			hair = attributes[2];
			eye = attributes[3];
			glass = attributes[4].equals("Y");
			hat = attributes[5].equals("Y");
			switch (hair) {
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
			switch (eye) {
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

		//Return whether or not the query is true
		boolean query(String query) {
			//Split question into pieces
			String[] aQuery = query.split(" ");
			//Match question to each possibility
			if (query.contains("Is your person a "))
				return gender.equals(aQuery[4].toLowerCase().replaceAll("\\W", "")); //regex to strip non alphabetical characters
			if (query.contains("Is your person"))
				return name.equals(aQuery[3].toLowerCase().replaceAll("\\W", "")); //regex to strip non alphabetical characters
			if (query.contains("hair"))
				return hair.equals(aQuery[4].toLowerCase());
			if (query.contains("eyes"))
				return eye.equals(aQuery[4].toLowerCase());
			if (query.contains("glasses"))
				return glass;
			return query.contains("hat") && hat;
		}
	}
}