package sample;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;
public class alertbox {

    static void display(String title, String message){
        Stage window = new Stage();
        window.setTitle(title);
        window.setMinWidth(400);

        Label label = new Label();
        label.setText(message);

        Button button = new Button();
        button.setText("close window");
        button.setOnAction(a -> {
            window.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.initModality(Modality.APPLICATION_MODAL);

        window.showAndWait();
    }

}
