package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;

public class TurnoSistema {
    private PriorityQueue<Usuario> colaDeTurnos;
    private JTextArea areaTurnos;

    public TurnoSistema() {
        colaDeTurnos = new PriorityQueue<>(new Comparador());

        // Configuración de la ventana principal
        JFrame frame = new JFrame("Sistema de Turnos Tiquetera");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout(10, 10)); // Agregamos espacio entre componentes

        // Panel para agregar usuarios usando GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes alrededor de cada componente
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Configuración de etiquetas y campos de texto
        JLabel nombreLabel = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nombreLabel, gbc);

        JTextField nombreField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // El campo de texto ocupará 2 columnas
        panel.add(nombreField, gbc);

        JLabel prioridadLabel = new JLabel("Prioridad (0-10):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Restablecemos el ancho a 1 columna
        panel.add(prioridadLabel, gbc);

        JTextField prioridadField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(prioridadField, gbc);

        // Botón para agregar usuario
        JButton agregarUsuarioBtn = new JButton("Agregar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(agregarUsuarioBtn, gbc);

        // Botón para atender al siguiente usuario
        JButton siguienteTurnoBtn = new JButton("Atender");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(siguienteTurnoBtn, gbc);

        // Área de turnos con título
        areaTurnos = new JTextArea(10, 30);
        areaTurnos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTurnos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Cola de Turnos"));

        // Agregamos componentes al frame
        frame.add(panel, BorderLayout.NORTH); // Panel de entrada en la parte superior
        frame.add(scrollPane, BorderLayout.CENTER); // Área de turnos en el centro

        // Centrar la ventana en la pantalla
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Lógica para agregar usuarios
        ActionListener agregarListener = e -> {
            String nombre = nombreField.getText().trim();

            // Validación del nombre
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validación de la prioridad
            int prioridad;
            try {
                prioridad = Integer.parseInt(prioridadField.getText().trim());
                if (prioridad < 0 || prioridad > 10) {
                    throw new NumberFormatException("Prioridad fuera de rango");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "La prioridad debe ser un número entero entre 0 y 10.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Usuario usuario = new Usuario(nombre, prioridad);
            colaDeTurnos.add(usuario);
            actualizarTurnos();
            nombreField.setText("");
            prioridadField.setText("");
        };
        agregarUsuarioBtn.addActionListener(agregarListener);

        // Lógica para atender al siguiente usuario
        ActionListener atenderListener = e -> {
            if (!colaDeTurnos.isEmpty()) {
                Usuario siguiente = colaDeTurnos.poll();
                JOptionPane.showMessageDialog(frame, "Atendiendo a: " + siguiente);
                actualizarTurnos();
            } else {
                JOptionPane.showMessageDialog(frame, "No hay más usuarios en la cola.");
            }
        };
        siguienteTurnoBtn.addActionListener(atenderListener);
    }

    // Método para actualizar el área de turnos con los usuarios en cola
    private void actualizarTurnos() {
        StringBuilder builder = new StringBuilder();
        for (Usuario usuario : colaDeTurnos) {
            builder.append(usuario).append("\n");
        }
        areaTurnos.setText(builder.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TurnoSistema::new);
    }
}
