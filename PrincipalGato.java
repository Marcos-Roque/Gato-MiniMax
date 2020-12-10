package modelo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import MinMax.Principal;

public class PrincipalGato extends JFrame {
	static PrincipalGato frame = new PrincipalGato();
	private JPanel contentPane;
	private boolean jugador;
    private String[][] jugada;
    private int[] turnoJugador;
    private int[] turnoMaquina; 
    private JButton btnIniciar;
    private JButton btnReiniciar;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JLabel label;
    private JLabel labelTurno;
    private JLabel labelGanador;
    
	public String Matriz[][];
	int profundidad=6;
	boolean change;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalGato frame = new PrincipalGato();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PrincipalGato(String [][] matriz) {
		initComponents();
		//_________________________Estado Inicial________________________________
		estadoInicial();
		//________________________________________________________________________
	}
	public PrincipalGato() {}
	public void initComponents() {
		//setTitle("Gato");
		//setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		setBounds(450, 150, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(216, 191, 216));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLUE);
		separator.setBackground(Color.BLUE);
		separator.setBounds(0, 426, 450, 9);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(85, 156, 293, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(85, 262, 293, 2);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.BLACK);
		separator_3.setForeground(Color.BLACK);
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(182, 55, 10, 310);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(Color.BLACK);
		separator_4.setForeground(Color.BLACK);
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(283, 55, 10, 310);
		contentPane.add(separator_4);
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource("../recursos/jugar.png"));
		int scale = 12; // 2 times smaller
		int width = ii.getIconWidth();
		int newWidth = width / scale;
		
