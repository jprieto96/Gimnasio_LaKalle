/**
 * 
 */
package presentacion.personal;

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

/*
* @author jose_
*/
public class VistaPersonal extends JFrame implements Vista {

	private JButton botonAltaPersonal;
	private JButton botonBajaPersonal;
	private JButton botonModificarPersonal;
	private JButton botonListarPersonal;
	private JButton botonMostrarPersonalPorID;
	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;

	public VistaPersonal() {
		super(VistaMain.TITULO_APP);
		initComponents();
		inicializarVista();
	}

	public void inicializarVista() {
		panelPrincipal.setLayout(new BorderLayout());
		panelBienvenida.setLayout(new FlowLayout());
		panelDatos.setLayout(new GridLayout(2, 3));
		panelSur.setLayout(new FlowLayout());
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(botonAltaPersonal);
		panelDatos.add(botonBajaPersonal);
		panelDatos.add(botonModificarPersonal);
		panelDatos.add(botonListarPersonal);
		panelDatos.add(botonMostrarPersonalPorID);
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

		botonAltaPersonal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaAltaPersonal().inicializarVista();
				dispose();
			}
		});
		botonBajaPersonal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaBajaPersonal().inicializarVista();
				dispose();
			}
		});
		botonModificarPersonal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaModificarPersonal().inicializarVista();
				dispose();
			}
		});
		botonListarPersonal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Evento.LISTAR_PERSONAL, null);
				dispose();
			}
		});
		botonMostrarPersonalPorID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaMostrarDatosPorPersonal().inicializarVista();
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
	}

	@Override
	public void actualizar(int evento, Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initComponents() {
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelDatos = new JPanel();
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Gestión del Personal");
		botonAltaPersonal = new JButton("Alta Personal");
		botonBajaPersonal = new JButton("Baja Personal");
		botonModificarPersonal = new JButton("Modificar Personal");
		botonListarPersonal = new JButton("Listar Personal");
		botonMostrarPersonalPorID = new JButton("Mostrar Personal por ID");
		botonAtras = new JButton("Atrás");
	}

}