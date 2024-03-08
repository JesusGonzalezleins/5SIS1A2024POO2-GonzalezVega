import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * Vamos a conectar con una base de datos
     * para poder hacer la conexion es necesario contar con 3 tipos de objeto
     */
    private Connection con;
    //es para establecer el objeto de conexion
    private Statement set;
    //el objeto statement es para poder definir las sentencias sql
    // por ejemplo crear una tabla, registrar un dato actualizar un dato, modificar la estructura de un atributo
    private ResultSet rs;
    //este objeto es exclusivo de consultas
    
    public void init(ServletConfig scg) throws ServletException{
        //sirve para configurar el servicio de la conexion con la bd
        String url = "jdbc:mysql:3306//localhost/registroalumnos";
                    //controlador:motorbd:puerto//IP/nombrebd
        String username = "root";
        String password = "jesusgonzalezvega";
        
        try{
            url = "jdbc:mysql://localhost/registroalumnos";
            //vamos a internar conectarnos a la BD
            Class.forName("com.mysql.jdbc.Driver");
            //despues debo de obtener el objeto de conexion
            con = DriverManager.getConnection(url, username, password);
            set = con.createStatement();
            
            System.out.println("Si conecto a la bd");
        }catch(Exception e){
            System.out.println("No se conecto a la bd");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //obtener los parametros del formulario
            String nombre, appat, apmat;
            int edad;
            
            nombre = request.getParameter("nombre");
            appat = request.getParameter("appat");
            apmat = request.getParameter("apmat");
            
            edad = Integer.parseInt(
                    request.getParameter("edad"));
            
            System.out.println(nombre);
            System.out.println(appat);
            System.out.println(apmat);
            System.out.println(edad);
            
            try{
                
                String q = "insert into alumno(nom_alu, appat_alu,"
                        + "apmat_alu, edad_alu) values ( '"+nombre+"', "
                        + "'"+appat+"', '"+apmat+"', "+edad+" )";
                
                //se debe de preparar la sentencia
                set.executeUpdate(q);
                System.out.println("Registro exitoso");
                out.println("<h1>Alumno registrado con exito</h1>");
            
            }catch(Exception e){
                System.out.println("Registro no exitoso");
                out.println("<h1>El Alumno no se pudo registrar</h1>");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registro</title>");            
            out.println("</head>");
            out.println("<body>");
         
            out.println("<a href='index.html' >Regresar al Menu Principal</a>");
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
