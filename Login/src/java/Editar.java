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

@WebServlet("/Editar")
public class Editar extends HttpServlet {
    
    private Connection con;
    
    @Override
    public void init() throws ServletException {
        String url = "jdbc:mysql://localhost/registroalumnos";
        String username = "root";
        String password = "jesusgonzalezvega";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Error en la inicialización del servlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        String sql = "SELECT * FROM alumno WHERE idAlumno = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                displayEditForm(rs, response);
            } else {
                response.getWriter().println("No se encontró el alumno con el ID proporcionado");
            }
        } catch (SQLException e) {
            throw new ServletException("Error al obtener el alumno para editar", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String appat = request.getParameter("appat");
        String apmat = request.getParameter("apmat");
        int edad = Integer.parseInt(request.getParameter("edad"));
        

        String sql = "UPDATE alumno SET nom_alu = ?, appat_alu = ?, apmat_alu = ?, edad_alu = ? WHERE idAlumno = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setString(2, appat);
            statement.setString(3, apmat);
            statement.setInt(4, edad);
            statement.setInt(5, id);
            statement.executeUpdate();
                        response.sendRedirect("Consultar");
        } catch (SQLException e) {
            throw new ServletException("Error al actualizar los datos del alumno", e);
        }
    }
    
    private void displayEditForm(ResultSet rs, HttpServletResponse response) throws IOException, SQLException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Editar Alumno</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Editar Alumno</h1>");
            out.println("<form action='Editar' method='post'>");
            out.println("<input type='hidden' name='id' value='" + rs.getInt("idAlumno") + "'/>");
            out.println("Nombre: <input type='text' name='nombre' value='" + rs.getString("nom_alu") + "'/><br/>");
            out.println("Apellido Paterno: <input type='text' name='appat' value='" + rs.getString("appat_alu") + "'/><br/>");
            out.println("Apellido Materno: <input type='text' name='apmat' value='" + rs.getString("apmat_alu") + "'/><br/>");
            out.println("Edad: <input type='number' name='edad' value='" + rs.getInt("edad_alu") + "'/><br/>");
            out.println("<input type='submit' value='Guardar'/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public void destroy() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
