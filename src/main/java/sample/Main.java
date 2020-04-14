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
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10,10,10,10));
    grid.setVgap(7);
    grid.setHgap(9);

    //Password label
    // Label password = new Label("Password");
    //grid.setConstraints(password,1,1);


    //Password input
    //TextField passtextfield = new TextField();
    //passtextfield.setPromptText("password");
    //grid.setConstraints(passtextfield,1,2);
    ArrayList<Player> players = new ArrayList<>();
    ListView<String> playerList = new ListView<>();
     //ADD PLAYER BUTTON.
     Button addPlayer = new Button("Add player");
     addPlayer.setId("Add_Player");
     addPlayer.setOnAction(e -> {
       Player player = Controller.infoinput();
       if(!closedwithoutfillingout(player)) {
              players.add(player);
              playerList.getItems().add(player.getFirstName() + " " + player.getLastName() + " , " + player.getClub() + " , " + player.getCountry());
          } } );
        grid.setConstraints(addPlayer,1,2);

        //REMOVE PLAYER BUTTON.
        Button removePlayer = new Button("Remove Player");
        removePlayer.setOnAction(event -> removeplayer(players,playerList));
        grid.setConstraints(removePlayer,2,2);

        //GENERATE SHIAI BUTTON.
        Button generate = new Button("Generate Shiai");
        generate.setOnAction(e -> {

            if (players.size() > 0) {
                ArrayList<Player> new_playerList = GatewaySort(players);
                try {

                    FXMLLoader loader = new FXMLLoader();
                    if(new_playerList.size() <= 16) {
                        loader.setLocation(getClass().getResource("/fxml/Four_Pools.fxml"));
                        Four_PoolController four_poolController = new Four_PoolController();
                        four_poolController.players = new_playerList;
                        //FXMLController fxmlController = new FXMLController();
                        //fxmlController.players = new_playerList;
                        loader.setController(four_poolController);
                    }

                    BorderPane borderPane = new BorderPane();

                    Parent root = loader.load();

                    borderPane.getChildren().add(root);

                    primaryStage.setScene(new Scene(borderPane, 1280, 720));
                } catch (IOException ez) {

                }
            }

        });
        grid.setConstraints(generate,2,4);

        grid.setConstraints(playerList,1,5);
        grid.getChildren().addAll(addPlayer,removePlayer,playerList,generate);
        //primary stage methods
        primaryStage.setTitle("Shiai");
        primaryStage.isResizable();
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closingProgram();});
        primaryStage.setScene(scene = new Scene(grid, 600, 500));
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
    private boolean closedwithoutfillingout(Player player){
        if(player.getFirstName().equals("") || player.getLastName().equals("") || player.getClub().equals("")
                || player.getCountry().equals("")|| player.getGrade() == null){
            return true;
        }
        return false;
    }

    private void removeplayer(ArrayList<Player> players, ListView<String> playerlist){
        ObservableList<String> player = playerlist.getSelectionModel().getSelectedItems();
        String[] name;
       for(String m: player) {
           name = m.split(" ");
           for (int i = 0; i < players.size(); i++) {
               if (players.get(i).getFirstName().equals(name[0]) && players.get(i).getLastName().equals(name[1])) {
                   players.remove(i);
                   playerlist.getItems().remove(m);
                   break;
               }
           }
           break;
       }

    }
    @FXML
    private ArrayList<Player> GatewaySort(ArrayList<Player> players){
        ArrayList<Player> Empty = new ArrayList<>();
        sort = new Sorter();
        //Randomize values to be used in CountrySort.
        int ran1 = random.nextInt(players.size());
        int ran2 = random.nextInt(players.size());
        int ran3 = random.nextInt(players.size());

        //work a bit more and then move on to club sort!
        ArrayList<Player> temp = sort.CountrySort(players,ran1,
               ran2,ran3,Empty);
        //re-randomize for new values for ClubSort.
        ran1 = random.nextInt(temp.size());
        ran2 = random.nextInt(temp.size());
        try {
            Empty = sort.ClubSort(temp, ran1, ran2, new ArrayList<Player>());
        }
        catch (StackOverflowError e){
           System.out.println("error");
        }
        return Empty;
    }
}

