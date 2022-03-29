/* To Do Later:
 Make the GUI look nicer
 Add a Clear Button?
 */
package enginealgorithm

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
    
    // Input
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
    
    // Layout Text Fields
    
    GridPane grid = new GridPane();
    HBox locationHbox = new HBox();
    HBox latitudeHbox = new HBox();
    HBox longitudeHbox = new HBox();
    HBox startTimeHbox = new HBox();
    HBox endTimeHbox = new HBox();
    
    grid.add(locationHbox, 1, 0);
    grid.add(latitudeHbox, 1, 1);
    grid.add(longitudeHbox, 1, 2);
    grid.add(startTimeHbox, 1, 3);
    grid.add(endTimeHbox, 1, 4);
    
    locationHbox.getChildren().addAll(locationLabel, locationField);
    latitudeHbox.getChildren().addAll(latitudeLabel, latitudeField);
    longitudeHbox.getChildren().addAll(longitudeLabel, longitudeField);
    startTimeHbox.getChildren().addAll(startTimeLabel, startTimeField);
    endTimeHbox.getChildren().addAll(endTimeLabel, endTimeField);
    
    // Error Handling
    Text errorText1 = new Text();
    Text errorText2 = new Text();
    errorText1.setText("One or more fields were submitted empty!");
    errorText2.setText("Please enter input for all fields and try again!");
    errorText1.setFill(Color.RED);
    errorText2.setFill(Color.RED);
    grid.add(errorText1, 1, 8);
    grid.add(errorText2, 1, 9);
    errorText1.setVisible(false);
    errorText2.setVisible(false);
    
    // Success Text
    Text successText1 = new Text();
    Text successText2 = new Text();
    successText1.setText("Your event has been successfully added!");
    successText2.setText("Please submit another input or press finish!");
    successText1.setFill(Color.GREEN);
    successText2.setFill(Color.GREEN);
    grid.add(successText1, 1, 6);
    grid.add(successText2, 1, 7);
    successText1.setVisible(false);
    successText2.setVisible(false);
    
    // Buttons
    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        if (locationField.getText() == null | locationField.getText().isEmpty()) {
          errorText1.setVisible(true);
          errorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else if (latitudeField.getText() == null | latitudeField.getText().isEmpty()) {
          errorText1.setVisible(true);
          errorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else if (longitudeField.getText() == null | longitudeField.getText().isEmpty()) {
          errorText1.setVisible(true);
          errorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else if (startTimeField.getText() == null | startTimeField.getText().isEmpty()) {
          errorText1.setVisible(true);
          errorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else if (endTimeField.getText() == null | endTimeField.getText().isEmpty()) {
          errorText1.setVisible(true);
          errorText2.setVisible(true);
          if (successText1.isVisible() == true) {
            successText1.setVisible(false);
            successText2.setVisible(false);
          }
        }
        else {
          locationName = locationField.getText();
          latitude = Double.parseDouble(latitudeField.getText());
          longitude = Double.parseDouble(longitudeField.getText());
          startTime = startTimeField.getText();
          endTime = endTimeField.getText();
          addEventXML(locationName, latitude, longitude, startTime, endTime);
          locationField.clear();
          latitudeField.clear();
          longitudeField.clear();
          startTimeField.clear();
          endTimeField.clear();
          if (errorText1.isVisible() == true) {
            errorText1.setVisible(false);
            errorText2.setVisible(false);
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
        Platform.exit();
      }});
    
    // Starting Visibility
    locationHbox.setVisible(false);
    latitudeHbox.setVisible(false);
    longitudeHbox.setVisible(false);
    startTimeHbox.setVisible(false);
    endTimeHbox.setVisible(false);
    submit.setVisible(false);
    finish.setVisible(false);
    
    // Start Button
    
    Button start = new Button("Start");
    start.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        // Visibility Change
        Button source = (Button) e.getSource();
        source.setVisible(false);
        locationHbox.setVisible(true);
        latitudeHbox.setVisible(true);
        longitudeHbox.setVisible(true);
        startTimeHbox.setVisible(true);
        endTimeHbox.setVisible(true);
        submit.setVisible(true);
        finish.setVisible(true);
        writeXMLIntro();
      }
    });
    
    // Layout Butons    
    HBox buttonHbox = new HBox();
    grid.add(buttonHbox, 1, 5);
    buttonHbox.getChildren().addAll(submit, start, finish);
    
    
    // Primary Setup
    Scene scene = new Scene(grid, 600, 400);
    primaryStage.setTitle("Time and Space Game Engine");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  // XML Writing Methods
  
  public void writeXMLIntro() {
    try {
      if(!xml.exists()) {
        xml.createNewFile();
      }
      FileWriter fileWrite = new FileWriter(xml);
      BufferedWriter buffWrite = new BufferedWriter(fileWrite);
      buffWrite.write("<?xml version='1.0'?>\n");
      buffWrite.write("<game>\n");
      buffWrite.close();
      
    } catch(IOException ioError) {
      ioError.printStackTrace();
    }
  }
  
  public void addEventXML(String location, double latitude, double longitude, 
                       String startTime, String endTime) {
    try {
      FileWriter fileWrite = new FileWriter(xml, true);
      BufferedWriter buffWrite = new BufferedWriter(fileWrite);
      buffWrite.write("<location id='" + locationName + "'>\n");
      buffWrite.write("<latitude>" + latitude + "</latitude>\n");
      buffWrite.write("<longitude>" + longitude + "</longitude>\n");
      buffWrite.write("<startTime>" + startTime + "</startTime>\n");
      buffWrite.write("<endTime>" + endTime + "</endTime>\n");
      buffWrite.close();
      
    } catch(IOException ioError) {
      ioError.printStackTrace();
    }
  }
  
  public void writeXMLOutro() {
    try {
      FileWriter fileWrite = new FileWriter(xml, true);
      BufferedWriter buffWrite = new BufferedWriter(fileWrite);
      buffWrite.write("<game>\n");
      buffWrite.close();
      
    } catch(IOException ioError) {
      ioError.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    Application.launch(args);
  }
}
