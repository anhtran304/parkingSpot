import java.util.Scanner;
import java.lang.Exception;

// ********************************************************************
// Application.java Author: Anh Tran - 101953626
//
// Interface class to handle all inputs and output of Parking Spot System, 
// which will help manage cars at a parking site for a company 
// 
// ********************************************************************

public class Application {

    // Instance variables
    private static String inputString, pSlotId;
    private static Scanner keyboard = new Scanner(System.in);
    private static boolean quit = false, yesNo = false;
    private static int menu = 0;
    private static CarPark carPark = new CarPark();
    private static Car carnew = new Car();
    /**
    * Main method for Parking Spot System
    */
    public static void main(String[] args) {

         // Looping until user enter something else, not Y
        while(!quit) {
            menu = selectMenu();
            switch (menu) {
                case 1:
                    addParking();
                    break;
                case 2:
                    deleteParking();
                    break;
                case 3:
                    listAllParkings();
                    break;
                case 4:
                    parkCar();
                    break;
                case 5:
                    findCar();
                    break;
                case 6:
                    removeCar();
                    break;
                case 7:
                    System.exit(0);
                default:
                    break;
            }

            while (!yesNo) {
                System.out.print("Do you want to run again? (Y/N): ");
                if (keyboard.hasNext()) {
                    inputString = keyboard.nextLine().toUpperCase();
                    if (!inputString.equals("Y") && !inputString.equals("N")) {
                        System.out.println("Please enter Y for YES and N for NO");
                        continue;
                    } else if (inputString.charAt(0) == 'N') {
                        quit = true;
                        yesNo = true;
                    } else if (inputString.charAt(0) == 'Y') {
                        quit = false;
                        yesNo = true;
                    } else {
                        continue;
                    }
                }
            }
            yesNo = false;
            inputString = "";
        }
        quit = false;
        keyboard.close();
    }

    /**
    * Listing all menu in the application and return selected menu
    */
    public static void listMenu() {
        System.out.println("*** Welcome to Parking Spot System ****");
        System.out.println("================= Menu =================");
        System.out.println("1 - Adding a parking slot");
        System.out.println("2 - Deleting a parking slot by ID");
        System.out.println("3 - Listing all parking slots");
        System.out.println("4 - Park a car into slot");
        System.out.println("5 - Find a car by registration");
        System.out.println("6 - Remove a car by registation");
        System.out.println("7 - Exit");
        System.out.print("Enter your option: ");
    }

    /**
    * Select and return selected menu
    */
    public static int selectMenu() {
        // Looping until user enter correct option
        while(!quit) {
            listMenu();
            if (keyboard.hasNextLine()) {
                inputString = keyboard.nextLine();
                try {
                    menu = Integer.parseInt(inputString);
                } catch (Exception e) {
                    System.out.println("The input is NOT an integer. ");
                    continue;
                } 
                if (menu < 1 || menu > 7) {
                    System.out.println("The input is NOT from 1 to 7");
                    continue;
                } else {
                    quit = true;
                }
            } 
        }
        quit = false;
        return menu;
    }

    /**
    * Get input for parkingSlotId
    */
    public static String inputParkingSlotId() {
        // Looping until user enter correct parkingSlotID, not checking parking slot id is existing in here
        while (!quit) {
            System.out.print("Please enter Parking Slot ID ( 3 letter with fist one is letter followed by 2 digits ): ");
            if (keyboard.hasNextLine()) {
                try {
                    inputString = keyboard.nextLine().toUpperCase();
                } catch (Exception e) {
                    System.out.println("The input is NOT correct. ");
                    continue;
                }
                // Check if inputString exist
                if (!inputString.equals("")) {
                    if (!Character.isLetter(inputString.charAt(0))) {
                        System.out.println("The Parking Slot ID is NOT starting with a letter");
                        continue;
                    } else if (inputString.length() != 3) {
                        System.out.println("The Parking Slot ID has to be 3 chracter");
                        continue;
                    } else if (!Character.isDigit(inputString.charAt(1)) || !Character.isDigit(inputString.charAt(2))) {
                        System.out.println("The Parking Slot ID is NOT followed by a two-digit number after first letter");
                        continue;
                    } else {
                        quit = true;
                    }
                }

            }
        }
        quit = false;
        return inputString;
    }

    /**
    * Get input for Car's registration
    */
    public static String inputRegistration() {
        // Looping until user enter correct car's registration
        while (!quit) {
            System.out.print("Please enter Car's registration ( 5 letter with fist one is letter followed by 4 digits ): ");
            if (keyboard.hasNextLine()) {
                try {
                    inputString = keyboard.nextLine().toUpperCase();
                } catch (Exception e) {
                    System.out.println("The input is NOT correct. ");
                    continue;
                }
                // Check if inputString exist
                if (!inputString.equals("")) {
                    if (!Character.isLetter(inputString.charAt(0))) {
                        System.out.println("The Car's registration is NOT starting with a letter");
                        continue;
                    } else if (inputString.length() != 5) {
                        System.out.println("The Car's registration has to be 5 chracter");
                        continue;
                    } else if (!Character.isDigit(inputString.charAt(1)) || !Character.isDigit(inputString.charAt(2)) ||
                                !Character.isDigit(inputString.charAt(3)) || !Character.isDigit(inputString.charAt(4))) {
                        System.out.println("The Car's registration is NOT followed by a 4-digit number after first letter");
                        continue;
                    } else {
                        quit = true;
                    }
                }
            }
        }
        quit = false;
        return inputString;
    }

