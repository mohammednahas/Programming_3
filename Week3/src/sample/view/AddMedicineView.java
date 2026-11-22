package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddMedicineView {

    private final Stage stage;

    private final TextField nameField;
    private final TextField priceField;
    private final TextField quantityField;

    private final Button saveButton;
    private final Button cancelButton;

    public AddMedicineView() {

        stage = new Stage();

        stage.setTitle("Add Medicine");

        stage.initModality(
                Modality.APPLICATION_MODAL
        );

        // =========================
        // Title
        // =========================

        Label title =
                new Label("Add New Medicine");

        // =========================
        // Labels
        // =========================

        Label nameLabel =
                new Label("Medicine Name");

        Label priceLabel =
                new Label("Price");

        Label quantityLabel =
                new Label("Quantity");

        // =========================
        // Text Fields
        // =========================

        nameField =
                new TextField();

        nameField.setPromptText(
                "Enter medicine name"
        );

        priceField =
                new TextField();

        priceField.setPromptText(
                "Enter price"
        );

        quantityField =
                new TextField();

        quantityField.setPromptText(
                "Enter quantity"
        );

        // =========================
        // Form
        // =========================

        GridPane form =
                new GridPane();

        form.setHgap(15);
        form.setVgap(15);

        form.add(
                nameLabel,
                0,
                0
        );

        form.add(
                nameField,
                1,
                0
        );

        form.add(
                priceLabel,
                0,
                1
        );

        form.add(
                priceField,
                1,
                1
        );

        form.add(
                quantityLabel,
                0,
                2
        );

        form.add(
                quantityField,
                1,
                2
        );

        // =========================
        // Buttons
        // =========================

        saveButton =
                new Button("Save Medicine");

        cancelButton =
                new Button("Cancel");

        HBox buttons =
                new HBox(10);

        buttons.setAlignment(
                Pos.CENTER_RIGHT
        );

        buttons.getChildren().addAll(
                saveButton,
                cancelButton
        );

        // =========================
        // Root
        // =========================

        VBox root =
                new VBox(20);

        root.setPadding(
                new Insets(25)
        );

        root.getChildren().addAll(
                title,
                form,
                buttons
        );

        // =========================
        // Scene
        // =========================

        Scene scene =
                new Scene(
                        root,
                        450,
                        350
                );

        stage.setScene(scene);
    }

    // =========================
    // Getters
    // =========================

    public Stage getStage() {
        return stage;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getPriceField() {
        return priceField;
    }

    public TextField getQuantityField() {
        return quantityField;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public void show() {
        stage.showAndWait();
    }
}