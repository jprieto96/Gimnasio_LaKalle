/**
 * 
 */
package presentacion.aula;

import javax.swing.JFrame;

import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VistaAula extends JFrame implements Vista {

	private JButton botonAltaAula;
	private JButton botonBajaAula;
	private JButton botonModificarAula;
	private JButton botonListarAulas;
	private JButton botonMostrarAulaPorID;
	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;

	public VistaAula() {
		super(VistaMain.TITULO_APP);
		initComponents();
		inicializarVista();
	}

	public void inicializarVista() {
		// begin-user-code
		panelPrincipal.setLayout(new BorderLayout());
		panelBienvenida.setLayout(new FlowLayout());
		panelDatos.setLayout(new GridLayout(2, 3));
		panelSur.setLayout(new FlowLayout());
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(botonAltaAula);
		panelDatos.add(botonBajaAula);
		panelDatos.add(botonModificarAula);
		panelDatos.add(botonListarAulas);
		panelDatos.add(botonMostrarAulaPorID);
		panelSur.add(botonAtras);
		panelPrincipal.add(panelBienvenida, BorderLayout.NORTH);
		panelPrincipal.add(panelDatos, BorderLayout.CENTER);
		panelPrincipal.add(panelSur, BorderLayout.SOUTH);

		getContentPane().add(panelPrincipal);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		botonAltaAula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaAltaAula().inicializarVista();
				dispose();
			}
		});
		botonBajaAula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaBajaAula().inicializarVista();
				dispose();
			}
		});
		botonModificarAula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaModificarAula().inicializarVista();
				dispose();
			}
		});
		botonListarAulas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Evento.LISTAR_AULAS, null);
				dispose();
			}
		});
		botonMostrarAulaPorID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaMostrarDatosPorAula().inicializarVista();
				dispose();
			}
		});
		botonAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaMain();
				dispose();
			}
		});
		// end-user-code
	}

	@Override
	public void actualizar(int evento, Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initComponents() {
		// begin-user-code
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelDatos = new JPanel();
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Gestión de Aulas");
		botonAltaAula = new JButton("Alta Aula");
		botonBajaAula = new JButton("Baja Aula");
		botonModificarAula = new JButton("Modificar Aula");
		botonListarAulas = new JButton("Listar Aulas");
		botonMostrarAulaPorID = new JButton("Mostrar Aula por ID");
		botonAtras = new JButton("Atrás");
		// end-user-code
	}

}