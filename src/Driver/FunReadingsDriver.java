//---------------------------------------------------------
// Assignment #1
// Written by: Karam Midani 40277218
//---------------------------------------------------------
package Driver;
//import all needed classes
import java.util.InputMismatchException;
import java.util.Scanner;
import Library.Item;
import Library.Media;
import Library.Book;
import Library.Journal;
import Client.Client;

public class FunReadingsDriver {

    //method that checks if a type of item is completely leased
    //(for example, if all media are leased, then return true to indicate that there are no available medias to lease)

    /**
     * so it does one two three
     * @param items items
     * @return sometjong
     */
    private static boolean checkTypeOfItemIfAllLeased(Item[] items){
        boolean allLeased = true;
        for (int i = 0; i < items.length; i++) {
            if(!items[i].getLease()){
                allLeased = false;
                break;
            }
        }
        return allLeased;
    }

    //remove client method that updates the driver's clients array and removes a client
    //passes the clients array and the index of the client to be removed as parameters
    //returns the new updated clients array
    private static Client[] removeClient(Client[] clients, int index){
        //creates a copy with size smaller by 1, and copies all elements 1 to 1, until index is reached, then copies 1 to 1+1
        Client[] clientsCopy = new Client[clients.length-1];
        for (int i = 0; i < clientsCopy.length; i++) {
            if (i == index) {
                for (; i < clientsCopy.length; i++) {
                    clientsCopy[i] = clients[i + 1];
                }
                break;
            }
            clientsCopy[i] = clients[i];
        }
        clients[index].returnAllItemsFromClient();
        return clientsCopy;
    }

    //add client method that updates the driver's clients array and adds a client
    //passes the clients array and  the client to be added as parameters
    //returns the new updated clients array
    private static Client[] addClient(Client[] clients, Client client){
        //creates a copy with size bigger by 1, and copies all elements 1 to 1
        Client[] clientsCopy = new Client[clients.length+1];
        for (int i = 0; i < clients.length; i++) {
            clientsCopy[i] = clients[i];
        }
        //assigns the new client to the new available spot in the clients array
        clientsCopy[clients.length] = client;
        return clientsCopy;
    }

    //add item method that updates the driver's items array and adds an item
    //passes the items array and the item to be added as parameters
    //returns the new updated items array
    private static Item[] addItem(Item[] items, Item item){
        Item[] itemsCopy = new Item[items.length+1];
        for (int i = 0; i < items.length; i++) {
            itemsCopy[i] = items[i]; 
        }
        itemsCopy[items.length] = item;
        return itemsCopy;
    }
    //remove item method that updates the driver's items array and removes an item
    //passes the items array and the index of the item to be removed as parameters
    //returns the new updated items array
    private static Item[] removeItem(Item[] items, int index){
        Item[] itemsCopy = new Item[items.length - 1];
        for (int i = 0; i < itemsCopy.length; i++) {
            if (i == index) {
                for (; i < itemsCopy.length; i++) {
                    itemsCopy[i] = items[i + 1];
                }
                break;
            }
            itemsCopy[i] = items[i];
        }
        return itemsCopy;
    }

    //get biggest book method that displays the biggest book in an array of books
    //passes an array of items as a parameter
    private static void getBiggestBooks(Item[] books){
        //we assign the page count of the first book as the max pages, then we compare it to each other page count of the other books
        int maxPages = ((Book)books[0]).getNumberOfPages();
        for (int i = 1; i < books.length; i++) {
            if(((Book)books[i]).getNumberOfPages()>maxPages){
                maxPages = ((Book)books[i]).getNumberOfPages();
            }
        }
        //we display every book that has the same count of max pages determined in the previous for loop
        for (int i = 0; i < books.length; i++) {
            if(maxPages == ((Book)books[i]).getNumberOfPages())
                System.out.println(books[i] + "\n");
        }
    }

    //copy books method that creates a deep copy of a books array
    //note: this works ONLY with a books array, because it utilizes the copy constructor found in the book class
    //passes the array of books to be copied as a parameter
    //and returns a copy of the array
    private static Item[] copyBooks(Item[] books){
        //declare the copy of books outside the try/catch statements to be able to return it later
        Item[] booksCopy = null;
        try {
            booksCopy = new Book[books.length];
            for (int i = 0; i < books.length; i++) {
                booksCopy[i] = new Book((Book) books[i]);
            }
        }
        //if this method was used to copy an array of a different type of item, it would throw a classcastexception exception
        catch(ClassCastException cce){
            System.out.println("Cannot copy another class type that is not books.");
        }
        catch(Exception exception){
            System.out.println("Unknown error.");
        }
        return booksCopy;
    }

