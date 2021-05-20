package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class KaleidoscopeController {
    @FXML    private ToggleButton drowbtn;
    @FXML    private ToggleButton rubberbtn;
    @FXML    private ToggleButton linebtn;
    @FXML    private ToggleButton rectbtn;
    @FXML    private ToggleButton circlebtn;
    @FXML    private Label Borderclr;
    @FXML    private ColorPicker cpLine;
    @FXML    private ColorPicker cpFill;
    @FXML    private Slider slider;
    @FXML    private Button undo;
    @FXML    private Button clearButton;
    @FXML    private Pane paneField;
    @FXML    private TextField numBox;

    double startX=0.0;
    double startY=0.0;

    public void initialize(){

        cpLine.setValue(Color.CYAN);

        drowbtn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {

                rubberbtn.setSelected(false);
                linebtn.setSelected(false);
                circlebtn.setSelected(false);
                rectbtn.setSelected(false);
            }
        });

        rubberbtn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                drowbtn.setSelected(false);
                linebtn.setSelected(false);
                circlebtn.setSelected(false);
                rectbtn.setSelected(false);
            }
        });
        linebtn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                drowbtn.setSelected(false);
                rubberbtn.setSelected(false);
                circlebtn.setSelected(false);
                rectbtn.setSelected(false);
            }
        });
        circlebtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drowbtn.setSelected(false);
                rubberbtn.setSelected(false);
                linebtn.setSelected(false);
                rectbtn.setSelected(false);
            }
        });
        rectbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drowbtn.setSelected(false);
                rubberbtn.setSelected(false);
                linebtn.setSelected(false);
                circlebtn.setSelected(false);
            }
        });


        paneField.setOnMousePressed(e->{
            if(drowbtn.isSelected()) {
                drowbtnPressed(e.getX(),e.getY(),slider.getValue());
            }
            else if(rubberbtn.isSelected()) {
                rubberbtnPressed(e.getX(),e.getY(),slider.getValue());
            }

            else if(linebtn.isSelected()) {
                startX=e.getX();
                startY=e.getY();

            }
            else if(rectbtn.isSelected()) {
                startX=e.getX();
                startY=e.getY();
            }
            else if(circlebtn.isSelected()) {
                startX=e.getX();
                startY=e.getY();

            }

        });

        paneField.setOnMouseDragged(e->{
            if(drowbtn.isSelected()) {
                drowbtnPressed(e.getX(),e.getY(),slider.getValue());
            }
            else if(rubberbtn.isSelected()){
                rubberbtnPressed(e.getX(),e.getY(),slider.getValue());

            }
        });

        paneField.setOnMouseReleased(e->{

            if(linebtn.isSelected()) {

                linebtnPressed(startX,startY,e.getX(),e.getY());
                startY=0.0;
                startX=0.0;

            }
            else if(rectbtn.isSelected()) {

                rectanglebtnPressed(e.getX(),e.getY(),startX,startY);
                startY=0.0;
                startX=0.0;

            }
            else if(circlebtn.isSelected()) {
                circlebtnPressed(e.getX(),e.getY(),startX,startY);
                startY=0.0;
                startX=0.0;
            }

        });

        undo.setOnAction(e->{
            int count = paneField.getChildren().size();

            if (count > 0) {
                paneField.getChildren().remove(count - 1);
            }

        });


        clearButton.setOnAction(e-> {
            int count = paneField.getChildren().size();
            if (count>0) {
                for (int i = 1; i <= count; i++) {
                    paneField.getChildren().remove(count-i);
                }
            }
        });

//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                if (!numBox.isFocused()){
//                    if (!numBox.getText().chars().allMatch( Character::isDigit )) {
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Error input");
//                        alert.setHeaderText(null);
//                        alert.setContentText(" "+"Enter valid Integer Value.");
//                        alert.show();
//                    }
//                }
//            }
//        }));

