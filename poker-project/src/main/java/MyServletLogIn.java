import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MyServletLogIn")
public class MyServletLogIn extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static String url = "jdbc:mysql://ec2-18-223-28-161.us-east-2.compute.amazonaws.com:3306/tech_exercise?allowPublicKeyRetrieval=true&useSSL=false";
    static String user = "newmysqlremoteuser"; // e.g., newmysqlremoteuser
    static String password = "mypassword"; // e.g., mypassword

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the login.html file
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.getWriter().println("Invalid action!");
            return;
        }

        if (action.equals("login")) {
            handleLogin(request, response);
        } else if (action.equals("signup")) {
            handleSignup(request, response);
        } else {
            response.getWriter().println("Invalid action!");
        }
    }


    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Get the connection instance from MyServletDB
        Connection connection = MyServletDB.connection;

        try {
            if (connection != null) {
                // Query the database to check if the user exists
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        // User exists, login successful
                        response.getWriter().println("Login successful!<br>");
                    } else {
                        // User does not exist or wrong password
                        response.getWriter().println("Invalid username or password!<br>");
                    }
                }
            } else {
                response.getWriter().println("Failed to make connection!<br>");
            }
        } catch (SQLException e) {
            response.getWriter().println("Connection Failed! Check output console<br>");
            e.printStackTrace();
        }
    }

    private void handleSignup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Get the connection instance from MyServletDB
        Connection connection = MyServletDB.connection;

        try {
            if (connection != null) {
                // Check if the user already exists
                String checkUserSql = "SELECT * FROM users WHERE username = ?";
                try (PreparedStatement checkUserStatement = connection.prepareStatement(checkUserSql)) {
                    checkUserStatement.setString(1, username);
                    ResultSet resultSet = checkUserStatement.executeQuery();
                    if (resultSet.next()) {
                        // User already exists
                        response.getWriter().println("User already exists!<br>");
                    } else {
                        // User does not exist, create a new user
                        String createUserSql = "INSERT INTO users (username, password) VALUES (?, ?)";
                        try (PreparedStatement createUserStatement = connection.prepareStatement(createUserSql)) {
                            createUserStatement.setString(1, username);
                            createUserStatement.setString(2, password);
                            int rowsInserted = createUserStatement.executeUpdate();
                            if (rowsInserted > 0) {
                                response.getWriter().println("User created successfully!<br>");
                            } else {
                                response.getWriter().println("Failed to create user!<br>");
                            }
                        }
                    }
                }
            } else {
                response.getWriter().println("MyServletConnectionError!<br>");
            }
        } catch (SQLException e) {
            response.getWriter().println("Connection Failed! Check output console<br>");
            e.printStackTrace();
        }
    }
}