    //the other copy books method, that creates a deep copy of an items array
    //note: this works with ALL types of items arrays, because it utilizes the clone method found in each item class
    //because clone exists in all inheritors of the parent class item, dynamic binding occurs in this situation
    //so during runtime, the program will know which clone method to use depending on the type of object.
    //passes the array of items to be copied as a parameter, and returns a copy of the array
    private static Item[] copyBooks2(Item[] items){
        //create new array copy
        Item[] itemsCopy = new Item[items.length];
        //for loop that copies all elements by utilizing the clone method
        for (int i = 0; i < items.length; i++) {
            itemsCopy[i] = items[i].clone();
        }
        return itemsCopy;
    }
    //this program contains 2 possible executions, the predefined example, and the menu
    //the predefined example is a hardcoded scenario that tests out some of the programs code, like the equals() methods, getbiggestbooks, and more,
    //it is made to just show the user how different objects in the program work with each other
    //the menu lists many different options, from adding items and clients, removing them or editing them, to displaying said items and clients.
    //a hierarchy exists between the class item and its 3 inheritors: media, journal and book
    //client, is a separate entity from the hierarchy previously mentioned
    //the driver contains a 2d array of items that contains 3 arrays of items for each type of item, and a 1d array of clients
    //the sizes of the arrays of items and the array of clients are flexible and always change in correlation to adding or removing an object
    //there exists a leasing system in the program, where a client could be leased an item that exists in one of the three different arrays of items
    //client objects have many instance variables, one of them is an array of items that contains references to items that they are being leased
    //(this array of items that each client object has, is similar in flexibility like the previous arrays mentioned above)
    //these references will point to the objects in the arrays of items, meaning it wont create new objects, just references that point to pre-existing objects
    //this allows us to see the correlation between the clients and the items
    //this program also makes sure to validate every input and catch all exceptions, to ensure a clean and smooth interaction with the user

