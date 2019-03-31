import java.util.ArrayList;
import java.util.Iterator;

// ********************************************************************
// CarPark.java Author: Anh Tran - 101953626
//
// Database class to maintain a list of available parking spots,
// which can find a slot, add a slot, delete a slot, and a list of all slots in car park
//
// ********************************************************************

public class CarPark {

    // Instance variables
    private ArrayList<ParkingSlot> parkingSlots;

    /**
     * Instructor to create a new, empty parkingSlots database - Arraylist of ParkingSlot
     */
    public CarPark() {
        parkingSlots = new ArrayList<ParkingSlot>();
    }

    /**
     * Add a ParkingSlot to the database
     */
    public void addParkingSlot(ParkingSlot p) {
        parkingSlots.add(p);
    }

    /**
     * Delete a ParkingSlot from the database with parkingSlotID
     */
    public boolean deleteParkingSlot(String parkingSlotID) {
        boolean deleteSuccess = false;
        for (int i = 0; i < parkingSlots.size(); i++) {
            if (parkingSlots.get(i).getParkingSlotID().equals(parkingSlotID)) {
                if (!parkingSlots.get(i).isOccupied()) {
                    parkingSlots.remove(parkingSlots.get(i));
                    deleteSuccess = true;
                } else {
                    deleteSuccess = false;
                }
            }
        }
        return deleteSuccess;
    }

    /**
     * List all ParkingSlot currently in the Car Park
     */
    public void listAll() {
        for (int i = 0 ; i < parkingSlots.size(); i++) {
            System.out.println(parkingSlots.get(i).toString());
            System.out.println("----------------------");
        }
    }

    /**
     * Check ParkingSlot is exist in the Car Park
     */
    public boolean packingSlotIsExist(String pSlotId) {
        boolean isExist = false;
        for (int i = 0 ; i < parkingSlots.size(); i++) {
            if (parkingSlots.get(i).getParkingSlotID().equals(pSlotId)) {
                isExist = true;
            }
        }
        return isExist;
    }

    /**
     * Check if Car park has any parking slot or not
     */
    public boolean carParkIsEmpty() {
        boolean isEmpty = false;
        if (parkingSlots.size() == 0) {
            isEmpty = true;
        }
        return isEmpty;
    }

    /**
     * Check if Car park has any parked car or not
     */
    public boolean hasCar() {
        boolean hasCar = false;
        if (parkingSlots.size() != 0) {
            for (int i = 0 ; i < parkingSlots.size(); i++) {
                if (parkingSlots.get(i).isOccupied()) {
                    hasCar = true;
                }
            }
        }
        return hasCar;
    }

    /**
     * Check ParkingSlot is occupied in the Car Park
     */
    public boolean packingSlotIsOccupied(String pSlotId) {
        boolean isOccupied = false;
        for (int i = 0 ; i < parkingSlots.size(); i++) {
            if (parkingSlots.get(i).getParkingSlotID().equals(pSlotId) && parkingSlots.get(i).isOccupied()) {
                isOccupied = true;
            }
        }
        return isOccupied;
    }

    /**
     * Check Car registration exist in the Car Park
     */
    public boolean carIsExist(String reg) {
        boolean carIsExist = false;
        for (int i = 0; i < parkingSlots.size(); i++) {
            if (parkingSlots.get(i).getCar().getRegistration().equals(reg)) {
                carIsExist = true;
            }
        }
        return carIsExist;
    }

    /**
     * Park car into parking slot
     */
    public boolean parkCarToSlot(String pSlotId, Car parkedCar) {
        boolean isParked = false;
        for (int i = 0 ; i < parkingSlots.size(); i++) {
            if (parkingSlots.get(i).getParkingSlotID().equals(pSlotId)) {
                if (parkingSlots.get(i).forStaff() && parkedCar.isStaff()) {
                    parkingSlots.get(i).parkCar(parkedCar);
                    isParked = true;
                } else if (!parkingSlots.get(i).forStaff() && !parkedCar.isStaff()) {
                    parkingSlots.get(i).parkCar(parkedCar);
                    isParked = true;
                } else {
                    isParked = false;
                }
            }
        }
        return isParked;
    }

    /**
     * Find a ParkingSlot with Car parked in it by car's registration
     */
    public ParkingSlot findParkedCarSlot(String registration) {
        ParkingSlot parkedSlot = new ParkingSlot();
        for (int i = 0; i < parkingSlots.size(); i++) {
            if (parkingSlots.get(i).getCar().getRegistration().equals(registration)) {
                parkedSlot = parkingSlots.get(i);
            }
        }
        return parkedSlot;
    }

    /**
     * Remove a car from Car Park
     */
    public ParkingSlot removeCarFromSlot(String registration) {
        ParkingSlot parkedSlot = new ParkingSlot();
        for (int i = 0; i < parkingSlots.size(); i++) {
            if (parkingSlots.get(i).getCar().getRegistration().equals(registration)) {
                parkedSlot = parkingSlots.get(i);
                parkingSlots.get(i).removeCar();
            }
        }
        return parkedSlot;
    }
}