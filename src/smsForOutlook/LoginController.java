package smsForOutlook;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class LoginController extends SMSForOutlookApplication{
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextArea txtMessage;

    @FXML
    private Text lblStatus;

    public void Login(ActionEvent event) throws Exception {
        if(attemptLogin(txtEmail.getText(), txtPassword.getText())) {
            changeStage("/smsForOutlook/main.fxml", "SMS for Outlook");

        }
        else
        {
            displayDialog("Email or Password Incorrect","Login failed. Please use your full email with proper domain name.");
        }
    }

    public void SendMessage(ActionEvent event) {

            lblStatus.setText("Sending...");
            try
            {
                if(sendMessage(txtPhoneNumber.getText(), txtSubject.getText(), txtMessage.getText())){
                    lblStatus.setText("Message sent!");
                }
                else
                {
                    lblStatus.setText("Message Failed!");
                }
            }
            catch(Exception e)
            {
                lblStatus.setText("Message Failed!");
            }
    }

    public void Close(ActionEvent event)
    {
        closeStage();
    }

    public void NewMessage(ActionEvent event)
    {
        txtSubject.setText("");
        txtMessage.setText("");
    }


    public void Copy(ActionEvent event)
    {
        txtMessage.copy();
        txtSubject.copy();
        txtPhoneNumber.copy();

    }

    public void Cut(ActionEvent event)
    {
        txtMessage.cut();
        txtSubject.cut();
        txtPhoneNumber.cut();
    }

    public void Paste(ActionEvent event)
    {
        txtMessage.paste();
    }

    public void About(ActionEvent event) {

        String readme = "";
        try {
            readme = new Scanner(new File("src/README.txt")).useDelimiter("\\A").next();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        displayDialog("About", readme);
    }

}
