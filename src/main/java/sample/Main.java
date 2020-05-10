package sample;

import static javafx.application.Platform.exit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.*;


public class Main extends Application {
  Scene scene;
   Sorter sort;
    Random random;
  @Override
   public void start(Stage primaryStage) throws Exception {

    random = new Random();


        //grid.setConstraints(generate,2,4);

        //grid.setConstraints(playerList,1,5);
        //grid.getChildren().addAll(addPlayer,removePlayer,playerList,generate);
        //Primary Stage Methods
      FXMLLoader mainloader = new FXMLLoader();

        mainloader.setLocation(getClass().getResource("/fxml/Main.fxml"));
        main_controller main_controll = new main_controller();
        main_controll.primaryStage = primaryStage;
        mainloader.setController(main_controll);

      BorderPane borderPane = new BorderPane();
      Parent root = mainloader.load();
      borderPane.getChildren().add(root);
        primaryStage.setTitle("Shiai");
        primaryStage.isResizable();
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closingProgram();});
        primaryStage.setScene(scene = new Scene(borderPane,800,611));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void closingProgram() {
        boolean req = ConfirmBox.display("Closing", "are you sure you want to close the program?");
        if (req) {
            exit();
        }

    }


}

