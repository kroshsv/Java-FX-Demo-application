
package custcontrol;

import java.io.*;
import java.sql.*;

import FormDemo.*;
import person.*;
import FormReport01.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.application.Platform;
import javafx.scene.layout.*;
import javafx.event.*;

public class MainMenu extends BorderPane {

    @FXML private StackPane stack;
    @FXML private ToolBar tool;
    
    private Connection con = null;
    
    private Login Login01 = null;
    private FormDemo fd = null;
    private FormReport01 fr01 = null;
    private PersonTable pt = null;
    
    private Button LogButton = null; 
    private Button fdButton = null;
    private Button ptButton = null;
    private Button fr01Button = null;
    
    
    public MainMenu() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    
    public void set_con(Connection con1) {
        
        con = con1;
    }
    
       
    public void close_cont() {
        
        VBox vb = new VBox();
        
        stack.getChildren().setAll(vb);    
        
        
        if (fdButton != null) {
           tool.getItems().remove(fdButton);
           fdButton = null; 
           fd = null;
        }
        if (ptButton != null) {
           tool.getItems().remove(ptButton);
           ptButton = null;  
           pt = null;
        }
        
       if (fr01Button != null) {
           tool.getItems().remove(fr01Button);
           fr01Button = null;  
           fr01 = null;
           
        }
    }
    
    
    @FXML
    protected void exit() {
        
        Platform.exit();
        System.exit(0);
        
    }

     @FXML
    protected void person() {
        
      if (con != null) {  
        
      if (pt == null) {
          
           pt = new PersonTable(con);
           stack.getChildren().setAll(pt);
           
        } else {
          
           stack.getChildren().setAll(pt);
        }      
      
         if (ptButton == null) {
            
            ClassLoader loader = this.getClass().getClassLoader();
	    InputStream is = loader.getResourceAsStream("icons/star_green.png");

            Image image = new Image(is);
            
            ptButton = new Button();
            ptButton.setTooltip(new Tooltip("Сотрудники"));
            
            ImageView imv = new ImageView(image);
            imv.setFitHeight( 20 );
	    imv.setFitWidth( 20 );

            ptButton.setGraphic(imv);
            
            ptButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                stack.getChildren().setAll(pt);
            }
        });           
            
        ContextMenu contextMenu = new ContextMenu(); 
          
        MenuItem item1 = new MenuItem("Закрыть объект");
        item1.setOnAction(new EventHandler<ActionEvent>() {
 
                @Override
                public void handle(ActionEvent event) {
                      if (ptButton != null) {
                   
                          VBox vb = new VBox();
        
                          stack.getChildren().setAll(vb);  
                   
                          tool.getItems().remove(ptButton);
                          ptButton = null; 
                          pt = null;
                       }
             
                }
        });
                
          contextMenu.getItems().addAll(item1);       
          
          ptButton.setContextMenu(contextMenu);  
          tool.getItems().add(ptButton);
        }

    } 
      
 }

    @FXML
    protected void demo() {
        
  
      if (con != null) {  
      if (fd == null) {
          
           fd = new FormDemo();
           stack.getChildren().setAll(fd);
           
        } else {
          
           stack.getChildren().setAll(fd);
        }      
      
        if (fdButton == null) {
            
            ClassLoader loader = this.getClass().getClassLoader();
	    InputStream is = loader.getResourceAsStream("icons/star_yellow.png");

            Image image = new Image(is);
            
            fdButton = new Button();
            fdButton.setTooltip(new Tooltip("Демо форма"));
            
            ImageView imv = new ImageView(image);
            imv.setFitHeight( 20 );
	    imv.setFitWidth( 20 );

            fdButton.setGraphic(imv);
            
            fdButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                stack.getChildren().setAll(fd);
            }
        });           
            
          ContextMenu contextMenu = new ContextMenu(); 
          
          MenuItem item1 = new MenuItem("Закрыть объект");
                item1.setOnAction(new EventHandler<ActionEvent>() {
 
                @Override
                   public void handle(ActionEvent event) {
                       if (fdButton != null) {
                   
                           VBox vb = new VBox();
        
                           stack.getChildren().setAll(vb);  
                   
                           tool.getItems().remove(fdButton);
                           fdButton = null; 
                           fd = null;
                       }
            
                   }
        });
                
           contextMenu.getItems().addAll(item1);       
          
           fdButton.setContextMenu(contextMenu); 
  
           tool.getItems().add(fdButton);
        }

      } 
      
    }


    @FXML
    protected void report01() {
    
        if (con != null) {  
        if (fr01 == null) {
            
           fr01 = new FormReport01(con);
           stack.getChildren().setAll(fr01);
           
        } else {
            
            stack.getChildren().setAll(fr01);
        }      
      
         if (fr01Button == null) {
            
            ClassLoader loader = this.getClass().getClassLoader();
	    InputStream is = loader.getResourceAsStream("icons/star_grey.png");
     
            Image image = new Image(is);
            
            fr01Button = new Button();
            fr01Button.setTooltip(new Tooltip("Отчет по сотрудникам"));
            
            ImageView imv = new ImageView(image);
            imv.setFitHeight( 20 );
	    imv.setFitWidth( 20 );

            fr01Button.setGraphic(imv);
            
            fr01Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                stack.getChildren().setAll(fr01);
            }
        });           
            
          ContextMenu contextMenu = new ContextMenu(); 
          
          MenuItem item1 = new MenuItem("Закрыть объект");
                item1.setOnAction(new EventHandler<ActionEvent>() {
 
                @Override
                  public void handle(ActionEvent event) {
                       if (fr01Button != null) {
                   
                           VBox vb = new VBox();
        
                           stack.getChildren().setAll(vb);  
                   
                           tool.getItems().remove(fr01Button);
                           fr01Button = null; 
                           fr01 = null;
                       }
                
                
                  }
        });
                
           contextMenu.getItems().addAll(item1);       
          
           fr01Button.setContextMenu(contextMenu); 
  
           tool.getItems().add(fr01Button);
        }
  
      } 

    }

    
       @FXML
    protected void db() throws FileNotFoundException {
    
        if (Login01 == null) {
            
           Login01 = new Login(this);
           stack.getChildren().setAll(Login01.createLoginPane());
           
        } else {
            
            stack.getChildren().setAll(Login01.get_pane());
        }    
        
        if (LogButton == null) {
            
            ClassLoader loader = this.getClass().getClassLoader();
	    InputStream is = loader.getResourceAsStream("icons/star_blue.png");

            Image image = new Image(is);
            
            LogButton = new Button();
            LogButton.setTooltip(new Tooltip("База данных"));
            
            ImageView imv = new ImageView(image);
            imv.setFitHeight( 20 );
	    imv.setFitWidth( 20 );

            LogButton.setGraphic(imv);
            
            LogButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                stack.getChildren().setAll(Login01.get_pane());
            }
        });           

           tool.getItems().add(LogButton);
        }

    }
}
