package assignment;
import java.util.*;

public class BookMyShow {
    private List<Location> locations;
    private List<Combo> combos;
    private Parking parking;
    private Payment paymentProcessor;

    public BookMyShow() {
        this.locations = new ArrayList<>();
        this.combos = new ArrayList<>();
        this.parking = new Parking(100); // Increased to 100 parking spots
        this.paymentProcessor = new Payment();
        initializeSystem();
    }

    private void initializeSystem() {
        // Initialize locations
        Location cityMall = new Location("City Mall");
        Location downtownCinema = new Location("Downtown Cinema");
        Location suburbanMultiplex = new Location("Suburban Multiplex");

        locations.add(cityMall);
        locations.add(downtownCinema);
        locations.add(suburbanMultiplex);

        // Initialize movies and screens
        Movie avengers = new Movie("Avengers: Endgame");
        Movie lionKing = new Movie("The Lion King");
        Movie inception = new Movie("Inception");
        Movie parasite = new Movie("Parasite");

        // Add screens to movies
        avengers.addScreen(new Screen(1, 100));
        avengers.addScreen(new Screen(2, 80));
        lionKing.addScreen(new Screen(1, 120));
        inception.addScreen(new Screen(1, 90));
        parasite.addScreen(new Screen(1, 70));

        // Add movies to locations
        cityMall.addMovie(avengers);
        cityMall.addMovie(lionKing);
        downtownCinema.addMovie(inception);
        downtownCinema.addMovie(parasite);
        suburbanMultiplex.addMovie(avengers);
        suburbanMultiplex.addMovie(lionKing);
        suburbanMultiplex.addMovie(inception);

        // Initialize combos
        combos.add(new Combo("Small Popcorn + Soda", 8.99));
        combos.add(new Combo("Large Popcorn + Soda", 10.99));
        combos.add(new Combo("Nachos + Cheese", 7.99));
        combos.add(new Combo("Hot Dog + Soda", 9.99));
        combos.add(new Combo("Family Pack: 2 Large Popcorn + 4 Sodas", 24.99));

        System.out.println("System initialized with:");
        System.out.println("- " + locations.size() + " locations");
        System.out.println("- " + combos.size() + " combo offers");
        System.out.println("- " + parking.getAvailableSpots() + " parking spots");
    }

    public void start() {
        System.out.println("BookMyShow is Starting...");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== BookMyShow Menu =====");
            System.out.println("1. Book a ticket");
            System.out.println("2. Cancel a ticket");
            System.out.println("3. View combos");
            System.out.println("4. Reserve parking");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookTicket(scanner);
                    break;
                case 2:
                    cancelTicket(scanner);
                    break;
                case 3:
                    viewCombos();
                    break;
                case 4:
                    reserveParking(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using BookMyShow!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void bookTicket(Scanner scanner) {
        System.out.println("\n----- Booking a Ticket -----");

        // Display available locations
        System.out.println("Available locations:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i).getName());
        }

        System.out.print("Select a location (enter number): ");
        int locationChoice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        Location selectedLocation = locations.get(locationChoice);

        // Display available movies
        System.out.println("Available movies at " + selectedLocation.getName() + ":");
        List<Movie> movies = selectedLocation.getMovies();
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).getName());
        }

        System.out.print("Select a movie (enter number): ");
        int movieChoice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        Movie selectedMovie = movies.get(movieChoice);

        // For simplicity, let's assume each movie has one screen
        Screen screen = selectedMovie.getScreens().get(0);

        System.out.print("Enter seat number (e.g., A1): ");
        String seatChoice = scanner.nextLine();

        if (screen.bookSeat(seatChoice)) {
            System.out.println("Ticket booked successfully for " + selectedMovie.getName() + " at " + selectedLocation.getName() + ", Seat: " + seatChoice);
        } else {
            System.out.println("Failed to book ticket. Seat might be already taken.");
        }
    }

    private void cancelTicket(Scanner scanner) {
        System.out.println("\n----- Canceling a Ticket -----");
        // Implement cancellation logic here
        System.out.println("Ticket cancellation functionality not implemented yet.");
    }

    private void viewCombos() {
        System.out.println("\n----- Available Combos -----");
        for (Combo combo : combos) {
            System.out.println(combo.getName() + " - $" + combo.getPrice());
        }
    }

    private void reserveParking(Scanner scanner) {
        System.out.println("\n----- Reserving Parking -----");
        if (parking.reserveSpot()) {
            System.out.println("Parking spot reserved successfully.");
        } else {
            System.out.println("No parking spots available. You've been added to the waiting list.");
        }
    }

    public static void main(String[] args) {
        BookMyShow system = new BookMyShow();
        system.start();
    }
}

class Location {
    private String name;
    private List<Movie> movies;

    public Location(String name) {
        this.name = name;
        this.movies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}

class Movie {
    private String name;
    private List<Screen> screens;

    public Movie(String name) {
        this.name = name;
        this.screens = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }
}

class Screen {
    private int number;
    private int capacity;
    private Set<String> availableSeats;
    private Set<String> bookedSeats;

    public Screen(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
        this.availableSeats = new HashSet<>();
        this.bookedSeats = new HashSet<>();
        initializeSeats();
    }

    private void initializeSeats() {
        int rows = (int) Math.ceil(capacity / 10.0);
        for (char row = 'A'; row < 'A' + rows; row++) {
            for (int seatNum = 1; seatNum <= 10 && availableSeats.size() < capacity; seatNum++) {
                availableSeats.add(row + String.valueOf(seatNum));
            }
        }
    }

    public boolean bookSeat(String seat) {
        if (availableSeats.contains(seat)) {
            availableSeats.remove(seat);
            bookedSeats.add(seat);
            return true;
        }
        return false;
    }
}

class Combo {
    private String name;
    private double price;

    public Combo(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Parking {
    private int availableSpots;
    private Queue<String> waitingList;

    public Parking(int totalSpots) {
        this.availableSpots = totalSpots;
        this.waitingList = new LinkedList<>();
    }

    public boolean reserveSpot() {
        if (availableSpots > 0) {
            availableSpots--;
            return true;
        }
        waitingList.offer("Customer"); // In a real system, you'd add customer details
        return false;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }
}

class Payment {
    public boolean processPayment(String method, double amount) {
        // Implement payment logic here
        return true; // Placeholder
    }
}