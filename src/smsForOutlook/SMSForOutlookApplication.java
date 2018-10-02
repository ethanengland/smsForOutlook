package smsForOutlook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import javax.mail.AuthenticationFailedException;
import java.net.URL;
import java.util.ArrayList;

/**
 * The smarts of the application.
 */
public class SMSForOutlookApplication extends Application {
    private static Stage loginStage;
    private static Stage mainStage;
    private static EmailSender sender;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        sender = new EmailSender();
        loginStage = primaryStage;
        mainStage = new Stage();
        loginStage.getIcons().add(new Image("file:src/images/Messenger-Icon.png"));
        Parent loginPage = replaceSceneContent("login.fxml");
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        loginStage.setTitle("SMS for Outlook");
        loginStage.setScene(new Scene(loginPage));
        loginStage.show();
    }

    public boolean attemptLogin(String email, String password){
        try {

            sender.signIn(email, password);

        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        sender.setUsername(email);
        sender.setPassword(password);
        sender.setFrom(email);

        return true;
    }


    public boolean sendMessage(String phoneNumber, String subject, String message) {
        Formatter formatter = new Formatter();
        formatter.addPhoneNumber(phoneNumber);
        ArrayList<String> phoneNumbers = formatter.getListOfFormattedPhoneNumbers();

        sender.setSubjectText(subject);
        sender.setMessageText(message);


        for (String number : phoneNumbers) {
            sender.setRecipient(number);
            try {
                sender.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    public void changeStage(String fxml, String title) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.getIcons().add(new Image("file:src/images/Messenger-Icon.png"));
        mainStage.setTitle(title);
        mainStage.setScene(scene);
        mainStage.show();
        this.loginStage.hide();
    }

    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = loginStage.getScene();
        loginStage.sizeToScene();
        return page;
    }

    public void displayDialog(String title, String content) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();

    }

    public String getUsername() {
        return sender.getUsername();
    }

    public void closeStage()
    {
        loginStage.close();
        mainStage.close();
    }


}
