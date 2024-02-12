/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
import java.util.ArrayList;

public class Accionespersona {
    public ArrayList<Persona> listapersonas = new ArrayList<>();

    public Accionespersona() {
       
        listapersonas.add(new Persona(1, "Mario", 30));
        listapersonas.add(new Persona(2, "Luigi", 29));
        listapersonas.add(new Persona(3, "Link", 25));
        listapersonas.add(new Persona(4, "Zelda", 24));
        listapersonas.add(new Persona(5, "Peach", 25));
        listapersonas.add(new Persona(6, "Rosalina", 24));
        listapersonas.add(new Persona(7, "Samus", 30));
        listapersonas.add(new Persona(8, "Pikachu", 250));
        listapersonas.add(new Persona(9, "Ash", 10));
        listapersonas.add(new Persona(10, "Yoshi", 1));
    }

    public void agregarPersona(Persona p) {
        listapersonas.add(p);
    }

    public Persona buscarPersona(int id) {
        for (Persona p : listapersonas) {
            if (id == p.getId()) {
                return p;
            }
        }
        System.out.println("No existe el registro de esa persona");
        return null;
    }

    public void actualizarPersona(Persona actualizada) {
        Persona personaExistente = buscarPersona(actualizada.getId());
        if (personaExistente != null) {
            listapersonas.remove(personaExistente);
            listapersonas.add(actualizada);
            System.out.println("Persona actualizada correctamente");
        } else {
            System.out.println("No se encontró ninguna persona con el ID proporcionado.");
        }
    }

    public void eliminarPersona(Persona eliminada) {
        listapersonas.remove(eliminada);
    }

    public ArrayList<Persona> mostrarPersonas() {
        return listapersonas;
    }

    public static void main(String[] args) {
    
        Accionespersona acciones = new Accionespersona();

        
        ArrayList<Persona> listaPersonas = acciones.mostrarPersonas();
        System.out.println("Lista de personas:");
        for (Persona persona : listaPersonas) {
            System.out.println("ID: " + persona.getId() + ", Nombre: " + persona.getNombre() + ", Edad: " + persona.getEdad());
        }
    }
}
