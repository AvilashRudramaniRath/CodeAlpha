import java.util.*;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean isAvailable;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public void bookRoom() {
        this.isAvailable = false;
    }

    public void cancelRoom() {
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Category: " + category + ", Price: $" + price + ", Available: " + isAvailable;
    }
}

class Reservation {
    String customerName;
    Room room;
    String checkInDate;
    String checkOutDate;
    double amountPaid;

    public Reservation(String customerName, Room room, String checkInDate, String checkOutDate, double amountPaid) {
        this.customerName = customerName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.amountPaid = amountPaid;
    }

    @Override
    public String toString() {
        return "Customer Name: " + customerName + "\nRoom: " + room.roomNumber + " (" + room.category + ")\nCheck-In: " + checkInDate +
                "\nCheck-Out: " + checkOutDate + "\nAmount Paid: $" + amountPaid;
    }
}

class HotelReservationSystem {
    List<Room> rooms = new ArrayList<>();
    List<Reservation> reservations = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public HotelReservationSystem() {
        // Initializing rooms
        rooms.add(new Room(101, "Standard", 100.0));
        rooms.add(new Room(102, "Standard", 100.0));
        rooms.add(new Room(201, "Deluxe", 200.0));
        rooms.add(new Room(202, "Deluxe", 200.0));
        rooms.add(new Room(301, "Suite", 300.0));
        rooms.add(new Room(302, "Suite", 300.0));
    }

    public void searchAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    public void makeReservation() {
        System.out.println("Enter your name:");
        String customerName = scanner.nextLine();

        System.out.println("Enter Check-in Date (dd/mm/yyyy):");
        String checkInDate = scanner.nextLine();

        System.out.println("Enter Check-out Date (dd/mm/yyyy):");
        String checkOutDate = scanner.nextLine();

        searchAvailableRooms();

        System.out.println("Enter Room Number to Book:");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Invalid room number or room is not available.");
            return;
        }

        System.out.println("Enter payment amount:");
        double amountPaid = scanner.nextDouble();

        selectedRoom.bookRoom();
        Reservation reservation = new Reservation(customerName, selectedRoom, checkInDate, checkOutDate, amountPaid);
        reservations.add(reservation);

        System.out.println("Reservation successful!");
        System.out.println(reservation);
    }

    public void viewReservations() {
        System.out.println("All Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
            System.out.println("-----------------------------");
        }
    }

    public void run() {
        while (true) {
            System.out.println("Hotel Reservation System");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View All Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

