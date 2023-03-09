package Connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

 

/**
 * Servlet implementation class database
 */
@WebServlet(name="Database", value="/database", loadOnStartup=0)
public class database extends HttpServlet{ 
	private static final long serialVersionUID = 1L;
	public static Connection dbConnection;
	public static Cache<String,String> data;
    public synchronized void init() {
		try {
			data=Caffeine.newBuilder().maximumSize(10000).build();
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GrowMore", "root", "root");
			PreparedStatement smt=dbConnection.prepareStatement("delete from sessions");
			smt.execute();
			 
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	HttpSession session = request.getSession(); // obtain the current session
		session.invalidate();
    
    
    }
}
