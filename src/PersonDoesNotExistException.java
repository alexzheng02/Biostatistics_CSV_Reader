/**
 * exception thrown when trying to access or remove a person who doesn't exist
 */

public class PersonDoesNotExistException extends Exception {
    /**
     * creates a PersonDoesNotExistException with a message
     * @param name name of person
     */
    public PersonDoesNotExistException(String name){
        super("Person named \"" + name + "\" doesn't exist.");
    }
}
