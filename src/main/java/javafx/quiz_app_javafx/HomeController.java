package javafx.quiz_app_javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HomeController{
    @FXML
    private Button btn_play;
    @FXML
    private Label welcome_label;
    @FXML
    private void initialize() throws IOException {
        btn_play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    thisstage.close();
                    FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/javafx/quiz_app_javafx/quiz.fxml"));
                    Scene scene=new Scene(fxmlLoader.load());
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    scene.setFill(Color.TRANSPARENT);
                    stage.show();
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
    public void setUserInformation(String username){
        welcome_label.setText("Welcome "+username+"❤ !");
        ResultController.getuser(username);
    }
}