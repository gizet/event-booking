package controller;

import entities.Event;
import entities.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import service.EventService;
import service.EventServiceImpl;
import service.UserService;
import service.UserServiceImpl;

import java.util.List;

import static utils.DateHelper.formatLocalDateTime;
import static utils.Style.*;

public class MyEventsController {

    private EventService eventService = new EventServiceImpl();

    private UserService userService = new UserServiceImpl();

    User loggedUser;

    public MyEventsController(User user) {
        this.loggedUser = user;
    }

    public void addEvents(VBox addEventVBox) {

        Label label = new Label("Text");
        addEventVBox.getChildren().add(label);
        addEventVBox.setVisible(true);
//        Event eventEntity = new Event();
//        HBox titleHbox = new HBox();
//
//        Label titleLabel = new Label();
//        titleLabel.setText("Title");
//        styleLabel(titleLabel, false);
//        TextField titleFiled = new TextField();
//
//        titleHbox.getChildren().addAll(titleLabel, titleFiled);
//
//        HBox descriptionHbox = new HBox();
//        Label descriptionLabel = new Label();
//        descriptionLabel.setText("Description");
//        styleLabel(descriptionLabel, false);
//        TextField descriptionField = new TextField();
//        descriptionField.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                eventEntity.setTitle(event.getTarget().toString());
//            }
//        });
//
//
//        descriptionHbox.getChildren().addAll(descriptionLabel, descriptionField);
//
//
//        addEventVBox.getChildren().addAll(titleHbox, descriptionHbox);
//
//
//        //redirectPane(actionEvent, CARD);
//
//        eventEntity.setEventId(1);
//        eventEntity.setLocation("Roman");
//        eventEntity.setTitle("Teatru");
//        eventEntity.setBookingAllowed(true);
//        eventEntity.setOnline(true);
//        eventEntity.setConstraints("10");
//        eventEntity.setDescription("Testing an event");
//        eventEntity.setStartDate(LocalDateTime.now());
//        eventEntity.setEndDate(LocalDateTime.of(2020, 10, 21, 9, 15, 0));
//        if (eventService.addEvent(eventEntity)) {
//            //use a notification and a button
//            System.out.println("Event created");
//        }
    }


    void getEvents(VBox eventVbox) {
        addHeader(eventVbox);
        //get all events
        List<Event> events = eventService.getEventsOrganisedByUser(loggedUser.getUserId());
        for (int i = 0; i < events.size(); i++) {
            Event eventEntity = events.get(i);

            HBox hbox = createHbox(eventEntity);
            styleHBox(hbox, i);

            eventVbox.getChildren().add(hbox);
            eventVbox.setSpacing(5);
            eventVbox.setVisible(true);
            VBox.setMargin(hbox, new Insets(5, 5, 5, 5));
            eventVbox.setPadding(new Insets(10, 10, 10, 10));
        }
    }


    private void addHeader(VBox vBox) {
        HBox hBox = new HBox();

        ImageView imageViewLabel = new ImageView();
        imageViewLabel.setImage(new Image(getClass().getResourceAsStream("/resources/images/eventIcon.png")));
        imageViewLabel.setFitHeight(50);
        imageViewLabel.setFitWidth(50);
        imageViewLabel.setVisible(false);


        Label titleLabel = new Label();
        titleLabel.setText("Event Title");
        styleLabel(titleLabel, true);

        Label descriptionLabel = new Label();
        descriptionLabel.setText("Description");
        styleLabel(descriptionLabel, true);

        Label locationLabel = new Label();
        locationLabel.setText("Location");
        styleLabel(locationLabel, true);

        Label startDateLabel = new Label();
        startDateLabel.setText("Event starts");
        styleLabel(startDateLabel, true);

        Label endDateLabel = new Label();
        endDateLabel.setText("Event ends");
        styleLabel(endDateLabel, true);

        Label noOfSeatsLabel = new Label();
        noOfSeatsLabel.setId("noOfSeats");
        noOfSeatsLabel.setText("Seats");
        styleLabel(noOfSeatsLabel, true);


        Label onlineLabel = new Label();
        onlineLabel.setId("online");
        onlineLabel.setText("Online");
        styleLabel(onlineLabel, true);

        Label organiserLabel = new Label();
        organiserLabel.setText("Organiser");
        styleLabel(organiserLabel, true);


        Label buttonLabel = new Label();
        buttonLabel.setText("Edit Event");
        styleLabel(buttonLabel, true);


        hBox.getChildren().addAll(
                imageViewLabel,
                titleLabel,
                descriptionLabel,
                locationLabel,
                startDateLabel,
                endDateLabel,
                noOfSeatsLabel,
                onlineLabel,
                organiserLabel,
                buttonLabel);

        styleHBox(hBox, -1);
        vBox.getChildren().add(hBox);
    }


    private HBox createHbox(Event eventEntity) {
        HBox hBox = new HBox();

        ImageView imageView = new ImageView();
        String image;
        if (true) { //change based of available seats.
            image = "/resources/images/eventIcon.png";
        } else {
            image = "/resources/images/event_full.png";
        }
        imageView.setImage(new Image(getClass().getResourceAsStream(image)));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);


        Label title = new Label();
        title.setText(eventEntity.getTitle());
        styleLabel(title, false);

        Text description = new Text();
        description.setText(eventEntity.getDescription());
        styleText(description);

        Label location = new Label();
        location.setText(eventEntity.getLocation());
        styleLabel(location, false);

        Label startDate = new Label();
        startDate.setText(formatLocalDateTime(eventEntity.getStartDate()));
        styleLabel(startDate, false);


        Label endDate = new Label();
        endDate.setText(formatLocalDateTime(eventEntity.getEndDate()));
        styleLabel(endDate, false);


        Label noOfSeats = new Label();
        noOfSeats.setId("noOfSeats");
        noOfSeats.setText(String.valueOf(eventEntity.getConstraints()));
        styleLabel(noOfSeats, false);


        Label online = new Label();
        online.setId("online");

        styleLabel(online, false);
        if (eventEntity.isOnline()) {
            online.setText(String.valueOf("YES"));
            online.setTextFill(Color.valueOf("green"));
        } else {
            online.setText(String.valueOf("NO"));
            online.setTextFill(Color.valueOf("red"));
        }


        Label organiser = new Label();
        String oraniserName = null;
        try {
            oraniserName = userService.getOrganiserName(eventEntity.getOrganiserId());
        } catch (Exception e) {
            System.out.println("Organiser is null");
            oraniserName = "Administrator";
        }
        organiser.setText(oraniserName);
        styleLabel(organiser, false);

        Button button = new Button();
        boolean isBooked = false;
        button.setText("Edit event");
        button.setDisable(false);
        button.setStyle("-fx-background-color: #febb02");

        styleButton(button, 0);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: ADD TO BOOKED EVENTS LIST
                System.out.println(eventEntity.getEventId());
            }
        });


        hBox.getChildren().addAll(
                imageView,
                title,
                description,
                location,
                startDate,
                endDate,
                noOfSeats,
                online,
                organiser,
                button);

        return hBox;
    }
}



