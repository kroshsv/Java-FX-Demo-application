package FormReport01.PersonReport;
 
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import javax.swing.JFrame;
 
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class PersonReport extends JFrame {
 
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement ps = null;
    
    private static final long serialVersionUID = 1L;
 
    public void showReport(Connection con) throws JRException, ClassNotFoundException, SQLException {
 
        String reportSrcFile = "report/person.jrxml";
        
        if (conn == null) {
            conn = con;
        }

        ResultSet rs = null;
        
        String sql = "Select firstname, lastname from person";

      try {
         
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);

        ClassLoader loader = this.getClass().getClassLoader();
        
	InputStream is = loader.getResourceAsStream(reportSrcFile);

        JasperReport jasperReport = JasperCompileManager.compileReport(is);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
 
        JRResultSetDataSource resultSetDataSource  = new JRResultSetDataSource (rs);
 
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, resultSetDataSource);
        
        JRViewer viewer = new JRViewer(print);
        
        viewer.setOpaque(true);
        
        viewer.setVisible(true);
        
        this.add(viewer);
        
        this.setSize(700, 500);
        
        this.setVisible(true);

         rs.close();
         stmt.close();
         conn = null;
         
       } catch (SQLException ex) {
         {
    }
       }
    }
}
 
     

   