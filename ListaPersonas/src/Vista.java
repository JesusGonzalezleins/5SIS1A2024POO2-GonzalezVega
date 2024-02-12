/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
import java.util.*;

public class Vista {
    private Accionespersona control = new Accionespersona();
    private Scanner entrada = new Scanner(System.in);

    public void menu() {
        int opcion;

        do {
            System.out.println(" Lista de personas ");
            System.out.println("Elija una opcion");
            System.out.println("1.- Mostrar Lista de persona");
            System.out.println("2.- Registrar persona");
            System.out.println("3.- Editar Persona");
            System.out.println("4.- Eliminar");
            System.out.println("5.- Salir");

            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Lista de personas:");
                    ArrayList<Persona> listapersonas = control.mostrarPersonas();
                    for (Persona objeto : listapersonas) {
                        System.out.println("El id es :" + objeto.getId() + "\n"
                                + "EL nombre es : " + objeto.getNombre() + "\n"
                                + "La edad es : " + objeto.getEdad() + "\n");
                    }
                    break;

                case 2:
                    System.out.println("Ingrese el id de la persona: ");
                    int idpersona = entrada.nextInt();
                    entrada.nextLine(); // Consumir la nueva línea pendiente

                    System.out.println("Ingresa el nombre de la persona :");
                    String nombrepersona = entrada.nextLine();

                    System.out.println("Ingrese la edad de la persona :");
                    int edadpersona = entrada.nextInt();
                    entrada.nextLine(); 

                    Persona personaRegis = new Persona(idpersona, nombrepersona, edadpersona);
                    control.agregarPersona(personaRegis);
                    break;

                case 3:
                    System.out.println("Ingresa el id de la persona a buscar");
                    int idpersonaEditar = entrada.nextInt();
                    entrada.nextLine(); 
                    Persona personabuscar = control.buscarPersona(idpersonaEditar);

                    if (personabuscar != null) {
                        System.out.println("La información de la persona: ");
                        System.out.println("ID: " + personabuscar.getId());
                        System.out.println("Nombre: " + personabuscar.getNombre());
                        System.out.println("Edad: " + personabuscar.getEdad());

                        System.out.println("Ingresa el nuevo nombre de la persona: ");
                        String nuevonombre = entrada.nextLine();

                        System.out.println("Ingresa la nueva edad de la persona: ");
                        int nuevaedad = entrada.nextInt();
                        entrada.nextLine(); 

                        personabuscar.setNombre(nuevonombre);
                        personabuscar.setEdad(nuevaedad);
                        control.actualizarPersona(personabuscar);
                    }
                    break;

                case 4:
                    System.out.println("Ingresa id de la persona a eliminar");
                    int idpersonaEliminar = entrada.nextInt();
                    entrada.nextLine(); 

                    Persona personaEliminar = control.buscarPersona(idpersonaEliminar);
                    if (personaEliminar != null) {
                        control.eliminarPersona(personaEliminar);
                        System.out.println("Persona eliminada");
                    } else {
                        System.out.println("Persona no encontrada");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (opcion != 5);
    }
}