//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();

    }

    void drowbtnPressed(double x,double y,double r){

        double ox = paneField.getWidth() / 2;
        double oy = paneField.getHeight() / 2;

        for (int i = 0; i < Integer.parseInt(numBox.getText()); i++) {
            double theta = Math.toRadians(i * 360 / Integer.parseInt(numBox.getText()));

            double xx = Math.cos(theta) * (x - ox) - Math.sin(theta) * (y - oy) + ox;
            double yy = Math.sin(theta) * (x - ox) + Math.cos(theta) * (y - oy) + oy;
            Circle newCircle = new Circle(xx, yy, r, cpLine.getValue());
            paneField.getChildren().add(newCircle);

        }
    }

    void rubberbtnPressed(double x,double y,double r){
        Circle circle = new Circle(x,y,r*10,Color.BLACK);
        paneField.getChildren().add(circle);
    }

    void linebtnPressed(double x1,double y1,double x2,double y2){

        double ox = paneField.getWidth() / 2;
        double oy = paneField.getHeight() / 2;

        for (int i = 0; i < Integer.parseInt(numBox.getText()); i++) {
            double theta = Math.toRadians(i * 360 / Integer.parseInt(numBox.getText()));

            double x = Math.cos(theta) * (x1 - ox) - Math.sin(theta) * (y1 - oy) + ox;
            double y = Math.sin(theta) * (x1 - ox) + Math.cos(theta) * (y1 - oy) + oy;
            double xx = Math.cos(theta) * (x2 - ox) - Math.sin(theta) * (y2 - oy) + ox;
            double yy = Math.sin(theta) * (x2 - ox) + Math.cos(theta) * (y2 - oy) + oy;
            Line line = new Line(x,y,xx,yy);
            line.setStroke(cpLine.getValue());

            paneField.getChildren().add(line);

        }
    }
    void rectanglebtnPressed(double x2,double y2,double x1,double y1){


        double x= Math.min(x2, x1)-(Math.abs(x1-x2)/2);
        double y = Math.min(y2, y1)-(Math.abs(y1-y2)/2);

        double ox = paneField.getWidth() / 2;
        double oy = paneField.getHeight() / 2;

        for (int i = 0; i < Integer.parseInt(numBox.getText()); i++) {
            double theta = Math.toRadians(i * 360 / Integer.parseInt(numBox.getText()));

            double xx = Math.cos(theta) * (x - ox) - Math.sin(theta) * (y - oy) + ox;
            double yy = Math.sin(theta) * (x - ox) + Math.cos(theta) * (y - oy) + oy;

            Rectangle rectangle = new Rectangle();
            rectangle.setWidth(Math.abs((x2 - x1)));
            rectangle.setHeight(Math.abs((y2 - y1)));
            rectangle.setX(xx);
            rectangle.setY(yy);
            rectangle.setStroke(cpLine.getValue());
            rectangle.setFill(cpFill.getValue());

            paneField.getChildren().add(rectangle);

        }

    }
    void circlebtnPressed(double x2,double y2,double x1,double y1){


        double r = (Math.abs(x2 - x1) + Math.abs(y1 - y2)) / 2;

        double x = (x1+x2)/2;
        double y = (y1+y2)/2;

        double ox = paneField.getWidth() / 2;
        double oy = paneField.getHeight() / 2;

        for (int i = 0; i < Integer.parseInt(numBox.getText()); i++) {
            double theta = Math.toRadians(i * 360 / Integer.parseInt(numBox.getText()));

            double xx = Math.cos(theta) * (x - ox) - Math.sin(theta) * (y - oy) + ox;
            double yy = Math.sin(theta) * (x - ox) + Math.cos(theta) * (y - oy) + oy;

            Circle newCircle = new Circle(xx, yy, r, cpLine.getValue());
            newCircle.setFill(cpFill.getValue());
            newCircle.setStroke(cpLine.getValue());

            paneField.getChildren().add(newCircle);

    }

}}
