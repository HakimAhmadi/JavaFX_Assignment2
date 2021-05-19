package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;

public class Controller {

    @FXML
    private WebView webView;

    @FXML
    private TextField TextField;

    @FXML
    private Button searchButton;

    @FXML
    private Button BButton;

    @FXML
    ArrayList<String> history = new ArrayList<>();
    WebEngine webEngine = new WebEngine();

    @FXML
    void back(ActionEvent event) {
        webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(history.get(history.size()-1));
    }

    @FXML
    void search(ActionEvent event) {
        webView = new WebView();
        history.add(TextField.getText());
        webEngine.load("http://"+TextField.getText());
    }
    public void initialize(){
        webEngine = webView.getEngine();
        webEngine.load("https://www.google.com");
    }

}
