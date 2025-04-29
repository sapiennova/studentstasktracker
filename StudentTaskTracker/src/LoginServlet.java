import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Connection con = DBConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                response.sendRedirect("dashboard.jsp");
            } else {
                response.getWriter().println("Invalid credentials");
            }

            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Login Failed");
        }
    }
}