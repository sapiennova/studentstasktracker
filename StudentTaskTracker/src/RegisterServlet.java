import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Connection con = DBConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement("INSERT INTO users (name, email, password) VALUES (?, ?, ?)");
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, password);
            st.executeUpdate();

            st.close();
            con.close();
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Registration Failed");
        }
    }
}