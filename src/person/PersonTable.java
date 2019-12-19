package person; 

import java.io.*;
import java.sql.*;
import java.util.*;

import javafx.beans.value.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.*;

public class PersonTable extends AnchorPane {
	@FXML
	private TextField filterField;
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	private ObservableList<Person> masterData;
	private ObservableList<Person> filteredData = FXCollections.observableArrayList();
        
        private Connection conn = null;
        private Statement stmt = null;
        private PreparedStatement ps = null;


	public PersonTable(Connection con) {
      
                if (conn != con){
                    conn = con;
                }
                    
                masterData = FXCollections.observableArrayList(getPerson());
 
		filteredData.addAll(masterData);

		masterData.addListener(new ListChangeListener<Person>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends Person> change) {
				updateFilteredData();
			}
		});

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonTable.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

	}

	
	@FXML
	private void initialize() {

		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

		personTable.setItems(filteredData);

		filterField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				updateFilteredData();
			}
		});
	}


	private void updateFilteredData() {
            
		filteredData.clear();

		for (Person p : masterData) {
			if (matchesFilter(p)) {
				filteredData.add(p);
			}
		}

		reapplyTableSortOrder();
	}


	private boolean matchesFilter(Person person) {
            
		String filterString = filterField.getText();
                
		if (filterString == null || filterString.isEmpty()) {
			
			return true;
		}

		String lowerCaseFilterString = filterString.toLowerCase();

		if (person.getFirstName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (person.getLastName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}

		return false; 
	}

	private void reapplyTableSortOrder() {
            
		ArrayList<TableColumn<Person, ?>> sortOrder = new ArrayList<>(personTable.getSortOrder());
		personTable.getSortOrder().clear();
		personTable.getSortOrder().addAll(sortOrder);
	}
        
        
        private List<Person> getPerson() {
            
          String sql = "Select firstname, lastname from person";
          List<Person> list = new ArrayList<>();
          
      
      try {
         
         stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         
         while (rs.next()) {
             
            Person p = createPerson(rs);
            list.add(p);
         }
         rs.close();
         
      } catch (SQLException ex) {
      }
      return list;
   }
        
    private Person createPerson(ResultSet rs) {
        
      Person p = new Person();
      
      try {
        
         p.setFirstName(rs.getString("firstname"));
         p.setLastName(rs.getString("lastname"));
        
      } catch (SQLException ex) {
      }
      return p;
   }
}
