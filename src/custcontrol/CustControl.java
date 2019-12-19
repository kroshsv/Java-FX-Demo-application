
package custcontrol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustControl extends Application {

   
    public void start(Stage stage) throws Exception {
        MainMenu Menu = new MainMenu();
        
        stage.setScene(new Scene(Menu));
        stage.setTitle("Демонстрационная программа");
        stage.setWidth(300);
        stage.setHeight(200);
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);

    }
    
}
