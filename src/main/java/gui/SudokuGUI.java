package gui;

import core.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class SudokuGUI extends JFrame {

    private final JTextField[][] campos = new JTextField[9][9];
    private Sudoku juego = new Sudoku();
    private int intentosRestantes = 5;
    private final JLabel intentosLabel = new JLabel("Intentos restantes: 5");
    private final JComboBox<String> selectorDificultad = new JComboBox<>(new String[]{"facil", "normal", "dificil"});
    private final JButton btnGenerar = new JButton("🆕 Generar nuevo");
    private final JButton btnComprobar = new JButton("✔️ Comprobar");
    private final JButton btnReiniciar = new JButton("🔁 Reiniciar");
    private final JButton btnResolver = new JButton("⚡ Resolver");
    private Timer timer;
    private int segundos = 0;
    private final JLabel tiempoLabel = new JLabel("00:00:00");

    public SudokuGUI() {
        setTitle("Sudoku Fran Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelCentral = new JPanel(new GridLayout(9, 9)) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                for (int i = 0; i <= 9; i++) {
                    if (i % 3 == 0) {
                        ((Graphics2D) g).setStroke(new BasicStroke(3));
                    } else {
                        ((Graphics2D) g).setStroke(new BasicStroke(1));
                    }
                    g.drawLine(0, i * getHeight() / 9, getWidth(), i * getHeight() / 9);
                    g.drawLine(i * getWidth() / 9, 0, i * getWidth() / 9, getHeight());
                }
            }
        };

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                JTextField campo = new JTextField();
                campo.setHorizontalAlignment(JTextField.CENTER);
                campo.setFont(new Font("SansSerif", Font.BOLD, 18));
                campos[fila][col] = campo;
                panelCentral.add(campo);
            }
        }

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(new JLabel("Dificultad: "));
        panelSuperior.add(selectorDificultad);
        panelSuperior.add(btnGenerar);
        panelSuperior.add(btnReiniciar);
        panelSuperior.add(btnResolver);
        panelSuperior.add(intentosLabel);

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnComprobar);
        panelInferior.add(new JLabel("  Tiempo: "));
        panelInferior.add(tiempoLabel);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        btnGenerar.addActionListener(e -> {
            generarTablero();
            iniciarTemporizador();
        });

        btnComprobar.addActionListener(e -> comprobarTablero());

        btnReiniciar.addActionListener(e -> {
            juego = new Sudoku();
            generarTablero();
        });

        btnResolver.addActionListener(e -> {
            int[][] tableroActual = juego.getTablero();
            juego.getGenerador().resolver(tableroActual);
            actualizarTableroDesdeModelo();
        });

        setVisible(true);
    }

    private void generarTablero() {
        juego = new Sudoku();
        String dificultad = (String) selectorDificultad.getSelectedItem();
        juego.generarTablero(dificultad);
        intentosRestantes = 5;
        intentosLabel.setText("Intentos restantes: 5");
        actualizarTableroDesdeModelo();
    }

    private void actualizarTableroDesdeModelo() {
        int[][] tablero = juego.getTablero();
        boolean[][] fijas = juego.getCeldasFijas();

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                campos[fila][col].setEditable(!fijas[fila][col]);
                if (tablero[fila][col] != 0) {
                    campos[fila][col].setText(String.valueOf(tablero[fila][col]));
                    campos[fila][col].setForeground(Color.BLACK);
                    campos[fila][col].setBackground(Color.WHITE);
                } else {
                    campos[fila][col].setText("");
                    campos[fila][col].setForeground(Color.BLUE);
                    campos[fila][col].setBackground(Color.WHITE);
                }
            }
        }
    }

    /**
     * Verifica todas las celdas del tablero.
     * Marca en verde las correctas y en rojo las incorrectas.
     * Solo descuenta intentos si hay errores nuevos.
     */
    private void comprobarTablero() {
        int[][] solucion = juego.getGenerador().getTableroResuelto();
        boolean seHaRestado = false;

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (!juego.getCeldasFijas()[fila][col]) {
                    String texto = campos[fila][col].getText().trim();
                    if (!texto.isEmpty()) {
                        try {
                            int valor = Integer.parseInt(texto);
                            if (valor == solucion[fila][col]) {
                                campos[fila][col].setBackground(Color.GREEN);
                                juego.colocarNumero(fila, col, valor);
                            } else {
                                campos[fila][col].setBackground(Color.PINK);
                                seHaRestado = true;
                            }
                        } catch (NumberFormatException e) {
                            campos[fila][col].setBackground(Color.PINK);
                            seHaRestado = true;
                        }
                    }
                }
            }
        }

        if (seHaRestado) {
            intentosRestantes--;
        }

        intentosLabel.setText("Intentos restantes: " + intentosRestantes);

        if (intentosRestantes <= 0) {
            JOptionPane.showMessageDialog(this, "Has perdido. Te quedaste sin intentos.");
        } else if (juego.estaResuelto()) {
            JOptionPane.showMessageDialog(this, "¡Felicidades! Has completado el Sudoku.");
        }
    }

    private void iniciarTemporizador() {
        segundos = 0;
        if (timer != null) timer.cancel();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                segundos++;
                int horas = segundos / 3600;
                int minutos = (segundos % 3600) / 60;
                int seg = segundos % 60;
                tiempoLabel.setText(String.format("%02d:%02d:%02d", horas, minutos, seg));
            }
        }, 0, 1000);
    }
}
