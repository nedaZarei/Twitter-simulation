package com.example.twitter_client;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import static com.example.twitter_client.Controller.input;

public class PollInfo implements Serializable {
    private String description;
    private int numOptions;
    private String options;
    private String id;
    public PollInfo(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOptions() {
        return numOptions;
    }

    public void setNumOptions(int numOptions) {
        this.numOptions = numOptions;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class Poll extends GridPane {
    private int numOptions;
    private Label numOptionsLabel;
    private TextField numOptionsField;
    private String description;
    private Label descriptionLabel;
    private TextField descriptionField;
    private Button createPollButton;
    private ScrollPane pollScrollPane;
    private VBox pollContent;
    private PollInfo pollInfo;

    public Poll(ScrollPane pollScrollPane, VBox pollContent, PollInfo pollInfo) {
        numOptionsLabel = new Label("Number of poll options:");
        descriptionLabel = new Label("Write description about your poll:");
        numOptionsField = new TextField();
        descriptionField = new TextField();
        createPollButton = new Button("Create Poll");
        this.pollContent = pollContent;
        this.pollScrollPane = pollScrollPane;
        this.pollInfo = pollInfo;
        setConfig();
        setLocation();
        setActions();
    }
    private void setConfig(){
        this.setPrefSize(330, 560);
        this.setPadding(new Insets(10));
        this.setVgap(10);
        this.setHgap(10);
    }
    private void setLocation(){
        this.add(descriptionLabel, 0, 0);
        this.add(descriptionField, 0, 1);
        this.add(numOptionsLabel, 0, 2);
        this.add(numOptionsField, 0, 3);
        this.add(createPollButton, 2, 5);
    }
    private void setActions(){
        createPollButton.setOnAction(event -> {
            description = descriptionField.getText();
            try {
                numOptions = Integer.parseInt(numOptionsField.getText());
                pollContent.getChildren().remove(this);
                pollInfo.setDescription(description);
                pollInfo.setNumOptions(numOptions);
                PollOptions poll2 = new PollOptions(numOptions, pollScrollPane, pollContent, pollInfo);
                pollContent.getChildren().add(poll2);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid number.");
                alert.showAndWait();
            }
        });
    }

    public int getNumOptions() {
        return numOptions;
    }

    public String getDescription() {
        return description;
    }

}
class PollOptions extends GridPane{
    private int numOptions;
    private RadioButton[] radioButtons;
    private TextField[] optionFields;
    private ScrollPane pollScrollPane;
    private VBox pollContent;
    String options = "";
    private PollInfo pollInfo;
    public PollOptions(int numOptions, ScrollPane pollScrollPane, VBox pollContent, PollInfo pollInfo) {
        this.numOptions = numOptions;
        this.pollScrollPane = pollScrollPane;
        this.pollContent = pollContent;
        this.pollInfo = pollInfo;
        setConfig();
        createPollForm();
    }

    private void setConfig(){
        this.setPrefSize(330, 560);
        this.setPadding(new Insets(10));
        this.setVgap(10);
        this.setHgap(10);
    }

    private void createPollForm() {
        Label[] optionLabels = new Label[numOptions];
        optionFields = new TextField[numOptions];
        for (int i = 0; i < numOptions; i++) {
            optionLabels[i] = new Label("Option " + (i+1) + ":");
            optionFields[i] = new TextField();
            this.add(optionLabels[i], 0, i);
            this.add(optionFields[i], 1, i);
        }
        Button submitButton = new Button("Submit");
        this.add(submitButton, 1, numOptions);
        submitButton.setOnAction(event -> {
            RadioButton[] radioButtons = new RadioButton[numOptions];
            pollContent.getChildren().remove(this);
            for (int i = 0; i < numOptions; i++) {
                String str = optionFields[i].getText();
                radioButtons[i] = new RadioButton(str);
                options += (str + ",");
            }
            pollInfo.setOptions(options.substring(0, options.length()-1));
            Scene scene = null;
            try {
                ClientRequest req = new ClientRequest("create-poll", ServerOutput.token, pollInfo.getDescription() + "$"+
                        pollInfo.getNumOptions() + "$" + pollInfo.getOptions());
                input.send(req);
                scene = Main.controller.setControllerTimeLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Main.controller.changeWindowToTimeline(scene);
        });
    }

    public RadioButton[] getRadioButtons() {
        return radioButtons;
    }

    public TextField[] getOptionFields() {
        return optionFields;
    }
    public String getOptions(){
        return options.substring(0, options.length()-1);
    }
}
class PollForm extends GridPane{
    private PollInfo poll;
    private String[] options;
    private ArrayList<RadioButton> radioButtons;
    private String selectionButton;
    public PollForm(PollInfo pollInfo) {
        this.poll = pollInfo;
        radioButtons = new ArrayList<>();
        setConfig();
        createPoll();
    }
    private void setConfig(){
        this.setPrefSize(330, 300);
        this.setPadding(new Insets(10));
        this.setVgap(10);
        this.setHgap(10);
    }
    private void createPoll(){
        options = poll.getOptions().split(",");
        this.add(new Label(poll.getDescription()), 0, 0);
        ToggleGroup toggleGroup = new ToggleGroup();

        for (int i = 0; i < poll.getNumOptions(); i++) {
            RadioButton radioButton = new RadioButton(options[i]);
            radioButton.setToggleGroup(toggleGroup);
            this.add(radioButton, 0, i+1);
            radioButtons.add(radioButton);
        }
        Button submitButton = new Button("Submit");
        this.add(submitButton, 1, poll.getNumOptions() +1);
        submitButton.setOnAction(event -> {
            for(RadioButton rd : radioButtons){
                if(rd.isSelected()){
                    ClientRequest req = new ClientRequest("vote", ServerOutput.token, rd.getText() + "$" + poll.getId());
                    try {
                        input.send(req);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }
        });
    }

    public String getSelectionButton() {
        return selectionButton;
    }
}