    /**
     * Get input for Car's owner
     */
    public static String inputOwner() {
        // Looping until user enter correct owner name
        while (!quit) {
            System.out.print("Please enter Car's Owner name: ");
            if (keyboard.hasNextLine()) {
                try {
                    inputString = keyboard.nextLine().toUpperCase();
                } catch (Exception e) {
                    System.out.println("The input is NOT correct. ");
                    continue;
                }
                if (!inputString.matches("[a-zA-Z]+")) {
                    System.out.println("Please enter only letter");
                    continue;
                } else {
                    quit = true;
                }
            }
        }
        quit = false;
        return inputString;
    }

    /**
     * Get input to identify Car Onwer is staff or not
     */
    public static boolean isStaff() {
        boolean returnIsStaff = false;
        // Looping until user enter correct owner isStaff
        while (!quit) {
            System.out.print("Is Car owner a staff? (Y/N): ");
            if (keyboard.hasNextLine()) {
                try {
                    inputString = keyboard.nextLine().toUpperCase();
                } catch (Exception e) {
                    System.out.println("The input is NOT correct. ");
                    continue;
                }
                if (!inputString.equals("Y") && !inputString.equals("N")) {
                    System.out.println("Please enter Y for YES and N for NO");
                    continue;
                } else {
                    if (inputString.equals("Y")) {
                        returnIsStaff = true;
                    }
                    quit = true;
                }
            }
        }
        quit = false;
        return returnIsStaff;
    }

    /**
     * Adding a parking slot with all information provided by users. 
     * Display message to get input then call method CarPark.addParkingSlot
     */
    public static void addParking() {
        boolean isForStaff = false;
        System.out.println("Adding a parking slot....");

        // Get input parkingSlotId and check it is exist into Car Park or not
        while (!quit) {
            pSlotId = inputParkingSlotId();
            if (carPark.packingSlotIsExist(inputString)) {
                System.out.println("The Parking Slot ID: " + pSlotId + " is EXISTING in Car Park");
                continue;
            } else {
                quit = true;
            }
        }
        quit = false;

        // Looping until user enter correct forStaff
        while (!quit) {
            System.out.print("Is this parking slot for staff? (Y/N): ");
            if (keyboard.hasNextLine()) {
                try {
                    inputString = keyboard.nextLine().toUpperCase();
                } catch (Exception e) {
                    System.out.println("The input is NOT correct. ");
                    continue;
                }
                if (!inputString.equals("Y") && !inputString.equals("N")) {
                    System.out.println("Please enter Y for YES and N for NO");
                    continue;
                } else {
                    if (inputString.equals("Y")) {
                        isForStaff = true;
                    }
                    quit = true;
                }
            }
        }
        quit = false;

        // Take action to add Parking Slot into Car Park
        try {
            carPark.addParkingSlot(new ParkingSlot(pSlotId, isForStaff));
            System.out.println("Successful add Parking Slot: " + pSlotId + " into Car Park");
        } catch (Exception e) {
            System.out.println("Unsuccessful add Parking Slot: " + pSlotId + " into Car Park. Because of " + e);
        }
    }

    /**
    * Deleting a parking slot by slot ID
    */
    public static void deleteParking() {
        
        // Check Car Park is empty or not
        if (carPark.carParkIsEmpty()) {
            System.out.println("Car Park do not have any parking slot");
        } else {
            System.out.println("Deleting a parking slot....");
            // Get input parkingSlotId and check it is exist into Car Park or not
            while (!quit) {
                pSlotId = inputParkingSlotId();
                if (!carPark.packingSlotIsExist(inputString)) {
                    System.out.println("The Parking Slot ID: " + pSlotId + " is NOT EXISTING in Car Park");
                    continue;
                } else {
                    quit = true;
                }
            }
            quit = false;
    
            // Take action to delete Parking Slot from Car Park
            try {
                if (carPark.deleteParkingSlot(pSlotId)) {
                    System.out.println("Successful delete Parking Slot: " + pSlotId + " from Car Park");
                } else {
                    System.out.println("Unsuccessful delete Parking Slot! Parking Slot: " + pSlotId + " is occupied");
                };
            } catch (Exception e) {
                System.out.println("Unsuccessful delete Parking Slot: " + pSlotId + " from Car Park. Because of " + e);
            }
        }
    }

