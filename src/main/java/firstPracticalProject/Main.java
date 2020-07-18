package firstPracticalProject;

import firstPracticalProject.model.Author;
import firstPracticalProject.services.AuthorRepository;
import firstPracticalProject.services.SessionManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Author author = AuthorRepository.getAuthorById(1);

    public static void main(String[] args) {
        Main.launch();
    }

    @Override
    public void start(Stage stage){
        getFirstAuthor(stage);
    }

    @Override
    public void stop() {
        System.out.println("Application stopped");
    }

    public void getFirstAuthor(Stage stage){

        String firstAuthorFirstName = author.getFirst_Name();
        String firstAuthorLastName = author.getLast_Name();

        System.out.println("First Author name is: " + firstAuthorFirstName);

        SessionManager.shutdown();

        System.out.println("Application started");
        VBox column2 = new VBox();
        column2.getChildren().add(new Label(firstAuthorFirstName+" "+ firstAuthorLastName));
        column2.getChildren().add(new Label(firstAuthorLastName+" "+ firstAuthorFirstName));

        VBox column1 = new VBox();
        column1.getChildren().add(new CheckBox("Check"));
        column1.getChildren().add(new CheckBox("Check"));

        HBox root = new HBox();
        root.getChildren().add(column1);
        root.getChildren().add(column2);

        Scene scene = new Scene(root,300,200);
        stage.setScene(scene);
        stage.show();
    }
}

