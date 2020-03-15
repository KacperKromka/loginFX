package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;


public class Controller {


    @FXML
    private TextField loginTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Label errorLbl;

    @FXML
    private Button loginBtn;

    @FXML
    public ButtonBar register;

    Connection myConnection;
    PreparedStatement myStatement = null;
    ResultSet rs = null;

    public Controller()
    {
        myConnection = ConnectionDB.conDB();
    }

    @FXML
    private void login()
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
                    alert.showAndWait();
                } else {
                    errorLbl.setText("Wprowadz poprawny login i hasło");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
