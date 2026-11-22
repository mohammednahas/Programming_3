
package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import sample.model.Medicine;

public class MedicineView {

    // =========================
    // Main Root
    // =========================

    private final BorderPane root;

    // =========================
    // Controls
    // =========================

    private TextField searchField;

    private TableView<Medicine> medicineTable;

    private Button addButton;

    private Button editButton;

    private Button salesButton;


    public MedicineView() {

        root = new BorderPane();

        createLayout();
    }

    // =========================
    // Main Layout
    // =========================

    private void createLayout() {

        root.setTop(createHeader());

        root.setLeft(createSidebar());

        root.setCenter(createMainContent());
    }

    // =========================================================
    // HEADER
    // =========================================================

    private HBox createHeader() {

        Label logo =
                new Label("FCI PHARMA CARE");

        Label subtitle =
                new Label("Pharmacy Management System");

        VBox branding =
                new VBox(3);

        branding.getChildren().addAll(
                logo,
                subtitle
        );

        Label user =
                new Label("Mohammed  ● Online");

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setPadding(
                new Insets(
                        15,
                        25,
                        15,
                        25
                )
        );

        header.setSpacing(20);

        header.getChildren().addAll(
                branding,
                user
        );

        HBox.setHgrow(
                branding,
                Priority.ALWAYS
        );

        return header;
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private VBox createSidebar() {

        VBox sidebar =
                new VBox(10);

        sidebar.setPadding(
                new Insets(20)
        );

        sidebar.setPrefWidth(220);

        // -------------------------
        // Dashboard Section
        // -------------------------

        Label dashboardTitle =
                new Label("DASHBOARD");

        Button homeButton =
                createMenuButton("🏠  Home");

        Button medicinesButton =
                createMenuButton("💊  Medicines");

        salesButton =
                createMenuButton("🛒  Sales");

        Button reportsButton =
                createMenuButton("📊  Reports");

        // -------------------------
        // Management Section
        // -------------------------

        Label managementTitle =
                new Label("MANAGEMENT");

        //addButton &editbutton
        // دول انا مفعلهم المطلوب منكم تفعيل الباقى ك تاسك

        addButton =
                createMenuButton("➕  Add Medicine");


        editButton =
                createMenuButton("✏  Edit Medicine");

        Button settingsButton =
                createMenuButton("⚙  Settings");

        // -------------------------
        // Spacer
        // -------------------------

        VBox spacer =
                new VBox();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        // -------------------------
        // Logout
        // -------------------------

        Button logoutButton =
                createMenuButton("🚪  Logout");

        // -------------------------
        // Add everything
        // -------------------------

        sidebar.getChildren().addAll(

                dashboardTitle,

                homeButton,
                medicinesButton,
                salesButton,
                reportsButton,

                managementTitle,

                addButton,
                editButton,
                settingsButton,

                spacer,

                logoutButton
        );

        return sidebar;
    }

    // =========================================================
    // MENU BUTTON
    // =========================================================

    private Button createMenuButton(String text) {

        Button button =
                new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(42);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        return button;
    }

    // =========================================================
    // MAIN CONTENT
    // =========================================================

    private VBox createMainContent() {

        VBox content =
                new VBox(20);

        content.setPadding(
                new Insets(30)
        );

        // -------------------------
        // Page Title
        // -------------------------

        Label title =
                new Label("Medicine Search");

        Label description =
                new Label(
                        "Search and manage available medicines"
                );

        VBox titleSection =
                new VBox(5);

        titleSection.getChildren().addAll(
                title,
                description
        );

        // -------------------------
        // Search
        // -------------------------

        HBox searchSection =
                createSearchSection();

        // -------------------------
        // Statistics
        // -------------------------

        HBox statistics =
                createStatistics();

        // -------------------------
        // Table Title
        // -------------------------

        Label tableTitle =
                new Label("Available Medicines");

        // -------------------------
        // Medicine Table
        // -------------------------

        TableView<Medicine> table =
                createMedicineTable();

        // -------------------------
        // Add Components
        // -------------------------

        content.getChildren().addAll(

                titleSection,

                searchSection,

                statistics,

                tableTitle,

                table
        );

        // -------------------------
        // Table takes remaining space
        // -------------------------

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        return content;
    }

    // =========================================================
    // SEARCH AREA
    // =========================================================

    private HBox createSearchSection() {

        searchField =
                new TextField();

        searchField.setPromptText(
                "Search medicine name..."
        );

        searchField.setPrefHeight(45);

        Button searchButton =
                new Button("Search");

        searchButton.setPrefHeight(45);

        HBox searchBox =
                new HBox(10);

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

        searchBox.getChildren().addAll(
                searchField,
                searchButton
        );

        // TextField takes remaining width

        HBox.setHgrow(
                searchField,
                Priority.ALWAYS
        );

        return searchBox;
    }

    // =========================================================
    // STATISTICS CARDS
    // =========================================================

    private HBox createStatistics() {

        HBox cards =
                new HBox(15);

        VBox totalCard =
                createCard(
                        "Total Medicines",
                        "120"
                );

        VBox availableCard =
                createCard(
                        "Available",
                        "108"
                );

        VBox outOfStockCard =
                createCard(
                        "Out of Stock",
                        "12"
                );

        cards.getChildren().addAll(

                totalCard,

                availableCard,

                outOfStockCard
        );

        return cards;
    }

    // =========================================================
    // SINGLE STATISTICS CARD
    // =========================================================

    private VBox createCard(
            String title,
            String value
    ) {

        Label titleLabel =
                new Label(title);

        Label valueLabel =
                new Label(value);

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(15)
        );

        card.setPrefWidth(200);

        card.getChildren().addAll(

                titleLabel,

                valueLabel
        );

        return card;
    }

