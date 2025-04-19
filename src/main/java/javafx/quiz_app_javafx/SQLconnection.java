
//package javafx.quiz_app_javafx;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.sql.*;
//
//public class SQLconnection {
//    public static void changeScene(ActionEvent event, String fxmlFile, String username) {
//        Parent root = null;
//        try {
//            FXMLLoader loader = new FXMLLoader(SQLconnection.class.getResource(fxmlFile));
//            root = loader.load();
//
//            if (username != null) {
//                HomeController homeController = loader.getController();
//                homeController.setUserInformation(username);
//            }
//
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            showAlert("Error loading scene: " + e.getMessage());
//        }
//    }
//
//    public static void signUpUser(ActionEvent event, String username, String password, String doYouLike, boolean terms) {
//        Connection connection = null;
//        PreparedStatement psInsert = null;
//        PreparedStatement psCheckUserExists = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizapp", "root", "root");
//            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
//            psCheckUserExists.setString(1, username);
//            resultSet = psCheckUserExists.executeQuery();
//
//            if (resultSet.isBeforeFirst()) {
//                showAlert("This username is already taken. Please choose another.");
//            } else {
//                psInsert = connection.prepareStatement("INSERT INTO users (username, password, likes_java, terms) VALUES (?, ?, ?, ?)");
//                psInsert.setString(1, username);
//                psInsert.setString(2, password);
//                psInsert.setString(3, doYouLike);
//                psInsert.setBoolean(4, terms);
//                psInsert = connection.prepareStatement("INSERT INTO users (username, password, likes_java, terms) VALUES (?, ?, ?, ?)");
//                psInsert.setString(1, username);
//                psInsert.setString(2, password);
//
//                JavaPreference preference = JavaPreference.fromString(doYouLike);
//                psInsert.setString(3, preference.name()); // Always "Yes" or "No" in proper case
//
//                psInsert.setBoolean(4, terms);
//
//
//                psInsert.executeUpdate();
//
//                changeScene(event, "/javafx/quiz_app_javafx/Home.fxml", username);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            showAlert("Database error: " + e.getMessage());
//        } finally {
//            close(resultSet);
//            close(psCheckUserExists);
//            close(psInsert);
//            close(connection);
//        }
//    }
//
//    public static void logInUser(ActionEvent event, String username, String password) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizapp", "root", "root");
//            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
//            preparedStatement.setString(1, username);
//            resultSet = preparedStatement.executeQuery();
//
//            if (!resultSet.isBeforeFirst()) {
//                showAlert("No user found with provided credentials.");
//            } else {
//                while (resultSet.next()) {
//                    String retrievedPassword = resultSet.getString("password");
//                    if (retrievedPassword.equals(password)) {
//                        changeScene(event, "/javafx/quiz_app_javafx/Home.fxml", username);
//                    } else {
//                        showAlert("Incorrect password. Try again.");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            showAlert("Database error: " + e.getMessage());
//        } finally {
//            close(resultSet);
//            close(preparedStatement);
//            close(connection);
//        }
//    }
//
//    private static void showAlert(String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setContentText(message);
//        alert.show();
//    }
//
//    private static void close(AutoCloseable ac) {
//        if (ac != null) {
//            try {
//                ac.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public enum JavaPreference {
//        Yes, No;
//
//        public static JavaPreference fromString(String value) {
//            return value.equalsIgnoreCase("yes") ? Yes : No;
//        }
//    }
//
//    public static void result(String username, int score, int totalQuestions) {
//        Connection connection = null;
//        PreparedStatement psUpdate = null;
//
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizapp", "root", "Kishore@2006");
//
//            // Update the user's result in the users table
//            String sql = "UPDATE users SET Score = ?, TotalQuestions = ? WHERE username = ?";
//            psUpdate = connection.prepareStatement(sql);
//            psUpdate.setInt(1, score);
//            psUpdate.setInt(2, totalQuestions);
//            psUpdate.setString(3, username);
//
//            int rowsUpdated = psUpdate.executeUpdate();
//            if (rowsUpdated > 0) {
//                System.out.println("Quiz result updated for user: " + username);
//            } else {
//                System.out.println("No user found to update.");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            showAlert("Error updating quiz result: " + e.getMessage());
//        } finally {
//            close(psUpdate);
//            close(connection);
//        }
//    }
//
//}
//package javafx.quiz_app_javafx;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.sql.*;
//import java.util.Objects;
//
//public class SQLconnection {
//    public static void changeScene(ActionEvent event,String fxmlFile,String username){
//        Parent root=null;
//        if(username!=null){
//            try{
//                FXMLLoader loader=new FXMLLoader(SQLconnection.class.getResource(fxmlFile));
//                root=loader.load();
//                HomeController homeController=loader.getController();
//                homeController.setUserInformation(username);
//            }
//            catch(IOException e){
//                e.printStackTrace();
//            }
//        }
//        else{
//            try{
//                root=FXMLLoader.load(SQLconnection.class.getResource(fxmlFile));
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.show();
//    }
//    public static void signUpUser(ActionEvent event,String username,String password,String DoYouLike,String terms){
//        Connection connection=null;
//        PreparedStatement psInsert=null;
//        PreparedStatement psCheckUserExists=null;
//        ResultSet resultSet=null;
//        try{
//            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Kishore@2006");
//            psCheckUserExists=connection.prepareStatement("SELECT * FROM users WHERE users_data=?");
//            psCheckUserExists.setString(1,username);
//            resultSet=psCheckUserExists.executeQuery();
//            if(resultSet.isBeforeFirst()){
//                System.out.println("User already exists!");
//                Alert alert=new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("You cannot use this username");
//                alert.show();
//            }
//            else {
//                psInsert = connection.prepareStatement("INSERT INTO users (username,password,like java or not,terms VALUES (?,?,?,?)");
//                psInsert.setString(1, username);
//                psInsert.setString(2, password);
//                psInsert.setString(3, DoYouLike);
//                psInsert.setString(4, terms);
//                psInsert.executeUpdate();
//
//                changeScene(event, "Home.fxml", username);
//
//            }
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//        finally {
//            if(resultSet!=null){
//                try{
//                    resultSet.close();
//                }
//                catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//            if(psCheckUserExists !=null){
//                try{
//                    psCheckUserExists.close();
//                }
//                catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//            if(psInsert!=null){
//                try{
//                    psInsert.close();
//                }
//                catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//            if(connection!=null){
//                try{
//                    connection.close();
//                }
//                catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    public static void logInUser(ActionEvent event,String username,String password){
//        Connection connection=null;
//        PreparedStatement preparedStatement=null;
//        ResultSet resultSet=null;
//        try{
//            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Kishore@2006");
//            preparedStatement=connection.prepareStatement("SELECT password FROM users WHERE username");
//            preparedStatement.setString(1,username);
//            resultSet=preparedStatement.executeQuery();
//
//            if(!resultSet.isBeforeFirst()){
//                System.out.println("Users not found in the database!");
//                Alert alert=new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Provided credentials are incorrect");
//                alert.show();
//            }
//            else{
//                while(resultSet.next()){
//                    String retrievedPassword=resultSet.getString("password");
//                    if(retrievedPassword.equals(password)){
//                        changeScene(event,"Home.fxml",username);
//                    }
//                    else{
//                        System.out.println("Passwords did not match!");
//                        Alert alert=new Alert(Alert.AlertType.ERROR);
//                        alert.setContentText("The provided credentials are incorrect!");
//                        alert.show();
//                    }
//                }
//            }
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//        finally {
//            if(resultSet!=null){
//                try{
//                    resultSet.close();
//                }
//                catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//            if(preparedStatement != null){
//                try{
//                    preparedStatement.close();
//                }
//                catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//            if(connection!=null){
//                try {
//                    connection.close();
//                }
//                catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
package javafx.quiz_app_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class SQLconnection {
    public static void changeScene(ActionEvent event, String fxmlFile, String username) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(SQLconnection.class.getResource(fxmlFile));
            root = loader.load();

            if (username != null) {
                HomeController homeController = loader.getController();
                homeController.setUserInformation(username);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error loading scene: " + e.getMessage());
        }
    }

    public enum JavaPreference {
                Yes, No;
        public static JavaPreference fromString(String value) {
            return value.equalsIgnoreCase("yes") ? Yes : No;
        }
    }

    public static void signUpUser(ActionEvent event, String username, String password, String doYouLike, boolean terms) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizapp", "root", "root");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                showAlert("This username is already taken. Please choose another.");
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password, likes_java, terms) VALUES (?, ?, ?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);

                JavaPreference preference = JavaPreference.fromString(doYouLike);
                psInsert.setString(3, preference.name()); // Always "Yes" or "No" in proper case

                psInsert.setBoolean(4, terms);


                psInsert.executeUpdate();

                changeScene(event, "/javafx/quiz_app_javafx/Home.fxml", username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database error: " + e.getMessage());
        } finally {
            close(resultSet);
            close(psCheckUserExists);
            close(psInsert);
            close(connection);
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizapp", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                showAlert("No user found with provided credentials.");
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "/javafx/quiz_app_javafx/Home.fxml", username);
                    } else {
                        showAlert("Incorrect password. Try again.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database error: " + e.getMessage());
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    private static void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void result(String username, int score, int totalQuestions) {
        Connection connection = null;
        PreparedStatement psUpdate = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizapp", "root", "root");

            // Update the user's result in the users table
            String sql = "UPDATE users SET Score = ?, TotalQuestions = ? WHERE username = ?";
            psUpdate = connection.prepareStatement(sql);
            psUpdate.setInt(1, score);
            psUpdate.setInt(2, totalQuestions);
            psUpdate.setString(3, username);

            int rowsUpdated = psUpdate.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Quiz result updated for user: " + username);
            } else {
                System.out.println("No user found to update.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error updating quiz result: " + e.getMessage());
        } finally {
            close(psUpdate);
            close(connection);
        }
    }

}