    /**
    * Listing all parking slots
    */
    public static void listAllParkings() {
        
        // Check Car Park is empty or not
        if (carPark.carParkIsEmpty()) {
            System.out.println("Car Park do not have any parking slot");
        } else {
            System.out.println("Listing all parking slots....");
            System.out.println("******************************");
            // Take action to list all Parking Slot from Car Park by calling CarPark.listAll
            try {
                carPark.listAll();
            } catch (Exception e) {
                System.out.println("Unsuccessful listing all Parking Slot from Car Park. Because of " + e);
            }
        }
    }

    /**
    * Park a car into slot
    */
    public static void parkCar() {
        String registration = "", owner;
        boolean isStaff = false;

        // Check Car Park is empty or not
        if (carPark.carParkIsEmpty()) {
            System.out.println("Car Park do not have any parking slot");
        } else {
            // Display message to get input then call method from CarPark.addParkingSlot
            System.out.println("Parking a car into slot....");
    
            // Get input parkingSlotId and check it is exist into Car Park or not
            while (!quit) {
                pSlotId = inputParkingSlotId();
                if (!carPark.packingSlotIsExist(pSlotId)) {
                    System.out.println("The Parking Slot ID: " + pSlotId + " is NOT EXISTING in Car Park");
                    continue;
                } else if (carPark.packingSlotIsOccupied(pSlotId)) {
                    System.out.println("The Parking Slot ID: " + pSlotId + " is OCCUPIED in Car Park");
                    continue;
                } else {
                    quit = true;
                }
            }
            quit = false;
    
            // Get input Car Registration and check it is exist into Car Park or not
            while (!quit) {
                registration = inputRegistration();
                if (carPark.carIsExist(registration)) {
                    System.out.println("Car's registration: " + registration + " is EXISTING in Car Park");
                    continue;
                } else {
                    quit = true;
                }
            }
            quit = false;
    
            // Get input for car information
            owner = inputOwner();
            isStaff = isStaff();
    
            if (registration != "") {
                carnew = new Car(registration, owner, isStaff);
            } else {
                System.out.print("No registration input");
            }
    
            // Take action to park car into Parking Slot by calling CarPark.parkCarToSlot
            try {
                if (carPark.parkCarToSlot(pSlotId, carnew)) {
                    System.out.println("Successful park car into Parking Slot");
                } else {
                    // Park visitor not park into visitor slot, staff not park into staff slot
                    System.out.println("Unsuccessful park car into this Parking Slot. Because of wrong permission");
                };
            } catch (Exception e) {
                System.out.println("Unsuccessful park car into Parking Slot. Because of " + e);
            }
        }
    }

    /**
    * Find a car parked in Car Park by its registration
    */
    public static void findCar() {
        String carReg = "";
        ParkingSlot parkedSlot = new ParkingSlot();
        
        // Check Car Park is empty or not
        if (carPark.carParkIsEmpty()) {
            System.out.println("Car Park do not have any parking slot");
        } else if (!carPark.hasCar()) {
            System.out.println("There is NO car parked in this Car Park");
        } else {
            System.out.println("Find a car by registration....");
            // Get input Car's registration and check it is exist into Car Park or not
            while (!quit) {
                carReg = inputRegistration();
                if (!carPark.carIsExist(carReg)) {
                    System.out.println("Car's registration: " + carReg + " is NOT EXISTING in Car Park");
                    continue;
                }  else {
                    quit = true;
                }
            }
            quit = false;
            // Take action to get car and parking slot information by calling CarPark.findParkedCarSlot
            try {
                parkedSlot = carPark.findParkedCarSlot(carReg);
                System.out.println("Car's registration: " + carReg + " is parked at slot ID: " + parkedSlot.getParkingSlotID());
            } catch (Exception e) {
                System.out.println("Unsuccessful find car registration from Car Parking. Because of " + e);
            }
        }
    }

    /**
    * Remove a car from parking slot by registation
    */
    public static void removeCar() {
        String carReg = "";
        ParkingSlot parkedSlot = new ParkingSlot();
        
        // Check Car Park is empty or not
        if (carPark.carParkIsEmpty()) {
            System.out.println("Car Park do not have any parking slot");
        } else if (!carPark.hasCar()) {
            System.out.println("There is NO car parked in this Car Park");
        } else {
            System.out.println("Remove a car by registation....");
            // Get input Car's registration and check it is exist into Car Park or not
            while (!quit) {
                carReg = inputRegistration();
                if (!carPark.carIsExist(carReg)) {
                    System.out.println("Car's registration: " + carReg + " is NOT EXISTING in Car Park");
                    continue;
                } else {
                    quit = true;
                }
            }
            quit = false;
            // Take action to remove car from Car Park by calling CarPark.removeCarFromSlot
            try {
                parkedSlot = carPark.removeCarFromSlot(carReg);
                System.out.println("Successful remove car: " + carReg +" from Parking Slot ID: " + parkedSlot.getParkingSlotID());
            } catch (Exception e) {
                System.out.println("Unsuccessful remove car: " + carReg + " from Car Park. Because of " + e);
            }
        }
    }
}