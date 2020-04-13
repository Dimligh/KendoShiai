package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;

import java.beans.EventHandler;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLController extends Pane implements Initializable {
    public ArrayList<Player> players;
    public TextField A1;
    public TextField A2;
    public TextField A3;
    public TextField B1;
    public TextField B2;
    public TextField B3;
    public TextField C1;
    public TextField C2;
    public TextField C3;
    public TextField D1;
    public TextField D2;
    public TextField D3;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(players.get(0).getFirstName());
        TextField[] text = {A1,A2,A3,B1,B2,B3,C1,C2,C3,D1,D2,D3};
//
        int i = 0;
        while(players.size()!= 0){
            SortData(text[i], players.get(0));
            i++;
            for(int y = 0; i<players.get(0).getOpponents().size();y++) {
                SortData(text[i], players.get(0).getOpponents().get(y));
                players.remove(players.get(0).getOpponents().get(y));
                i++;
                if(players.get(0).getOpponents().size() == 1){
                    i++;
                }
            }
            players.remove(players.get(0));
        }
       // for(int i = 0 ; i < players.size();i++) {
//            //needs to go from
//            SortData(text[i], players.get(i));
//            for(int y = i+1; i<players.get(i).getOpponents().size();y++){
//                SortData(text[y],players.get(i).getOpponents().get(y-1));
//                players.remove(players.get(i).getOpponents().get(y-1));
//    for(int y = i+1; i<players.get(i).getOpponents().size();y++){
//        SortData(text[y],players.get(i).getOpponents().get(y-1));
//        players.remove(players.get(i).getOpponents().get(y-1));
//        if(y == players.get(i).getOpponents().size()-1){
//            i=y;
//        }
//        players.remove(players.get(i));
//            }
//        }
    }

    public void SortData(TextField TextF ,Player player){
            TextF.setText(player.getFirstName() + " " + player.getLastName());
        TextF.editableProperty().setValue(false);
    }

}


