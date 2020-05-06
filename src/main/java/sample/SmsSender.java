package sample;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class SmsSender implements Initializable {

    public static final String ACCOUNT_SID = "ACa9157a0f21ac537a5c5837c29ba32a28";
    public static final String AUTH_TOKEN = "f64670bd4813bd559c6ec69c8b4a62c0";
    public static PhoneNumber PHONE_NUMBER = new PhoneNumber("+13342493876");


    @FXML
    private TextField textTo;

    @FXML
    private TextArea textBody;

    @FXML
    private Label errorLbl;

    @FXML
    private Button btnSend;


    public void createMessage() {
        String to = textTo.getText();
        String body = textBody.getText();
        Message message = Message.creator(
                new PhoneNumber(to),
                PHONE_NUMBER,
                body
        ).create();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
    }

    public void handleButtonEvent(ActionEvent event) {
        if(event.getSource() == btnSend) createMessage();
    }
}
