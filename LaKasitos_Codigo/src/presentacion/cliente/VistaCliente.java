/**
 * 
 */
package presentacion.cliente;

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
* @author marcos_
*/
public class VistaCliente extends JFrame implements Vista {

	private JButton botonAltaCliente;
	private JButton botonBajaCliente;
	private JButton botonModificarCliente;
	private JButton botonListarCliente;
	private JButton botonMostrarClientePorID;
	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;

	public VistaCliente() {
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
		panelDatos.add(botonAltaCliente);
		panelDatos.add(botonBajaCliente);
		panelDatos.add(botonModificarCliente);
		panelDatos.add(botonListarCliente);
		panelDatos.add(botonMostrarClientePorID);
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

		botonAltaCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaAltaCliente().inicializarVista();
				dispose();
			}
		});
		botonBajaCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaBajaCliente().inicializarVista();
				dispose();
			}
		});
		botonModificarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaModificarCliente().inicializarVista();
				dispose();
			}
		});
		botonListarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Evento.LISTAR_CLIENTES, null);
				dispose();
			}
		});
		botonMostrarClientePorID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaMostrarDatosCliente().inicializarVista();
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
		labelBienvenida = new JLabel("Gestión del Cliente");
		botonAltaCliente = new JButton("Alta Cliente");
		botonBajaCliente = new JButton("Baja Cliente");
		botonModificarCliente = new JButton("Modificar Cliente");
		botonListarCliente = new JButton("Listar Clientes");
		botonMostrarClientePorID = new JButton("Mostrar Cliente por ID");
		botonAtras = new JButton("Atrás");
	}

}