package javafx.quiz_app_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button btn_signup;

    @FXML
    private Button btn_signin1;

    @FXML
    private RadioButton su_java_yes;

    @FXML
    private RadioButton su_java_no;

    @FXML
    private CheckBox su_terms;

    @FXML
    private TextField su_username;

    @FXML
    private TextField su_password;

    private ToggleGroup toggleGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Grouping radio buttons
        toggleGroup = new ToggleGroup();
        su_java_yes.setToggleGroup(toggleGroup);
        su_java_no.setToggleGroup(toggleGroup);
        su_java_yes.setSelected(true);  // Default selection

        su_terms.setSelected(false); // Default: not agreed

        btn_signup.setOnAction(this::handleSignUp);
        btn_signin1.setOnAction(this::handleSignIn);
    }

    private void handleSignUp(ActionEvent event) {
        String username = su_username.getText().trim();
        String password = su_password.getText().trim();
        String javaPreference = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
        boolean termsAgreed = su_terms.isSelected();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Please fill in all information to sign up!");
        } else if (!termsAgreed) {
            showError("You must agree to the Terms and Conditions to proceed.");
        } else {
            SQLconnection.signUpUser(event, username, password, javaPreference, termsAgreed);
        }
    }

    private void handleSignIn(ActionEvent event) {
        SQLconnection.changeScene(event, "/javafx/quiz_app_javafx/sign-in.fxml", null);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
