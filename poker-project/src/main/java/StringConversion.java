import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StringConversion")
public class StringConversion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StringConversion() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the string input from the HTML form
        String userInputHand = request.getParameter("hand");
        String userInputFlop = request.getParameter("flop");
        String userInputTurn = request.getParameter("turn");
        String userInputPlayers = request.getParameter("players");
        try {
            // Convert the string input to an integer
            int intValueHand = Integer.parseInt(userInputHand);
            int intValueTurn = Integer.parseInt(userInputTurn);
            int intValuePlayers = Integer.parseInt(userInputPlayers);
            // Now you have the integer value, you can use it in your servlet logic
            // For example, you can send it to another method, save it to a database, etc.

            // Example: printing the integer value
            System.out.println("Integer value: " + intValueHand);
            System.out.println("Integer value: " + intValueTurn);
            System.out.println("Integer value: " + intValuePlayers);
            // Here you can continue with your servlet logic
            // For example, forwarding the request or sending a response back to the client
        } catch (NumberFormatException e) {
            // If the input cannot be parsed as an integer, handle the exception
            // For example, you can send an error response back to the client
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: Not a valid integer");
        }
    }
}
