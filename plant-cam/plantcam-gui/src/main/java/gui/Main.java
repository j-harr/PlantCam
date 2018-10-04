package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.util.Callback;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        final Model model = new Model();

        FXMLLoader loader = new FXMLLoader(Controller.class.getClassLoader().getResource("fxml/plantcam.fxml"));
        System.out.println("Setting location");
        System.out.println("Location set allegedly");
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> aClass){
                return new Controller(model);
            }
        });
        System.out.println("Here");
        Parent root = loader.load();

        System.out.println("Root loaded");
        primaryStage.setTitle("PlantCam");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
