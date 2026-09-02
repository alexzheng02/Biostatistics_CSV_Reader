import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * driver class of PersonDataManager
 * creates a menu for managing statistics for persons
 */
public class PersonManager {
    /**
     * shows a menu and takes user input
     * @param args displays everything, takes user input
     */

    public static void main(String[] args){
        PersonDataManager manager = new PersonDataManager();
        Scanner input = new Scanner(System.in);

        System.out.println("Person Data Manager");

        boolean running = true;
        while(running){
            System.out.println("\n(i) import file");
            System.out.println("(a) add person");
            System.out.println("(r) remove person");
            System.out.println("(g) get person information");
            System.out.println("(p) print table");
            System.out.println("(s) save file");
            System.out.println("(q) quit");
            System.out.print("input command: ");

            String command = input.nextLine().trim();

            if (command.equalsIgnoreCase("i")){
                System.out.print("input CSV file path: ");
                String path = input.nextLine().trim();
                try{
                    manager.buildFromFile(path);
                }
                catch (FileNotFoundException e){
                    System.out.println("error:" + e.getMessage());

                } catch (IllegalArgumentException e) {
                    System.out.println("error:" + e.getMessage());
                }
            }
            else if (command.equalsIgnoreCase("a")){
                try{
                    System.out.print("enter name: ");
                    String name = input .nextLine().trim();

                    System.out.print("enter gender (m/f): ");
                    String gender = input.nextLine().trim().toUpperCase();
                    if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
                        System.out.println("Error: Gender must be M or F.");
                        continue;
                    }

                    System.out.print("Enter age: ");
                    int age = Integer.parseInt(input.nextLine());

                    System.out.print("enter height (in): ");
                    double height = Double.parseDouble(input.nextLine());

                    System.out.print("enter weight (lbs): ");
                    double weight = Double.parseDouble(input.nextLine());

                    Person p = new Person(name, gender, age, height, weight);
                    manager.addPerson(p);
                }
                catch(NumberFormatException e){
                    System.out.println("error, invalid number for age, height, or weight");
                }
                catch(PersonAlreadyExistsException e){
                    System.out.println("Error: " + e.getMessage());
                }

            }
            else if (command.equalsIgnoreCase("r")){
                System.out.print("enter name of person for removal: ");
                String name = input.nextLine().trim();
                try{
                    manager.removePerson(name);
                }
                catch(PersonDoesNotExistException e){
                    System.out.println("error: " + e.getMessage());
                }

            }
            else if (command.equalsIgnoreCase("g")){
                System.out.print("who are you looking for?: ");
                String name = input.nextLine().trim();
                try{
                    Person p = manager.getPerson(name);
                    System.out.println(p);
                }
                catch(PersonDoesNotExistException e){
                    System.out.println("error: " + e.getMessage());
                }

            }
            else if (command.equalsIgnoreCase("p")){
                manager.printTable();

            }
            else if (command.equalsIgnoreCase("s")){
                System.out.print("enter output file name: ");
                String filename = input.nextLine().trim();
                manager.saveToFile(filename);

            } else if (command.equalsIgnoreCase("q")){
                System.out.println("goodbye!");
                running = false;

            } else{
                System.out.println("Invalid command. Please try again.");
            }

        }
        input.close();
    }
}
