import java.util.Scanner;
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}
abstract class Ride {
    protected String driverName;
    protected String vehicleNumber;
    protected double distance;
    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }
    public String getDriverName() {
        return driverName;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public double getDistance() {
        return distance;
    }
    public abstract double calculateFare();
}
class BikeRide extends Ride {
    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }
    @Override
    public double calculateFare() {
        return getDistance() * 10;
    }
}
class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }
    @Override
    public double calculateFare() {
        return getDistance() * 20;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String rideType = scanner.nextLine().trim().toLowerCase();
            double distance = Double.parseDouble(scanner.nextLine());
            if (distance <= 0) {
                System.out.println("Error: Distance must be a positive number.");
                return;
            }
            Ride ride; /
            switch (rideType) {
                case "bike":
                    ride = new BikeRide("Ravi Kumar", "MH12 AB-1234", distance);
                    break;
                case "car":
                    ride = new CarRide("Priya Sharma", "MH14 CD-5678", distance);
                    break;
                default:
                    throw new InvalidRideTypeException("Invalid ride type. Please choose 'bike' or 'car'.");
            }
            double fare = ride.calculateFare();
            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.printf("Distance: %.1f km\n", ride.getDistance());          
            System.out.printf("Fare: \u20B9%.2f\n", fare);
        } catch (InvalidRideTypeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input for distance. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
