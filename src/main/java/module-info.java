module javafx.quiz_app_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens javafx.quiz_app_javafx to javafx.fxml;
    exports javafx.quiz_app_javafx;
}