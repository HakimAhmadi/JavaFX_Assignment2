package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.RadioButton;

import java.util.Stack;


public class PainterAppController {


    @FXML
    private Slider redSlider;
    @FXML
    private Slider greenSlider;
    @FXML
    private Slider blueSlider;
    @FXML
    private Slider alphaSlider;
    @FXML
    private TextField redTextField;
    @FXML
    private TextField greenTextField;
    @FXML
    private TextField blueTextField;
    @FXML
    private TextField alphaTextField;
    @FXML
    private Rectangle colorRectangle;
    @FXML
    private Button undoButton;
    @FXML
    private Button clearButton;
    @FXML private Canvas drawingAreaCanvas;

    @FXML private RadioButton lineButton;
    @FXML private RadioButton ovalButton;
    @FXML private RadioButton RectangleButton;
    @FXML private RadioButton EraserButton;
    @FXML private Slider thicknessSlider;



    @FXML
    GraphicsContext gc = drawingAreaCanvas.getGraphicsContext2D();

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1.0;
    Line line = new Line();
    Rectangle rect = new Rectangle();
    Ellipse oval = new Ellipse();
    Stack<Shape> history = new Stack();


    private Paint brushColor = Color.BLACK;


    public void initialize() {

        redTextField.textProperty().bind(
                redSlider.valueProperty().asString("%.0f"));
        greenTextField.textProperty().bind(
                greenSlider.valueProperty().asString("%.0f"));
        blueTextField.textProperty().bind(
                blueSlider.valueProperty().asString("%.0f"));
        alphaTextField.textProperty().bind(
                alphaSlider.valueProperty().asString("%.2f"));

        drawingAreaCanvas.setOnMousePressed(e -> {
            if (lineButton.isSelected()) {
                line.setStartX(e.getX());
                line.setStartY(e.getY());
                line.setEndX(e.getX());
                line.setEndY(e.getY());
            } else if (RectangleButton.isSelected()) {
                rect.setX(e.getX());
                rect.setY(e.getY());
            } else if (ovalButton.isSelected()) {
                oval.setCenterX(e.getX());
                oval.setCenterY(e.getY());
            }

            else if (EraserButton.isSelected()) erase(e.getX(), e.getY());

        });

        drawingAreaCanvas.setOnMouseReleased(e -> {

            gc.setStroke(Color.rgb(red, green, blue));

            if (lineButton.isSelected()) {
                line.setStrokeWidth(thicknessSlider.getValue());
                line.setFill(Color.rgb(red, green, blue));
                line.setEndX(e.getX());
                line.setEndY(e.getY());
                gc.setLineWidth(thicknessSlider.getValue());
                gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
                Line tempLine = new Line(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
                tempLine.setStroke(Color.rgb(red, green, blue));
                tempLine.setStrokeWidth(line.getStrokeWidth());
                history.push(tempLine);
            }
            else if (RectangleButton.isSelected()) {
                rect.setWidth(Math.abs((e.getX() - rect.getX())));
                rect.setHeight(Math.abs((e.getY() - rect.getY())));
                rect.setX(Math.min(rect.getX(), e.getX()));
                rect.setY(Math.min(rect.getY(), e.getY()));
                rect.setFill(Color.rgb(red, green, blue));
                gc.setFill(Color.rgb(red, green, blue));
                gc.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                gc.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                Rectangle tempRect = new Rectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                tempRect.setFill(Color.rgb(red, green, blue));
                history.push(tempRect);
            } else if (ovalButton.isSelected()) {
                oval.setRadiusX(Math.abs(e.getX() - oval.getCenterX()));
                oval.setRadiusY(Math.abs(e.getY() - oval.getCenterY()));
                oval.setCenterX(Math.min(oval.getCenterX(), e.getX()));
                oval.setCenterY(Math.min(oval.getCenterY(), e.getY()));
                oval.setFill(Color.rgb(red, green, blue));
                gc.setFill(Color.rgb(red, green, blue));
                gc.fillOval(oval.getCenterX(), oval.getCenterY(), oval.getRadiusX(), oval.getRadiusY());
                gc.strokeOval(oval.getCenterX(), oval.getCenterY(), oval.getRadiusX(), oval.getRadiusY());
                Ellipse tempOval = new Ellipse(oval.getCenterX(), oval.getCenterY(), oval.getRadiusX(), oval.getRadiusY());
                tempOval.setFill(Color.rgb(red, green, blue));
                history.push(tempOval);
            }

            else if (EraserButton.isSelected()) erase(e.getX(), e.getY());
                gc.clearRect(0, 0, drawingAreaCanvas.getWidth(), drawingAreaCanvas.getHeight());
                drawShapes(history);
        });

    }


//    @FXML
//    private void drawingAreaMouseDragged(MouseEvent e) {
//        Circle newCircle = new Circle(e.getX(), e.getY(),
//                2, brushColor);
//        drawingAreaPane.getChildren().add(newCircle);
//    }


    @FXML
    private void undoButtonPressed(ActionEvent event) {
        if (!history.isEmpty()) {
            history.pop();
            gc.clearRect(0, 0, drawingAreaCanvas.getWidth(), drawingAreaCanvas.getHeight());
            drawShapes(history);
        }

    }
    private void drawShapes(Stack<Shape> shapes) {
        for (Shape s: shapes) {
            if(s.getClass() == Line.class) {
                Line temp = (Line) s;
                gc.setLineWidth(temp.getStrokeWidth());
                gc.setStroke(temp.getStroke());
                gc.strokeLine(temp.getStartX(), temp.getStartY(), temp.getEndX(), temp.getEndY());
            }
            else if(s.getClass() == Rectangle.class) {
                Rectangle temp = (Rectangle) s;
                gc.setLineWidth(temp.getStrokeWidth());
                gc.setFill(temp.getFill());
                gc.setStroke(temp.getStroke());
                gc.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
                gc.strokeRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
            }
            else if(s.getClass() == Ellipse.class) {
                Ellipse temp = (Ellipse) s;
                gc.setStroke(temp.getStroke());
                gc.setLineWidth(temp.getStrokeWidth());
                gc.setFill(temp.getFill());
                gc.fillOval(temp.getCenterX(), temp.getCenterY(), temp.getRadiusX(), temp.getRadiusY());
                gc.strokeOval(temp.getCenterX(), temp.getCenterY(), temp.getRadiusX(), temp.getRadiusY());
            }
        }
    }

    private void erase(double x, double y) {
        double sise = thicknessSlider.getValue();
        gc.clearRect(x - sise / 2, y - sise / 2, sise, sise);
    }
    @FXML
    private void clearButtonPressed(ActionEvent event) {
        gc.clearRect(0, 0, drawingAreaCanvas.getWidth(), drawingAreaCanvas.getHeight());
        history.clear();
    }
}
