package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.*;

public class Main extends Application {

    @FXML
    private TextField loginTxt;

    @FXML
    private TextField password;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginPanel.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    protected void login()
    {
        try {
            Connection myConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/login_sql?serverTimezone=UTC",
                    "root",
                    "s3cret"
            );
            PreparedStatement myStatement = myConnection.prepareStatement("SELECT * FROM users WHERE login_mail=? ");
            myStatement.setString(1, loginTxt.getText());
            ResultSet rs = myStatement.executeQuery();
            while (rs.next())
            {
                String tmp_pass = rs.getString("login_password");
                if (tmp_pass == password.toString())
                    System.out.println("Zalogowano");
                else
                    System.out.println("Zle hasło");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

