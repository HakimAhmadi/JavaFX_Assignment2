package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.util.Duration;
import com.jfoenix.controls.JFXButton;


import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {


    @FXML    private TextArea textReader;
    @FXML    private ProgressBar TimeBar;
    @FXML    private TextArea textWriter;
    @FXML    private ProgressIndicator ProgressBar;
    @FXML
    private JFXButton caps;
    @FXML
    private JFXButton c;
    @FXML
    private JFXButton tap;
    @FXML
    private JFXButton ctrl;
    @FXML
    private JFXButton shift;
    @FXML
    private JFXButton one;
    @FXML
    private JFXButton q;
    @FXML
    private JFXButton two;
    @FXML
    private JFXButton f;
    @FXML
    private JFXButton d;
    @FXML
    private JFXButton r;
    @FXML
    private JFXButton four;
    @FXML
    private JFXButton e;
    @FXML
    private JFXButton s;
    @FXML
    private JFXButton w;
    @FXML
    private JFXButton a;
    @FXML
    private JFXButton three;
    @FXML
    private JFXButton g;
    @FXML
    private JFXButton t;
    @FXML
    private JFXButton five;
    @FXML
    private JFXButton six;
    @FXML
    private JFXButton y;
    @FXML
    private JFXButton n;
    @FXML
    private JFXButton seven;
    @FXML
    private JFXButton m;
    @FXML
    private JFXButton h;
    @FXML
    private JFXButton ctrl2;
    @FXML
    private JFXButton space;
    @FXML
    private JFXButton alt2;
    @FXML
    private JFXButton b;
    @FXML
    private JFXButton v;
    @FXML
    private JFXButton alt;
    @FXML
    private JFXButton x;
    @FXML
    private JFXButton z;
    @FXML
    private JFXButton zero;
    @FXML
    private JFXButton nine;
    @FXML
    private JFXButton backslash;
    @FXML
    private JFXButton i;
    @FXML
    private JFXButton o;
    @FXML
    private JFXButton comma;
    @FXML
    private JFXButton dot;
    @FXML
    private JFXButton k;
    @FXML
    private JFXButton u;
    @FXML
    private JFXButton j;
    @FXML
    private JFXButton plus;
    @FXML
    private JFXButton minus;
    @FXML
    private JFXButton forword;
    @FXML
    private JFXButton backspace;
    @FXML
    private JFXButton shift2;
    @FXML
    private JFXButton quote;
    @FXML
    private JFXButton semi;
    @FXML
    private JFXButton l;
    @FXML
    private JFXButton openbracket;
    @FXML
    private JFXButton p;
    @FXML
    private JFXButton enter;
    @FXML
    private JFXButton closebracket;
    @FXML
    private JFXButton eight;


    String text1 = "Computers have taken over our lives. People could not function without them, our electricity is run by computers, the government could not function without computers, and there are many others. Hackers are people who illegally gain access to, and sometimes tamper with, information in a computer system. Due to recent media coverage and corporate interest, hacker's activities are now looked down on by society as criminals. Despite the growing trend of hacking, very little research has been done on the hacking world and its culture. The image of a computer hacker has grown from a harmless nerd into a vicious techno-criminal. In reality most hackers are not out to destroy the world. The hackers in today's society are not just board teenagers. Since the introduction of personal computers in the 1970's, the art of computer hacking has grown along with the changing roles of computers in society.";
    String[] ReaderLen = text1.split(" ");
    double max = 100.0;
    double min = 0.0;
    int  wordCount= 0;
    Timeline timeline = new Timeline();

    @Override
    public void initialize(URL url, ResourceBundle rb){

        textReader.setText(text1);
        textReader.setWrapText(true);
        textWriter.setBackground(Background.EMPTY);

        textWriter.setOnKeyPressed((KeyEvent event)-> {

            if (event.getCode() == KeyCode.SPACE) {
                String[] WriterLen = textWriter.getText().split(" ");
                
                if (Arrays.equals(WriterLen, Arrays.copyOfRange(ReaderLen, 0, WriterLen.length))){

                    int wordLen =0;
                    for (int i = 0; i < WriterLen.length; i++) {
                        wordLen += ReaderLen[i].length();
                    }
                    textReader.selectRange(WriterLen.length+wordLen , ReaderLen[WriterLen.length].length()+wordLen+WriterLen.length);
                    wordCount+=1;
            } }

            keyPressed(event.getCode().getChar());

            if (event.getCode() == KeyCode.SHIFT){
                shift.arm(); shift2.arm();
                shift.disarm(); shift2.disarm();
            }
            if(event.getCode() == KeyCode.CONTROL){
                ctrl.arm(); ctrl.arm();
                ctrl2.disarm(); ctrl2.disarm();
            }
            if(event.getCode() == KeyCode.CAPS){
                caps.arm(); caps.arm();
            }
            if(event.getCode() == KeyCode.ALT){
                alt.arm(); alt.arm();
                alt2.arm(); alt2.arm();
            }
            if(event.getCode() == KeyCode.TAB){
                tap.arm(); tap.arm();
            }

        });

        textWriter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                String[] WriterLen = textWriter.getText().split(" ");
                ProgressBar.setProgress((double)WriterLen.length/(double)ReaderLen.length);

            }
        });
    }

    @FXML
    void StartPressed(ActionEvent event) {
        timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        min += 1.0;
                        TimeBar.setProgress(min / max);
                        if (TimeBar.getProgress()==1.0){

                            timesUp();
                            TimeBar.setProgress(0.0);
                            min=0;
                            timeline.stop();

                        }
                    }
                }
        ));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        textWriter.setEditable(true);
        textReader.selectRange(0, ReaderLen[0].length());
    }

    void timesUp(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Times up Title");
        alert.setHeaderText(null);
        alert.setContentText("Times up");
        alert.setContentText("WPM: "+wordCount/(max/60));
        alert.show();
    }

    void keyPressed(String key){

        if(Character.isLetter(key.charAt(0)))
            key = key.toLowerCase();
        switch(key){
            case "q": q.arm(); q.disarm(); break;
            case "w": w.arm(); w.disarm(); break;
            case "e": e.arm(); e.disarm(); break;
            case "r": r.arm(); r.disarm(); break;
            case "t": t.arm(); t.disarm(); break;
            case "y": y.arm(); y.disarm(); break;
            case "u": u.arm(); u.disarm(); break;
            case "i": i.arm(); i.disarm(); break;
            case "o": o.arm(); o.disarm(); break;
            case "p": p.arm(); p.disarm(); break;
            case "a": a.arm(); a.disarm(); break;
            case "s": s.arm(); s.disarm(); break;
            case "d": d.arm(); d.disarm(); break;
            case "f": f.arm(); f.disarm(); break;
            case "g": g.arm(); g.disarm(); break;
            case "h": h.arm(); h.disarm(); break;
            case "j": j.arm(); j.disarm(); break;
            case "k": k.arm(); k.disarm(); break;
            case "l": l.arm(); l.disarm(); break;
            case "z": z.arm(); z.disarm(); break;
            case "x": x.arm(); x.disarm(); break;
            case "c": c.arm(); c.disarm(); break;
            case "v": v.arm(); v.disarm(); break;
            case "b": b.arm(); b.disarm(); break;
            case "n": n.arm(); n.disarm(); break;
            case "m": m.arm(); m.disarm(); break;
            case "1":
            case "!": one.arm(); one.disarm(); break;
            case "2":
            case "@": two.arm(); two.disarm(); break;
            case "3":
            case "#": three.arm(); three.disarm(); break;
            case "4":
            case "$": four.arm(); four.disarm(); break;
            case "5":
            case "%": five.arm(); five.disarm(); break;
            case "6":
            case "^": six.arm(); six.disarm(); break;
            case "7":
            case "&": seven.arm(); seven.disarm(); break;
            case "8":
            case "*": eight.arm(); eight.disarm(); break;
            case "9":
            case "(": nine.arm(); nine.disarm(); break;
            case "0":
            case ")": zero.arm(); zero.disarm(); break;
            case "-":
            case "_": minus.arm(); minus.disarm(); break;
            case "+":
            case "=": plus.arm(); plus.disarm(); break;
            case "{":
            case "[": openbracket.arm(); openbracket.disarm(); break;
            case "}":
            case "]": closebracket.arm(); closebracket.disarm(); break;
            case "|":
            case "\\": backslash.arm(); backslash.disarm(); break;
            case ";":
            case ":": semi.arm(); semi.disarm(); break;
            case "\"":
            case "'": quote.arm(); quote.disarm(); break;
            case ",":
            case "<": comma.arm(); comma.disarm(); break;
            case ">":
            case ".": dot.arm(); dot.disarm(); break;
            case "?":
            case "/": forword.arm(); forword.disarm(); break;
            case "\u21E7": shift.arm();shift2.arm();shift.disarm();shift2.disarm();break;
            case " ": space.arm();space.disarm();break;
            case "\u0008": backspace.arm();backspace.disarm(); break;
        }

    }
}
