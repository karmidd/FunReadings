//---------------------------------------------------------
// Assignment #1
// Written by: Karam Midani 40277218
//---------------------------------------------------------
package Library;
//inheritor child class of item (parent)
public class Media extends Item {
    //one instance variable to add on top of the inherited instance variable from item
    private String type;

    //parameterized constructor
    public Media(String name, String[] authors, int yearOfPublication, int type){
        //utilize super to call the parameterized constructor of the parent class
        super(name, authors, yearOfPublication);
        //assign type according to the number
        if(type == 1)
            this.type = "Audio";
        else if(type == 2)
            this.type = "Video";
        else if(type == 3)
            this.type = "Interactive";
        else
            this.type = "N/A";
        //uses inherited setID method and adds the letter M, to indicate that this is a media item
        setID('M');
    }

    //unused default constructor
    /*public Media() {
        //also utilizes super
        super("N/A", null, 0);
        this.type = "N/A";
    }*/

    //copy constructor
    public Media(Media otherMedia){
        //utilizes super to use the copy constructor from item (parent)
        super(otherMedia);
        this.type = otherMedia.type;
        //gives the letter M to the id
        setID('M');
    }

    //overriding clone method, returns media object by utilizing the copy constructor
    public Media clone(){
        return new Media(this);
    }

    //getter
    public String getType() {
        return type;
    }
    //setter
    public void setType(int type){
        if(type == 1)
            this.type = "Audio";
        else if(type == 2)
            this.type = "Video";
        else if(type == 3)
            this.type = "Interactive";
        else
            this.type = "N/A";
    }

    //tostring, utilizes super to use tostring of item
    @Override
    public String toString(){
        String itemString = super.toString();
        return itemString + "\nType of Media: " + this.type;
    }

    //equals, utilizes super to use equals of item
    @Override
    public boolean equals(Object otherObject){
        if(otherObject == null)
            return false;
        else if(otherObject.getClass() != this.getClass())
            return false;
        Media otherMedia = (Media) otherObject;
        boolean itemBoolean = super.equals(otherMedia);
        return(itemBoolean && this.type.equals(otherMedia.type));
    }
}
