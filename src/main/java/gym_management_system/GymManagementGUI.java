package gym_management_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

public class GymManagementGUI extends Application {
    private MemberManager memberManager;
    private WorkoutManager workoutManager;

    @Override
    public void start(Stage primaryStage) {
        memberManager = new MemberManager();
        workoutManager = new WorkoutManager(memberManager);

        // Create the main layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(40));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle("-fx-background-color: black;");

        // Create the header
        Label welcomeLabel = new Label("Gym Management System");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        welcomeLabel.setTextFill(Color.WHITE);

        // Create the button container
        VBox buttonContainer = new VBox(15);
        buttonContainer.setAlignment(Pos.CENTER);

        // Create buttons
        Button registerMemberBtn = createSimpleButton("Register Member");
        Button recordWorkoutBtn = createSimpleButton("Record Workout");
        Button calculateBMIBtn = createSimpleButton("Calculate BMI");
        Button memberInfoBtn = createSimpleButton("Member Info");
        Button exitBtn = createSimpleButton("Exit");

        // Set button actions
        registerMemberBtn.setOnAction(e -> showRegisterMemberDialog());
        recordWorkoutBtn.setOnAction(e -> showRecordWorkoutDialog());
        calculateBMIBtn.setOnAction(e -> showCalculateBMIDialog());
        memberInfoBtn.setOnAction(e -> showMemberInfoDialog());
        exitBtn.setOnAction(e -> {
            Alert confirmExit = new Alert(Alert.AlertType.CONFIRMATION);
            confirmExit.setTitle("Exit Application");
            confirmExit.setHeaderText("Are you sure you want to exit?");
            confirmExit.setContentText("Any unsaved changes will be lost.");
            confirmExit.getDialogPane().setStyle("-fx-background-color: black;");
            
            Button okButton = (Button) confirmExit.getDialogPane().lookupButton(ButtonType.OK);
            Button cancelButton = (Button) confirmExit.getDialogPane().lookupButton(ButtonType.CANCEL);
            styleSimpleButton(okButton);
            styleSimpleButton(cancelButton);
            
            confirmExit.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    primaryStage.close();
                }
            });
        });

        // Add buttons to container
        buttonContainer.getChildren().addAll(
            registerMemberBtn,
            recordWorkoutBtn,
            calculateBMIBtn,
            memberInfoBtn,
            exitBtn
        );

        // Add all components to main layout
        mainLayout.getChildren().addAll(welcomeLabel, buttonContainer);

        // Create the scene
        Scene scene = new Scene(mainLayout, 400, 500);
        primaryStage.setTitle("Gym Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createSimpleButton(String text) {
        Button button = new Button(text);
        styleSimpleButton(button);
        return button;
    }

    private void styleSimpleButton(Button button) {
        button.setStyle(
            "-fx-background-color: black; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-color: white; " +
            "-fx-border-width: 1px;"
        );
        button.setOnMouseEntered(e -> button.setStyle(
            "-fx-background-color: white; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 14px; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-color: white; " +
            "-fx-border-width: 1px;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
            "-fx-background-color: black; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-color: white; " +
            "-fx-border-width: 1px;"
        ));
    }

    private void showRegisterMemberDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Register New Member");
        dialog.setHeaderText("Enter Member Details");
        dialog.getDialogPane().setStyle("-fx-background-color: black;");

        TextField nameField = new TextField();
        TextField ageField = new TextField();
        ComboBox<String> membershipTypeComboBox = new ComboBox<>();
        membershipTypeComboBox.getItems().addAll("Gold", "Silver", "Bronze");
        membershipTypeComboBox.setValue("Bronze"); // Default selection

        // Add input validation for age field
        ageField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ageField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        styleSimpleTextField(nameField);
        styleSimpleTextField(ageField);
        membershipTypeComboBox.setStyle(
            "-fx-background-color: black; " +
            "-fx-text-fill: white; " +
            "-fx-border-color: white; " +
            "-fx-border-width: 1px; " +
            "-fx-padding: 5px; " +
            "-fx-font-size: 14px;"
        );

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: black;");

        Label nameLabel = new Label("Name:");
        Label ageLabel = new Label("Age:");
        Label typeLabel = new Label("Membership Type:");
        nameLabel.setTextFill(Color.WHITE);
        ageLabel.setTextFill(Color.WHITE);
        typeLabel.setTextFill(Color.WHITE);

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(ageLabel, 0, 1);
        grid.add(ageField, 1, 1);
        grid.add(typeLabel, 0, 2);
        grid.add(membershipTypeComboBox, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
        styleSimpleButton(okButton);
        styleSimpleButton(cancelButton);

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String name = nameField.getText().trim();
                String ageText = ageField.getText().trim();
                String membershipType = membershipTypeComboBox.getValue();

                // Validate inputs
                if (name.isEmpty()) {
                    showAlert("Error", "Name cannot be empty.");
                    return;
                }
                if (ageText.isEmpty()) {
                    showAlert("Error", "Age cannot be empty.");
                    return;
                }

                try {
                    int age = Integer.parseInt(ageText);
                    if (age <= 0 || age > 120) {
                        showAlert("Error", "Please enter a valid age between 1 and 120.");
                        return;
                    }
                    memberManager.registerNewMember(name, age, membershipType);
                    showAlert("Success", "Member registered successfully!");
                } catch (NumberFormatException e) {
                    showAlert("Error", "Please enter a valid age (numbers only).");
                }
            }
        });
    }

    private void showRecordWorkoutDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Record Workout");
        dialog.setHeaderText("Enter Workout Details");
        dialog.getDialogPane().setStyle("-fx-background-color: black;");

        TextField memberIdField = new TextField();
        TextField exerciseField = new TextField();
        TextField setsField = new TextField();

        // Add input validation for numeric fields
        memberIdField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                memberIdField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        setsField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[1-5]")) {
                setsField.setText(newValue.replaceAll("[^1-5]", ""));
            }
        });

        styleSimpleTextField(memberIdField);
        styleSimpleTextField(exerciseField);
        styleSimpleTextField(setsField);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: black;");

        Label idLabel = new Label("Member ID:");
        Label exerciseLabel = new Label("Exercise:");
        Label setsLabel = new Label("Number of Sets (1-5):");
        idLabel.setTextFill(Color.WHITE);
        exerciseLabel.setTextFill(Color.WHITE);
        setsLabel.setTextFill(Color.WHITE);

        grid.add(idLabel, 0, 0);
        grid.add(memberIdField, 1, 0);
        grid.add(exerciseLabel, 0, 1);
        grid.add(exerciseField, 1, 1);
        grid.add(setsLabel, 0, 2);
        grid.add(setsField, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
        styleSimpleButton(okButton);
        styleSimpleButton(cancelButton);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    String memberIdText = memberIdField.getText().trim();
                    String exercise = exerciseField.getText().trim();
                    String setsText = setsField.getText().trim();

                    if (memberIdText.isEmpty() || exercise.isEmpty() || setsText.isEmpty()) {
                        showAlert("Error", "Please fill in all fields");
                        return null;
                    }

                    int memberId = Integer.parseInt(memberIdText);
                    int sets = Integer.parseInt(setsText);

                    if (sets < 1 || sets > 5) {
                        showAlert("Error", "Number of sets must be between 1 and 5");
                        return null;
                    }

                    // Verify member exists
                    try {
                        memberManager.getMember(memberId);
                    } catch (Exception e) {
                        showAlert("Error", "Member ID not found");
                        return null;
                    }

                    // Record workout
                    workoutManager.recordWorkout(memberId, exercise, sets);
                    showAlert("Success", "Workout recorded successfully");
                } catch (NumberFormatException e) {
                    showAlert("Error", "Please enter valid numbers for Member ID and Sets");
                } catch (Exception e) {
                    showAlert("Error", "Failed to record workout: " + e.getMessage());
                }
            }
            return buttonType;
        });

        dialog.showAndWait();
    }

    private void showMemberInfoDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Member Information");
        dialog.setHeaderText("Enter Member ID");
        dialog.getDialogPane().setStyle("-fx-background-color: black;");

        TextField memberIdField = new TextField();
        memberIdField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                memberIdField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        styleSimpleTextField(memberIdField);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: black;");

        Label idLabel = new Label("Member ID:");
        idLabel.setTextFill(Color.WHITE);

        grid.add(idLabel, 0, 0);
        grid.add(memberIdField, 1, 0);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
        styleSimpleButton(okButton);
        styleSimpleButton(cancelButton);

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String memberIdText = memberIdField.getText().trim();
                
                if (memberIdText.isEmpty()) {
                    showAlert("Error", "Member ID cannot be empty.");
                    return;
                }

                try {
                    int memberId = Integer.parseInt(memberIdText);
                    String memberInfo = memberManager.getMemberInfo(memberId);
                    
                    // Create a custom alert with black theme
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Member Information");
                    alert.setHeaderText(null);
                    alert.getDialogPane().setStyle("-fx-background-color: black;");
                    
                    // Create a text area for the member info
                    TextArea textArea = new TextArea(memberInfo);
                    textArea.setEditable(false);
                    textArea.setWrapText(true);
                    textArea.setStyle(
                        "-fx-control-inner-background: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-family: 'Courier New';" +
                        "-fx-font-size: 14px;"
                    );
                    
                    alert.getDialogPane().setContent(textArea);
                    
                    // Style the OK button
                    Button alertOkButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                    styleSimpleButton(alertOkButton);
                    
                    alert.showAndWait();
                } catch (NumberFormatException e) {
                    showAlert("Error", "Please enter a valid Member ID (numbers only).");
                } catch (IllegalArgumentException e) {
                    showAlert("Error", "Member not found. Please enter a valid Member ID.");
                }
            }
        });
    }

    private void showCalculateBMIDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Calculate BMI");
        dialog.setHeaderText("Enter Member Details");
        dialog.getDialogPane().setStyle("-fx-background-color: black;");

        TextField memberIdField = new TextField();
        TextField heightField = new TextField();
        TextField weightField = new TextField();

        // Add input validation for numeric fields
        memberIdField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                memberIdField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        heightField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                heightField.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });

        weightField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                weightField.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });

        styleSimpleTextField(memberIdField);
        styleSimpleTextField(heightField);
        styleSimpleTextField(weightField);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: black;");

        Label idLabel = new Label("Member ID:");
        Label heightLabel = new Label("Height (cm):");
        Label weightLabel = new Label("Weight (kg):");
        idLabel.setTextFill(Color.WHITE);
        heightLabel.setTextFill(Color.WHITE);
        weightLabel.setTextFill(Color.WHITE);

        grid.add(idLabel, 0, 0);
        grid.add(memberIdField, 1, 0);
        grid.add(heightLabel, 0, 1);
        grid.add(heightField, 1, 1);
        grid.add(weightLabel, 0, 2);
        grid.add(weightField, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
        styleSimpleButton(okButton);
        styleSimpleButton(cancelButton);

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String memberIdText = memberIdField.getText().trim();
                String heightText = heightField.getText().trim();
                String weightText = weightField.getText().trim();

                // Validate inputs
                if (memberIdText.isEmpty()) {
                    showAlert("Error", "Member ID cannot be empty.");
                    return;
                }
                if (heightText.isEmpty()) {
                    showAlert("Error", "Height cannot be empty.");
                    return;
                }
                if (weightText.isEmpty()) {
                    showAlert("Error", "Weight cannot be empty.");
                    return;
                }

                try {
                    int memberId = Integer.parseInt(memberIdText);
                    double heightCm = Double.parseDouble(heightText);
                    double weight = Double.parseDouble(weightText);

                    if (heightCm <= 0 || heightCm > 300) {
                        showAlert("Error", "Please enter a valid height between 0 and 300 cm.");
                        return;
                    }
                    if (weight <= 0 || weight > 300) {
                        showAlert("Error", "Please enter a valid weight between 0 and 300 kg.");
                        return;
                    }

                    Member member = memberManager.getMember(memberId);
                    if (member == null) {
                        showAlert("Error", "Member not found. Please enter a valid Member ID.");
                        return;
                    }

                    // Convert height from cm to meters
                    double heightMeters = heightCm / 100.0;
                    member.setHeight(heightMeters);
                    member.setWeight(weight);

                    String bmiInfo = String.format("BMI Calculation Results:\n\n" +
                            "Height: %.2f cm (%.2f m)\n" +
                            "Weight: %.2f kg\n" +
                            "BMI: %.2f\n" +
                            "Status: %s",
                            heightCm, heightMeters, weight, member.getBMI(), member.getBMIStatus());

                    showAlert("BMI Results", bmiInfo);
                } catch (NumberFormatException e) {
                    showAlert("Error", "Please enter valid numbers for all fields.");
                }
            }
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.getDialogPane().setStyle("-fx-background-color: black;");
        
        // Create a text area for the content
        TextArea textArea = new TextArea(content);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setStyle(
            "-fx-control-inner-background: black;" +
            "-fx-text-fill: white;" +
            "-fx-font-family: 'Arial';" +
            "-fx-font-size: 14px;"
        );
        
        alert.getDialogPane().setContent(textArea);
        
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        styleSimpleButton(okButton);
        
        alert.showAndWait();
    }

    private void styleSimpleTextField(TextField textField) {
        textField.setStyle(
            "-fx-background-color: black; " +
            "-fx-text-fill: white; " +
            "-fx-border-color: white; " +
            "-fx-border-width: 1px; " +
            "-fx-padding: 5px; " +
            "-fx-font-size: 14px;"
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
} 