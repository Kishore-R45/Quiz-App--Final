package javafx.quiz_app_javafx;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    @FXML
    private Button btn_signin;
    @FXML
    private Button btn_signup1;
    @FXML
    private TextField si_username;
    @FXML
    private TextField si_password;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_signin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SQLconnection.logInUser(event,si_username.getText(),si_password.getText());
            }
        });
        btn_signup1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SQLconnection.changeScene(event,"/javafx/quiz_app_javafx/sign-up.fxml",null);
            }
        });
    }
}
