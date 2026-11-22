
package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.model.Medicine;

import java.util.List;

public class SalesView {

    private final Stage stage;

    private ComboBox<Medicine> medicineComboBox;
    private TextField quantityField;

    private Label stockLabel;
    private Label priceLabel;
    private Label totalLabel;

    private Button sellButton;

    // Synchronization options
    private RadioButton withoutSyncRadio;
    private RadioButton withSyncRadio;

    public SalesView(String cashierName) {

        stage = new Stage();

        // Title
        Label title = new Label("Pharmacy Sales");

        Label cashierLabel =
                new Label("Cashier: " + cashierName);

        // Medicine selection
        medicineComboBox = new ComboBox<>();
        medicineComboBox.setPromptText("Select Medicine");
        medicineComboBox.setMaxWidth(Double.MAX_VALUE);

        // Quantity
        quantityField = new TextField();
        quantityField.setPromptText("Enter quantity");

        // Information labels
        stockLabel = new Label("Available stock: -");
        priceLabel = new Label("Unit price: -");
        totalLabel = new Label("Total: -");

        // Sale button
        sellButton = new Button("Complete Sale");
        sellButton.setMaxWidth(Double.MAX_VALUE);

        // Synchronization options
        ToggleGroup synchronizationGroup = new ToggleGroup();

        withoutSyncRadio =
                new RadioButton("Without Synchronization");

        withSyncRadio =
                new RadioButton("With Synchronization");

        withoutSyncRadio.setToggleGroup(synchronizationGroup);
        withSyncRadio.setToggleGroup(synchronizationGroup);

        // Default selection
        withoutSyncRadio.setSelected(true);

        // Form layout
        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(15);
        form.setPadding(new Insets(20));

        form.add(new Label("Medicine:"), 0, 0);
        form.add(medicineComboBox, 1, 0);

        form.add(new Label("Quantity:"), 0, 1);
        form.add(quantityField, 1, 1);

        form.add(stockLabel, 1, 2);
        form.add(priceLabel, 1, 3);
        form.add(totalLabel, 1, 4);

        // Synchronization selection
        form.add(new Label("Sale Mode:"), 0, 5);
        form.add(withoutSyncRadio, 1, 5);
        form.add(withSyncRadio, 1, 6);

        // Main layout
        VBox root = new VBox(15);

        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER_LEFT);

        root.getChildren().addAll(
                title,
                cashierLabel,
                form,
                sellButton
        );

        // Scene
        Scene scene = new Scene(root, 450, 450);

        stage.setTitle("Sales - " + cashierName);
        stage.setScene(scene);

    }

    // Load medicines into ComboBox
    public void setMedicines(List<Medicine> medicines) {

        medicineComboBox.getItems().setAll(medicines);
    }

    // Getters

    public Stage getStage() {
        return stage;
    }

    public ComboBox<Medicine> getMedicineComboBox() {
        return medicineComboBox;
    }

    public TextField getQuantityField() {
        return quantityField;
    }

    public Label getStockLabel() {
        return stockLabel;
    }

    public Label getPriceLabel() {
        return priceLabel;
    }

    public Label getTotalLabel() {
        return totalLabel;
    }

    public Button getSellButton() {
        return sellButton;
    }

    public RadioButton getWithoutSyncRadio() {
        return withoutSyncRadio;
    }

    public RadioButton getWithSyncRadio() {
        return withSyncRadio;
    }
}
