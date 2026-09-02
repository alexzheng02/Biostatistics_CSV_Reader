/**
 * exception thrown when you try to add a person who already exists in the data
 */

public class PersonAlreadyExistsException extends Exception{
    /**
     * creates a PersonAlreadyExistsException with a message
     * @param name name of person
     */
    public PersonAlreadyExistsException(String name){
        super("person with name \"" + name + "\" already exists");
    }
}


