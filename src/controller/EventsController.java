package controller;

import entities.Event;
import entities.User;
import java.io.IOException;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import static utils.DateHelper.formatLocalDateTime;
import utils.Path;
import utils.Redirect;
import utils.Style;
import static utils.Style.*;

public class EventsController {

    private EventService eventService = new EventServiceImpl();

    private UserService userService = new UserServiceImpl();


    private User loggedUser;

    public EventsController(User loggedUser) {
        this.loggedUser = loggedUser;
    }


    void getEvents(VBox eventVbox) {
        addHeader(eventVbox);
        //get all events
        List<Event> events = eventService.getAllEvents();
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
        imageViewLabel.setImage(new Image(getClass().getResourceAsStream("/resources/images/event.png")));
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
        buttonLabel.setText("    Actions  ");
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
            image = "/resources/images/event.png";
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


        //VBOX
        VBox buttons = new VBox();
        buttons.setSpacing(5);
        buttons.setAlignment(Pos.CENTER);

        Button viewDetailsButton = new Button();
        viewDetailsButton.setText("View Details");
        viewDetailsButton.setDisable(false);
        viewDetailsButton.setStyle("-fx-background-color: #febb02");
        viewDetailsButton.setDisable(false);

        styleButton(viewDetailsButton, 0);
        viewDetailsButton.setOnAction(event -> {
            //TODO: IMPLEMENT VIEW DETAILS
            try {
                new Redirect().openInfoEventModal(event, Path.EVENT_INFO,eventEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        Button bookButton = new Button();
        boolean isBooked = eventService.isEventBooked(eventEntity.getEventId(), loggedUser.getUserId());
        Style.styleButton(bookButton, isBooked);
        styleButton(bookButton, 0);
        bookButton.setOnAction(event -> {
            System.out.println(eventEntity.getEventId());
            boolean isBooked1 = eventService.bookEvent(eventEntity, loggedUser.getUserId());
            Style.styleButton(bookButton, isBooked1);
        });

        buttons.getChildren().addAll(viewDetailsButton, bookButton);


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
                buttons);

        return hBox;
    }


}