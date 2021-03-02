package presentacion.matricula;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

public class VistaMatricula extends JFrame implements Vista {

	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;
	private JPanel panelPrincipal;
	private JLabel labelBienvenida;
	private JButton botonAtras;
	private JButton botonAltaMatricula;
	private JButton botonBajaMatricula;
	private JButton botonListarMatriculas;
	private JButton botonModificarMatricula;
	private JButton botonMostrarMatriculasPorClase;
	private JButton botonMostrarMatriculasPorCliente;

	public VistaMatricula() {
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
		panelDatos.add(botonAltaMatricula);
		panelDatos.add(botonBajaMatricula);
		panelDatos.add(botonModificarMatricula);
		panelDatos.add(botonListarMatriculas);
		panelDatos.add(botonMostrarMatriculasPorClase);
		panelDatos.add(botonMostrarMatriculasPorCliente);
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

		botonAltaMatricula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaAltaMatricula().inicializarVista();
				dispose();
			}
		});
		botonBajaMatricula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaBajaMatricula().inicializarVista();
				dispose();
			}
		});
		botonModificarMatricula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaModificarMatricula().inicializarVista();
				dispose();
			}
		});
		botonListarMatriculas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Evento.LISTAR_MATRICULAS, null);
				dispose();
			}
		});
		botonMostrarMatriculasPorClase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaListarMatriculasPorClase().inicializarVista();
				dispose();
			}
		});
		botonMostrarMatriculasPorCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaListarMatriculasPorCliente().inicializarVista();
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
		labelBienvenida = new JLabel("Gestión de Matriculas");
		botonAltaMatricula = new JButton("Alta Matricula");
		botonBajaMatricula = new JButton("Baja Matricula");
		botonModificarMatricula = new JButton("Modificar Matricula");
		botonListarMatriculas = new JButton("Listar Matriculas");
		botonMostrarMatriculasPorClase = new JButton("Mostrar Matriculas por Clase");
		botonMostrarMatriculasPorCliente = new JButton("Mostrar Matriculas por Cliente");
		botonAtras = new JButton("Atrás");
		// end-user-code
	}

}
