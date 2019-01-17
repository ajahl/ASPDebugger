package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.parser.ASPParser;

import java.util.List;

public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ASP Debugger");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        ASPParser aspParser = new ASPParser();
        String modelPath = System.getProperty("user.dir") + "/src/models/";

        aspParser.parseFile(modelPath + "model.lp");
        List facts = aspParser.getFacts();
        facts.forEach((f)->System.out.println("Fact: " + f));
        aspParser.parseFile(modelPath + "component.lp");
        List rules = aspParser.getRules();
        rules.forEach((f)->System.out.println("Rule: " + f));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
