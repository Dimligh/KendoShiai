package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
    static boolean answer;
    static boolean display(String title, String message){
        Stage window = new Stage();
        window.setTitle(title);
        window.setMinWidth(400);

        Label label = new Label();
        label.setText(message);


        Button yesbutton = new Button("yes");
        Button noButton = new Button("no");

        yesbutton.setOnAction(a -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(a -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,yesbutton,noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.initModality(Modality.APPLICATION_MODAL);

        window.showAndWait();
        return answer;
    }
}
