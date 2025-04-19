package javafx.quiz_app_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class quizController {
    @FXML
    public Label question;
    @FXML
    public Button opt1, opt2, opt3, opt4;

    private int counter = 0;
    public static int correct = 0;
    public static int wrong = 0;

    @FXML
    private void initialize() {
        loadQuestions();
    }

    private void loadQuestions() {
        switch (counter) {
            case 0 -> {
                question.setText("1. What is the capital city of France?");
                setOptions("New Delhi", "Mexico", "London", "Paris");
            }
            case 1 -> {
                question.setText("2. In which year did World War II end?");
                setOptions("1947", "1945", "1946", "1948");
            }
            case 2 -> {
                question.setText("3. Which planet is known as the “Red Planet”?");
                setOptions("Mars", "Earth", "Jupiter", "Saturn");
            }
            case 3 -> {
                question.setText("4. What is the largest mammal in the world?");
                setOptions("Elephant", "Dolphin", "Blue Whale", "Human");
            }
            case 4 -> {
                question.setText("5. What is the capital of China?");
                setOptions("Beijing", "New Delhi", "London", "Moscow");
            }
            case 5 -> {
                question.setText("6. Who is known as the “Father of Modern Physics”?");
                setOptions("Niels Bohr", "Leo Szilard", "Nikola Tesla", "Albert Einstein");
            }
            case 6 -> {
                question.setText("7. What is the largest island in the world?");
                setOptions("Greenland", "India", "Sri Lanka", "Australia");
            }
            case 7 -> {
                question.setText("8. Which planet is known as the “Blue Planet”?");
                setOptions("Mars", "Saturn", "Earth", "Jupiter");
            }
            case 8 -> {
                question.setText("9. What is the currency of Italy?");
                setOptions("Rupees", "Euro", "Dollar", "Yuan");
            }
            case 9 -> {
                question.setText("10. Who discovered the law of gravity?");
                setOptions("Nikola Tesla", "Albert Einstein", "Niels Bohr", "Sir Isaac Newton");
            }
        }
    }

    private void setOptions(String a, String b, String c, String d) {
        opt1.setText(a);
        opt2.setText(b);
        opt3.setText(c);
        opt4.setText(d);
    }

    private boolean checkAnswer(String answer) {
        return switch (counter) {
            case 0 -> answer.equals("Paris");
            case 1 -> answer.equals("1945");
            case 2 -> answer.equals("Mars");
            case 3 -> answer.equals("Blue Whale");
            case 4 -> answer.equals("Beijing");
            case 5 -> answer.equals("Albert Einstein");
            case 6 -> answer.equals("Greenland");
            case 7 -> answer.equals("Earth");
            case 8 -> answer.equals("Euro");
            case 9 -> answer.equals("Sir Isaac Newton");
            default -> false;
        };
    }

    private void handleAnswer(String selectedAnswer, ActionEvent event) {
        if (checkAnswer(selectedAnswer)) {
            correct++;
        } else {
            wrong++;
        }

        if (counter == 9) {
            showResultPage(event);
        } else {
            counter++;
            loadQuestions();
        }
    }

    private void showResultPage(ActionEvent event) {
        try {
            System.out.println("Correct Answers: " + correct);
            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("result.fxml"));
            Parent root = loader.load();
            Scene resultScene = new Scene(root);


            resultScene.setFill(Color.TRANSPARENT);

            Stage resultStage = new Stage();
            resultStage.setScene(resultScene);
            resultStage.initStyle(StageStyle.TRANSPARENT);
            resultStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void opt1Clicked(ActionEvent event) { handleAnswer(opt1.getText(), event); }
    @FXML public void opt2Clicked(ActionEvent event) { handleAnswer(opt2.getText(), event); }
    @FXML public void opt3Clicked(ActionEvent event) { handleAnswer(opt3.getText(), event); }
    @FXML public void opt4Clicked(ActionEvent event) { handleAnswer(opt4.getText(), event); }
}