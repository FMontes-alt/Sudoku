// Proyecto Maven reestructurado
package gui;

import core.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaz gráfica para jugar al Sudoku.
 * Controla la visualización del tablero y permite validar movimientos.
 */
public class SudokuGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField[][] celdas;
    private Sudoku sudoku;

    public SudokuGUI() {
        sudoku = new Sudoku();
        int[][] tableroInicial = sudoku.getTablero();

        setTitle("Sudoku 2.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelTablero = new JPanel(new GridLayout(9, 9));
        celdas = new JTextField[9][9];

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                JTextField campo = new JTextField();
                campo.setHorizontalAlignment(JTextField.CENTER);

                int valor = tableroInicial[fila][col];
                if (valor != 0) {
                    campo.setText(String.valueOf(valor));
                    campo.setEditable(false);
                    campo.setBackground(Color.LIGHT_GRAY);
                } else {
                    campo.setText("");
                }

                celdas[fila][col] = campo;
                panelTablero.add(campo);
            }
        }

        JButton botonValidar = new JButton("Validar");
        botonValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarTablero();
            }
        });

        add(panelTablero, BorderLayout.CENTER);
        add(botonValidar, BorderLayout.SOUTH);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Valida el tablero leyendo el contenido de las celdas y mostrando errores.
     */
    private void validarTablero() {
        boolean valido = true;

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                String texto = celdas[fila][col].getText().trim();
                if (!texto.matches("[1-9]")) {
                    celdas[fila][col].setBackground(Color.PINK);
                    valido = false;
                    continue;
                }

                int valor = Integer.parseInt(texto);
                if (!sudoku.esMovimientoValido(fila, col, valor)) {
                    celdas[fila][col].setBackground(Color.RED);
                    valido = false;
                } else {
                    celdas[fila][col].setBackground(Color.WHITE);
                }
            }
        }

        if (valido) {
            JOptionPane.showMessageDialog(this, "✅ Tablero válido");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Hay errores en el tablero");
        }
    }
}