    //main method (driver)
    public static void main(String[] args) {
        //declaration of the scanner
        Scanner scanner = new Scanner(System.in);
        //welcome messages, and prompting the user to choose a predefined scenario or to use the menu themselves
        System.out.println("Welcome to Fun Readings made by Karam Midani, would you like a predefined program example, or to use the menu yourself?");
        System.out.print("Enter 1 for the predefined example, and 2 for the menu: ");
        String choice =  scanner.next();
        //input validation
        while(!choice.equals("1") && !choice.equals("2")){
            System.out.println("Invalid input. (1 for predefined scenario, 2 for menu)");
            System.out.print("Try again: ");
            choice =  scanner.next();
        }
        //declaration of variables that are going to be used inside the switch
        //junk that helps us avoid problems while using nextLine with the scanner
        String junk;
        String name;
        int yearOfPublication;
        int numberOfAuthors;
        String[] authors;
        //declaration of a 2d array of items, that holds 3 arrays of different types of items
        Item[][] allItems = {new Media[0], new Book[0], new Journal[0]};
        //declaration of an array of clients
        Client[] allClients = new Client[0];
        switch(choice) {
            //case 1 is the predefined scenario
            case "1":
                System.out.println("Welcome to the predefined scenario, the program will create\npredefined items and use them to showcase the functionality of the program.");
                //creates 3 items of each type, so 9 items in total
                allItems[0] = addItem(allItems[0], new Media("Sonate Pacifique", new String[]{"L'Imperatrice", "Isaac Delusion"}, 2014, 1));
                allItems[0] = addItem(allItems[0], new Media("Oppenheimer", new String[]{"Christopher Nolan"}, 2023, 2));
                allItems[0] = addItem(allItems[0], new Media("Elden Ring", new String[]{"FromSoftware", "Hidetaka Miyazaki"}, 2022, 3));
                allItems[1] = addItem(allItems[1], new Book("Harry Potter and the Philosopher's Stone", new String[]{"J. K. Rowling"}, 1997, 223));
                allItems[1] = addItem(allItems[1], new Book("Bleach VOL1", new String[]{"Tite Kubo"}, 2002, 192));
                allItems[1] = addItem(allItems[1], new Book("The Alchemist", new String[]{"Paulo Coelho"}, 1988, 208));
                allItems[2] = addItem(allItems[2], new Journal("JOURNAL1", new String[]{"AUTHOR1", "AUTHOR2"}, 2012, 78));
                allItems[2] =  addItem(allItems[2], new Journal("JOURNAL1", new String[]{"AUTHOR2", "AUTHOR1"}, 2012, 78));
                allItems[2] =  addItem(allItems[2], new Journal("JOURNAL3", new String[]{"AUTHOR4", "AUTHOR5", "AUTHOR6"}, 2023, 432));
                //lists out all items that were just created
                System.out.println("List of all items:");
                for (int i = 0; i < allItems.length; i++) {
                    for (int j = 0; j < allItems[i].length; j++) {
                        System.out.println(allItems[i][j]);
                    }
                }
                //tests the equality between items
                System.out.println("Equality of 2 journals with the same information:");
                System.out.println(allItems[2][0].equals(allItems[2][1]));
                System.out.println("Equality of a book and a media:");
                System.out.println(allItems[0][0].equals(allItems[1][1]));
                System.out.println("Equality of 2 books with different information:");
                System.out.println(allItems[1][0].equals(allItems[1][2]));
                //displays the biggest book created
                System.out.println("Book with the most pages:");
                getBiggestBooks(allItems[1]);
                //showcase of the usage and difference between copyBooks (copy constructor) and copyBooks2 (clone) methods
                System.out.println("Attempting to copy the media array using copyBooks (copy constructor), and displaying first element:");
                Item[] newMedia = copyBooks(allItems[0]);
                System.out.println(newMedia[0]);
                System.out.println("Copying the media array using copyBooks2 (clone), and displaying first element:");
                Item[] newMedia2 = copyBooks2(allItems[0]);
                System.out.println(newMedia2[0]);
                break;
            //case 2 is the menu
            case "2":
                String choice2;
                //do while loops that keeps the menu running until the user chooses to exit
                do {
                    System.out.println("\nWhat would you like to do?");
                    System.out.println("1) Add an item to the system.");
                    System.out.println("2) Remove an item from the system.");
                    System.out.println("3) Edit an item in the system.");
                    System.out.println("4) Add a client to the system.");
                    System.out.println("5) Remove a client from the system.");
                    System.out.println("6) Edit a client in the system.");
                    System.out.println("7) Lease an item to a client.");
                    System.out.println("8) Return a leased item from a client.");
                    System.out.println("9) Display all clients.");
                    System.out.println("10) Display all leased items.");
                    System.out.println("11) Display the biggest book.");
                    System.out.println("12) Display all medias.");
                    System.out.println("13) Display all books.");
                    System.out.println("14) Display all journals.");
                    System.out.println("15) Display all items.");
                    System.out.println("16) Copy all books.");
                    System.out.println("17) Exit the system.");
                    System.out.print("Enter your choice: ");
                    choice2 = scanner.next();
                    switch(choice2){
                        //case 1 is adding an item
                        case "1":
                            //prompt the user to choose which type of item
                            System.out.println("\nWhich type of item would you like to add?");
                            System.out.print("Enter 1 for Media, 2 for Book, 3 for Journal: ");
                            String case1choice1 = scanner.next();
                            //input validation
                            while(!case1choice1.equals("1") && !case1choice1.equals("2") && !case1choice1.equals("3")){
                                System.out.print("Invalid input, try again: ");
                                case1choice1 = scanner.next();
                            }
                            junk = scanner.nextLine();
                            System.out.print("Name of item: ");
                            name = scanner.nextLine();
                            System.out.print("Year of publication: ");
                            //input validation that utilizes try/catch, to ensure the right input is entered
                            while(true) {
                                try {
                                    yearOfPublication = scanner.nextInt();
                                    if (yearOfPublication <= 0)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers and 0 are invalid values, try again: ");
                                }
                            }
                            System.out.print("Number of authors: ");
                            //input validation
                            while(true) {
                                try {
                                    numberOfAuthors = scanner.nextInt();
                                    if (numberOfAuthors <= 0)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers and 0 are invalid values, try again: ");
                                }
                            }
                            authors = new String[numberOfAuthors];
                            junk = scanner.nextLine();
                            //prompting the user to enter the name(s) of the author(s)
                            for (int i = 0; i < authors.length; i++) {
                                System.out.print("Enter the name of author number " + (i+1) + ": ");
                                authors[i] = scanner.nextLine();
                            }
                            //depending on the type of item the user wants to create, we ask for the relevant info
                            if(case1choice1.equals("1")) {
                                System.out.println("Which type of media?");
                                System.out.print("1 for Audio, 2 for Video, 3 for Interactive: ");
                                int typeOfMedia;
                                //input validation using try/catch
                                while(true) {
                                    try {
                                        typeOfMedia = scanner.nextInt();
                                        if (typeOfMedia <= 0 || typeOfMedia > 3)
                                            throw (new Exception());
                                        break;
                                    }
                                    catch (InputMismatchException ime) {
                                        System.out.print("Not a valid number input, try again: ");
                                        scanner.next();
                                    }
                                    catch(Exception exception){
                                        System.out.print("Negative numbers, 0, and numbers that exceed your amount of choices are invalid values, try again: ");
                                    }
                                }
                                //adding the item using addItem method
                                allItems[0] = addItem(allItems[0], new Media(name, authors, yearOfPublication, typeOfMedia));
                                System.out.println("Item added successfully.\n");
                                break;
                            }
                            if(case1choice1.equals("2")) {
                                System.out.print("Number of pages: ");
                                int numberOfPages;
                                //input validation using try/catch
                                while(true) {
                                    try {
                                        numberOfPages = scanner.nextInt();
                                        if (numberOfPages <= 0)
                                            throw (new Exception());
                                        break;
                                    }
                                    catch (InputMismatchException ime) {
                                        System.out.print("Not a valid number input, try again: ");
                                        scanner.next();
                                    }
                                    catch(Exception exception){
                                        System.out.print("Negative numbers and 0 are invalid values, try again: ");
                                    }
                                }
                                //adding the item using addItem method
                                allItems[1] = addItem(allItems[1], new Book(name, authors, yearOfPublication, numberOfPages));
                                System.out.println("Item added successfully.\n");
                                break;
                            }
                            if(case1choice1.equals("3")) {
                                System.out.print("Number of volumes: ");
                                int volumeNumber;
                                //input validation try/catch
                                while(true) {
                                    try {
                                        volumeNumber = scanner.nextInt();
                                        if (volumeNumber <= 0)
                                            throw (new Exception());
                                        break;
                                    }
                                    catch (InputMismatchException ime) {
                                        System.out.print("Not a valid number input, try again: ");
                                        scanner.next();
                                    }
                                    catch(Exception exception){
                                        System.out.print("Negative numbers and 0 are invalid values, try again: ");
                                    }
                                }
                                //addItem method
                                allItems[2] = addItem(allItems[2], new Journal(name, authors, yearOfPublication, volumeNumber));
                                System.out.println("Item added successfully.\n");
                                break;
                            }
                            break;
                        //case 2 is remove item
                        case "2":
                            //we check if there is any items at all to begin with. if none, we break out of the case
                            if(allItems[0].length == 0 && allItems[1].length == 0 && allItems[2].length == 0){
                                System.out.println("There aren't any items in the system.\n");
                                break;
                            }
                            System.out.println("Which type of item would you like to remove? Enter 1 for Media, 2 for Book, 3 for Journal: ");
                            String case2choice1 = scanner.next();
                            //input validation
                            while(!case2choice1.equals("1") && !case2choice1.equals("2") && !case2choice1.equals("3")){
                                System.out.print("Invalid input, try again: ");
                                case2choice1 = scanner.next();
                            }
                            //we check if there are any medias
                            if(case2choice1.equals("1")){
                                if (allItems[0].length == 0) {
                                    System.out.println("There aren't any medias in the system.");
                                    break;
                                }
                                //if yes, we list them and their corresponding index in their array
                                System.out.println("List of media(s):");
                                for (int i = 0; i < allItems[0].length; i++) {
                                    System.out.println(allItems[0][i]);
                                    System.out.println("(Enter " + i + " to remove this item from the system)\n");
                                }
                            }
                            //same thing for books
                            if(case2choice1.equals("2")) {
                                if (allItems[1].length == 0) {
                                    System.out.println("There aren't any books in the system.");
                                    break;
                                }
                                System.out.println("List of book(s):");
                                for (int i = 0; i < allItems[1].length; i++) {
                                    System.out.println(allItems[1][i]);
                                    System.out.println("(Enter " + i + " to remove this item from the system)\n");
                                }
                            }
                            //same thing for journals
                            if(case2choice1.equals("3")) {
                                if (allItems[2].length == 0) {
                                    System.out.println("There aren't any journals in the system.");
                                    break;
                                }
                                System.out.println("List of journal(s):");
                                for (int i = 0; i < allItems[2].length; i++) {
                                    System.out.println(allItems[2][i]);
                                    System.out.println("(Enter " + i + " to remove this item from the system)\n");
                                }
                            }
                            System.out.print("Which item would you like to remove? Enter your choice: ");
                            int case2choice2;
                            //input validation try/catch
                            while(true) {
                                try {
                                    case2choice2 = scanner.nextInt();
                                    if (case2choice2 < 0 || case2choice2 > allItems[(Integer.parseInt(case2choice1))-1].length-1)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers and numbers that exceed your amount of choices are invalid values, try again: ");
                                }
                            }
                            //if the item chosen to be removed is currently being leased, we notify the user, and make sure if they want to proceed
                            if(allItems[(Integer.parseInt(case2choice1))-1][case2choice2].getLease()){
                                System.out.println("This item is currently being leased to a client.");
                                System.out.print("Are you sure you want to proceed? Enter 1 to cancel, or anything else to continue: ");
                                String case2choice3 = scanner.next();
                                if(case2choice3.equals("1"))
                                    break;
                                //if they choose to proceed, we look for which client is leasing this item, then return it from them
                                for (int i = 0; i < allClients.length; i++) {
                                    allClients[i].returnSpecificItem(allItems[(Integer.parseInt(case2choice1))-1][case2choice2]);
                                }
                            }
                            //deleting the item from its corresponding array
                            allItems[(Integer.parseInt(case2choice1))-1] = removeItem(allItems[(Integer.parseInt(case2choice1))-1], case2choice2);
                            System.out.println("Item removed successfully.\n");
                            break;
                        //case 3 is editing an item
                        case "3":
                            //we check if at least an item exists
                            if(allItems[0].length == 0 && allItems[1].length == 0 && allItems[2].length == 0){
                                System.out.println("There aren't any items in the system.\n");
                                break;
                            }
                            //if yes, we prompt the user to enter which type of item they would like to edit
                            System.out.print("Which type of item would you like to edit? Enter 1 for Media, 2 for Book, 3 for Journal: ");
                            String case3choice1 = scanner.next();
                            //input validation
                            while(!case3choice1.equals("1") && !case3choice1.equals("2") && !case3choice1.equals("3")){
                                System.out.print("Invalid input, try again: ");
                                case3choice1 = scanner.next();
                            }
                            //if statements that correspond to each type of items array
                            //checks if one item of that type at least exists, then continues to list the items and their corresponding index
                            if(case3choice1.equals("1")){
                                if (allItems[0].length == 0) {
                                    System.out.println("There isn't any media in the system.");
                                    break;
                                }
                                System.out.println("List of media(s):");
                                for (int i = 0; i < allItems[0].length; i++) {
                                    System.out.println(allItems[0][i]);
                                    System.out.println("(Enter " + i + " to edit this item from the system)\n");
                                }
                            }
                            if(case3choice1.equals("2")) {
                                if (allItems[1].length == 0) {
                                    System.out.println("There aren't any books in the system.");
                                    break;
                                }
                                System.out.println("List of book(s):");
                                for (int i = 0; i < allItems[1].length; i++) {
                                    System.out.println(allItems[1][i]);
                                    System.out.println("(Enter " + i + " to edit this item from the system)\n");
                                }
                            }
                            if(case3choice1.equals("3")) {
                                if (allItems[2].length == 0) {
                                    System.out.println("There aren't any journals in the system.");
                                    break;
                                }
                                System.out.println("List of journal(s):");
                                for (int i = 0; i < allItems[2].length; i++) {
                                    System.out.println(allItems[2][i]);
                                    System.out.println("(Enter " + i + " to edit this item from the system)\n");
                                }
                            }
                            System.out.print("Which item would you like to edit? Enter your choice: ");
                            int case3choice2;
                            //input validation try/catch
                            while(true) {
                                try {
                                    case3choice2 = scanner.nextInt();
                                    if (case3choice2 < 0 || case3choice2 > allItems[(Integer.parseInt(case3choice1))-1].length-1)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers & numbers that exceed your amount of choices are invalid values, try again: ");
                                }
                            }
                            junk = scanner.nextLine();
                            System.out.print("Name of item: ");
                            name = scanner.nextLine();
                            //set name
                            allItems[(Integer.parseInt(case3choice1))-1][case3choice2].setName(name);
                            System.out.print("Year of publication: ");
                            //input validation
                            while(true) {
                                try {
                                    yearOfPublication = scanner.nextInt();
                                    if (yearOfPublication <= 0)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers and 0 are invalid values, try again: ");
                                }
                            }
                            //set year
                            allItems[(Integer.parseInt(case3choice1))-1][case3choice2].setYearOfPublication(yearOfPublication);
                            System.out.print("Number of authors: ");
                            //input validation
                            while(true) {
                                try {
                                    numberOfAuthors = scanner.nextInt();
                                    if (numberOfAuthors <= 0)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers and 0 are invalid values, try again: ");
                                }
                            }
                            authors = new String[numberOfAuthors];
                            junk = scanner.nextLine();
                            for (int i = 0; i < authors.length; i++) {
                                System.out.print("Enter the name of author number " + (i+1) + ": ");
                                authors[i] = scanner.nextLine();
                            }
                            //set authors array
                            allItems[(Integer.parseInt(case3choice1))-1][case3choice2].setAuthors(authors);
                            //determining the specific class of the item being edited by using instanceof
                            //(could be done by checking case3choice1 like before, i just wanted to test out instanceof)
                            if(allItems[(Integer.parseInt(case3choice1))-1][case3choice2] instanceof Media){
                                System.out.println("Which type of media?");
                                System.out.print("1 for Audio, 2 for Video, 3 for Interactive: ");
                                int typeOfMedia;
                                //input validation
                                while(true) {
                                    try {
                                        typeOfMedia = scanner.nextInt();
                                        if (typeOfMedia <= 0 || typeOfMedia > 3)
                                            throw (new Exception());
                                        break;
                                    }
                                    catch (InputMismatchException ime) {
                                        System.out.print("Not a valid number input, try again: ");
                                        scanner.next();
                                    }
                                    catch(Exception exception){
                                        System.out.print("Negative numbers, 0, and numbers that exceed your amount of choices are invalid values, try again: ");
                                    }
                                }
                                ((Media) allItems[(Integer.parseInt(case3choice1))-1][case3choice2]).setType(typeOfMedia);
                            }
                            else if(allItems[(Integer.parseInt(case3choice1))-1][case3choice2] instanceof Book){
                                System.out.print("Number of pages: ");
                                int numberOfPages;
                                //input validation
                                while(true) {
                                    try {
                                        numberOfPages = scanner.nextInt();
                                        if (numberOfPages <= 0)
                                            throw (new Exception());
                                        break;
                                    }
                                    catch (InputMismatchException ime) {
                                        System.out.print("Not a valid number input, try again: ");
                                        scanner.next();
                                    }
                                    catch(Exception exception){
                                        System.out.print("Negative numbers and 0 are invalid values, try again: ");
                                    }
                                }
                                ((Book) allItems[(Integer.parseInt(case3choice1))-1][case3choice2]).setNumberOfPages(numberOfPages);
                            }
                            else if(allItems[(Integer.parseInt(case3choice1))-1][case3choice2] instanceof Journal){
                                System.out.print("Number of volumes: ");
                                int volumeNumber;
                                //input validation
                                while(true) {
                                    try {
                                        volumeNumber = scanner.nextInt();
                                        if (volumeNumber <= 0)
                                            throw (new Exception());
                                        break;
                                    }
                                    catch (InputMismatchException ime) {
                                        System.out.print("Not a valid number input, try again: ");
                                        scanner.next();
                                    }
                                    catch(Exception exception){
                                        System.out.print("Negative numbers and 0 are invalid values, try again: ");
                                    }
                                }
                                ((Journal) allItems[(Integer.parseInt(case3choice1))-1][case3choice2]).setVolumeNumber(volumeNumber);
                            }
                            System.out.println("Item edited successfully.\n");
                            break;
                        //case 4 is adding a client
                        case "4":
                            junk = scanner.nextLine();
                            System.out.print("Client's name: ");
                            String clientName = scanner.nextLine();
                            System.out.print("Client's Email: ");
                            String clientEmail = scanner.next();
                            System.out.print("Client's phone number: ");
                            long clientPhoneNumber;
                            //input validation
                            while(true) {
                                try {
                                    clientPhoneNumber = scanner.nextLong();
                                    if (clientPhoneNumber <= 0)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers and 0 are invalid values, try again: ");
                                }
                            }
                            //we check if the client that was just created already exists in the system
                            boolean clientExists = false;
                            for (int i = 0; i < allClients.length; i++) {
                                //we use the parametrized client constructor that is specified for anonymous objects only,
                                //to not increment the id
                                if(allClients[i].equals(new Client(clientName, clientPhoneNumber, clientEmail, 0))){
                                    System.out.println("Client already exists in the system.");
                                    clientExists = true;
                                    break;
                                }
                            }
                            if(clientExists)
                                break;
                            allClients = addClient(allClients, new Client(clientName, clientPhoneNumber, clientEmail));
                            System.out.println("Client added successfully.");
                            break;
                        //case 5 to remove client
                        case "5":
                            //we check if there are any clients
                            if(allClients.length == 0){
                                System.out.println("There aren't any clients in the system.");
                                break;
                            }
                            //we list clients with their corresponding index
                            System.out.println("Which client would you like to remove?");
                            System.out.println("List of client(s):");
                            for (int i = 0; i < allClients.length; i++) {
                                System.out.println(allClients[i]);
                                System.out.println("(Enter " + i + " to remove this client from the system)\n");
                            }
                            System.out.print("Enter your choice here: ");
                            int case5choice1;
                            //input validation
                            while(true) {
                                try {
                                    case5choice1 = scanner.nextInt();
                                    if (case5choice1 < 0 || case5choice1 > allClients.length-1)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers & numbers that exceed your amount of choices are invalid values, try again: ");
                                }
                            }
                            //remove client
                            allClients = removeClient(allClients, case5choice1);
                            System.out.println("Client removed successfully.");
                            break;
                        //case 6 edits a client
                        case "6":
                            //checks if there is at least a client
                            if(allClients.length == 0){
                                System.out.println("There aren't any clients in the system.");
                                break;
                            }
                            System.out.println("Which client would you like to edit?");
                            //lists clients with their corresponding index
                            System.out.println("List of client(s):");
                            for (int i = 0; i < allClients.length; i++) {
                                System.out.println(allClients[i]);
                                System.out.println("(Enter " + i + " to edit this client's information)\n");
                            }
                            System.out.print("Enter your choice here: ");
                            int case6choice1;
                            //input validation
                            while(true) {
                                try {
                                    case6choice1 = scanner.nextInt();
                                    if (case6choice1 < 0 || case6choice1 > allClients.length-1)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers & numbers that exceed your amount of choices are invalid values, try again: ");
                                }
                            }
                            junk = scanner.nextLine();
                            System.out.print("Client's name: ");
                            clientName = scanner.nextLine();
                            System.out.print("Client's Email: ");
                            clientEmail = scanner.next();
                            System.out.print("Client's phone number: ");
                            //input validation
                            while(true) {
                                try {
                                    clientPhoneNumber = scanner.nextLong();
                                    if (clientPhoneNumber <= 0)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers and 0 are invalid values, try again: ");
                                }
                            }
                            //set the new info
                            allClients[case6choice1].setName(clientName);
                            allClients[case6choice1].setEmail(clientEmail);
                            allClients[case6choice1].setPhoneNumber(clientPhoneNumber);
                            System.out.println("Client's information edited successfully.");
                            break;
                        //case 7 leases an item to a client
                        case "7":
                            //checks if there is at least an item
                            if(allItems[0].length == 0 && allItems[1].length == 0 && allItems[2].length == 0){
                                System.out.println("There aren't any items in the system.");
                                break;
                            }
                            //checks if there is at least a client
                            else if(allClients.length == 0){
                                System.out.println("There aren't any clients in the system.");
                                break;
                            }
                            //prompts the user to choose which item
                            System.out.print("Which type of item would you like to lease? Enter 1 for Media, 2 for Book, 3 for Journal: ");
                            String case7choice1 = scanner.next();
                            //input validation
                            while(!case7choice1.equals("1") && !case7choice1.equals("2") && !case7choice1.equals("3")){
                                System.out.print("Invalid input, try again: ");
                                case7choice1 = scanner.next();
                            }
                            //checks if the type of item is available to lease
                            //if available, lists them and their corresponding index
                            if(case7choice1.equals("1")){
                                if (allItems[0].length == 0) {
                                    System.out.println("There aren't any medias in the system.");
                                    break;
                                }
                                else if(checkTypeOfItemIfAllLeased(allItems[0])){
                                    System.out.println("There aren't any available medias to lease.");
                                    break;
                                }
                                System.out.println("List of available media(s):");
                                for (int i = 0; i < allItems[0].length; i++) {
                                    if(!allItems[0][i].getLease()) {
                                        System.out.println(allItems[0][i]);
                                        System.out.println("(Enter " + i + " to lease this item)\n");
                                    }
                                }
                            }
                            if(case7choice1.equals("2")) {
                                if (allItems[1].length == 0) {
                                    System.out.println("There aren't any books in the system.");
                                    break;
                                }
                                else if(checkTypeOfItemIfAllLeased(allItems[1])){
                                    System.out.println("There aren't any available books to lease.");
                                    break;
                                }
                                System.out.println("List of available book(s):");
                                for (int i = 0; i < allItems[1].length; i++) {
                                    if(!allItems[1][i].getLease()) {
                                        System.out.println(allItems[1][i]);
                                        System.out.println("(Enter " + i + " to lease this item)\n");
                                    }
                                }
                            }
                            if(case7choice1.equals("3")) {
                                if (allItems[2].length == 0) {
                                    System.out.println("There aren't any journals in the system.");
                                    break;
                                }
                                else if(checkTypeOfItemIfAllLeased(allItems[2])){
                                    System.out.println("There aren't any available journals to lease.");
                                    break;
                                }
                                System.out.println("List of available journal(s):");
                                for (int i = 0; i < allItems[2].length; i++) {
                                    if(!allItems[2][i].getLease()) {
                                        System.out.println(allItems[2][i]);
                                        System.out.println("(Enter " + i + " to lease this item)\n");
                                    }
                                }
                            }
                            System.out.print("Which item would you like to lease? Enter your choice: ");
                            int case7choice2;
                            //input validation
                            while(true) {
                                try {
                                    case7choice2 = scanner.nextInt();
                                    if (case7choice2 < 0 || case7choice2 > allItems[(Integer.parseInt(case7choice1))-1].length-1 || allItems[(Integer.parseInt(case7choice1))-1][case7choice2].getLease())
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers & numbers that exceed your amount of choices are invalid values, try again: ");
                                }
                            }
                            //check if the item is being leased
                            if(allItems[(Integer.parseInt(case7choice1))-1][case7choice2].getLease()){
                                System.out.println("Sorry, this item is being leased at the moment.");
                                break;
                            }
                            System.out.println("Which client would you like to lease \"" + allItems[(Integer.parseInt(case7choice1))-1][case7choice2].getName() + "\" to?");
                            System.out.println("List of client(s):");
                            //displays clients and their corresponding indexes
                            for (int i = 0; i < allClients.length; i++) {
                                System.out.println(allClients[i]);
                                System.out.println("(Enter " + i + " to select this client)\n");
                            }
                            System.out.print("Select a client: ");
                            int case7choice3;
                            //input validation
                            while(true) {
                                try {
                                    case7choice3 = scanner.nextInt();
                                    if (case7choice3 < 0 || case7choice3 > allClients.length-1)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers & numbers that exceed your amount of choices are invalid values, try again: ");
                                }
                            }
                            allClients[case7choice3].leaseItem(allItems[(Integer.parseInt(case7choice1))-1][case7choice2]);
                            System.out.println("Item leased to client successfully.");
                            break;
                        //case 8 returns an item
                        case "8":
                            //checks if theres at least an item
                            if(allItems[0].length == 0 && allItems[1].length == 0 && allItems[2].length == 0){
                                System.out.println("There aren't any items in the system.");
                                break;
                            }
                            //checks if there's at least a client
                            else if(allClients.length == 0){
                                System.out.println("There aren't any clients in the system.");
                                break;
                            }
                            //checks if there is any client that is leasing an item
                            boolean isAnyItemLeased = false;
                            for (int i = 0; i < allClients.length; i++) {
                                if(allClients[i].isClientLeasing()){
                                    isAnyItemLeased = true;
                                    break;
                                }
                            }
                            if(!isAnyItemLeased){
                                System.out.println("There aren't any leased items at the moment.");
                                break;
                            }
                            //displays clients that are leasing
                            System.out.println("List of client(s) that are leasing an item:");
                            for (int i = 0; i < allClients.length; i++) {
                                if(allClients[i].isClientLeasing()) {
                                    System.out.println(allClients[i]);
                                    System.out.println("(Enter " + i + " to select this client)\n");
                                }
                            }
                            System.out.print("Select a client to return a leased item from: ");
                            int case8choice1;
                            //input validation
                            while(true) {
                                try {
                                    case8choice1 = scanner.nextInt();
                                    if (case8choice1 < 0 || case8choice1 > allClients.length-1 || !allClients[case8choice1].isClientLeasing())
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers & numbers that exceed your amount of choices are invalid values, try again: ");
                                }
                            }
                            //display all items leased by the selected client
                            System.out.println("List of item(s) being leased by this client: ");
                            allClients[case8choice1].displayItems();
                            System.out.print("Select which item you would like to return: ");
                            int case8choice2;
                            //input validation
                            while(true) {
                                try {
                                    case8choice2 = scanner.nextInt();
                                    if (case8choice2 < 0 || case8choice2 > allClients[case8choice1].getItemsLength()-1)
                                        throw (new Exception());
                                    break;
                                }
                                catch (InputMismatchException ime) {
                                    System.out.print("Not a valid number input, try again: ");
                                    scanner.next();
                                }
                                catch(Exception exception){
                                    System.out.print("Negative numbers & numbers that exceed your amount of choices are invalid values, try again: ");
                                }
                            }
                            allClients[case8choice1].returnItem(case8choice2);
                            System.out.println("Item returned successfully");
                            break;
                        //case 9 displays all clients
                        case "9":
                            if(allClients.length == 0){
                                System.out.println("There aren't any clients in the system.");
                                break;
                            }
                            System.out.println("List of client(s):");
                            for (int i = 0; i < allClients.length; i++) {
                                System.out.println(allClients[i] + "\n");
                            }
                            break;
                        //case 10 displays all leased items
                        case "10":
                            //checks if there are items
                            if(allItems[0].length == 0 && allItems[1].length == 0 && allItems[2].length == 0){
                                System.out.println("There aren't any items in the system.");
                                break;
                            }
                            //checks if there is any leased item
                            isAnyItemLeased = false;
                            for (int i = 0; i < allClients.length; i++) {
                                if(allClients[i].isClientLeasing()){
                                    isAnyItemLeased = true;
                                    break;
                                }
                            }
                            if(!isAnyItemLeased){
                                System.out.println("There aren't any leased items at the moment.");
                                break;
                            }
                            //displays all leased items
                            System.out.println("List of leased item(s):");
                            for (int i = 0; i < allItems.length; i++) {
                                for (int j = 0; j < allItems[i].length; j++) {
                                    if(allItems[i][j].getLease())
                                        System.out.println(allItems[i][j] + "\n");
                                }
                            }
                            break;
                        //case 11 displays the biggest books
                        case "11":
                            //checks if there are any books
                            if(allItems[1].length == 0){
                                System.out.println("There aren't any books in the system.");
                                break;
                            }
                            System.out.println("List of book(s) with the most pages in the system:");
                            //use getbiggestbook method and display the books
                            getBiggestBooks(allItems[1]);
                            break;
                        //case 12 displays all medias
                        case "12":
                            //checks if there are medias
                            if(allItems[0].length == 0){
                                System.out.println("There aren't any medias in the system.");
                                break;
                            }
                            //displays medias
                            System.out.println("List of all media(s):");
                            for (int i = 0; i < allItems[0].length; i++) {
                                System.out.println(allItems[0][i] + "\n");
                            }
                            break;
                        //case 13 displays all books
                        case "13":
                            //checks if there are books
                            if(allItems[1].length == 0){
                                System.out.println("There aren't any books in the system.");
                                break;
                            }
                            //displays all books
                            System.out.println("List of all books(s):");
                            for (int i = 0; i < allItems[1].length; i++) {
                                System.out.println(allItems[1][i] + "\n");
                            }
                            break;
                        //case 14 displays all journals
                        case "14":
                            //checks if there are journals
                            if(allItems[2].length == 0){
                                System.out.println("There aren't any journals in the system.");
                                break;
                            }
                            //displays all journals
                            System.out.println("List of all journal(s):");
                            for (int i = 0; i < allItems[2].length; i++) {
                                System.out.println(allItems[2][i]);
                            }
                            break;
                        //case 15 displays all items
                        case "15":
                            //checks if there is at least an item
                            if(allItems[0].length == 0 && allItems[1].length == 0 && allItems[2].length == 0){
                                System.out.println("There aren't any items in the system.");
                                break;
                            }
                            //displays all items using a nested for loop
                            System.out.println("List of all item(s):");
                            for (int i = 0; i < allItems.length; i++) {
                                for (int j = 0; j < allItems[i].length; j++) {
                                    System.out.println(allItems[i][j]);
                                }
                            }
                            break;
                        //case 16 creates a deep copy of books
                        case "16":
                            if(allItems[1].length == 0){
                                System.out.println("There aren't any books in the system.");
                                break;
                            }
                            //utilizes copybooks method that uses copy constructor
                            Item[] booksCopy = copyBooks(allItems[1]);
                            System.out.println("Books copied successfully.");
                            System.out.println("List of copied books:");
                            for (int i = 0; i < booksCopy.length; i++) {
                                System.out.println(booksCopy[i] + "\n");
                            }
                            break;
                        //case 17 terminates the program
                        case "17":
                            System.out.println("Program will now terminate. Have a good day!");
                            break;
                        //default case is for invalid inputs
                        default:
                            System.out.println("Invalid input, try again.");
                            break;
                    }
                }while (!choice2.equals("17"));
                break;
        }
    }
}
