import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Consultar extends HttpServlet {

    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    public void init(ServletConfig scg) throws ServletException{
        String url = "jdbc:mysql://localhost/registroalumnos";
        String username = "root";
        String password = "jesusgonzalezvega";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            set = con.createStatement();
            
            System.out.println("Conexión exitosa a la base de datos");
        }catch(Exception e){
            System.out.println("No se pudo conectar a la base de datos");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Consultar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de alumnos registrados</h1>"
                    + "<br>"
                    + "<table border = '2'>"
                    + "<tr>"
                    + "<th>Boleta</th>"
                    + "<th>Nombre Completo</th>"
                    + "<th>Edad</th>"
                    
                    + "</tr>");
            try{
                int id, edad;
                String nom, appat, apmat;
                
                String q = "SELECT * FROM alumno";
                
                set = con.createStatement();
                rs = set.executeQuery(q);
                
                while(rs.next()){
                    id = rs.getInt("idAlumno");
                    nom = rs.getString("nom_alu");
                    appat = rs.getString("appat_alu");
                    apmat = rs.getString("apmat_alu");
                    edad = rs.getInt("edad_alu");
                    
                    out.println("<tr>"
                            + "<td>"+id+"</td>"
                            + "<td>"+nom+ " "+appat+" "+apmat+ "</td>"
                            + "<td>"+edad+"</td>"
                            + "<td><a href='Editar?id=" + id + "'>Editar</a></td>"
                            + "</tr>");
                }
                rs.close();
                set.close();
            
            }catch(Exception e){
            }
            out.println("</table>");
            out.println("<a href='index.html' >Regresar al Menú Principal</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
