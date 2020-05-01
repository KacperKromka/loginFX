package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {

    @FXML
    private TextField loginTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private PasswordField passwordTxt2;

    @FXML
    private Label errorLbl;

    @FXML
    private Button loginBtn;

    @FXML
    private Button register;

    @FXML
    protected ChoiceBox<String> txtGender;

    Connection myConnection;
    PreparedStatement myStatement;

    public RegisterController() {
        myConnection = ConnectionDB.conDB();

    }

    @FXML
    private void handleButtonEvent(ActionEvent event) throws IOException {
        if(event.getSource() == loginBtn) loadLogin(event);
        if(event.getSource() == register) saveData();
    }

    @FXML
    private void loadLogin(ActionEvent event) throws IOException
    {
        URL url = getClass().getResource("LoginPanel.fxml");
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void saveData()
    {
        if(passwordTxt.getText().equals(passwordTxt2.getText())) {

            try {
                String st = "INSERT INTO users (id, login_email, login_password) VALUES (NULL ,?,?)";

                myStatement = myConnection.prepareStatement(st);

                myStatement.setString(1, loginTxt.getText());
                myStatement.setString(2, passwordTxt.getText());
                myStatement.executeUpdate();
                errorLbl.setTextFill(Color.GREEN);
                errorLbl.setText("Added Successfully");


            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                errorLbl.setTextFill(Color.TOMATO);
                errorLbl.setText(ex.getMessage());
            }
        }
        else
        {
            errorLbl.setTextFill(Color.TOMATO);
            errorLbl.setText("Podane hasła muszą być identyczne");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtGender.getItems().addAll("Male","Famale");
    }
}
