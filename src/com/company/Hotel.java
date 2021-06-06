package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Hotel{
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Room [] Hotel = new Room[8];
        String firstname;
        String surname;
        int creditNumber;
        int guestCount=0;
        int roomNum = 0;
        String roomName = "";
        initialize(Hotel);

        while (true) {
            System.out.println("**********Welcome to TRAVELIFY**********\n");
            System.out.println("---------Select your preference---------\n" +
                    "A: Add customer to room\n" +
                    "V: View all rooms\n" +
                    "E: Display Empty rooms\n" +
                    "D: Delete customer from room\n" +
                    "F: Find room from customer name\n" +
                    "S: Store program data into file\n" +
                    "L: Load program data from file\n" +
                    "O: View guests ordered alphabetically\n");

            System.out.println("Enter your preference:");
            char option = (char) System.in.read();
            option= Character.toUpperCase(option);

            switch (option) {
                case 'A': {

                    emptyRooms(Hotel);
                    System.out.print("Enter room number (1-8): ");
                    roomNum = input.nextInt()-1 ;
                    System.out.println("Enter first name: ");
                    firstname = input.next();
                    System.out.println("Enter Surname: ");
                    surname = input.next();
                    System.out.println("Enter credit card number: ");
                    creditNumber = input.nextInt();
                    System.out.println("Enter number of Guests: ");
                    guestCount = input.nextInt();
                    Queue.addQueue(roomName);


                    addCustomer(Hotel,roomNum,firstname,surname,creditNumber,guestCount);
                    break;
                }
                case 'V':
                    viewRooms(Hotel);
                    break;
                case 'E':
                    emptyRooms(Hotel);
                    break;
                case 'D': {
                    System.out.print("Enter customer name: ");
                    firstname = input.next();
                    System.out.println();
                    Queue.delete(roomName);
                    deleteCustomer(Hotel);
                    break;
                }
                case 'F':
                    findRoom(Hotel);
                    break;
                case 'S':
                    storeData(Hotel);
                    break;
                case 'L':
                    loadData(Hotel);
                    break;
                case 'O':
                    guestsOrder(Hotel);
                    break;

                default:
                    System.out.println("!!!Wrong Entry!!!");
                    break;
            }
        }
    }



    private static void initialize(Room[] Hotel) {
        for (int x = 0; x < Hotel.length; x++) {
            Hotel[x] = new Room();
            Hotel[x].setFirstname("Vacant");
            Hotel[x].setSurname("");
            Hotel[x].setCreditNumber(0);
            Hotel[x].setGuestCount(0);

            Hotel[x].setRoom(x+1);

        }
    }

    private static void addCustomer(Room[] Hotel, int roomNum,String firstname, String surname, int creditNumber,int guestCount) {

        Hotel[roomNum].setFirstname(firstname);
        Hotel[roomNum].setSurname(surname);
        Hotel[roomNum].setCreditNumber(creditNumber);
        Hotel[roomNum].setGuestCount(guestCount);

        System.out.println("Customer Added!");
        viewRooms(Hotel);


    }

    private static void viewRooms(Room[] Hotel) {
        for (int x = 0; x < Hotel.length; x++) {

                System.out.println("Room " + Hotel[x].getRoom() + " is occupied by "  +Hotel[x].getFirstname()+" "+Hotel[x].getSurname()+"\n"+"Credit Card No: "+Hotel[x].getCreditNumber()+"\n"+"No of Guests: "+Hotel[x].getGuestCount());
            }
        }


    private static void emptyRooms(Room[] Hotel ) {
        for (int x = 0; x < Hotel.length; x++) {
            if(Hotel[x].getFirstname().equals("Vacant")) {
                System.out.println("Room "+Hotel[x].getRoom()+" is Vacant.");
            }
        }
    }

    private static void deleteCustomer(Room[] Hotel) {
        Scanner input = new Scanner(System.in);
        System.out.println("The customer in which room number do you want to delete?");
        int roomNum = input.nextInt() - 1;
        Hotel[roomNum].setFirstname("Vacant");
        Hotel[roomNum].setSurname("");
        Hotel[roomNum].setCreditNumber(0);
        Hotel[roomNum].setGuestCount(0);


        System.out.println("Entry Deleted!");
        viewRooms(Hotel);
    }

    private static void findRoom(Room[] Hotel) {
        Scanner input = new Scanner(System.in);
        String roomName;
        System.out.println("Enter name of the customer to search for:");
        roomName = input.next();
        int x;
        boolean Checker = false;
        for (x = 0; x < Hotel.length; x++) {
            if (roomName.equals(Hotel[x].getFirstname())) {
                System.out.println("The room number with that customer is " + Hotel[x].getRoom()+".");
                Checker = true;
            }
        }
        if (Checker == false) {
            System.out.println("There are no rooms booked with that name\n(make sure you've used the correct CAP's)");
        }
    }

    private static void guestsOrder(Room[] Hotel) {
        System.out.println("The customer names sorted alphabetically are as follows:");
        String[] myArray = new String[Hotel.length];
        for (int i = 0; i < Hotel.length; i++) {
            myArray[i] = Hotel[i].getFirstname();
        }

        Arrays.sort(myArray);
        for (int a = 0; a < myArray.length; a++) {
            System.out.println(myArray[a]);
        }
    }

    private static void storeData(Room[] Hotel) throws IOException {
        try {
            File file = new File("Hotel.txt");
            FileWriter myWriter = new FileWriter("Hotel.txt");
            int x = 0;
            if(Hotel[x].equals("Vacant")) {
                myWriter.write("Room "+Hotel[x].getRoom()+" is Vacant.");

            }else{
                myWriter.write("Room " + Hotel[x].getRoom() + " is occupied by " + Hotel[x].getFirstname() );
            }


            myWriter.close();
            System.out.println("File Writing Successful");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void loadData(Room[] Hotel) throws IOException {
        try {
            File file = new File("Hotel.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
            System.out.println("File Loading Successful");
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }


}


