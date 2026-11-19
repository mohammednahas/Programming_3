package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javafx.scene.input.KeyCode;
import sample.model.Medicine;
import sample.service.MedicineService;
import sample.view.AddMedicineView;
import sample.view.EditMedicineView;
import sample.view.MedicineView;

public class MedicineController {

    private final MedicineView view;
    private final MedicineService service;

    private final ObservableList<Medicine>
            allMedicines;

    public MedicineController() {

        view =
                new MedicineView();

        service =
                new MedicineService();

        allMedicines =
                FXCollections.observableArrayList(
                        service.loadMedicines()
                );

        view.getMedicineTable()
                .setItems(allMedicines);



        setupMouseEvents();

        setupKeyboardEvents();

        setupSearch();

        setupAddButton();

        setupEditButton();
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void setupSearch() {

        view.getSearchField()
                .textProperty()
                .addListener(
                        (observable,
                         oldValue,
                         newValue) -> {

                            filterMedicines(
                                    newValue
                            );
                        }
                );






    }

    private void filterMedicines(
            String searchText) {

        ObservableList<Medicine>
                filtered =
                FXCollections.observableArrayList();

        String search =
                searchText
                        .toLowerCase()
                        .trim();

        for (Medicine medicine :
                allMedicines) {

            if (medicine.getName()
                    .toLowerCase()
                    .startsWith(search)) {

                filtered.add(medicine);
            }
        }

        view.getMedicineTable()
                .setItems(filtered);
    }

    // =========================================================
    // ADD MEDICINE
    // =========================================================

    private void setupAddButton() {

        view.getAddButton()
                .setOnAction(event -> {

                    addMedicine();
                });
    }

    private void addMedicine() {

        AddMedicineView addView =
                new AddMedicineView();

        addView.getSaveButton()
                .setOnAction(event -> {

                    saveNewMedicine(
                            addView
                    );
                });

        addView.getCancelButton()
                .setOnAction(event -> {

                    addView.getStage()
                            .close();
                });

        addView.show();
    }

    private void saveNewMedicine(
            AddMedicineView addView) {

        try {

            String name =
                    addView.getNameField()
                            .getText()
                            .trim();

            String priceText =
                    addView.getPriceField()
                            .getText()
                            .trim();

            String quantityText =
                    addView.getQuantityField()
                            .getText()
                            .trim();

            // -------------------------
            // Validate Name
            // -------------------------

            if (name.isEmpty()) {

                showAlert(
                        "Invalid Data",
                        "Medicine name cannot be empty."
                );

                return;
            }

            // -------------------------
            // Validate Price
            // -------------------------

            double price =
                    Double.parseDouble(
                            priceText
                    );

            if (price < 0) {

                showAlert(
                        "Invalid Data",
                        "Price cannot be negative."
                );

                return;
            }

            // -------------------------
            // Validate Quantity
            // -------------------------

            int quantity =
                    Integer.parseInt(
                            quantityText
                    );

            if (quantity < 0) {

                showAlert(
                        "Invalid Data",
                        "Quantity cannot be negative."
                );

                return;
            }

            // -------------------------
            // Create Medicine
            // -------------------------

            Medicine medicine =
                    new Medicine(
                            name,
                            price,
                            quantity
                    );

            // -------------------------
            // Save to File
            // -------------------------

            service.addMedicine(
                    medicine
            );

            // -------------------------
            // Add to ObservableList
            // -------------------------

            allMedicines.add(
                    medicine
            );

            // -------------------------
            // Refresh Table
            // -------------------------

            view.getMedicineTable()
                    .setItems(allMedicines);

            view.getMedicineTable()
                    .refresh();

            // -------------------------
            // Close Window
            // -------------------------

            addView.getStage()
                    .close();

            // -------------------------
            // Success Message
            // -------------------------

            showAlert(
                    "Success",
                    "Medicine added successfully."
            );

        } catch (NumberFormatException e) {

            showAlert(
                    "Invalid Data",
                    "Price must be a number and quantity must be an integer."
            );
        }
    }

    // =========================================================
    // EDIT MEDICINE
    // =========================================================

    private void setupEditButton() {

        view.getEditButton()
                .setOnAction(event -> {

                    editSelectedMedicine();
                });
    }

    private void editSelectedMedicine() {

        Medicine selectedMedicine =
                view.getMedicineTable()
                        .getSelectionModel()
                        .getSelectedItem();

        if (selectedMedicine == null) {

            showAlert(
                    "No Medicine Selected",
                    "Please select a medicine first."
            );

            return;
        }

        EditMedicineView editView =
                new EditMedicineView(
                        selectedMedicine
                );

        editView.getSaveButton()
                .setOnAction(event -> {

                    saveEditedMedicine(
                            selectedMedicine,
                            editView
                    );
                });

        editView.getCancelButton()
                .setOnAction(event -> {

                    editView.getStage()
                            .close();
                });

        editView.show();
    }

    private void saveEditedMedicine(
            Medicine oldMedicine,
            EditMedicineView editView) {

        try {

            String name =
                    editView.getNameField()
                            .getText()
                            .trim();

            double price =
                    Double.parseDouble(
                            editView
                                    .getPriceField()
                                    .getText()
                                    .trim()
                    );

            int quantity =
                    Integer.parseInt(
                            editView
                                    .getQuantityField()
                                    .getText()
                                    .trim()
                    );

            if (name.isEmpty()) {

                showAlert(
                        "Invalid Data",
                        "Medicine name cannot be empty."
                );

                return;
            }

            if (price < 0) {

                showAlert(
                        "Invalid Data",
                        "Price cannot be negative."
                );

                return;
            }

            if (quantity < 0) {

                showAlert(
                        "Invalid Data",
                        "Quantity cannot be negative."
                );

                return;
            }

            Medicine updatedMedicine =
                    new Medicine(
                            name,
                            price,
                            quantity
                    );

            service.updateMedicine(
                    oldMedicine,
                    updatedMedicine
            );

            int index =
                    allMedicines.indexOf(
                            oldMedicine
                    );

            allMedicines.set(
                    index,
                    updatedMedicine
            );

            view.getMedicineTable()
                    .refresh();

            editView.getStage()
                    .close();

            showAlert(
                    "Success",
                    "Medicine updated successfully."
            );

        } catch (NumberFormatException e) {

            showAlert(
                    "Invalid Data",
                    "Price must be a number and quantity must be an integer."
            );
        }
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }

    // =========================================================
// MOUSE EVENTS
// =========================================================
    private void setupMouseEvents() {

        // 1. Mouse Clicked
        view.getSearchField().setOnMouseClicked(event -> {
            System.out.println("Mouse Clicked");
        });

        // 2. Mouse Pressed
        view.getSearchField().setOnMousePressed(event -> {
            System.out.println("Mouse Pressed");
        });

        // 3. Mouse Released
        view.getSearchField().setOnMouseReleased(event -> {
            System.out.println("Mouse Released");
        });

        // 4. Mouse Entered
        view.getSearchField().setOnMouseEntered(event -> {
            System.out.println("Mouse Entered Search Field");
        });

        // 5. Mouse Exited
        view.getSearchField().setOnMouseExited(event -> {
            System.out.println("Mouse Exited Search Field");
        });

        // 6. Mouse Moved
        view.getSearchField().setOnMouseMoved(event -> {
            System.out.println("Mouse Moved");
        });

        // 7. Mouse Dragged
        view.getSearchField().setOnMouseDragged(event -> {
            System.out.println("Mouse Dragged");
        });


        view.getSearchField()
                .setOnMouseClicked(event -> {

                    if (event.getClickCount() == 2) {
                        view.getSearchField().clear();
                    }
                });
    }

    // =========================================================
// KEYBOARD EVENTS
// =========================================================
    private void setupKeyboardEvents() {

        // 1. Key Pressed
        view.getSearchField().setOnKeyPressed(event -> {

            System.out.println(
                    "Key Pressed: " + event.getCode()
            );

            // Press ESC to clear search
            if (event.getCode() == KeyCode.ESCAPE) {
                view.getSearchField().clear();
            }
        });

        // 2. Key Released
        view.getSearchField().setOnKeyReleased(event -> {

            System.out.println(
                    "Key Released: " + event.getCode()
            );
        });

        // 3. Key Typed
        view.getSearchField().setOnKeyTyped(event -> {

            System.out.println(
                    "Key Typed: " + event.getCharacter()
            );
        });
    }

    // =========================================================
    // GET VIEW
    // =========================================================

    public MedicineView getView() {

        return view;
    }
}