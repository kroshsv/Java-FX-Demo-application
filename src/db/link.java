
package db;

import java.io.*;
import java.sql.*;
import java.util.*;

import custcontrol.MainMenu;

public class link {
    
 private Connection con = null;
 private MainMenu mm = null;
 
 public static final String DATABASE = "databasename";
 public static final String DATABASE_USER = "user";
 public static final String DATABASE_PASSWORD = "password";

 
  public link(MainMenu m01) {
      mm = m01;
    }  
  
  public Connection get_con () {
      return con;
  }
  
  public int open_con (String user, String pass) {
       
       int result = 0;
       
       Properties connProperties = new Properties();
       
       Properties properties = new Properties();
       
        ClassLoader loader = this.getClass().getClassLoader();
	InputStream is = loader.getResourceAsStream("config/config.properties");
        
        try {
            
          properties.load(is);
          is.close();
          
        } catch (IOException e) {
            
          e.printStackTrace();
        }

        String url = properties.getProperty("url");

        String database = properties.getProperty("database");

        connProperties.put(DATABASE, database);
        connProperties.put(DATABASE_USER, user);
        connProperties.put(DATABASE_PASSWORD, pass);

        try{

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            
            con = DriverManager.getConnection(url,connProperties);
            
            mm.set_con(con);
   
        }
        catch(ClassNotFoundException cnfex){
            result = 1;
        
        }
  
        catch(SQLException ex){
            result = 1;
   
        }

      return result;
  }
   
   public void close_con ()        
   {
        try { 
             
             if(con != null){
                 
                con.close();
                con = null;
                mm.set_con(con);
                
             }
    	  }
    	  catch (SQLException ex){
              ex.printStackTrace();    		  
   		  
    	  }

  }  

}
