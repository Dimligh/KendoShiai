package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Four_PoolController extends Pane implements Initializable {
    public ArrayList<Player> players;
    public TextField A1;
    public TextField A2;
    public TextField A3;
    public TextField A4;
    public TextField B1;
    public TextField B2;
    public TextField B3;
    public TextField B4;
    public TextField C1;
    public TextField C2;
    public TextField C3;
    public TextField C4;
    public TextField D1;
    public TextField D2;
    public TextField D3;
    public TextField D4;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextField[] text = {A1,A2,A3,A4,B1,B2,B3,B4,C1,C2,C3,C4,D1,D2,D3,D4};
//
        int i = 0;
        while(players.size()!= 0){
            if(players.get(0).getOpponents().size() == 2){
                System.out.println("Entered the disable field");
                text[i].visibleProperty().setValue(false);
                i++;
            }
            SortData(text[i], players.get(0));
            i++;
            for(int y = 0; y<players.get(0).getOpponents().size();y++) {
                if(!players.get(0).getOpponents().get(y).getFirstName().equals("Blank")) {
                    SortData(text[i], players.get(0).getOpponents().get(y));
                    players.remove(players.get(0).getOpponents().get(y));
                    i++;
                }
            }
            players.remove(players.get(0));
        }
    }

    public void SortData(TextField TextF , Player player){
        if(!player.getFirstName().equals("Blank")) {
            TextF.setText(player.getFirstName() + " " + player.getLastName());
            TextF.editableProperty().setValue(false);
        }
    }
}
