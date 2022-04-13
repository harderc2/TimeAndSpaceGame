/* To Do Later:
 Make the GUI look nicer
 Add a Clear Button
 */
package enginealgorithm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GameEngineGUI extends Application {
  
  // Necessary Variables
  String title;
  int timeTog;
  int locID;
  int onTime;
  int wrongTime;
  String locationName;
  double latitude;
  double longitude;
  String startTime;
  String endTime;
  // Indicates if an event has been added or not
  boolean containsData = false;
  File xml = new File("input.XML");
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    
    // Main Input
    Label locIDLabel = new Label("Enter a location ID number: ");
    TextField locIDField = new TextField();
    Label onTimeLabel = new Label("Enter the location ID for the next location after a success: ");
    TextField onTimeField = new TextField();
    Label wrongTimeLabel = new Label("Enter the location ID for the next location after a failure: ");
    TextField wrongTimeField = new TextField();
    Label locationLabel = new Label("Enter a location name: ");
    TextField locationField = new TextField();
    Label latitudeLabel = new Label("Enter the location's latitude: ");
    TextField latitudeField = new TextField();
    Label longitudeLabel = new Label("Enter the location's longitude: ");
    TextField longitudeField = new TextField();
    Label startTimeLabel = new Label("Enter a start time for reaching the location: ");
    TextField startTimeField = new TextField();
    Label endTimeLabel = new Label("Enter an end time for reaching the location: ");
    TextField endTimeField = new TextField();
    
    // Intro Input
    Label titleLabel = new Label("Enter your game's title: ");
    TextField titleField = new TextField();
    Label timeTogLabel = new Label("Enter 0 for running times or 1 for hard times: ");
    TextField timeTogField = new TextField();
    
    // Time Tog Explanation Text
    Text explanationText1 = new Text();
    Text explanationText2 = new Text();
    Text explanationText3 = new Text();
    
    explanationText1.setText("Running times indicate a certain amount of time after the game starts.");
    explanationText2.setText("(For example, 00:00 or 00:15, indicating 0 and 15 minutes after the game starts)");
    explanationText3.setText("Hard times indicate exact times, like 12:15 or 1:30.");
    
    // Layout Main Text Fields
    
    GridPane mainGrid = new GridPane();
    GridPane introGrid = new GridPane();
    HBox locIDHbox = new HBox();
    HBox onTimeHbox = new HBox();
    HBox wrongTimeHbox = new HBox();
    HBox locationHbox = new HBox();
    HBox latitudeHbox = new HBox();
    HBox longitudeHbox = new HBox();
    HBox startTimeHbox = new HBox();
    HBox endTimeHbox = new HBox();
    
    mainGrid.add(locIDHbox, 1, 0);
    mainGrid.add(onTimeHbox, 1, 1);
    mainGrid.add(wrongTimeHbox, 1, 2);
    mainGrid.add(locationHbox, 1, 3);
    mainGrid.add(latitudeHbox, 1, 4);
    mainGrid.add(longitudeHbox, 1, 5);
    mainGrid.add(startTimeHbox, 1, 6);
    mainGrid.add(endTimeHbox, 1, 7);
    
    locIDHbox.getChildren().addAll(locIDLabel, locIDField);
    onTimeHbox.getChildren().addAll(onTimeLabel, onTimeField);
    wrongTimeHbox.getChildren().addAll(wrongTimeLabel, wrongTimeField);
    locationHbox.getChildren().addAll(locationLabel, locationField);
    latitudeHbox.getChildren().addAll(latitudeLabel, latitudeField);
    longitudeHbox.getChildren().addAll(longitudeLabel, longitudeField);
    startTimeHbox.getChildren().addAll(startTimeLabel, startTimeField);
    endTimeHbox.getChildren().addAll(endTimeLabel, endTimeField);
    
    // Layout Intro Text Fields
    HBox titleHbox = new HBox();
    HBox timeTogHbox = new HBox();
    
    introGrid.add(titleHbox, 1, 0);
    introGrid.add(timeTogHbox, 1, 1);
    introGrid.add(explanationText1, 1, 2);
    introGrid.add(explanationText2, 1, 3);
    introGrid.add(explanationText3, 1, 4);
    titleHbox.getChildren().addAll(titleLabel, titleField);
    timeTogHbox.getChildren().addAll(timeTogLabel, timeTogField);
    
    // Main Error Handling
    Text mainErrorText1 = new Text();
    Text mainErrorText2 = new Text();
    mainErrorText1.setText("One or more fields were submitted empty!");
    mainErrorText2.setText("Please enter input for all fields and try again!");
    mainErrorText1.setFill(Color.RED);
    mainErrorText2.setFill(Color.RED);
    mainGrid.add(mainErrorText1, 1, 11);
    mainGrid.add(mainErrorText2, 1, 12);
    mainErrorText1.setVisible(false);
    mainErrorText2.setVisible(false);
    
    // Intro Error Handling
    Text introErrorText1 = new Text();
    Text introErrorText2 = new Text();
    Text introErrorText3 = new Text();
    introErrorText1.setText("One or more fields were submitted empty!");
    introErrorText2.setText("Please enter input for all fields and try again!");
    introErrorText3.setText("The time toggle field must be set to 0 or 1!");
    introErrorText1.setFill(Color.RED);
    introErrorText2.setFill(Color.RED);
    introErrorText3.setFill(Color.RED);
    introGrid.add(introErrorText1, 1, 6);
    introGrid.add(introErrorText2, 1, 7);
    introGrid.add(introErrorText3, 1, 8);
    introErrorText1.setVisible(false);
    introErrorText2.setVisible(false);
    introErrorText3.setVisible(false);
    
    // Main Success Text
    Text successText1 = new Text();
    Text successText2 = new Text();
    successText1.setText("Your event has been successfully added!");
    successText2.setText("Please submit another input or press finish!");
    successText1.setFill(Color.GREEN);
    successText2.setFill(Color.GREEN);
    mainGrid.add(successText1, 1, 9);
    mainGrid.add(successText2, 1, 10);
    successText1.setVisible(false);
    successText2.setVisible(false);
    
    // Buttons
    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        if (locIDField.getText() == null | locIDField.getText().isEmpty()) {
          mainErrorText1.setVisible(true);
          mainErrorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else if (onTimeField.getText() == null | onTimeField.getText().isEmpty()) {
          mainErrorText1.setVisible(true);
          mainErrorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else if (wrongTimeField.getText() == null | wrongTimeField.getText().isEmpty()) {
          mainErrorText1.setVisible(true);
          mainErrorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else if (locationField.getText() == null | locationField.getText().isEmpty()) {
          mainErrorText1.setVisible(true);
          mainErrorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else if (latitudeField.getText() == null | latitudeField.getText().isEmpty()) {
          mainErrorText1.setVisible(true);
          mainErrorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else if (longitudeField.getText() == null | longitudeField.getText().isEmpty()) {
          mainErrorText1.setVisible(true);
          mainErrorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else if (startTimeField.getText() == null | startTimeField.getText().isEmpty()) {
          mainErrorText1.setVisible(true);
          mainErrorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else if (endTimeField.getText() == null | endTimeField.getText().isEmpty()) {
          mainErrorText1.setVisible(true);
          mainErrorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else {
          locID = Integer.parseInt(locIDField.getText());
          onTime = Integer.parseInt(onTimeField.getText());
          wrongTime = Integer.parseInt(wrongTimeField.getText());
          locationName = locationField.getText();
          latitude = Double.parseDouble(latitudeField.getText());
          longitude = Double.parseDouble(longitudeField.getText());
          startTime = startTimeField.getText();
          endTime = endTimeField.getText();
          addEventXML(locationName, locID, onTime, wrongTime, latitude, longitude, startTime, endTime);
          locIDField.clear();
          onTimeField.clear();
          wrongTimeField.clear();
          locationField.clear();
          latitudeField.clear();
          longitudeField.clear();
          startTimeField.clear();
          endTimeField.clear();
          if (mainErrorText1.isVisible() == true) {
            mainErrorText1.setVisible(false);
            mainErrorText2.setVisible(false);
          }
          successText1.setVisible(true);
          successText2.setVisible(true);
          containsData = true;
        }
      }});
    
    Button finish = new Button("Finish");
    finish.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        writeXMLOutro();
        String[] args = {""};
        EngineAlgorithm.main(args);
        Platform.exit();
      }});
    
    // Scene Setup
    Scene mainScene = new Scene(mainGrid, 600, 400);
    Scene introScene = new Scene(introGrid, 600, 400);
    
    // Start Button
    Button start = new Button("Start");
    start.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        if (titleField.getText() == null | titleField.getText().isEmpty()) {
          introErrorText1.setVisible(true);
          introErrorText2.setVisible(true);
        }
        else if (timeTogField.getText() == null | timeTogField.getText().isEmpty()) {
          introErrorText1.setVisible(true);
          introErrorText2.setVisible(true);
        }
        else if (Integer.parseInt(timeTogField.getText()) != 0 && Integer.parseInt(timeTogField.getText()) != 1) {
          introErrorText3.setVisible(true);
          introErrorText1.setVisible(false);
          introErrorText2.setVisible(false);
        }
        else {
          primaryStage.setScene(mainScene);
          title = titleField.getText();
          timeTog = Integer.parseInt(timeTogField.getText());
          writeXMLIntro(title, timeTog);
        }
      }
    });
    
    // Layout Butons    
    HBox mainButtonHbox = new HBox();
    mainGrid.add(mainButtonHbox, 1, 8);
    mainButtonHbox.getChildren().addAll(submit, finish);
    HBox introButtonHbox = new HBox();
    introGrid.add(introButtonHbox, 1, 5);
    introButtonHbox.getChildren().addAll(start);
   
    // Primary Setup
    primaryStage.setTitle("Time and Space Game Engine");
    primaryStage.setScene(introScene);
    primaryStage.show();
  }
  
  // XML Writing Methods
  
  public void writeXMLIntro(String title, int timeTog) {
    try {
      if(!xml.exists()) {
        xml.createNewFile();
      }
      FileWriter fileWrite = new FileWriter(xml);
      BufferedWriter buffWrite = new BufferedWriter(fileWrite);
      buffWrite.write("<?xml version='1.0'?>\n");
      buffWrite.write("<game>\n");
      buffWrite.write("<title>" + title + "</title>\n");
      buffWrite.write("<timeTog>" + timeTog + "</timeTog>\n");
      buffWrite.close();
      
    } catch(IOException ioError) {
      ioError.printStackTrace();
    }
  }
  
  public void addEventXML(String location, int locID, int onTime, int wrongTime, 
                          double latitude, double longitude, String startTime, String endTime) {
    try {
      FileWriter fileWrite = new FileWriter(xml, true);
      BufferedWriter buffWrite = new BufferedWriter(fileWrite);
      buffWrite.write("<location id='" + locationName + "'>\n");
      buffWrite.write("<locID>" + locID + "</locID>\n");
      buffWrite.write("<onTime>" + onTime + "</onTime>\n");
      buffWrite.write("<wrongTime>" + wrongTime + "</wrongTime>\n");
      buffWrite.write("<latitude>" + latitude + "</latitude>\n");
      buffWrite.write("<longitude>" + longitude + "</longitude>\n");
      buffWrite.write("<startTime>" + startTime + "</startTime>\n");
      buffWrite.write("<endTime>" + endTime + "</endTime>\n");
      buffWrite.write("</location>\n");
      buffWrite.close();
      
    } catch(IOException ioError) {
      ioError.printStackTrace();
    }
  }
  
  public void writeXMLOutro() {
    try {
      FileWriter fileWrite = new FileWriter(xml, true);
      BufferedWriter buffWrite = new BufferedWriter(fileWrite);
      buffWrite.write("</game>\n");
      buffWrite.close();
      
    } catch(IOException ioError) {
      ioError.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    Application.launch(args);
  }
}