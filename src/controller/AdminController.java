package controller;

import static entities.Roles.*;
import entities.User;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import service.UserService;
import service.UserServiceImpl;
import static utils.Style.*;

public class AdminController {

    private User loggedUser;

    private UserService userService = new UserServiceImpl();

    public AdminController(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void getUsers(VBox adminVbox) {
        addHeader(adminVbox);
        //get all events
        List<User> users = userService.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            User userEntity = users.get(i);

            HBox hbox = createHbox(userEntity);
            styleHBox(hbox, i);

            adminVbox.getChildren().add(hbox);
            adminVbox.setSpacing(5);
            adminVbox.setVisible(true);
            VBox.setMargin(hbox, new Insets(5, 5, 5, 5));
            adminVbox.setPadding(new Insets(10, 10, 10, 10));
        }
    }


    private void addHeader(VBox vBox) {
        HBox hBox = new HBox();

        ImageView imageViewLabel = new ImageView();
        imageViewLabel.setImage(new Image(getClass().getResourceAsStream("/resources/images/eventIcon.png")));
        imageViewLabel.setFitHeight(50);
        imageViewLabel.setFitWidth(50);
        imageViewLabel.setVisible(false);


        Label firstNameLabel = new Label();
        firstNameLabel.setText("First Name");
        styleLabelForAdmin(firstNameLabel, true);

        Label lastNameLabel = new Label();
        lastNameLabel.setText("Last Name");
        styleLabelForAdmin(lastNameLabel, true);

        Label emailLabel = new Label();
        emailLabel.setText("Email");
        styleLabelForAdmin(emailLabel, true);

        Label userNameLabel = new Label();
        userNameLabel.setId("userName");
        userNameLabel.setText("User Name");
        styleLabelForAdmin(userNameLabel, true);

        Label roleLabel = new Label();
        roleLabel.setId("roleLabel");
        roleLabel.setText("Role");
        styleLabelForAdmin(roleLabel, true);

        Label editRoleButtonLabel = new Label();
        editRoleButtonLabel.setText("Edit Role");
        styleLabelForAdmin(editRoleButtonLabel, true);


        hBox.getChildren().addAll(
                imageViewLabel,
                firstNameLabel,
                lastNameLabel,
                emailLabel,
                userNameLabel,
                roleLabel,
                editRoleButtonLabel);
        styleHBox(hBox, -1);
        vBox.getChildren().add(hBox);
    }


    private HBox createHbox(User userEntity) {
        HBox hBox = new HBox();

        ImageView imageView = new ImageView();
        String image;
        if (userEntity.getRole() != null) {
            switch (userEntity.getRole()) {
                case ADMINISTRATOR:
                    image = "/resources/images/admin.png";
                    break;
                case EVENT_ORGANISER:
                    image = "/resources/images/supervisor.png";
                    break;
                default:
                    image = "/resources/images/userPage.png";
            }
        } else {
            image = "/resources/images/userPage.png";
        }
        imageView.setImage(new Image(getClass().getResourceAsStream(image)));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        Label firstName = new Label();
        firstName.setText(userEntity.getFirstName());
        styleLabelForAdmin(firstName, false);

        Text lastName = new Text();
        lastName.setText(userEntity.getLastName());
        styleText(lastName);

        Label email = new Label();
        email.setText(userEntity.getEmail());
        styleLabelForAdmin(email, false);

        Label userName = new Label();
        userName.setId("userName");
        userName.setText(String.valueOf(userEntity.getUsername()));
        styleLabelForAdmin(userName, false);

        ChoiceBox roleChoicheBox = new ChoiceBox();
        roleChoicheBox.setItems(FXCollections.observableArrayList(
                STUDENT, ADMINISTRATOR, EVENT_ORGANISER));
        if (userEntity.getRole() != null) {
            roleChoicheBox.setValue(String.valueOf(userEntity.getRole().toString()));
        } else {
            roleChoicheBox.setValue(String.valueOf(STUDENT));
        }
        //styleLabelForAdmin(roleChoicheBox, false);

        Button editRoleButton = new Button();
        editRoleButton.setText("Edit role");
        editRoleButton.setDisable(false);
        editRoleButton.setStyle("-fx-background-color: #febb02");

        styleButton(editRoleButton, 0);
        editRoleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });

        hBox.getChildren().addAll(
                imageView,
                firstName,
                lastName,
                email,
                userName,
                roleChoicheBox,
                editRoleButton);

        return hBox;
    }
}

