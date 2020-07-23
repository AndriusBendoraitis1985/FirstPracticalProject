package firstPracticalProject;

import firstPracticalProject.model.Author;
import firstPracticalProject.services.AuthorRepository;
import firstPracticalProject.services.SessionManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    List<Author> authorList = AuthorRepository.getAuthorsList();
    Author author = AuthorRepository.getAuthorById(1);

    public static void main(String[] args) {

        Main.launch();
    }

    @Override
    public void start(Stage stage) {
        //giveFirstAuthor(stage);
        giveAllAuthorsInTable(stage);
        //authorList.stream().forEach(System.out::println);
        //System.out.println(authorList.get(1));
        //System.out.println(authorList.size());

    }

    @Override
    public void stop() {
        SessionManager.shutdown();
        System.out.println("Application stopped");
    }

    public void giveFirstAuthor(Stage stage) {

        String firstAuthorFirstName = author.getFirst_Name();
        String firstAuthorLastName = author.getLast_Name();

        System.out.println("First Author name is: " + firstAuthorFirstName);

        System.out.println("Application started");
        VBox column2 = new VBox();
        column2.getChildren().add(new Label(firstAuthorFirstName + " " + firstAuthorLastName));
        column2.getChildren().add(new Label(firstAuthorLastName + " " + firstAuthorFirstName));

        VBox column1 = new VBox();
        column1.getChildren().add(new CheckBox("Check"));
        column1.getChildren().add(new CheckBox("Check"));

        HBox root = new HBox();
        root.getChildren().add(column1);
        root.getChildren().add(column2);

        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    public void giveAllAuthorsInTable (Stage stage){
        System.out.println("Application started");
        TableView tableView = new TableView();

        TableColumn<Integer, Author> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id_author"));

        TableColumn<String, Author> column2 = new TableColumn<>("First name");
        column2.setCellValueFactory(new PropertyValueFactory<>("first_Name"));

        TableColumn<String, Author> column3 = new TableColumn<>("Last name");
        column3.setCellValueFactory(new PropertyValueFactory<>("last_Name"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        tableView.getItems().setAll(authorList);

        tableView.setPlaceholder(new Label("No rows to display"));

        VBox vBox = new VBox();
        vBox.getChildren().add(tableView);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);

        stage.show();

    }

}

