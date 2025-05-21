//---------------------------------------------------------
// Assignment #1
// Written by: Karam Midani 40277218
//---------------------------------------------------------
package Library;
//inheritor child class of item (parent)
public class Book extends Item {
    //one instance variable to add on top of the inherited instance variable from item
    private int numberOfPages;

    //parameterized constructor
    public Book(String name, String[] authors, int yearOfPublication, int numberOfPages){
        //utilize super to call the parameterized constructor of the parent class
        super(name, authors, yearOfPublication);
        this.numberOfPages = numberOfPages;
        //uses inherited setID method and adds the letter B, to indicate that this is a book item
        setID('B');
    }
    /*public Book(){
        super("N/A", null, 0);
        this.numberOfPages = 0;
    }*/

    //copy constructor
    public Book(Book otherBook){
        //utilizes super to use the copy constructor from item (parent)
        super(otherBook);
        this.numberOfPages = otherBook.numberOfPages;
        //gives the letter B to the id
        setID('B');
    }

    //overriding clone method, returns media object by utilizing the copy constructor
    public Book clone(){
        return new Book(this);
    }

    //getter
    public int getNumberOfPages() {
        return numberOfPages;
    }
    //setter
    public void setNumberOfPages(int numberOfPages){
        this.numberOfPages = numberOfPages;
    }

    //tostring, utilizes super
    @Override
    public String toString(){
        String itemString = super.toString();
        return itemString + "\nNumber of Pages: " + this.numberOfPages;
    }

    //equals, utilizes super
    @Override
    public boolean equals(Object otherObject){
        if(otherObject == null)
            return false;
        else if(otherObject.getClass() != this.getClass())
            return false;
        Book otherBook = (Book) otherObject;
        boolean itemBoolean = super.equals(otherBook);
        return(itemBoolean && this.numberOfPages == otherBook.numberOfPages);
    }
}
