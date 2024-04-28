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
    static String url = "jdbc:mysql://ec2-18-225-5-151.us-east-2.compute.amazonaws.com:3306/myDB?allowPublicKeyRetrieval=true&useSSL=false";
    static String user = "newmysqlremoteuser"; // e.g., newmysqlremoteuser
    static String password = "mypassword"; // e.g., mypassword

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the login.html file
        RequestDispatcher dispatcher = request.getRequestDispatcher("loginScreen.html");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.getWriter().println("Invalid action!");
            return;
        }

        if (action.equals("login")) {
            // Debugging statements before handling login
            System.out.println("Handling login action...");
            handleLogin(request, response);
        } else if (action.equals("signup")) {
            // Debugging statements before handling signup
            System.out.println("Handling signup action...");
            handleSignup(request, response);
        } else {
            response.getWriter().println("Invalid action!");
        }
    }


    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Debugging statements before executing login logic
        System.out.println("Executing login logic...");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Debugging statement before using the database connection
        System.out.println("Attempting to use database connection...");
        // Get the connection instance from MyServletDB
        Connection connection = DBConnection.connection;

        try {
            DBConnection.getDBConnection(getServletContext());
            connection = DBConnection.connection;
            if (connection != null) {
                // Debugging statement before executing SQL query
                System.out.println("Executing SQL query...");

                // Query the database to check if the user exists
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Debugging statement before setting parameters
                    System.out.println("Setting SQL parameters...");

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
                // Debugging statement for connection failure
                System.out.println("Failed to make connection!<br>");
                response.getWriter().println("Failed to make connection!<br>");
            }
        } catch (SQLException e) {
            // Debugging statement for SQL exception
            System.out.println("SQL Exception occurred:");
            e.printStackTrace();

            // Print error message to response
            response.getWriter().println("Connection Failed! Check output console<br>");
        }
    }

    private void handleSignup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Print to indicate the method is invoked
        System.out.println("Handling signup action...");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Get the connection instance from MyServletDB
        Connection connection = DBConnection.connection;

        try {
            // Print to check if DB connection is obtained
            System.out.println("Obtaining DB connection...");
            DBConnection.getDBConnection(getServletContext());
            connection = DBConnection.connection;

            if (connection != null) {
                // Print to indicate successful DB connection
                System.out.println("DB connection established successfully!");

             // Check if the user already exists
                String checkUserSql = "SELECT * FROM users WHERE username = ?";
                try (PreparedStatement checkUserStatement = connection.prepareStatement(checkUserSql)) {
                    checkUserStatement.setString(1, username);
                    ResultSet resultSet = checkUserStatement.executeQuery();

                    // Print to indicate user existence check
                    System.out.println("Checking if user already exists...");

                    if (resultSet.next()) {
                        // Print to indicate user already exists
                        System.out.println("User already exists!");
                        response.getWriter().println("User already exists!<br>");
                    } else {
                        // Print to indicate creating new user
                        System.out.println("Creating new user...");

                        // Debug print statement to check the value of 'username'
                        System.out.println("Username received: " + username);

                        String createUserSql = "INSERT INTO users (username, password) VALUES (?, ?)";
                        try (PreparedStatement createUserStatement = connection.prepareStatement(createUserSql)) {
                            createUserStatement.setString(1, username);
                            createUserStatement.setString(2, password);
                            int rowsInserted = createUserStatement.executeUpdate();
                            if (rowsInserted > 0) {
                                // Print to indicate successful user creation
                                System.out.println("User created successfully!");
                                response.getWriter().println("User created successfully!<br>");
                            } else {
                                // Print to indicate failure in user creation
                                System.out.println("Failed to create user!");
                                response.getWriter().println("Failed to create user!<br>");
                            }
                        }
                    }
                }

            } else {
                // Print to indicate failure in obtaining DB connection
                System.out.println("Failed to establish DB connection!");
                response.getWriter().println("MyServletConnectionError!<br>");
            }
        } catch (SQLException e) {
            // Print stack trace for SQL exception
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            response.getWriter().println("Connection Failed! Check output console<br>");
        }
    }

}
