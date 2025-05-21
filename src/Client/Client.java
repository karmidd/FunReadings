//---------------------------------------------------------
// Assignment #1
// Written by: Karam Midani 40277218
//---------------------------------------------------------
package Client;

//import item to use in this class
import Library.Item;
//client class, has its own information, including an array of leased items
public class Client {

    //static variable that is used as a counter to keep track of how many clients are being created,
    //and use a client's order of creation as their unique ID
    private static int idNum = 0;
    //instance variables of client
    private final String id;
    private String name;
    private long phoneNumber;
    private String email;
    private Item[] items = new Item[0];

    //parameterized constructor
    public Client(String name, long phoneNumber, String email){
        //increment the count to indicate the creation of a client
        idNum++;
        //adds the first character of the client's name to the id, to make it more unique
        this.id = Character.toUpperCase(name.charAt(0)) + String.valueOf(idNum);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    //parameterized constructor that has one extra constructor to avoid being the same as the previous parameterized constructor
    //the goal of this constructor is to be used in an anonymous object invocation
    //since the object made by this constructor is anonymous and isn't going to be used later on,
    //the difference between it and the previous parameterized constructor is that the id counter does not increment here
    public Client(String name, long phoneNumber, String email, int anon){
        this.id = name.charAt(0) + String.valueOf(idNum);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    //unused default and copy constructors
    /*public Client(){
        this("N/A","N/A","N/A");
    }*/
    /*public Client(Client otherClient){
        this(otherClient.name, otherClient.phoneNumber, otherClient.email);
    }*/
    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setPhoneNumber(long phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email){
        this.email = email;
    }
    //getters
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public long getPhoneNumber(){
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public int getItemsLength(){
        return items.length;
    }

    //overriding tostring method
    @Override
    public String toString() {
        return "Client's Name: " + name + "\nClient's ID: " + id + "\nClient's Email: " + email + "\nClient's Phone Number: " + phoneNumber + "\nAmount of leases: " + items.length;
    }

    //method that displays the names of the items in the items array of client only
    public void displayItems(){
        for (int i = 0; i < items.length; i++) {
            System.out.println("Name of item #" + i + ": " + items[i].getName());
        }
    }

    //overriding equals method
    @Override
    public boolean equals(Object otherObject) {
        if(otherObject == null)
            return false;
        else if(otherObject.getClass() != this.getClass())
            return false;
        Client otherClient = (Client) otherObject;
        return(this.name.equals(otherClient.name) && this.email.equals(otherClient.email) && this.phoneNumber == otherClient.phoneNumber);
    }

    //lease item method, takes an item as a parameter
    public void leaseItem(Item item) {
        //first sets the desired item to leased
        item.setItemLeased();
        //creates a copy of the items array, and copies all of its elements
        Item[] itemsCopy = new Item[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            itemsCopy[i] = items[i];
        }
        //assigns the last index of the new array to the passed item
        itemsCopy[items.length] = item;
        //assigns the new items array to the instance variable
        items = itemsCopy;
    }

    //return item method, takes an int that represents an index as a parameter
    public void returnItem(int index){
        //sets the item to not leased (returned)
        items[index].setItemNotLeased();
        //create new items array copy and copy all items except the desired items that needs to be returned
        //this is done by copying one to one until the index is reached, where it will copy 1 to 1+1
        Item[] itemsCopy = new Item[items.length - 1];
        for (int i = 0; i < itemsCopy.length; i++) {
            //when index is reached, we start copying the next index of the old array to the current index of the new array
            if (i == index) {
                for (; i < itemsCopy.length; i++) {
                    itemsCopy[i] = items[i + 1];
                }
                break;
            }
            //when the index is not reached, we copy the current index of the old array to the current index of the new array
            itemsCopy[i] = items[i];
        }
        //assign the new array to the instance variable
        items = itemsCopy;
    }

    //method that sets all items leased by client as returned, on the case that the client is to be deleted from the system
    public void returnAllItemsFromClient(){
        for (int i = 0; i < items.length; i++) {
            items[i].setItemNotLeased();
        }
    }

    //method that checks if the client is leasing at least 1 item
    public boolean isClientLeasing(){
        return (items.length != 0);
    }

    //method that returns a specific item that is to be deleted from the system
    //passes the desired item to be deleted as a parameter
    //and checks if it is being leased by the client
    public void returnSpecificItem(Item item){
        for (int i = 0; i < items.length; i++) {
            if(items[i].equals(item)){
                returnItem(i);
                break;
            }
        }
    }
}