		btnIniciar = new JButton("");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarBtns();
				btn1();
			}
		});
		btnIniciar.setFocusPainted(false);
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIniciar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnIniciar.setHideActionText(true);
		btnIniciar.setContentAreaFilled(false);
		btnIniciar.setForeground(Color.ORANGE);
		btnIniciar.setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));
		btnIniciar.setBorderPainted(false);
		btnIniciar.setBounds(41, 446, 71, 43);
		contentPane.add(btnIniciar);
		
		ImageIcon ir = new ImageIcon(this.getClass().getResource("../recursos/reiniciar.png"));
		
		btnReiniciar = new JButton("");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciar();
			}
		});
		btnReiniciar.setEnabled(false);
		btnReiniciar.setFocusPainted(false);
		btnReiniciar.setContentAreaFilled(false);
		btnReiniciar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReiniciar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnReiniciar.setIcon(new ImageIcon(ir.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));
		btnReiniciar.setBorderPainted(false);
		btnReiniciar.setBounds(178, 442, 71, 47);
		contentPane.add(btnReiniciar);
		
		ImageIcon is = new ImageIcon(this.getClass().getResource("../recursos/logout.png"));
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		btnSalir.setFocusPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setIcon(new ImageIcon(is.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(326, 442, 63, 47);
		contentPane.add(btnSalir);
		
		btn1 = new JButton("");
		btn1.setEnabled(false);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn1();
			}
		});
		btn1.setBounds(92, 62, 85, 88);
		contentPane.add(btn1);
		
		btn2 = new JButton("");
		btn2.setEnabled(false);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn2();
			}
		});
		btn2.setBounds(189, 61, 88, 88);
		contentPane.add(btn2);
		
		btn3 = new JButton("");
		btn3.setEnabled(false);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn3();
			}
		});
		btn3.setBounds(289, 60, 83, 90);
		contentPane.add(btn3);
		
		btn4 = new JButton("");
		btn4.setEnabled(false);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn4();
			}
		});
		btn4.setBounds(92, 165, 85, 88);
		contentPane.add(btn4);
		
		btn5 = new JButton("");
		btn5.setEnabled(false);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn5();
			}
		});
		btn5.setBounds(189, 165, 88, 88);
		contentPane.add(btn5);
		
		btn6 = new JButton("");
		btn6.setEnabled(false);
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn6();
			}
		});
		btn6.setBounds(289, 165, 85, 90);
		contentPane.add(btn6);
		
		btn7 = new JButton("");
		btn7.setEnabled(false);
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn7();
			}
		});
		btn7.setBounds(92, 270, 85, 88);
		contentPane.add(btn7);
		
		btn8 = new JButton("");
		btn8.setEnabled(false);
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn8();
			}
		});
		btn8.setBounds(189, 270, 88, 88);
		contentPane.add(btn8);
		
		btn9 = new JButton("");
		btn9.setEnabled(false);
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn9();
			}
		});
		btn9.setBounds(292, 272, 83, 88);
		contentPane.add(btn9);
		
		JLabel lblNewLabel = new JLabel("GATO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\MarkRoque\\Downloads\\Eclipse 2019\\eclipse\\Proyectos IA\\Gato\\src\\resources\\title.jpg"));
		lblNewLabel.setBounds(0, 0, 450, 44);
		contentPane.add(lblNewLabel);
		
		label = new JLabel("Turno:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(21, 369, 63, 21);
		contentPane.add(label);
		
		labelTurno = new JLabel("");
		labelTurno.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelTurno.setBounds(85, 369, 125, 23);
		contentPane.add(labelTurno);
		
		labelGanador = new JLabel("");
		labelGanador.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelGanador.setBounds(244, 394, 196, 21);
		contentPane.add(labelGanador);
	}

	//________________________________Estado Inicial______________________________
	//Metotodo estado inicial el cual inicializara a nuestro jugador, 
	//la jugada que sera nuestro tablero de 3x3 y lo llenaremos con "-" 
	//el cual significa vacio y lo usaremos como referencia despues para saber los campos vacios mas que nada
	public void estadoInicial() {
		jugador = true; 
        jugada = new String[3][3];
        llenarGato();
	}
	
	public void btn1() {
		ImageIcon imgBoton = null;
	       if(jugador && jugada[0][0].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/X.png"));
	           colocarJugada(0, 0, "X");
	           jugador = false;
	           if(maquina()) {
	        	   jugador = true;
	               labelGanador.setText("¡La Maquina ha ganado!");
	               deshabilitarBtns();
	           }
	       }else if(!jugador && jugada[0][0].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/O.png"));
	           colocarJugada(0,0,"O");
	           jugador = true;
	           if(persona()) {
	        	   jugador = false;
	        	   labelGanador.setText("¡El Jugador ha ganado!");
	               deshabilitarBtns();
	           }
	       }
	       imgBoton = new ImageIcon(imgBoton.getImage().getScaledInstance(50, -1, java.awt.Image.SCALE_DEFAULT));
	       btn1.setIcon(imgBoton);
	       verificarTurno(jugador);
	       btnIniciar.setEnabled(false);
	       btn1.setEnabled(false);
	}
	
	public void btn2() {
		ImageIcon imgBoton=null;
	       if(jugador && jugada[0][1].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/X.png"));
	           colocarJugada(0,1,"X");
	           jugador = false;
	           if(maquina()) {
	        	   jugador = true;
	               labelGanador.setText("¡La Maquina ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }else if(!jugador && jugada[0][1].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/O.png"));
	           colocarJugada(0,1,"O");
	           jugador = true;
	           if (jugador) {
	        	   turnoMaquina = estrategiaMaquina(jugada, "X", "O");
                   turnoJugador = estrategiaMaquina(jugada, "O", "X");
                   if (turnoJugador != null) {
                       maquinaBoton(turnoJugador);
                   }else if(turnoMaquina != null){
                       maquinaBoton(turnoMaquina);
                   } 
	           }
	           if(persona()) {
	        	   jugador = false;
	               labelGanador.setText("¡El Jugador ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }
	       imgBoton = new ImageIcon(imgBoton.getImage().getScaledInstance(50, -1, java.awt.Image.SCALE_DEFAULT));
	       btn2.setIcon(imgBoton);
	       verificarTurno(jugador);
	       btn2.setEnabled(false);
	}
	
	public void btn3() {
		ImageIcon imgBoton=null;
	       if(jugador && jugada[0][2].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/X.png"));
	           colocarJugada(0,2,"X");
	           jugador = false;
	           if(maquina()) {
	        	   jugador = true;
	               labelGanador.setText("¡La Maquina ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }else if(!jugador && jugada[0][2].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/O.png"));
	           colocarJugada(0,2,"O");
	           jugador = true;
	           if (jugador) {
	        	   int[] turnoMaquina = estrategiaMaquina(jugada, "X", "O");
                   int[] turnoJugador = estrategiaMaquina(jugada, "O", "X");
                   if (turnoJugador != null) {
                       maquinaBoton(turnoJugador);
                   }else if(turnoMaquina != null){
                       maquinaBoton(turnoMaquina);
                   } 
	           }
	           if(persona()) {
	        	   jugador = false;
	               labelGanador.setText("¡El Jugador ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }
	       imgBoton = new ImageIcon(imgBoton.getImage().getScaledInstance(50, -1, java.awt.Image.SCALE_DEFAULT));
	       btn3.setIcon(imgBoton);
	       verificarTurno(jugador);
	       btn3.setEnabled(false);
	}
	
	public void btn4() {
		ImageIcon imgBoton=null;
	       if(jugador && jugada[1][0].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/X.png"));
	           colocarJugada(1,0,"X");
	           jugador = false;
	           if(maquina()) {
	        	   jugador = true;
	               labelGanador.setText("¡La Maquina ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }else if(!jugador && jugada[1][0].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/O.png"));
	           colocarJugada(1,0,"O");
	           jugador = true;
	           if (jugador) {
	        	   int[] turnoMaquina = estrategiaMaquina(jugada, "X", "O");
                   int[] turnoJugador = estrategiaMaquina(jugada, "O", "X");
                   if (turnoJugador != null) {
                       maquinaBoton(turnoJugador);
                   }else if(turnoMaquina != null){
                       maquinaBoton(turnoMaquina);
                   } 
	           }
	           if(persona()) {
	        	   jugador = false;
	               labelGanador.setText("¡El Jugador ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }
	       imgBoton = new ImageIcon(imgBoton.getImage().getScaledInstance(50, -1, java.awt.Image.SCALE_DEFAULT));
	       btn4.setIcon(imgBoton);
	       verificarTurno(jugador);
	       btn4.setEnabled(false);
	}
	
	public void btn5() {
		ImageIcon imgBoton=null;
	       if(jugador && jugada[1][1].equals("-"))
	       {
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/X.png"));
	           colocarJugada(1,1,"X");
	           jugador = false;
	           if(maquina()) {
	        	   jugador = true;
	               labelGanador.setText("¡La Maquina ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }else if(!jugador && jugada[1][1].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/O.png"));
	           colocarJugada(1,1,"O");
	           jugador = true;
	           if (jugador) {
	        	   int[] turnoMaquina = estrategiaMaquina(jugada, "X", "O");
                   int[] turnoJugador = estrategiaMaquina(jugada, "O", "X");
                   if (turnoJugador != null) {
                       maquinaBoton(turnoJugador);
                   }else if(turnoMaquina != null){
                       maquinaBoton(turnoMaquina);
                   } 
	           }
	           if(persona()) {
	        	   jugador = false;
	               labelGanador.setText("El Jugador ha ganado!!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }
	       imgBoton = new ImageIcon(imgBoton.getImage().getScaledInstance(50, -1, java.awt.Image.SCALE_DEFAULT));
	       btn5.setIcon(imgBoton);
	       verificarTurno(jugador);
	       btn5.setEnabled(false);
	}
	
	public void btn6() {
		ImageIcon imgBoton=null;
	       if(jugador && jugada[1][2].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/X.png"));
	           colocarJugada(1,2,"X");
	           jugador = false;
	           if(maquina()) {
	        	   jugador = true;
	               labelGanador.setText("¡La Maquina ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }else if(!jugador && jugada[1][2].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/O.png"));
	           colocarJugada(1,2,"O");
	           jugador = true;
	           if (jugador) {
	        	   int[] turnoMaquina = estrategiaMaquina(jugada, "X", "O");
                   int[] turnoJugador = estrategiaMaquina(jugada, "O", "X");
                   if (turnoJugador != null) {
                       maquinaBoton(turnoJugador);
                   }else if(turnoMaquina != null){
                       maquinaBoton(turnoMaquina);
                   } 
	           }
	           if(persona()) {
	        	   jugador = false;
	               labelGanador.setText("¡El Jugador ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }
	       imgBoton = new ImageIcon(imgBoton.getImage().getScaledInstance(50, -1, java.awt.Image.SCALE_DEFAULT));
	       btn6.setIcon(imgBoton);
	       verificarTurno(jugador);
	       btn6.setEnabled(false);
	}
	
	public void btn7() {
		ImageIcon imgBoton=null;
	       if(jugador && jugada[2][0].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/X.png"));
	           colocarJugada(2,0,"X");
	           jugador = false;
	           if(maquina()) {
	        	   jugador = true;
	               labelGanador.setText("¡La Maquina ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }else if(!jugador && jugada[2][0].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/O.png"));
	           colocarJugada(2,0,"O");
	           jugador = true;
	           if (jugador) {
	        	   int[] turnoMaquina = estrategiaMaquina(jugada, "X", "O");
                   int[] turnoJugador = estrategiaMaquina(jugada, "O", "X");
                   if (turnoJugador != null) {
                       maquinaBoton(turnoJugador);
                   }else if(turnoMaquina != null){
                       maquinaBoton(turnoMaquina);
                   } 
	           }
	           if(persona()) {
	        	   jugador = false;
	               labelGanador.setText("¡El Jugador ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }
	       imgBoton = new ImageIcon(imgBoton.getImage().getScaledInstance(50, -1, java.awt.Image.SCALE_DEFAULT));
	       btn7.setIcon(imgBoton);
	       verificarTurno(jugador);
	       btn7.setEnabled(false);
	}
	
	public void btn8() {
		ImageIcon imgBoton=null;
	       if(jugador && jugada[2][1].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/X.png"));
	           colocarJugada(2,1,"X");
	           jugador = false;
	           if(maquina()) {
	        	   jugador = true;
	               labelGanador.setText("¡La Maquina ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }else if(!jugador && jugada[2][1].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/O.png"));
	           colocarJugada(2,1,"O");
	           jugador = true;
	           if (jugador) {
	        	   int[] turnoMaquina = estrategiaMaquina(jugada, "X", "O");
                   int[] turnoJugador = estrategiaMaquina(jugada, "O", "X");
                   if (turnoJugador != null) {
                       maquinaBoton(turnoJugador);
                   }else if(turnoMaquina != null){
                       maquinaBoton(turnoMaquina);
                   } 
	           }
	           if(persona()) {
	        	   jugador = false;
	               labelGanador.setText("¡El Jugador ha ganado!");
	               deshabilitarBtns();
	           }
	       }
	       imgBoton = new ImageIcon(imgBoton.getImage().getScaledInstance(50, -1, java.awt.Image.SCALE_DEFAULT));
	       btn8.setIcon(imgBoton);
	       verificarTurno(jugador);
	       btn8.setEnabled(false);
	}
	
	public void btn9() {
		ImageIcon imgBoton=null;
	       if(jugador && jugada[2][2].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/X.png"));
	           colocarJugada(2,2,"X");
	           jugador = false;
	           if(maquina()) {
	        	   jugador = true;
	               labelGanador.setText("¡La Maquina ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }else if(!jugador && jugada[2][2].equals("-")){
	           imgBoton = new ImageIcon(this.getClass().getResource("../recursos/O.png"));
	           colocarJugada(2,2,"O");
	           jugador = true;
	           if (jugador) {
	        	   int[] turnoMaquina = estrategiaMaquina(jugada, "X", "O");
                   int[] turnoJugador = estrategiaMaquina(jugada, "O", "X");
                   if (turnoJugador != null) {
                       maquinaBoton(turnoJugador);
                   }else if(turnoMaquina != null){
                       maquinaBoton(turnoMaquina);
                   } 
	           }
	           if(persona()) {
	        	   jugador = false;
	               labelGanador.setText("¡El Jugador ha ganado!");
	               deshabilitarBtns();
	           }else if(validarEmpate(jugada)){
	        	   labelGanador.setText("¡Es un empate!");
	           }
	       }
	       imgBoton = new ImageIcon(imgBoton.getImage().getScaledInstance(50, -1, java.awt.Image.SCALE_DEFAULT));
	       btn9.setIcon(imgBoton);
	       verificarTurno(jugador);
	       btn9.setEnabled(false);
	}
	
	public void verificarTurno(boolean turno) {
		if (turno) {
			labelTurno.setText("Maquina    'X'");
		}else {
			labelTurno.setText("Jugador    'O'");
		}
	}
	
	//____________________________________Funcion test terminal______________________________________ 
	//nuestras funciones que determinan el termino del juego son estos 4 metodos
	//maquina() : verifica las posibles jugadas dentro de la matriz tablero 3x3 en "X" turno de Maquina
	//persona() : verifica las posibles jugadas dentro de la matriz tablero 3x3 en "O" turno de Persona
	//estrategiaMaquina() : verifica todo el tablero donde el turno de la persona esta a punto de ganar
	//y bloquea su jugada colocando "X" cuando encuentre dos "O" seguidos.
	//maquinaBoton() : verifica que boton debe oprimir cuando es el turno de la maquina "X"
	public boolean maquina(){
        if(((jugada[0][0].equals("X")) && (jugada[0][1].equals("X")) && (jugada[0][2].equals("X")))
                || ((jugada[1][0].equals("X")) && (jugada[1][1].equals("X")) && (jugada[1][2].equals("X")))
                || ((jugada[2][0].equals("X")) && (jugada[2][1].equals("X")) && (jugada[2][2].equals("X")))
                || ((jugada[0][0].equals("X")) && (jugada[1][0].equals("X")) && (jugada[2][0].equals("X")))
                || ((jugada[0][1].equals("X")) && (jugada[1][1].equals("X")) && (jugada[2][1].equals("X")))
                || ((jugada[0][2].equals("X")) && (jugada[1][2].equals("X")) && (jugada[2][2].equals("X")))
                || ((jugada[0][0].equals("X")) && (jugada[1][1].equals("X")) && (jugada[2][2].equals("X")))
                || ((jugada[0][2].equals("X")) && (jugada[1][1].equals("X")) && (jugada[2][0].equals("X"))))
                return true;
                
         else
            return false;
    }
    
    public boolean persona(){
        if(((jugada[0][0].equals("O")) && (jugada[0][1].equals("O")) && (jugada[0][2].equals("O")))
                || ((jugada[1][0].equals("O")) && (jugada[1][1].equals("O")) && (jugada[1][2].equals("O")))
                || ((jugada[2][0].equals("O")) && (jugada[2][1].equals("O")) && (jugada[2][2].equals("O")))
                || ((jugada[0][0].equals("O")) && (jugada[1][0].equals("O")) && (jugada[2][0].equals("O")))
                || ((jugada[0][1].equals("O")) && (jugada[1][1].equals("O")) && (jugada[2][1].equals("O")))
                || ((jugada[0][2].equals("O")) && (jugada[1][2].equals("O")) && (jugada[2][2].equals("O")))
                || ((jugada[0][0].equals("O")) && (jugada[1][1].equals("O")) && (jugada[2][2].equals("O")))
                || ((jugada[0][2].equals("O")) && (jugada[1][1].equals("O")) && (jugada[2][0].equals("O"))))
                return true;
                
         else
            return false;
    }
    
    //______________________________________Funcion utilidad___________________________________________
    //estrategiaMaquina() : verifica y busca en todo el tablero donde el turno de la persona esta a punto de ganar
  	//y bloquea su jugada colocando "X" cuando encuentre dos "O" seguidos.
    public int[] estrategiaMaquina(String[][] partida, String m, String j){        
        for (int i = 0; i < partida.length; i++) {
            if (partida[i][0] == m && partida[i][1] == m && partida[i][2] != j) 
                return new int[]{i,2};                      
            else if (partida[i][0] == m && partida[i][2] == m && partida[i][1] != j) 
                return new int[]{i,1};                      
            else if (partida[i][1] == m && partida[i][2] == m && partida[i][0] != j) 
                return new int[]{i,0};                      
            else if (partida[0][i] == m && partida[1][i] == m && partida[2][i] != j) 
                return new int[]{2,i};                      
            else if (partida[0][i] == m && partida[2][i] == m && partida[1][i] != j) 
                return new int[]{1,i};                      
            else if (partida[1][i] == m && partida[2][i] == m && partida[0][i] != j) 
                return new int[]{0,i};                      
        }

        if ((partida[0][0] == m && partida[2][2] == m && partida[1][1] != j) || (partida[0][2] == m && partida[2][0] == m && partida[1][1] != j)) 
                return new int[]{1,1};                      
        else if (partida[0][0] == m && partida[1][1] == m && partida[2][2] != j) 
                return new int[]{2,2};                      
        else if (partida[1][1] == m && partida[2][2] == m && partida[0][0] != j) 
                return new int[]{0,0};                      
        else if (partida[0][2] == m && partida[1][1] == m && partida[2][0] != j) 
                return new int[]{2,0};                      
        else if (partida[1][1] == m && partida[2][0] == m && partida[0][2] != j) 
                return new int[]{0,2};                      
        return null;
    }  
    
    //maquinaBoton() : verifica que boton debe oprimir cuando es el turno de la maquina "X"
    private void maquinaBoton(int[] turnoMaquina){
        if(turnoMaquina[0] == 0 && turnoMaquina[1] == 0)  btn1.doClick();
        else if(turnoMaquina[0] == 0 && turnoMaquina[1] == 1)  btn2.doClick();
        else if(turnoMaquina[0] == 0 && turnoMaquina[1] == 2)  btn3.doClick();
        else if(turnoMaquina[0] == 1 && turnoMaquina[1] == 0)  btn4.doClick();
        else if(turnoMaquina[0] == 1 && turnoMaquina[1] == 1)  btn5.doClick();
        else if(turnoMaquina[0] == 1 && turnoMaquina[1] == 2)  btn6.doClick();
        else if(turnoMaquina[0] == 2 && turnoMaquina[1] == 0)  btn7.doClick();
        else if(turnoMaquina[0] == 2 && turnoMaquina[1] == 1)  btn8.doClick();
        else if(turnoMaquina[0] == 2 && turnoMaquina[1] == 2)  btn9.doClick();
    }
    
    //_______________________________________Metodo Sucesor____________________________________________
    //Metodo sucesor seria este metodo el cual sustituira el caracter - por X o O dependiendo del turno del jugador 
    //i y j son las coordenadas del movimiento y m sera X o O
    public void colocarJugada(int i, int j, String m){
        jugada[i][j] = m;
    }
    //___________________________________________MINMAX_________________________________________________
    //Donde 1 = "O" y 2 = "X"
	
	
	public boolean estaLleno(String Matriz[][]) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (Matriz[j][i] == "-") {
					return false;
				}
			}
		}
		return true;
		
	}
	
	private void readNumbers(int x,int y) {
		boolean ver = true;
		do {
			if(x < 3 && y < 3) {
				if (Matriz[x][y] == "-") {
					Matriz[x][y] = "O";
					ver = false;
				}
			}
		} while (ver);
		change = true;
	}

	public int ganador(String m[][]) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (j + 2 < 3) {
					if (m[j][i] == "O" && m[j+1][i] == "O" && m[j+2][i] == "O") {
						return 1;
					}
				}
				if(i+2 < 3) {
					if (m[j][i] == "O" && m[j][i+1] == "O" && m[j][i+2] == "O") {
						return 1;
					}
				}
				if (i+2 < 3 && j+2 < 3) {
					if (m[j][i] == "O" && m[j+1][i+1] == "O" && m[j+2][i+2] == "O") {
						return 1;
					}
				}
				if (i-3 > -1 && j+3 < 3) {
					if (m[j][i] == "O" && m[j+1][i-1] == "O" && m[j+2][i-2] == "O") {
						return 1;
					}
				}
				if (i+2 < 3 && j-2 > -1) {
					if (m[j][i] == "O" && m[j-1][i+1] == "O" && m[j-2][i+2] == "O") {
						return 1;
					}
				}
				if (j+2 < 3) {
					if (m[j][i] == "X" && m[j+1][i] == "X" && m[j+2][i] == "X") {
						return 2;
					}
				}
				if (i+2 < 3) {
					if (m[j][i] == "X" && m[j][i+1] == "X" && m[j][i+2] == "X") {
						return 2;
					}
				}
				if (i+2 < 3 && j+2 < 3) {
					if (m[j][i] == "X" && m[j+1][i+1] == "X" && m[j+2][i+2] == "X") {
						return 2;
					}
				}
				if (i-2 > -1 && j+2 < 3) {
					if (m[j][i] == "X" && m[j+1][i-1] == "X" && m[j+2][i-2] == "X") {
						return 2;
					}
				}
				if (i+2 < 3 && j-2 > -1) {
					if (m[j][i] == "X" && m[j-1][i+1] == "X" && m[j-2][i+2] == "X") {
						return 2;
					}
				}
			}
		}
		return 0;
	}
	
	private void printBoard(String m[][]) {
		System.out.println("");
		System.out.println("");
		if (ganador(m) == 1) {
			System.out.println("Ganaste Humano");
		}
		if (ganador(m) == 2) {
			System.out.println("Ganaste Maquina");
		}
		if (estaLleno(m)) {
			System.out.println("Es un empate");
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(m[j][i]+"\t");
			}
			System.out.println("");
		}
		
	}
	
	private void miniMax(String[][] matriz) {
		int mejorFila=-1,mejorColumna=-1;
		int max, maxActual;
		max = Integer.MIN_VALUE;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (matriz[j][i] == "-") {
					int tempFila,tempColumna;
					matriz[j][i] = "X";
					tempFila=i;
					tempColumna=j;
					maxActual=valorMin(matriz,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
					matriz[tempColumna][tempFila]="-";
					if (max < maxActual) {
						max = maxActual;
						mejorFila = tempFila;
						mejorColumna = tempColumna;
					}
				}
			}
		}
		Matriz[mejorColumna][mejorFila]="X";
		change=false;
	}
	
	public int valorMin(String[][] matriz, int profundidad, int alfa, int beta) {
		if (ganador(matriz) == 1 || ganador(matriz) == 2) {
			return heuristica(matriz);
		}else if(estaLleno(matriz)) {
			return heuristica(matriz);
		}else if (this.profundidad<profundidad) {
			return heuristica(matriz);
		}else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (matriz[j][i] == "-") {
						int tempFila,tempColumna;
						matriz[j][i] = "O";
						tempFila=i;
						tempColumna=j;
						beta=Integer.min(beta,valorMax(matriz,profundidad+1,alfa,beta));
						matriz[tempColumna][tempFila]="-";
						if (alfa >= beta) {
							return alfa;
						}
					}
				}
			}
			return beta;
		}
	}

	public int valorMax(String[][] matriz, int profundida, int alfa, int beta) {
		if (ganador(matriz) == 1 || ganador(matriz) == 2) {
			return heuristica(matriz);
		}else if(estaLleno(matriz)) {
			return heuristica(matriz);
		}else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (matriz[j][i] == "-") {
						int tempFila,tempColumna;
						matriz[j][i] = "X";
						tempFila=i;
						tempColumna=j;
						alfa=Integer.max(alfa,valorMin(matriz,profundida+1,alfa,beta));
						matriz[tempColumna][tempFila]="-";
						if (alfa >= beta) {
							return beta;
						}
					}
				}
			}
			return alfa;
		}
	}
	
	public int heuristica(String[][] matriz) {
		int costo = 0;
		costo = costo(matriz,"X")-costo(matriz,"O");
		return costo;
		
	}
	
	public int costo(String[][] matriz, String jugador) {
		int value = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (j+2<3) {
					if (matriz[j][i] == jugador && matriz[j+1][i] == jugador && matriz[j+2][i] == jugador) {
						return 500;
					}
				}
				if (i+2<3) {
					if (matriz[j][i] == jugador && matriz[j][i+1] == jugador && matriz[j][i+2] == jugador) {
						return 500;
					}
				}
				if (i+2<3 && j+2<3) {
					if (matriz[j][i] == jugador && matriz[j+1][i+1] == jugador && matriz[j+2][i+2] == jugador) {
						return 500;
					}
				}
				if (i-2>-1 && j+2<3) {
					if (matriz[j][i] == jugador && matriz[j+1][i-1] == jugador && matriz[j+2][i-2] == jugador) {
						return 500;
					}
				}
				if (i+2<3 && j-2 > -1) {
					if (matriz[j][i] == jugador && matriz[j-1][i+1] == jugador && matriz[j-2][i+2] == jugador) {
						return 500;
					}
				}
				if (j+1<3) {
					if (matriz[j][i] == jugador && matriz[j+1][i] == jugador) {
						value = 300;
					}
				}
				if (i+1<3) {
					if (matriz[j][i] == jugador && matriz[j][i+1] == jugador) {
						value = 300;
					}
				}
				if (i+1<3 && j+1 < 3) {
					if (matriz[j][i] == jugador && matriz[j+1][i+1] == jugador) {
						value = 300;
					}
				}
				if (i-1>-1 && j+1<3) {
					if (matriz[j][i] == jugador && matriz[j+1][i-1] == jugador) {
						value = 300;
					}
				}
				if (i+1<3 && j-1 > -1) {
					if (matriz[j][i] == jugador && matriz[j-1][i+1] == jugador) {
						value = 300;
					}
				}
			}
		}
		return value;
	}
   
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void llenarGato(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                jugada[i][j] = "-";
            }
        }
    }
    
    public final boolean validarEmpate(String[][] partida){
        for (String[] movimiento : partida) {
            for (int j = 0; j < movimiento.length; j++) {
                if (movimiento[j] == "-") {  
                    return false;
                }
            }
        }
        return true;
    }
    
    public void reiniciar() {
    	turnoJugador = null;
    	turnoMaquina = null;
    	llenarGato();
    	habilitarBtns();
        btn1.setText("");
        btn1.setIcon(null);
        btn2.setText("");
        btn2.setIcon(null);
        btn3.setText("");
        btn3.setIcon(null);
        btn4.setText("");
        btn4.setIcon(null);
        btn5.setText("");
        btn5.setIcon(null);
        btn6.setText("");
        btn6.setIcon(null);
        btn7.setText("");
        btn7.setIcon(null);
        btn8.setText("");
        btn8.setIcon(null);
        btn9.setText("");
        btn9.setIcon(null);
        jugador = true;
        labelTurno.setText("Maquina");
        labelGanador.setText("");
        btn1();
    }
    
    public void habilitarBtns() {
    	btnReiniciar.setEnabled(true);
    	btn1.setEnabled(true);
    	btn2.setEnabled(true);
    	btn3.setEnabled(true);
    	btn4.setEnabled(true);
    	btn5.setEnabled(true);
    	btn6.setEnabled(true);
    	btn7.setEnabled(true);
    	btn8.setEnabled(true);
    	btn9.setEnabled(true);
    	labelTurno.setText("Maquina");
    }	
    
    public void deshabilitarBtns() {
    	btnIniciar.setEnabled(false);
    	btn1.setEnabled(false);
    	btn2.setEnabled(false);
    	btn3.setEnabled(false);
    	btn4.setEnabled(false);
    	btn5.setEnabled(false);
    	btn6.setEnabled(false);
    	btn7.setEnabled(false);
    	btn8.setEnabled(false);
    	btn9.setEnabled(false);
    }

}
