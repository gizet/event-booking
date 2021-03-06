package utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Style {


    public static void styleHBox(HBox hbox, int value) {
        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(hbox, new Insets(5, 20, 5, 5));
        hbox.setPadding(new Insets(20, 20, 20, 10));
        if (value % 2 == 0) {
            hbox.setStyle("-fx-background-color: #e4e7ed");
        } else {
            hbox.setStyle("-fx-background-color: #FFFFFF");
        }
        // if hbox is header change to yellow
        if (value == -1) {
            hbox.setStyle("-fx-background-color:#febb02");
        }
    }

    /**
     * style box for addEvent
     *
     * @param hbox
     */
    public static void styleHBox(HBox hbox) {
        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(hbox, new Insets(5, 20, 5, 5));
        hbox.setPadding(new Insets(20, 20, 20, 10));
    }


    public static String styleImageView(int value) {
        if (value % 2 == 0) {
            return ("-fx-background-color: transparent");
        } else {
            return ("-fx-background-color: red");
        }
    }


    public static void styleLabel(Node node, boolean isHeader) {
        Label object = null;
        if (node instanceof Label) {
            object = (Label) node;
        }
        if (isHeader) {
            object.setFont(new Font("Arial Bold", 16));
            object.setTextFill(Color.web("#FFFFFF"));
        } else {
            object.setFont(new Font("Lucida Sans Demibold", 12));
            object.setTextFill(Color.web("#000000"));
        }

        object.setAlignment(Pos.CENTER_LEFT);
        int width = 100;
        int height = 50;
        if (object.getId() != null) {
            if (object.getId().equals("noOfSeats") || object.getId().equals("online")) {
                width = 70;
            }
        }

        object.setMinWidth(width);
        object.setMaxWidth(width);
        object.setPrefWidth(width);

        object.setMinHeight(height);
        object.setPrefHeight(height);
        object.setMaxHeight(height);
    }

    public static void styleLabelForAdmin(Node node, boolean isHeader) {
        Label object = null;
        if (node instanceof Label) {
            object = (Label) node;
        }
        if (isHeader) {
            object.setFont(new Font("Arial Bold", 16));
            object.setTextFill(Color.web("#FFFFFF"));
        } else {
            object.setFont(new Font("Lucida Sans Demibold", 12));
            object.setTextFill(Color.web("#000000"));
        }

        object.setAlignment(Pos.CENTER_LEFT);
        int width = 150;
        int height = 50;

        object.setMinWidth(width);
        object.setMaxWidth(width);
        object.setPrefWidth(width);

        object.setMinHeight(height);
        object.setPrefHeight(height);
        object.setMaxHeight(height);
    }


    public static void styleText(Text text) {
        text.setFont(new Font("Lucida Sans Demibold", 12));
        text.setWrappingWidth(100);
    }

    public static void styleButton(Button button, int value) {
        switch (value) {
            case 0:
                button.setTextFill(Color.web("#FFFFFF"));
                button.setFont(new Font("System Bold", 15));
                break;
            case 1:

                button.setTextFill(Color.web("#FFFFFF"));
                button.setFont(new Font("System Bold", 15));
                button.setAlignment(Pos.CENTER_RIGHT);
            default:
                break;
        }
    }

    public static void styleButton(Button button, boolean isBooked) {
        if (!isBooked) {
            button.setText("Book");
            button.setDisable(false);
            button.setStyle("-fx-background-color: #febb02");
        } else {
            button.setText("Booked");
            button.setDisable(true);
            button.setStyle("-fx-background-color: #00b300");
        }
    }

    public static void styleChoiceBox(ChoiceBox choiceBox) {
        choiceBox.setStyle("-fx-background-color: transparent");
        choiceBox.setStyle("-fx-border-color: #1397d5");
        choiceBox.setStyle("-fx-border-color: #1397d5");
        choiceBox.setStyle("-fx-border-width: 2 2 2 2");
    }

    public static void styleTextBasedOnBoolean(Text text, boolean value) {
        text.setFill(value ? Color.GREEN : Color.RED);
    }
}
