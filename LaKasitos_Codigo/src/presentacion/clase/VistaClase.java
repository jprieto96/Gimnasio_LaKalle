/**
 * 
 */
package presentacion.clase;

import javax.swing.JFrame;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VistaClase extends JFrame implements Vista {

	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;
	private JLabel labelBienvenida;
	private JButton botonAltaClase;
	private JButton botonBajaClase;
	private JButton botonModificarClase;
	private JButton botonListarClases;
	private JButton botonMostrarDatosClase;
	private JButton botonMostrarClasePorAula;
	private JButton botonMostrarClasePorEntrenador;
	private JButton botonAsignarClaseAEntrenador;
	private JButton botonAsignarClaseAAula;
	private JButton botonAtras;

	public VistaClase() {
		super(VistaMain.TITULO_APP);
		initComponents();
		inicializarVista();
	}

	public void inicializarVista() {
		// begin-user-code
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(botonAltaClase);
		panelDatos.add(botonBajaClase);
		panelDatos.add(botonModificarClase);
		panelDatos.add(botonListarClases);
		panelDatos.add(botonMostrarDatosClase);
		panelDatos.add(botonMostrarClasePorAula);
		panelDatos.add(botonMostrarClasePorEntrenador);
		panelDatos.add(botonAsignarClaseAAula);
		panelDatos.add(botonAsignarClaseAEntrenador);
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

		botonAltaClase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaAltaClase().inicializarVista();
				dispose();
			}
		});
		botonBajaClase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaBajaClase().inicializarVista();
				dispose();
			}
		});
		botonModificarClase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaModificarClase().inicializarVista();
				dispose();
			}
		});
		botonListarClases.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Evento.LISTAR_CLASES, null);
				dispose();
			}
		});
		botonMostrarDatosClase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaMostrarDatosClase().inicializarVista();
				dispose();
			}
		});
		botonMostrarClasePorAula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaMostrarClasesPorAula().inicializarVista();
				dispose();
			}
		});
		botonMostrarClasePorEntrenador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaMostrarClasesPorEntrenador().inicializarVista();
				dispose();
			}
		});
		botonAsignarClaseAAula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaAsignarClaseAAula().inicializarVista();
				dispose();
			}
		});
		botonAsignarClaseAEntrenador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaAsignarClaseAEntrenador().inicializarVista();
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

	public void actualizar(int evento, Object object) {
		// begin-user-code

		// end-user-code
	}

	public void initComponents() {
		// begin-user-code
		panelPrincipal = new JPanel(new BorderLayout());
		panelBienvenida = new JPanel();
		panelDatos = new JPanel(new GridLayout(3, 3));
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Gestión de Clases");
		botonAltaClase = new JButton("Alta Clase");
		botonBajaClase = new JButton("Baja Clase");
		botonModificarClase = new JButton("Modificar Clase");
		botonListarClases = new JButton("Listar Clases");
		botonMostrarDatosClase = new JButton("Mostrar Clase por ID");
		botonMostrarClasePorAula = new JButton("Mostrar Clases Por Aula");
		botonMostrarClasePorEntrenador = new JButton("Mostrar Clases Por Entrenador");
		botonAsignarClaseAAula = new JButton("Asignar Clase a Aula");
		botonAsignarClaseAEntrenador = new JButton("Asignar Clase a Entrenador");
		botonAtras = new JButton("Atrás");
		// end-user-code
	}
}