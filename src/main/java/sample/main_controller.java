package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class main_controller extends Pane implements Initializable {
    Sorter sort;
    Random random;
    Stage primaryStage;
   @FXML public Button add_player;
   @FXML public Button remove_player;
   @FXML public Button generate_shiai;
   @FXML public ListView<String> list_view;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        list_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ArrayList<Player> players = new ArrayList<>();

        //ADD PLAYER BUTTON.
        add_player.setOnAction(e -> {
            Player player = Controller.infoinput();
            if(!closedwithoutfillingout(player)) {
                players.add(player);

                list_view.getItems().add(player.getFirstName() + " " + player.getLastName() + " , " + player.getClub() + " , " + player.getCountry());

            } } );

        //REMOVE PLAYER BUTTON.
        remove_player.setOnAction(event -> removeplayer(players,list_view));

        //GENERATE SHIAI BUTTON.

        generate_shiai.setOnAction(e -> {

            if (players.size() > 0) {
                ArrayList<Player> new_playerList = GatewaySort(players);
                try {

                    FXMLLoader loader = new FXMLLoader();
                    if(new_playerList.size() <= 16) {
                        loader.setLocation(getClass().getResource("/fxml/Four_Pools.fxml"));
                        Four_PoolController four_poolController = new Four_PoolController();
                        four_poolController.players = new_playerList;
                        loader.setController(four_poolController);
                    }

                    else if(new_playerList.size() > 16 && new_playerList.size() <= 32) {
                        loader.setLocation(getClass().getResource("/fxml/Eight_Pools.fxml"));
                        Eight_PoolController eight_poolController = new Eight_PoolController();
                        eight_poolController.players = new_playerList;
                        loader.setController(eight_poolController);
                    }

                    BorderPane borderPane = new BorderPane();
                    Parent root = loader.load();
                    borderPane.getChildren().add(root);
                    primaryStage.setScene(new Scene(borderPane, 1280, 720));
                } catch (IOException ez) {

                }
            }

        });
    }


    private void closingProgram() {
        boolean req = ConfirmBox.display("Closing", "are you sure you want to close the program?");
        if (req) {
            exit();
        }

    }
    private boolean closedwithoutfillingout(Player player){
        if(player.getFirstName().equals("") || player.getLastName().equals("") || player.getClub().equals("")
                || player.getCountry().equals("") || player.getGrade() == null){
            return true;
        }
        return false;
    }

    private void removeplayer(ArrayList<Player> players, ListView<String> playerlist){
        if(players.size()>0) {
            ObservableList<String> player = playerlist.getSelectionModel().getSelectedItems();
            String[] name;
            for (String m : player) {
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
    }
    @FXML
    private ArrayList<Player> GatewaySort(ArrayList<Player> players) {
        random = new Random();
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

        Empty = sort.ClubSort(temp);

        return Empty;
    }

}
