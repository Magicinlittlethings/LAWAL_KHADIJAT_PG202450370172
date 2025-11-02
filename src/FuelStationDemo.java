import java.util.Random;

/**
 * * This file contains a complete Java demonstration of the core
 * Object-Oriented Programming (OOP) concepts: Abstraction,
 * Encapsulation, Inheritance, and Polymorphism, using a simple
 * Automated Fuel Station Management system.
 */

/**
 * Vehicle represents the abstract concept of any vehicle that needs fuel.
 * It enforces that all subclasses must implement certain behaviors.
 */
abstract class Vehicle {

    //ENCAPSULATION: Attributes are private to protect the data
    private String licensePlate;
    private double fuelCapacity;
    private double currentFuelLevel;

    public Vehicle(String licensePlate, double fuelCapacity, double currentFuelLevel) {
        this.licensePlate = licensePlate;
        this.fuelCapacity = fuelCapacity;
        this.currentFuelLevel = currentFuelLevel;
    }

    //ENCAPSULATION: Public Getter Methods for Controlled read-only access
    public String getLicensePlate() {
        return licensePlate;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public double getCurrentFuelLevel() {
        return currentFuelLevel;
    }

    // ENCAPSULATION: Public Setter Method for Controlled modification
    public void refuel(double amount) {
        if (amount > 0) {
            currentFuelLevel += amount;
            if (currentFuelLevel > fuelCapacity) {
                currentFuelLevel = fuelCapacity;
                System.out.println("Tank is now full!");
            }
        }
    }

    // ABSTRACTION:
    // It defines the required behavior which is calculating how much fuel is needed)
    // but the implementation details are hidden from the parent class.
    public abstract double calculateRefuelAmount();

    // Another abstract method for polymorphic display
    public abstract String getVehicleType();
}

/**
 * Car is a specific type of Vehicle.
 */
class Car extends Vehicle {
    private String fuelType = "Regular Unleaded";

    public Car(String licensePlate, double currentFuelLevel) {
        // INHERITANCE: Calling the parent constructor (Vehicle)
        super(licensePlate, 50.0, currentFuelLevel);
    }

    //POLYMORPHISM: Provides the specific implementation for the abstract method
    @Override
    public double calculateRefuelAmount() {
        double needed = getFuelCapacity() - getCurrentFuelLevel();
        return needed;
    }

    // POLYMORPHISM: Implementation of the abstract type getter
    @Override
    public String getVehicleType() {
        return "Car (Fuel: " + fuelType + ")";
    }
}

/**
 * Truck is another specific type of Vehicle with different characteristics.
 */
class Truck extends Vehicle {
    private boolean isDiesel;

    public Truck(String licensePlate, double currentFuelLevel, boolean isDiesel) {
        //INHERITANCE: Calling the parent constructor (Vehicle) ---

        super(licensePlate, 200.0, currentFuelLevel);
        this.isDiesel = isDiesel;
    }

    //POLYMORPHISM: Provides a different implementation for the abstract method ---
    @Override
    public double calculateRefuelAmount() {
        double needed = getFuelCapacity() - getCurrentFuelLevel();
        double refuelIncrement = 100.0;
        return Math.ceil(needed / refuelIncrement) * refuelIncrement;
    }

    //POLYMORPHISM: Implementation of the abstract type getter ---
    @Override
    public String getVehicleType() {
        return "Truck (Diesel: " + (isDiesel ? "Yes" : "No") + ")";
    }
}


/**
 * FuelPump manages the reserve and processes transactions.
 */
class FuelPump {
    // ENCAPSULATION: Private attributes to maintain pump state integrity
    private int pumpID;
    private double pricePerLitre;
    private double fuelReserveInLitres;

    public FuelPump(int pumpID, double pricePerLitre, double initialReserve) {
        this.pumpID = pumpID;
        this.pricePerLitre = pricePerLitre;
        this.fuelReserveInLitres = initialReserve;
    }

    public void startRefuel(Vehicle v) {
        System.out.println("\n-------------------------------------------");
        System.out.println("Pump " + pumpID + " serving " + v.getLicensePlate());

        //POLYMORPHISM:
        // The pump calls the same method on both Car and Truck objects,
        // but gets the appropriate result from each based on its type.
        double amountNeeded = v.calculateRefuelAmount();
        System.out.println("Vehicle Type: " + v.getVehicleType());
        System.out.printf("Current Level: %.2f L. Needs: %.2f L.\n", v.getCurrentFuelLevel(), amountNeeded);

        dispenseFuel(v, amountNeeded);
    }

    // ENCAPSULATION: This method controls access and modification to 'fuelReserveInLitres'
    private void dispenseFuel(Vehicle v, double amount) {
        if (amount > fuelReserveInLitres) {
            System.out.printf("ERROR: Not enough fuel! Only %.2f L left in pump.\n", fuelReserveInLitres);
            amount = fuelReserveInLitres;
        }

        double totalCost = amount * pricePerLitre;
        fuelReserveInLitres -= amount;

        v.refuel(amount);

        System.out.printf("Dispensed %.2f L. Total Cost: $%.2f\n", amount, totalCost);
        System.out.printf("Pump %d Reserve remaining: %.2f L\n", pumpID, fuelReserveInLitres);
    }
}

// ====================================================================
// MAIN DEMONSTRATION CLASS
// ====================================================================
public class FuelStationDemo {
    public static void main(String[] args) {
        System.out.println("<<< FUEL STATION MANAGEMENT SYSTEM DEMO >>>");

        // --- OBJECTS: Creating instances of classes ---

        // 1. Create Vehicles
        Car sedan = new Car("ABC-123", 10.0);
        Truck hauler = new Truck("XYZ-987", 50.0, true);

        // 2. Create Fuel Pumps
        FuelPump pump1 = new FuelPump(1, 1.55, 500.0);
        FuelPump pump2 = new FuelPump(2, 1.40, 50.0);




        // Test Case 1: Car refuels at Pump 1
        // Demonstrates Inheritance (Vehicle properties used) and Encapsulation (Pump reserve used)
        pump1.startRefuel(sedan);

        // Test Case 2: Truck refuels at Pump 1
        // Demonstrates Polymorphism (Truck calculates needs differently)
        pump1.startRefuel(hauler);

        // Test Case 3: Vehicle attempts to refuel at a low-reserve pump
        // Demonstrates Encapsulation/Controlled Access (Pump prevents negative reserve)
        pump2.startRefuel(sedan);

        System.out.println("\n<<< DEMO COMPLETE >>>");

        System.out.printf("Final check: Truck %s has %.2f L of fuel.\n",
                hauler.getLicensePlate(), hauler.getCurrentFuelLevel());
    }
}
