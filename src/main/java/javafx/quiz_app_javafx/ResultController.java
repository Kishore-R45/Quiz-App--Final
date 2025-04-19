package javafx.quiz_app_javafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ResultController {

    @FXML
    private Label remark, marks, markstext, correcttext, wrongtext;

    @FXML
    private ProgressIndicator correct_progress, wrong_progress;

    @FXML
    private Button logout_button;

    private int correct;
    private int wrong;

    @FXML
    private void initialize() {
        // 🎯 Get score from quiz controller
        correct = quizController.correct;
        wrong = quizController.wrong;

        // 🧠 Set score-related texts
        correcttext.setText("Correct Answers : " + correct);
        wrongtext.setText("Incorrect Answers : " + wrong);
        marks.setText(correct + "/10");
        markstext.setText(correct + " Marks Scored");

        // ⏳ Set progress indicators
        correct_progress.setProgress((float) correct / 10);
        wrong_progress.setProgress((float) wrong / 10);

        // 🗣️ Set dynamic remark
        if (correct < 2) {
            remark.setText("Oh no..! You have failed the quiz. It seems that you need to improve your general knowledge. Practice daily!");
        } else if (correct < 5) {
            remark.setText("Oops..! You have scored less marks. It seems like you need to improve your general knowledge.");
        } else if (correct <= 7) {
            remark.setText("Good. A bit more improvement might help you to get better results. Practice is the key to success.");
        } else if (correct == 8 || correct == 9) {
            remark.setText("Congratulations! It's your hard work and determination which helped you to score good marks.");
        } else {
            remark.setText("Congratulations! You have passed the quiz with full marks because of your hard work and dedication. Keep it up!");
        }

        // 🔒 Logout button logic
        logout_button.setOnAction((ActionEvent event) -> {
            try {
                Stage currentStage = (Stage) logout_button.getScene().getWindow();
                currentStage.close();
                SQLconnection.result(str,quizController.correct,10);
                quizController.correct=0;
                quizController.wrong=0;

                FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-up.fxml"));
                Scene scene = new Scene(loader.load());

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace(); // You can replace with a dialog if needed
            }
        });
    }
    public static String str;
    public static void getuser(String s){
        str=s;
    }

}