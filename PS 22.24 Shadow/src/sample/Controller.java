package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private Rectangle Rectangle;

    @FXML private Slider slider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DropShadow ds = new DropShadow();

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<?extends Number> observable, Number oldValue, Number newValue){
                ds.setOffsetY(newValue.floatValue());
                ds.setOffsetX(newValue.floatValue());DropShadow ds = new DropShadow();
                ds.setColor(Color.GRAY);
                Rectangle.setEffect(ds);
            }
        });




    }
}
