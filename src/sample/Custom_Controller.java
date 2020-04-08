package sample;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Custom_Controller extends Pane {
    @FXML private TextField textFieldA;



    public Custom_Controller(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("test.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
     try{
         fxmlLoader.load();
     }
     catch (IOException e){
        throw new RuntimeException(e);
     }
    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {

        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return textFieldA.textProperty();
    }

    @FXML
    protected void doSomething() {
        System.out.println("The button was clicked!");
    }

}
