package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Controller {

    static Player infoinput(){
        Stage window = new Stage();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(7);
        grid.setHgap(9);

        //Window settings
        window.setTitle("Register Player");
        window.setMinWidth(600);
        window.setMinHeight(700);
        //Labels
        Label FirstName = new Label("First Name");
        Label LastName = new Label("Last Name");
        Label Club = new Label("Club Name");
        Label Country = new Label("Country");
        Label Grade = new Label("Grade");

        //Text Fields
        TextField FNameText = new TextField();
        TextField LNameText = new TextField();
        TextField ClubText = new TextField();
        TextField CountryText = new TextField();


        ChoiceBox<String> Gradedrop = new ChoiceBox<>();
        Gradedrop.getItems().addAll("4th Kyu", "3rd Kyu", "2nd Kyu", "1st Kyu",
                "1st Dan","2nd Dan", "3rd Dan","4th Dan","5th Dan","6th Dan","7th Dan");
        grid.setConstraints(Gradedrop,1,9);

        //Set Constraints for Label.
        grid.setConstraints(FirstName,1,0);
        grid.setConstraints(LastName,1,2);
        grid.setConstraints(Club,1,4);
        grid.setConstraints(Country,1,6);
        grid.setConstraints(Grade,1,8);

        //Set Constraints for Textfield.

        grid.setConstraints(FNameText,1,1);
        grid.setConstraints(LNameText,1,3);
        grid.setConstraints(ClubText,1,5);
        grid.setConstraints(CountryText,1,7);

        //Button to submit info.
        Button submitt = new Button("Submit");
        grid.setConstraints(submitt,1,10);
        submitt.setOnAction(event -> window.close());

        //Add children to grid.
        grid.getChildren().addAll(FirstName,LastName,Club,Country,Grade,FNameText,LNameText,ClubText,CountryText,Gradedrop,submitt);
        window.setScene(new Scene(grid));

        window.initModality(Modality.APPLICATION_MODAL);

        window.showAndWait();
        return new Player(FNameText.getText(),LNameText.getText(),ClubText.getText(),CountryText.getText(),Gradedrop.getValue());
    }

}
