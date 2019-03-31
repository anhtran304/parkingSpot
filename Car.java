// ********************************************************************
// Car.java Author: Anh Tran - 101953626
//
// Class Car is used to store car registration,
// owner and show that owner is staff or not
//
// ********************************************************************

public class Car {

    // Instance variables
    private String registration;
    private String owner;
    private boolean isStaff = false;

    /**
     * Create a Car with empty registration, owner
     */
    Car() {
        this.registration = "";
        this.owner = "";
    }

    /**
     * Create a Car with given registration, owner and flag owner is staff or not
     */
    Car(String registration, String owner, boolean isStaff) {
        this.registration = registration;
        this.owner = owner;
        this.isStaff = isStaff;
    }

    /**
     * Show this Car's owner is staff or not
     */
    public boolean isStaff() {
        return isStaff;
    }

    /**
     * Get this Car's registration
     */
    public String getRegistration() {
        return registration;
    }

    /**
     * Return a string representation of this object.
     */
    public String toString() {
        return "Registration: " + registration + " - Owner: " + owner + "\n";
    }
}