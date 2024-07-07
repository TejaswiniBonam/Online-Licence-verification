import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException*/
public class ProcessB extends HttpServlet{
public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out=res.getWriter();

String cred = req.getParameter("aadhar");

try{

Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useSSL=false&allowPublicKeyRetrieval=true","root","mysql");
Statement stm = con.createStatement();

ResultSet rs = stm.executeQuery("select * from db where vehicle_id='"+cred+"'");
if(rs.next()){
	String id = rs.getString("vehicle_id");
	String pic = rs.getString("photo");
	String name = rs.getString("owner_name");
	String aad = rs.getString("adhaar_no");
	String type = rs.getString("vehicle_type");
	String col = rs.getString("vehicle_color");
	String rc = rs.getString("rc_id");
	String lc = rs.getString("licence_id");
	String exp = rs.getString("exp_date");
	
	out.println("<html><body><center>");
	out.println("OWNER: <img src="+pic+" alt='Photo of driver'><br>");
	out.println("NAME: "+name+"<br>");
	out.println("AADHAAR: "+aad+"<br>");
	out.println("VEHICLE TYPE: "+type+"<br>");
	out.println("VEHICLE COLOR: "+col+"<br>");
	out.println("RC ID: "+rc+"<br>");
	out.println("LICENCE NO: "+lc+"<br>");
	out.println("EXP DATE OF LICENCE: "+exp+"<br>");
	out.println("</center></body></html>");
}
else{
	out.println("<h1>ILLEAGAL (DATA NOT FOUND)</h1>");
}
con.close();
}
catch(Exception e){	
out.println(e.getMessage());
}
}
}
