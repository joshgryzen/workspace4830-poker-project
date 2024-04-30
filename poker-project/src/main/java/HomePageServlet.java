import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HomePageServlet")
public class HomePageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Get session to access username
    	HttpSession session = request.getSession();
    	
    	response.setContentType("application/json");
        // Set character encoding before writing response
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            DBConnection.getDBConnection(getServletContext());
            connection = DBConnection.connection;

            String username = (String) session.getAttribute("username"); 
            String selectSQL = "SELECT * FROM pokerTable WHERE username = \"" + username + "\"";
            preparedStatement = connection.prepareStatement(selectSQL);
            resultSet = preparedStatement.executeQuery();

            StringBuilder jsonData = new StringBuilder("[");
            while (resultSet.next()) {

                String hand = resultSet.getString("hand");
                String flop = resultSet.getString("flop");
                int turn = resultSet.getInt("turn");
                int players = resultSet.getInt("players");
                int stats = resultSet.getInt("stats");
                
                jsonData.append("{\"hand\":\"").append(hand).append("\",")
                        .append("\"flop\":\"").append(flop).append("\",")
                        .append("\"turn\":").append(turn).append(",")
                        .append("\"players\":\"").append(players).append("\",")
                        .append("\"stats\":").append(stats).append("},");
            }
            if (jsonData.charAt(jsonData.length() - 1) == ',') {
                jsonData.setCharAt(jsonData.length() - 1, ']'); // Replace last comma with ']'
            } else {
                jsonData.append("]");
            }
            System.out.println(jsonData.toString());
            out.println(jsonData);
            
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
