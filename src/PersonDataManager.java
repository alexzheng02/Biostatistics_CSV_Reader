import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * takes an array of Person objects through file input.
 * objects can be added, removed, looked up, and printed. File can be output
 * There is no ArrayList.
 */

public class PersonDataManager {
    private Person[] people;
    private int size;

    /**
     * No-Argument Constructor for an empty PersonDataManager
     */
    public PersonDataManager(){
        people = new Person[10];
        size = 0;
    }

    /**
     * makes sure the array has enough space for at least one more element;
     * array is doubled in size if necessary
     */
    public void ensureCapacity(){
        if(size >= people.length){
            Person[] P1 = new Person [people.length * 2];
            for (int i = 0; i<size; i++){
                P1[i] = people[i];
            }
            people = P1;
        }
    }

    /**
     * Reads data from Person objects in CSV file and records it
     * Skips the example row and removes quotes.
     *
     * @param location where the CSV file is
     * @throws IllegalArgumentException if a row is formatted wrong
     * @throws FileNotFoundException if the file can't be found
     */
    public void buildFromFile(String location) throws IllegalArgumentException, FileNotFoundException {
        people = new Person[10];
        size = 0;

        File file = new File(location);
        if(!file.exists()){
            throw new FileNotFoundException("File not found: " + location);
        }

        //skips header row
        Scanner scanner = new Scanner(file);
        if(scanner.hasNextLine()){
            scanner.nextLine();
        }

        while(scanner.hasNextLine()){
            String line = scanner.nextLine().trim();
            if(line.isEmpty()){
                continue;
            }

            //divides line into parts by the commas
            String[] parts = line.split(",");

            if(parts.length < 5){
                throw new IllegalArgumentException("Invalid row: " + line);

            }

            //assigns name to each part of a row and removes big spaces
            String name = parts[0].trim().replace("\"", "");
            String gender = parts[1].trim().replace("\"", "");
            String ageStr = parts[2].trim().replace("\"", "");
            String heightStr = parts[3].trim().replace("\"", "");
            String weightStr = parts[4].trim().replace("\"", "");

            //changes values to ints and doubles
            int age;
            double height;
            double weight;
            try{
                age = Integer.parseInt(ageStr);
                height = Double.parseDouble(heightStr);
                weight = Double.parseDouble(weightStr);
            }
            catch(NumberFormatException e){
                throw new IllegalArgumentException("Invalid number in row: " + line);
            }

            // creates person object for the data
            Person newP = new Person(name,gender,age,height,weight);
            ensureCapacity();
            people[size] = newP;
            size++;

        }
        scanner.close();
        System.out.println(size + "people loaded.");
    }

    /**
     * Adds new Person to manager
     *
     * @param nPerson the new person
     * @throws PersonAlreadyExistsException if a person with the same name already exists
     */
    public void addPerson(Person nPerson) throws PersonAlreadyExistsException {
        for (int i=0; i<size; i++){
            if(people[i].getName().equalsIgnoreCase(nPerson.getName())){
                throw new PersonAlreadyExistsException(nPerson.getName());
            }
        }
        ensureCapacity();
        people[size] = nPerson;
        size++;
        System.out.println(nPerson.getName() + " added.");
    }

    /**
     * retrieves a person by name
     * @param name name of person we're looking for
     * @return returns person information
     * @throws PersonDoesNotExistException if person does not exist, throw this
     */
    public Person getPerson(String name) throws PersonDoesNotExistException{
        for (int i=0; i<size; i++){
            if(people[i].getName().equalsIgnoreCase(name)){
                return people[i];
            }
        }
        throw new PersonDoesNotExistException(name);
    }

    /**
     * removes a person by name and moves all remaining elements to the left
     * @param name takes name of a person on the file to remove
     * @throws PersonDoesNotExistException if person does not exist, throw this
     */
    public void removePerson(String name) throws PersonDoesNotExistException{
        int index = -1;
        for(int i=0; i<size; i++){
            if(people[i].getName().equalsIgnoreCase(name)){
                index = i;
                break;
            }
        }
        if (index == -1){
            throw new PersonDoesNotExistException(name);
        }

        // moves elements to the left
        for(int i=index; i<size-1; i++){
            people[i] = people[i+1];
        }
        people[size] = null;
        size--;
        System.out.println(name + " removed.");
    }

    /**
     * prints all people in a table format
     */
    public void printTable(){
        if(size == 0){
            System.out.println("none to display.");
            return;
        }
        System.out.println(String.format("%-15s %-8s %-6s %-12s %-12s", "Name", "Gender", "Age", "Height(in)", "Weight(lbs)"));
        System.out.println("-".repeat(57));
        for(int i=0; i<size; i++){
            System.out.println(people[i]);
        }
    }

    /**
     * saves all people to a CSV file
     * @param filename name of the file we're using
     */
    public void saveToFile(String filename){
        try{
            FileWriter writer = new FileWriter(filename);

            writer.write("Name, Sex, Age, Height(in), Weight(lbs)\n");

            for(int i=0; i<size; i++){
                Person p = people[i];
                writer.write(p.getName() + "," + p.getGender() + "," + p.getAge() + "," + p.getHeight() + "," + p.getWeight() + "\n");
            }

            writer.close();
            System.out.println("saved as " + filename);
        }
        catch (IOException e){
            System.out.println("error saving new file: " + e.getMessage());
        }
    }

    /**
     * returns the number of person objects saved
     * @return num of people
     */
    public int getSize(){
        return size;
    }

}
