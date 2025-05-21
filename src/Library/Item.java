//---------------------------------------------------------
// Assignment #1
// Written by: Karam Midani 40277218
//---------------------------------------------------------
package Library;
//abstract parent class of 3 inheritors
//it is abstract because no objects of it will be constructed
public abstract class Item{

    //static variable that is used as a counter to keep track of how many items are being created,
    //and use an item's order of creation as their unique ID
    private static int idNum = 0;
    //instance variables of item
    private String id;
    private String name;
    private String[] authors;
    private int yearOfPublication;
    private boolean lease;

    //parametric constructor
    public Item(String name, String[] authors, int yearOfPublication){
        //increment the count to indicate the creation of an item
        idNum++;
        this.name = name;
        this.authors = authors;
        this.yearOfPublication = yearOfPublication;
        //assigns the current count of items as a string to the object's id instance variable
        this.id = String.valueOf(idNum);
        this.lease = false;
    }
    //default constructor (never needed to be used)
    /*public Item(){
        this("N/A", null, 0);
    }*/

    //copy constructor
    public Item(Item otherItem){
        //increments counter to indicate creation of an item
        idNum++;
        //checks if authors is null, if not, a deep copy of authors is given to the copy of item
        if(otherItem.authors == null){
            this.authors = null;
        }
        else {
            String[] copy = new String[otherItem.authors.length];
            for (int i = 0; i < copy.length; i++) {
                copy[i] = otherItem.authors[i];
            }
            this.authors = copy;
        }
        this.name = otherItem.name;
        this.yearOfPublication = otherItem.yearOfPublication;
        this.id = String.valueOf(idNum);
        this.lease = false;
    }

    //abstract clone method that is overridden in each inheritor
    public abstract Item clone();

    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setItemLeased(){
        this.lease = true;
    }
    public void setItemNotLeased(){
        this.lease = false;
    }
    public void setAuthors(String[] authors){
        this.authors = authors;
    }
    public void setYearOfPublication(int yearOfPublication){
        this.yearOfPublication = yearOfPublication;
    }
    //special setter that only adds the desired letter to indicate the type of item (will see more in inheritors)
    //protected because used in inheritors only
    protected void setID(char type){
        id = type + id;
    }
    //getters
    public String getId(){
        return id;
    }
    public int getYearOfPublication() {
        return yearOfPublication;
    }
    public String getName() {
        return name;
    }
    //getter that returns a deep copy of the array of authors
    public String[] getAuthors() {
        if(authors == null)
            return null;
        String[] copy = new String[authors.length];
        for(int i=0; i<copy.length; i++){
            copy[i] = authors[i];
        }
        return copy;
    }
    public boolean getLease(){
        return lease;
    }

    //overriding toString method
    @Override
    public String toString(){
        //creates separate strings first, then combines all of them in the return at the end
        String nameString = "Name of item: " + name;
        String authorsString = "\nAuthor(s):";
        for(int i = 0; i < authors.length; i++){
            if(authors.length == 1 || i == authors.length-1)
                authorsString = authorsString + " " + authors[i] + ".";
            else{
                authorsString = authorsString + " " + authors[i] + ",";
            }
        }
        String yopString = "\nYear of Publication: " + yearOfPublication;
        String idString = "\nID: " + id;
        String leaseString;
        if(lease){
            leaseString = "\nLease: Unavailable";
        }
        else{
            leaseString = "\nLease: Available";
        }
        return nameString + authorsString + yopString + idString + leaseString;
    }

    //overriding equals method
    @Override
    public boolean equals(Object otherObject){
        //checks if null first
        if(otherObject == null)
            return false;
        //then checks if it's the same class type
        else if(otherObject.getClass() != this.getClass())
            return false;
        //if same class type, we create a new object and cast it to our class (item)
        Item otherItem = (Item) otherObject;
        //check the equality of authors first since it's an array
        //first, if any of the two item's have a null authors array
        boolean conclusion = true;
        if((otherItem.authors == null && this.authors != null) || (otherItem.authors != null && this.authors == null))
            conclusion = false;
        else if(otherItem.authors == null && this.authors == null)
            conclusion = true;
        //if both of them are not null, we check how many authors each item has
        else if(otherItem.authors.length != this.authors.length)
            conclusion = false;
        //if equal amount of authors, we count the amount of matching authors between two authors arrays
        //if the count equals the whole length of the array of authors, that means all authors names' match
        else{
            int correctCounter = 0;
            for (int i = 0; i < this.authors.length; i++) {
                for (int j = 0; j < otherItem.authors.length; j++) {
                    if(this.authors[i].equals(otherItem.authors[j])){
                        correctCounter++;
                        break;
                    }
                }
                conclusion = (correctCounter == this.authors.length);
            }
        }
        return (this.name.equals(otherItem.name) && this.yearOfPublication == otherItem.yearOfPublication && conclusion);
    }

}
