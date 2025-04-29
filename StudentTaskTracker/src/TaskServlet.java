import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class TaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");

        try {
            Connection con = DBConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement("INSERT INTO tasks (task) VALUES (?)");
            st.setString(1, task);
            st.executeUpdate();

            st.close();
            con.close();
            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Task submission failed");
        }
    }
}