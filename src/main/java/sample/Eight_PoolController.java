package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Eight_PoolController extends Pane implements Initializable {
    ArrayList<Player> players;
    public TextField A1;
    public TextField A2;
    public TextField A3;
    public TextField A4;
    public TextField A5;
    public TextField A6;
    public TextField B1;
    public TextField B2;
    public TextField B3;
    public TextField B4;
    public TextField B5;
    public TextField B6;
    public TextField C1;
    public TextField C2;
    public TextField C3;
    public TextField C4;
    public TextField C5;
    public TextField C6;
    public TextField D1;
    public TextField D2;
    public TextField D3;
    public TextField D4;
    public TextField D5;
    public TextField D6;
    public TextField E1;
    public TextField E2;
    public TextField E3;
    public TextField E4;
    public TextField E5;
    public TextField E6;
    public TextField F1;
    public TextField F2;
    public TextField F3;
    public TextField F4;
    public TextField F5;
    public TextField F6;
    public TextField G1;
    public TextField G2;
    public TextField G3;
    public TextField G4;
    public TextField G5;
    public TextField G6;
    public TextField H1;
    public TextField H2;
    public TextField H3;
    public TextField H4;
    public TextField H5;
    public TextField H6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextField[] text = {A1,A2,A3,A4,A5,A6,B1,B2,B3,B4,B5,B6,C1,C2,C3,C4,C5,C6,D1,D2,D3,
                D4,D5,D6,E1,E2,E3,E4,E5,E6,F1,F2,F3,F4,F5,F6,G1,G2,G3,G4,G5,G6,H1,H2,H3,H4,H5,H6};

        int i = 0;
        while(players.size()!= 0){
            if(players.get(0).getOpponents().size() == 4){
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
    private void SortData(TextField TextF , Player player){
        TextF.setText(player.getFirstName() + " " + player.getLastName());
        TextF.editableProperty().setValue(false);
    }
}