    // =========================================================
    // MEDICINE TABLE
    // =========================================================

    private TableView<Medicine> createMedicineTable() {

        medicineTable =
                new TableView<>();

        // -----------------------------------------------------
        // Medicine Name Column
        // -----------------------------------------------------

        TableColumn<Medicine, String> nameColumn =
                new TableColumn<>("Medicine");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        // -----------------------------------------------------
        // Price Column
        // -----------------------------------------------------

        TableColumn<Medicine, Double> priceColumn =
                new TableColumn<>("Price");

        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );

        // -----------------------------------------------------
        // Quantity Column
        // -----------------------------------------------------

        TableColumn<Medicine, Integer> quantityColumn =
                new TableColumn<>("Stock");

        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );

        // -----------------------------------------------------
        // Status Column
        // -----------------------------------------------------

        TableColumn<Medicine, String> statusColumn =
                new TableColumn<>("Status");

        statusColumn.setCellFactory(
                column ->
                        new TableCell<Medicine, String>() {

                            @Override
                            protected void updateItem(
                                    String item,
                                    boolean empty
                            ) {

                                super.updateItem(
                                        item,
                                        empty
                                );

                                if (empty) {

                                    setText(null);

                                } else {

                                    Medicine medicine =
                                            getTableView()
                                                    .getItems()
                                                    .get(getIndex());

                                    int quantity =
                                            medicine.getQuantity();

                                    if (quantity == 0) {

                                        setText(
                                                "● Out of Stock"
                                        );

                                    } else if (quantity <= 10) {

                                        setText(
                                                "● Low Stock"
                                        );

                                    } else {

                                        setText(
                                                "● Available"
                                        );
                                    }
                                }
                            }
                        }
        );

        // -----------------------------------------------------
        // Add Columns
        // -----------------------------------------------------

        medicineTable.getColumns().addAll(

                nameColumn,

                priceColumn,

                quantityColumn,

                statusColumn
        );

        // -----------------------------------------------------
        // Column Width
        // -----------------------------------------------------

        nameColumn.setPrefWidth(250);

        priceColumn.setPrefWidth(150);

        quantityColumn.setPrefWidth(150);

        statusColumn.setPrefWidth(180);

        // -----------------------------------------------------
        // Table Resize Policy
        // -----------------------------------------------------

        medicineTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        return medicineTable;
    }

    // =========================================================
    // GET ROOT
    // =========================================================

    public BorderPane getRoot() {

        return root;
    }

    // =========================================================
    // GET SEARCH FIELD
    // =========================================================

    public TextField getSearchField() {

        return searchField;
    }

    // =========================================================
    // GET MEDICINE TABLE
    // =========================================================

    public TableView<Medicine> getMedicineTable() {

        return medicineTable;
    }

    // =========================================================
    // GET EDIT BUTTON
    // =========================================================

    public Button getEditButton() {

        return editButton;
    }


    // =========================================================
    // GET EDIT BUTTON
    // =========================================================

    public Button getAddButton() {

        return addButton;
    }

    // =========================================================
    // GET SALES BUTTON
    // =========================================================

    public Button getSalesButton() {

        return salesButton;
    }
}

