package sample;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class SmsSender implements Initializable {

    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";
    public static PhoneNumber PHONE_NUMBER = new PhoneNumber("+13342493876");


    @FXML
    private TextField textTo;

    @FXML
    private TextField textBody;

    @FXML
    private Label errorLbl;

    @FXML
    private Button btnSend;

    @FXML
    private void handleButtonEvent(ActionEvent event,URL url, ResourceBundle resourceBundle) {
        if(event.getSource() == btnSend) createMessage();
    }

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
}
