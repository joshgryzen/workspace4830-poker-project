/**
 * @file CardInsertServlet.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CardInsertServlet")
public class CardInsertServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public CardInsertServlet() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String hand = request.getParameter("hand");
      String flop = request.getParameter("flop");
      String turn = request.getParameter("turn");
      String players = request.getParameter("players");

      Connection connection = null;
      
      String insertSql = "INSERT INTO pokerTable (hand, flop, turn, players) VALUES (?, ?, ?, ?)";

      try {
          DBConnection.getDBConnection(getServletContext());
          connection = DBConnection.connection;
    	  PreparedStatement insertStmt = connection.prepareStatement(insertSql);
          insertStmt.setString(1, hand);
          insertStmt.setString(2, flop);
          insertStmt.setString(3, turn);
          insertStmt.setString(4, players);
          insertStmt.executeUpdate();
          
          
          connection.close();
      } catch (Exception e) {
          e.printStackTrace();
      }

      // Set response content type
      // TODO: Update the response and integrate card evaluation API, here or in another file
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Card Data to DB";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Hand</b>: " + hand + "\n" + //
            "  <li><b>Flop</b>: " + flop + "\n" + //
            "  <li><b>players</b>: " + players + "\n" + //
            "  <li><b>Turn</b>: " + turn + "\n" + //

            "</ul>\n");

      out.println("<a href=/poker-project/home.jsp>Return Home</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
