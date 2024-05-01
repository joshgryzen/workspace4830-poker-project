/**
 * @file CardInsertServlet.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;

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
	  StringConversion servlet = new StringConversion();
	  servlet.doPost(request, response);	   
      String hand = request.getParameter("hand");
      String flop = request.getParameter("flop");
      String username = (String) session.getAttribute("username"); 
      String turn = request.getParameter("turnNumber");
      String players = request.getParameter("playerNumber");

      Connection connection = null;
      
      String insertSql = "INSERT INTO pokerTable (hand, flop, turn, players, stats) VALUES (?, ?, ?, ?, ?)";
      // Get the number of players from the servlet       
      int intValuePlayers = servlet.getIntValuePlayers(); 
      
      
      // Populate cardsInput1 and cardsInput2 in CardAcronyms class
      CardAcronyms.setInputValues(hand, flop);

      // Convert cardsInput1 array to a list of Card objects
      List<Card> Hand = new ArrayList<>(Arrays.asList(CardAcronyms.cardsInput1));
      
      // Convert cardsInput2 array to a list of Card objects
      List<Card> table = new ArrayList<>(Arrays.asList(CardAcronyms.cardsInput2));
      
      double winrate = MonteCarloPoker.monteCarloWinRatio(Hand, table, intValuePlayers, 10000);
      
      
      try {
    	  
          DBConnection.getDBConnection(getServletContext());
          connection = DBConnection.connection;
    	  PreparedStatement insertStmt = connection.prepareStatement(insertSql);
          insertStmt.setString(1, hand);
          insertStmt.setString(2, flop);
          insertStmt.setString(3, turn);
          insertStmt.setString(4, players);
          insertStmt.setDouble(5, winrate);
          insertStmt.executeUpdate();
            
	      // Redirect to cardgame.html with winrate as a parameter
	      response.sendRedirect("home.jsp");          
          connection.close();
      } catch (Exception e) {
          e.printStackTrace();
      }


   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
      
   }

}
