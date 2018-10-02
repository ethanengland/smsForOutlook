package smsForOutlook;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends SMSForOutlookApplication implements Initializable {

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextArea txtMessage;

    @FXML
    private Label lblEmail;

    public MainController() {
        lblEmail.setText("HHello");
    }


    public void SendMessage(ActionEvent event) {
        sendMessage(txtPhoneNumber.getText(), txtSubject.getText(), txtMessage.getText());
    }

    public void initialize(){
        lblEmail.setText("hello");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblEmail.setText("Hello");
    }
}
