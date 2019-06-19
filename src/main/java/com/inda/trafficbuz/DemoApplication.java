package com.inda.trafficbuz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inda.trafficbuz.config.ConnectionPool;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("/")
	  public String hello() {
		int size =0 ;
		Statement stmt ;
		DataSource datasource = ConnectionPool.createConnectionPool();
		String returnString="";
	    try (Connection conn = datasource.getConnection()) {
	     stmt =conn.createStatement();
	     String sql;
	      sql = "SELECT * FROM users";
	      ResultSet rs = stmt.executeQuery(sql);
	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         String userid = rs.getString("userid");
	         String password = rs.getString("password");

	         System.out.print(", userid: " + userid);
	         System.out.println(", password: " + password);
	         returnString=returnString+":"+userid+"|"+password ;
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   return returnString ;

	    } catch (SQLException ex) {
	      // If something goes wrong, the application needs to react appropriately. This might mean
	      // getting a new connection and executing the query again, or it might mean redirecting the
	      // user to a different page to let them know something went wrong.
	    System.out.println("Exception occurred "+ex);
	    }
	    return "hello world!";
	  }
	  

}
