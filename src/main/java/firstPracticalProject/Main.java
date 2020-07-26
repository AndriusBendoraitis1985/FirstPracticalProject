package firstPracticalProject;

import firstPracticalProject.model.Author;
import firstPracticalProject.repository.AuthorRepository;
import firstPracticalProject.services.SessionManager;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {

    private final TableView<Author> tableAuthors = new TableView<>();
    private final ObservableList<Author> authorList = FXCollections.observableArrayList(AuthorRepository.getAuthorsList());
    private final HBox authorsAddBox = new HBox();

    public static void main(String[] args) {
        Main.launch();
    }

    @Override
    public void start(Stage stage) {
        mapVbox(stage);
    }

    @Override
    public void stop() {
        SessionManager.shutdown();
        System.out.println("Application stopped");
    }

    public void mapVbox(Stage stage) {
        System.out.println("Application started");
        addAllAuthorDataToTable();
        addAuthor();
        VBox root = new VBox();
        root.getChildren().add(tableAuthors);
        root.getChildren().add(authorsAddBox);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addAllAuthorDataToTable() {
        setTableappearance();
        tableAuthors.setEditable(true);

        TableColumn<Author, Integer> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id_author"));

        TableColumn<Author, String> column2 = new TableColumn<>("First name");
        column2.setCellValueFactory(new PropertyValueFactory<>("first_Name"));
        column2.setCellFactory(TextFieldTableCell.forTableColumn());
        column2.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Author, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Author, String> t) {
                        ((Author) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setFirst_Name(t.getNewValue());
                    }
                }
        );

        TableColumn<Author, String> column3 = new TableColumn<>("Last name");
        column3.setCellValueFactory(new PropertyValueFactory<>("last_Name"));
        column3.setCellFactory(TextFieldTableCell.forTableColumn());
        column3.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Author, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Author, String> t) {
                        ((Author) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setLast_Name(t.getNewValue());
                    }
                }
        );

        tableAuthors.getColumns().setAll(column1, column2, column3);
        addDeleteButton();
        addUpdateButton();

        tableAuthors.setItems(authorList);
        tableAuthors.setPlaceholder(new Label("No rows to display"));
    }

    public void setTableappearance() {
        tableAuthors.setPrefWidth(500);
    }

    public void addAuthor() {
        TextField firstNameText = new TextField();
        firstNameText.setPromptText("Enter first name");
        TextField lastNameText = new TextField();
        lastNameText.setPromptText("Enter last name");
        Button addAuthorButton = new Button("Add");
        authorsAddBox.getChildren().add(firstNameText);
        authorsAddBox.getChildren().add(lastNameText);
        authorsAddBox.getChildren().add(addAuthorButton);
        StringProperty firstNameTextProperty = firstNameText.textProperty();
        StringProperty lastNameTextProperty = lastNameText.textProperty();
        firstNameTextProperty.addListener((observable, oldValue, newValue) -> {
            firstNameText.setText(newValue);
        });
        lastNameTextProperty.addListener((observable, oldValue, newValue) -> {
            lastNameText.setText(newValue);
        });
        addAuthorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AuthorRepository.createAuthor(new Author(firstNameText.getText(), lastNameText.getText()));
                System.out.println("new Author added");
                ObservableList<Author> authorList = FXCollections.observableArrayList(AuthorRepository.getAuthorsList());
                tableAuthors.setItems(authorList);
            }
        });
    }

    public void addDeleteButton() {
        TableColumn<Author, Void> button_column = new TableColumn("Delete");
        Callback<TableColumn<Author, Void>, TableCell<Author, Void>> cellFactory = new Callback<TableColumn<Author, Void>, TableCell<Author, Void>>() {
            @Override
            public TableCell<Author, Void> call(final TableColumn<Author, Void> param) {
                final TableCell<Author, Void> cell = new TableCell<Author, Void>() {
                    private final Button button = new Button("Delete");

                    {
                        button.setOnAction((ActionEvent event) -> {
                            Author author = getTableView().getItems().get(getIndex());
                            System.out.println("selected data to delete: " + author);
                            AuthorRepository.deleteAuthor(author);
                            ObservableList<Author> authorList = FXCollections.observableArrayList(AuthorRepository.getAuthorsList());
                            tableAuthors.setItems(authorList);
                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };
        button_column.setCellFactory(cellFactory);
        tableAuthors.getColumns().add(button_column);
    }

    public void addUpdateButton() {
        TableColumn<Author, Void> button_column = new TableColumn();
        Callback<TableColumn<Author, Void>, TableCell<Author, Void>> cellFactory = new Callback<TableColumn<Author, Void>, TableCell<Author, Void>>() {
            @Override
            public TableCell<Author, Void> call(final TableColumn<Author, Void> param) {
                final TableCell<Author, Void> cell = new TableCell<Author, Void>() {
                    private final Button button = new Button("Update");

                    {
                        button.setOnAction((ActionEvent event) -> {
                            Author author = getTableView().getItems().get(getIndex());
                            System.out.println("selected data to update: " + author);
                            AuthorRepository.updateAuthor(author);
                            ObservableList<Author> authorList = FXCollections.observableArrayList(AuthorRepository.getAuthorsList());
                            tableAuthors.setItems(authorList);
                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };
        button_column.setCellFactory(cellFactory);
        tableAuthors.getColumns().add(button_column);
    }
}

