/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Interfazl {

    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JLabel resultLabel;
    private static JTabbedPane tabbedPane;
    private static List<DefaultTableModel> tableModels = new ArrayList<>();
    private static JFrame currentFrame; 

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Inicio de sesión del empleado");
        ventana.setSize(370, 170);
        ventana.setBounds(470, 280, 350, 220);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        ventana.add(panel);

        agregarComponentes(panel);
        ventana.setVisible(true);
    }

    private static void agregarComponentes(JPanel panel) {
        panel.setLayout(null);

        JLabel userlabel = new JLabel("Nombre de usuario");
        userlabel.setBounds(10, 10, 120, 50);
        panel.add(userlabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(160, 20, 160, 25);
        panel.add(usernameField);

        JLabel userpass = new JLabel("Contraseña");
        userpass.setBounds(10, 40, 80, 50);
        panel.add(userpass);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(160, 50, 160, 25);
        panel.add(passwordField);

        JButton loginbutton = new JButton("Iniciar sesión");
        loginbutton.setBounds(10, 80, 120, 25);
        panel.add(loginbutton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(10, 110, 300, 25);
        panel.add(resultLabel);

        loginbutton.addActionListener(e -> loginButtonClicked());
    }

private static void loginButtonClicked() {
    String username = usernameField.getText();
    char[] passwordChars = passwordField.getPassword();
    String passtxt = new String(passwordChars);

    if (username.equals("Inspector") && passtxt.equals("12")) {
        resultLabel.setText("Bienvenido Inspector");
        JOptionPane.showMessageDialog(null, "¡Bienvenido Inspector!");
        int numTablas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de tablas que desea crear:"));
        mostrarTablas(numTablas);
    }  else if (username.equals("Proveedor") && passtxt.equals("1234")) {
        resultLabel.setText("Bienvenido Proveedor");
        JOptionPane.showMessageDialog(null, "¡Bienvenido Proveedor!");
        mostrarTablasInspector();
    } else {
        resultLabel.setText("Usuario no válido");
    }
}

    private static void mostrarTablas(int numTablas) {
        JFrame ventanaBienvenida = new JFrame("Tablas de muestras");
        ventanaBienvenida.setSize(800, 600);
        ventanaBienvenida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame = ventanaBienvenida; // Guardamos la ventana actual

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel fechaLabel = new JLabel("Fecha:");
        fechaLabel.setBounds(20, 20, 80, 25);
        panel.add(fechaLabel);

        JTextField fechaField = new JTextField();
        fechaField.setBounds(110, 20, 100, 25);
        panel.add(fechaField);

        JLabel reporteLabel = new JLabel("Número de Reporte:");
        reporteLabel.setBounds(220, 20, 150, 25);
        panel.add(reporteLabel);

        JTextField reporteField = new JTextField();
        reporteField.setBounds(360, 20, 100, 25);
        panel.add(reporteField);

        JLabel modeloLabel = new JLabel("Modelo:");
        modeloLabel.setBounds(490, 20, 80, 25);
        panel.add(modeloLabel);

        JTextField modeloField = new JTextField();
        modeloField.setBounds(540, 20, 100, 25);
        panel.add(modeloField);

        tabbedPane = new JTabbedPane();

        for (int i = 0; i < numTablas; i++) {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Puntos a medir");
            model.addColumn("Ficha técnica");
            model.addColumn("Número de prendas/colores");
            model.addColumn("Total de prendas");

            int numFilas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de puntos a medir para la tabla " + (i + 1) + ":"));

            for (int j = 0; j < numFilas; j++) {
                model.addRow(new Object[]{"", "", "", ""});
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            tabbedPane.addTab("Tabla " + (i + 1), scrollPane);
            tableModels.add(model);
        }

        tabbedPane.setBounds(20, 50, 750, 500);
        panel.add(tabbedPane);

        // Botón de guardar
        JButton guardarButton = new JButton("Guardar");
        guardarButton.setBounds(20, 560, 100, 25);
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
        panel.add(guardarButton);

        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        cerrarSesionButton.setBounds(130, 560, 120, 25);
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
        panel.add(cerrarSesionButton);

        ventanaBienvenida.add(panel);
        ventanaBienvenida.setVisible(true);
    }

    private static void guardarDatos() {
        for (DefaultTableModel model : tableModels) {
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object value = model.getValueAt(i, j);
                    System.out.print(value + "\t");
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
    }

    private static void cerrarSesion() {
        currentFrame.dispose(); 
        JOptionPane.showMessageDialog(null, "Sesión cerrada correctamente");
    }
    private static void mostrarTablasInspector() {
    JFrame ventanaSupervisor = new JFrame("Tablas de muestras del Inspector");
    ventanaSupervisor.setSize(800, 600);
    ventanaSupervisor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    currentFrame = ventanaSupervisor; // Guardamos la ventana actual

    JPanel panel = new JPanel();
    panel.setLayout(null);

    tabbedPane = new JTabbedPane();

    for (int i = 0; i < tableModels.size(); i++) {
        DefaultTableModel model = tableModels.get(i);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tabbedPane.addTab("Tabla " + (i + 1), scrollPane);
    }

    tabbedPane.setBounds(20, 50, 750, 500);
    panel.add(tabbedPane);

    JButton cerrarSesionButton = new JButton("Cerrar Sesión");
    cerrarSesionButton.setBounds(130, 560, 120, 25);
    cerrarSesionButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cerrarSesion();
        }
    });
    panel.add(cerrarSesionButton);

    ventanaSupervisor.add(panel);
    ventanaSupervisor.setVisible(true);
}
}
