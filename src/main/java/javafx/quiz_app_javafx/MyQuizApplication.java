package javafx.quiz_app_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class MyQuizApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/quiz_app_javafx/sign-up.fxml"));
            Scene scene = new Scene(fxmlLoader.<Parent>load());
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            URL fxmlUrl = getClass().getResource("/javafx/quiz_app_javafx/sign-up.fxml");
            System.out.println("FXML Path: " + fxmlUrl);

            if (fxmlUrl == null) {
                System.out.println("FXML file not found! Check the path!");
                System.exit(1);
            }

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);

        }
    }


    public static void main(String[] args) {
        launch();
    }
}
