package firstPracticalProject;

import firstPracticalProject.model.Author;
import firstPracticalProject.services.AuthorRepository;
import firstPracticalProject.services.SessionManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    ObservableList<Author> authorList = FXCollections.observableArrayList(AuthorRepository.getAuthorsList());

    public static void main(String[] args) {

        Main.launch();
    }

    @Override
    public void start(Stage stage) {

        giveAllAuthorsInTable(stage);
    }

    @Override
    public void stop() {
        SessionManager.shutdown();
        System.out.println("Application stopped");
    }


    public void giveAllAuthorsInTable(Stage stage) {
        System.out.println("Application started");
        Button button = new Button("Delete");
        TableView<Author> tableView = new TableView<>();

        TableColumn<Author, Integer> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id_author"));

        TableColumn<Author, String> column2 = new TableColumn<>("First name");
        column2.setCellValueFactory(new PropertyValueFactory<>("first_Name"));

        TableColumn<Author, String> column3 = new TableColumn<>("Last name");
        column3.setCellValueFactory(new PropertyValueFactory<>("last_Name"));

        TableColumn<Author, Button> column4 = new TableColumn<>("Action");

        tableView.getColumns().setAll(column1, column2, column3, column4);

        tableView.getItems().setAll(authorList);

        tableView.setPlaceholder(new Label("No rows to display"));

        VBox vBox = new VBox();
        vBox.getChildren().add(tableView);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);

        stage.show();

    }


}

