package com.srlab.database.mysql;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sail.so.xml.Post;
import com.srlab.so.main.FileReadWrite;


//  A set of SQL Database query 

/*String query = "SELECT VERSION()";

try (Connection con = DriverManager.getConnection(url, user, password);
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(query)) {

    if (rs.next()) {
        
        System.out.println(rs.getString(1));
    }

} catch (SQLException ex) {
    
    System.out.println("Sql Error: " + ex.getMessage());
} */



public class MySQlOperation {
	
	static Connection con;
	static FileReadWrite flrw;
	public static void connectionEstablish()
	{
		String url = "jdbc:mysql://localhost:3306/soTags?useSSL=false";
        String user = "root";
        String password = "1234";
        
        try
        {
        	con = DriverManager.getConnection(url, user, password);
        	System.out.println("Successfully Connected");
        } catch (SQLException ex) {
			// TODO: handle exception
        	System.out.println("SQL Error: " + ex.getMessage());
        	System.exit(1);
		}
        
    }
	public static void insertDB(Post post, String FileNamepath)
	{
		if (post.getTitle() != null)
		{
			post.setTitle(post.getTitle().replaceAll("'", ""));
		}
		
		String query = "INSERT INTO libraryWiki VALUES('"+post.getId()+"','"+post.getPostTypeId()+"','"+post.getCreationDate()+"','"+post.getScore()
		+"','"+post.getViewCount()+"','"+post.getBody().replaceAll("'", "")+"','"+post.getOwnerUserId()+"','"+post.getLastActivityDate()+"','"
		+post.getTitle()+"','"+post.getTags()+"','"+post.getAnswerCount()+"','"+post.getCommentCount()+"','"
		+post.getFavoriteCount()+"','"+FileNamepath+"','"+post.getAcceptedAnswerId()+"')";
		
		//query = "INSERT INTO Posts VALUES(8,'1', '2016-01-12T20:34:37.910', '8', '56', '&lt;p&gt;Looking at the highly upvoted &lt;a href=&quot;http://area51.stackexchange.com/proposals/82438/3d-printing?page=1&amp;amp;tab=votes&amp;amp;phase=definition#tab-top&quot;&gt;area 51 questions&lt;/a&gt;, we seem to have a lot of questions focusing on 3D printing from the viewpoint of someone producing items, but a dearth of those discussing actual printer construction, modification, and firmware configuration/creation/programming. Are such questions on-topic for this sites scope?&lt;/p&gt;&#xA;\', '62', '2016-01-12T20:41:20.997', 'Are questions discussing printer construction, internals, and firmware on-topic here?', '&lt;support&gt;&lt;scope&gt;', '1', '0', 'null','/home/sr-p2irc-big14/Desktop/stackexchange/xmlTest', -999)";
		
		//System.out.println(query);
		
		try {
			Statement st = con.createStatement();
			int i = st.executeUpdate(query);
			if(i != 0)
			{
				System.out.println("Entry Successful");
			}

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			System.out.println("SQL Error: " + ex.getMessage());
        	flrw.FileWrite(query, "query.txt");
		}	     	
	}
	
	
}
