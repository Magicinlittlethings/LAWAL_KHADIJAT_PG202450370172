Automated Fuel Station Management System (Java OOP Project)

This mini-project demonstrates the four core pillars of Object-Oriented Programming (OOP)—Abstraction, Encapsulation, Inheritance, and Polymorphism—using a simulation of an automated fuel station.

Project Goal

The primary goal is to structure a system where different types of vehicles and fuel pumps interact, ensuring that data integrity is maintained and complex logic is handled through clear object relationships.

Demonstration of OOP Principles

Implementation Details (Referenced in Code)

1. Abstraction

Hiding the complex implementation details and showing only the necessary features.

The Vehicle class is declared abstract. It defines a common interface (calculateRefuelAmount()) that all vehicles must adhere to, but it doesn't define how they calculate it. The complex logic is assigned to the concrete subclasses.

2. Encapsulation

Bundling data (attributes) with the methods that operate on that data, restricting direct access.

All attributes in Vehicle (currentFuelLevel) and FuelPump (fuelReserveInLitres) are marked private. Access and modification are only allowed through controlled public methods like refuel(amount) or the private dispenseFuel(). This prevents external code from accidentally setting the fuel reserve to a negative number.

3. Inheritance

The mechanism where one class (child/subclass) acquires the properties and methods of another class (parent/superclass).

The Car and Truck classes both extend Vehicle. They automatically inherit common properties (like licensePlate, fuelCapacity) and methods (like getLicensePlate()), reducing code duplication.

4. Polymorphism

The ability of an object to take on many forms, allowing a single method call to behave differently based on the object type.

The FuelPump.startRefuel() method calls v.calculateRefuelAmount(). This is polymorphic:



- When a Car object is passed, the Car's specific, small-tank calculation runs.



- When a Truck object is passed, the Truck's specific, large-tank, bulk-refuel calculation runs.

