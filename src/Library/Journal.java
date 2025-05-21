//---------------------------------------------------------
// Assignment #1
// Written by: Karam Midani 40277218
//---------------------------------------------------------
package Library;
//inheritor child class of item (parent)
public class Journal extends Item {
    //one instance variable to add on top of the inherited instance variable from item
    private int volumeNumber;

    //parameterized constructor
    public Journal(String name, String[] authors, int yearOfPublication, int volumeNumber){
        //utilize super to call the parameterized constructor of the parent class
        super(name, authors, yearOfPublication);
        this.volumeNumber = volumeNumber;
        //uses inherited setID method and adds the letter J, to indicate that this is a journal item
        setID('J');
    }
    //unused default constructor, utilizes super
    /*public Journal(){
        super("N/A", null, 0);
        this.volumeNumber = 0;
    }*/

    //copy constructor
    public Journal(Journal otherJournal){
        //utilizes super to use the copy constructor from item (parent)
        super(otherJournal);
        this.volumeNumber = otherJournal.volumeNumber;
        //gives the letter J to the id
        setID('J');
    }

    //overriding clone method, returns media object by utilizing the copy constructor
    public Journal clone(){
        return new Journal(this);
    }

    //getter
    public int getVolumeNumber() {
        return volumeNumber;
    }
    //setter
    public void setVolumeNumber(int volumeNumber){
        this.volumeNumber = volumeNumber;
    }

    //tostring, utilizes super
    @Override
    public String toString(){
        String itemString = super.toString();
        return itemString + "\nNumber of Volumes: " + this.volumeNumber;
    }

    //equals, utilizes super
    @Override
    public boolean equals(Object otherObject){
        if(otherObject == null)
            return false;
        else if(otherObject.getClass() != this.getClass())
            return false;
        Journal otherJournal = (Journal) otherObject;
        boolean itemBoolean = super.equals(otherJournal);
        return(itemBoolean && this.volumeNumber == otherJournal.volumeNumber);
    }
}
