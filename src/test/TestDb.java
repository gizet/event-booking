package test;


import connection.JdbcConnection;
import entities.Entity;
import entities.Event;
import entities.Roles;
import entities.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import service.EventService;
import service.EventServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class TestDb {
    public static void main(String[] args) {
        JdbcConnection.createConnection();
//getMyEvents();
        getAllUsers();
//        updateUserRole();

//        getUserById();
//        getAllEvents();
//        createEvent();
//        updateEvent();
//        createEvent();
//        createEvent();
//        createEvent();
//        printTable(Tables.EVENT_TABLE, Event.class.getName());
//        printTable(Tables.USER_TABLE, Event.class.getName());
    }

    public static void getMyEvents() {
        EventService eventService = new EventServiceImpl();
        List<Event> events = eventService.getEventsOrganisedByUser(2);
        printResultList(events);
    }

    public static void getAllUsers() {
        UserService userService = new UserServiceImpl();
        List<User> users = userService.getAllUsers();
        printResultList(users);
    }

    public static void updateUserRole() {
        UserService userService = new UserServiceImpl();
        userService.updateUserRole(2, Roles.ADMINISTRATOR);
    }

    public static void getUserById() {
        UserService userService = new UserServiceImpl();
        User user = userService.getUserById(2);
        System.out.println("user with id 2  is " + user);
        String name = userService.getOrganiserName(2);
        System.out.println("Organiser name " + name);
    }

    public static void updateEvent() {
        EventService eventService = new EventServiceImpl();
        Event.Builder builder = new Event.Builder();
        builder.eventId(2);
        builder.title("test event updated").description("Description updated").isOnline(true).location("London NEW").startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).constraints("COVID constraints 2").isBookingAllowed(true);

        eventService.updateEvent(builder.createEvent());
    }

    public static void getAllEvents() {
        EventService eventService = new EventServiceImpl();
        List<Event> events = eventService.getAllEvents();
        printResultList(events);
    }

    public static void createEvent() {
        EventService eventService = new EventServiceImpl();
        Event.Builder builder = new Event.Builder();
        builder.title("test event").description("Description test").isOnline(false).location("London").startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).constraints("COVID constraints").isBookingAllowed(true).organiserId(2);


        eventService.addEvent(builder.createEvent());
    }

    public static void printTable(String table, String className) {

//        JdbcConnection.createConnection();
        System.out.println("-----------------------------------");
        System.out.println(String.format("ALL %s", table));
        String query = String.format("SELECT * FROM %s", table);
        ArrayList<Entity> obj = JdbcConnection.getAll(query, className);
        printResultList(obj);
        System.out.println("------------------------------------");
    }

    public static void printResultList(List list) {
        list.stream().forEach(x -> {
            System.out.println(x);
            System.out.println("***");
        });
    }
}
