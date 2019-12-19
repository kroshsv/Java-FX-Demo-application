
package FormReport01;

import FormReport01.PersonReport.PersonReport;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.JRException;

public class FormReport01 extends VBox {
    
    private Connection conn = null;
    
    @FXML private TextField textField;

    public FormReport01(Connection con) {
        
        if (conn != con){
                    conn = con;
                }
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormReport01.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    public String getText() {
        return textProperty().get();
    }
    
    public void setText(String value) {
        textProperty().set(value);
    }
    
    public StringProperty textProperty() {
        return textField.textProperty();                
    }
        
    @FXML
    protected void report() {
            if (conn != null) {  
   
            try {
                new PersonReport().showReport(conn);
            } catch (ClassNotFoundException | JRException | SQLException e1) {
                e1.printStackTrace();
            }

        }
        
    }
}
