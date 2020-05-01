package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;


public class Controller {


    @FXML
    private TextField loginTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Label errorLbl;

    @FXML
    private Button loginBtn;

    @FXML
    public Button register;


    Connection myConnection;
    PreparedStatement myStatement = null;
    ResultSet rs = null;

    public Controller()
    {
        myConnection = ConnectionDB.conDB();
    }

    @FXML
    private void handleButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource() == loginBtn) login(event);
        if (event.getSource() == register) loadRegister(event);
    }

    @FXML
    private void loadRegister(ActionEvent event) throws IOException
    {
        URL url = getClass().getResource("RegisterPanel.fxml");
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void loadApi(ActionEvent event) throws IOException
    {
        URL url = getClass().getResource("ApiPanel.fxml");
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void login(ActionEvent event)
    {
        String login = loginTxt.getText();
        String password = passwordTxt.getText();
        if(login.isEmpty() || password.isEmpty())
        {
            errorLbl.setText("Wprowadz login i hasło");
        }
        else {
            try {

                PreparedStatement myStatement = myConnection.prepareStatement("SELECT * FROM users WHERE login_email = ? and login_password = ? ");
                myStatement.setString(1, loginTxt.getText());
                myStatement.setString(2, passwordTxt.getText());
                ResultSet rs = myStatement.executeQuery();
                if (rs.next()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Zalogowano pomyślnie");
                    alert.show();
                    loadApi(event);

                } else {
                    errorLbl.setText("Wprowadz poprawny login i hasło");
                }

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
