
package sample.controller;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;

import sample.model.Medicine;
import sample.service.MedicineService;
import sample.service.SalesService;
import sample.view.SalesView;

public class SalesController {

    private final SalesService salesService;
    private final SalesView view;
    private final String cashierName;

    public SalesController(
            MedicineService medicineService,
            String cashierName) {

        this.salesService = new SalesService(medicineService);
        this.cashierName = cashierName;

        view = new SalesView(cashierName);

        loadMedicines();

        setupMedicineSelection();

        setupSellButton();

        view.getStage().showAndWait();
    }

    // =====================================================
    // LOAD MEDICINES
    // =====================================================

    private void loadMedicines() {

        view.setMedicines(
                salesService.loadMedicines()
        );
    }

    // =====================================================
    // MEDICINE SELECTION
    // =====================================================

    private void setupMedicineSelection() {

        view.getMedicineComboBox()
                .setOnAction(event -> {

                    Medicine medicine =
                            view.getMedicineComboBox().getValue();

                    if (medicine != null) {

                        view.getStockLabel().setText(
                                "Available stock: "
                                        + medicine.getQuantity()
                        );

                        view.getPriceLabel().setText(
                                "Unit price: "
                                        + medicine.getPrice()
                        );

                        updateTotal();
                    }
                });

        view.getQuantityField()
                .textProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updateTotal()
                );
    }


    private void updateTotal() {

        Medicine medicine =
                view.getMedicineComboBox().getValue();

        if (medicine == null) {

            view.getTotalLabel().setText("Total: -");
            return;
        }

        try {

            int quantity = Integer.parseInt(
                    view.getQuantityField().getText()
            );

            if (quantity <= 0) {

                view.getTotalLabel().setText("Total: -");
                return;
            }

            double total =
                    medicine.getPrice() * quantity;

            view.getTotalLabel().setText(
                    String.format("Total: %.2f", total)
            );

        } catch (NumberFormatException e) {

            view.getTotalLabel().setText("Total: -");
        }
    }

    // =====================================================
    // SELL BUTTON
    // =====================================================

    private void setupSellButton() {

        view.getSellButton()
                .setOnAction(event -> processSale());
    }

    // =====================================================
    // PROCESS SALE USING BACKGROUND THREAD
    // =====================================================

    private void processSale() {

        Medicine selectedMedicine =
                view.getMedicineComboBox().getValue();

        if (selectedMedicine == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Select Medicine",
                    "Please select a medicine."
            );

            return;
        }

        int quantity;

        try {

            quantity = Integer.parseInt(
                    view.getQuantityField().getText()
            );

            if (quantity <= 0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Invalid Quantity",
                    "Please enter a valid positive quantity."
            );

            return;
        }

        String medicineName =
                selectedMedicine.getName();

        double total =
                selectedMedicine.getPrice() * quantity;

        boolean useSynchronization =
                view.getWithSyncRadio().isSelected();

        // Create background Task

        Task<Boolean> saleTask = new Task<Boolean>() {

            @Override
            protected Boolean call() {

                return salesService.sellMedicine(
                        medicineName,
                        quantity,
                        useSynchronization
                );
            }
        };

        view.getSellButton().setDisable(true);

        // =================================================
        // TASK SUCCEEDED
        // =================================================

        saleTask.setOnSucceeded(event -> {

            view.getSellButton().setDisable(false);

            boolean success = saleTask.getValue();

            if (success) {

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Sale Completed",
                        "Cashier: " + cashierName
                                + "\nMedicine: " + medicineName
                                + "\nQuantity: " + quantity
                                + "\nTotal: " + total
                                + "\nMode: "
                                + (useSynchronization
                                ? "With Synchronization"
                                : "Without Synchronization")
                );

                loadMedicines();

                view.getMedicineComboBox()
                        .getSelectionModel()
                        .clearSelection();

                view.getQuantityField().clear();

                view.getStockLabel().setText(
                        "Available stock: -"
                );

                view.getPriceLabel().setText(
                        "Unit price: -"
                );

                view.getTotalLabel().setText(
                        "Total: -"
                );

            } else {

                // Reload stock because another cashier
                // may have changed the available quantity

                loadMedicines();

                showAlert(
                        Alert.AlertType.WARNING,
                        "Sale Failed",
                        "Medicine not found or insufficient stock."
                );
            }
        });

        // =================================================
        // TASK FAILED   task fails to execute call
        // =================================================

        saleTask.setOnFailed(event -> {

            view.getSellButton().setDisable(false);

            Throwable error = saleTask.getException();

            if (error != null) {
                error.printStackTrace();
            }

            showAlert(
                    Alert.AlertType.ERROR,
                    "Error",
                    "An error occurred during the sale."
            );
        });

        // =================================================
        // START BACKGROUND THREAD
        // =================================================

        Thread cashierThread = new Thread(saleTask);



        cashierThread.start();
    }

    // =====================================================
    // ALERT
    // =====================================================

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
