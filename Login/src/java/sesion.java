import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sesion")
public class sesion extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Connection con;

    public void init() throws ServletException {
        // Configurar la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/registroalumnos";
        String username = "root";
        String password = "jesusgonzalezvega";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error en la inicialización del servlet", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Consulta SQL para verificar las credenciales del usuario
            String query = "SELECT * FROM usuarios WHERE username=? AND password=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Inicio de sesión exitoso, crear una sesión y redirigir al usuario
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("Consultar");
            } else {
                // Inicio de sesión fallido, mostrar mensaje de error
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><head><title>Error de inicio de sesión</title></head><body>");
                out.println("<h2>Usuario o contraseña incorrectos.</h2>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error en la consulta SQL", e);
        }
    }

    public void destroy() {
        // Cerrar la conexión a la base de datos al destruir el servlet
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


