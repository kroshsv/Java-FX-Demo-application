
package custcontrol;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import db.*;
import java.sql.*;

public class Login  {

    TextField tf;
    PasswordField pf;
    Button b01;
    Button b02;
     
    link db01; 
    
    GridPane grid;
    
    Label lbl;
    
    MainMenu mm;
    
    public Login(MainMenu m01) {
        
        mm = m01;
        db01 = new link(m01);
      
    }
    
     public Connection get_con () {
      return db01.get_con();
  }

     public Pane createLoginPane() {
     
       tf =  new TextField(); 
       pf = new PasswordField();
       
       b01  = new Button("Вход");
       b02  = new Button("Выход");
       
       lbl = new Label();
       
       b01.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                String user = tf.textProperty().get();
                
                String pass= pf.textProperty().get();
                
                int result  = db01.open_con(user, pass);
                
                if (result == 0) {
                   
                   lbl.setText("Соединение с БД установлено.");
                   
                   tf.textProperty().set("");
                   pf.textProperty().set("");
                   
                   tf.setDisable(true);
                   pf.setDisable(true);
                   
                   b01.setDisable(true);
                   b02.setDisable(false);
                   
                } else
                {
                    lbl.setText("Ощибка соединения с БД.");
                }
            }
       });
       
          b02.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

               db01.close_con();
               
               tf.setDisable(false);
               pf.setDisable(false);
               
               b01.setDisable(false);
               b02.setDisable(true);
                   
               lbl.setText("Соединение с БД закрыто.");
                   
               mm.close_cont();

            }
       });    

      grid = new GridPane();
      
      grid.setPadding(new Insets(5, 5, 5, 5));
      grid.setHgap(5);
      grid.setVgap(5);
      
      grid.add(new Label("Имя:"), 0, 0);
      grid.add(tf, 1, 0);
      grid.add(new Label("Пароль:"), 0, 1);
      grid.add(pf, 1, 1);
      
      grid.add(b02, 1, 2);
      grid.add(b01, 0, 2);
      grid.add(lbl, 1, 3);
      
      b02.setDisable(true);
      
      return grid;
   }
     
    
    public Pane get_pane() {
          return grid;
    }
     
}
