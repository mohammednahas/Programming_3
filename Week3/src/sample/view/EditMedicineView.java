package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import sample.model.Medicine;

public class EditMedicineView {

    private final Stage stage;

    private final TextField nameField;
    private final TextField priceField;
    private final TextField quantityField;

    private final Button saveButton;
    private final Button cancelButton;

    public EditMedicineView(Medicine medicine) {

        stage = new Stage();

        stage.setTitle("Edit Medicine");

        stage.initModality(Modality.APPLICATION_MODAL);

        Label title = new Label("Edit Medicine");

        Label nameLabel = new Label("Medicine Name");
        Label priceLabel = new Label("Price");
        Label quantityLabel = new Label("Quantity");

        nameField = new TextField();
        priceField = new TextField();
        quantityField = new TextField();

        nameField.setText(medicine.getName());
        priceField.setText(String.valueOf(medicine.getPrice()));
        quantityField.setText(String.valueOf(medicine.getQuantity()));

        saveButton = new Button("Save Changes");
        cancelButton = new Button("Cancel");

        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(15);

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);

        form.add(priceLabel, 0, 1);
        form.add(priceField, 1, 1);

        form.add(quantityLabel, 0, 2);
        form.add(quantityField, 1, 2);

        VBox buttons = new VBox(10);

        buttons.setAlignment(Pos.CENTER_RIGHT);

        buttons.getChildren().addAll(
                saveButton,
                cancelButton
        );

        VBox root = new VBox(20);

        root.setPadding(new Insets(25));

        root.getChildren().addAll(
                title,
                form,
                buttons
        );

        Scene scene = new Scene(root, 450, 350);

        stage.setScene(scene);
    }

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