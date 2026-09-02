/**
 * A class for a person with certain statistics.
 * @author Alex Zheng
 */

public class Person {
    private String name;
    private String gender;
    private int age;
    private double height;
    private double weight;

    /**
     * No-argument constructor for the person class, provides some values by default
     */
    public Person(){
        this.name = "";
        this.gender = "";
        this.age = 0;
        this.height = 0.0;
        this.weight = 0.0;
    }

    /**
     * constructor with parameter
     * @param n person name
     * @param g person gender
     * @param a person age
     * @param h person height in in
     * @param w person weight in lbs
     * @throws IllegalArgumentException if the numbers inputted are invalid
     */

    public Person(String n,String g,int a,double h,double w){
        if(a<0){
            throw new IllegalArgumentException("Age can't be negative.");
        }
        if(h<0){
            throw new IllegalArgumentException("height cannot be negative.");
        }
        if(w<0){
            throw new IllegalArgumentException("weight cannot be negative.");
        }
        name = n;
        gender = g;
        age = a;
        height = h;
        weight = w;
    }

    /**
     * Accessor methods (getters)
     *
     */
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight(){
        return weight;
    }

    /**
     * Mutator methods (setter)
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        if(age<0){
            throw new IllegalArgumentException("Age can't be negative.");
        }
        this.age = age;
    }

    public void setHeight(double height) {
        if(height<0){
            throw new IllegalArgumentException("Height can't be negative.");
        }
        this.height = height;
    }

    public void setWeight(double weight) {
        if(weight<0){
            throw new IllegalArgumentException("Weight can't be negative.");
        }
        this.weight = weight;
    }

    /**
     * returns a string with the details of the person that is easy to read
     *
     */
    @Override
    public String toString() {
        return name + ", " + gender + ", " + age + ", " + height + ", " + weight;
    }
}
