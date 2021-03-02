/**
 * 
 */
package presentacion.cliente.bajaClientes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

public class VistaBajaCliente extends JFrame implements Vista {

	private JButton botonOK;
	private JButton botonReset;
	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JLabel labelId;
	private JTextField textoId;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelInfo;
	private JPanel panelSur;

	public VistaBajaCliente() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void initComponents() {
		// begin-user-code
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelDatos = new JPanel();
		panelInfo = new JPanel();
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Baja de Cliente");
		labelId = new JLabel("ID del Cliente:");
		textoId = new JTextField(10);
		botonOK = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		// end-user-code
	}

	@Override
	public void inicializarVista() {
		// begin-user-code
		labelId.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelBienvenida.setLayout(new FlowLayout());
		panelInfo.setLayout(new FlowLayout());
		panelDatos.setLayout(new FlowLayout());
		panelSur.setLayout(new FlowLayout());
		panelInfo.add(labelId);
		panelInfo.add(textoId);
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(panelInfo);
		panelSur.add(botonOK);
		panelSur.add(botonReset);
		panelSur.add(botonAtras);
		panelPrincipal.add(panelBienvenida, BorderLayout.NORTH);
		panelPrincipal.add(panelDatos, BorderLayout.CENTER);
		panelPrincipal.add(panelSur, BorderLayout.SOUTH);

		getContentPane().add(panelPrincipal);
		setResizable(false);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		botonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comprobacionSintacticaDatos())
					Controlador.getInstancia().accion(Evento.BAJA_CLIENTE, Integer.valueOf(textoId.getText()));
			}
		});
		botonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoId.setText("");
			}
		});
		botonAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaCliente();
				dispose();
			}
		});
		// end-user-code
	}

	private boolean comprobacionSintacticaDatos() {
		try {
			if (Integer.valueOf(textoId.getText()) <= 0)
				throw new NumberFormatException();
			return true;
		} catch (NumberFormatException e) {
			textoId.setText("");
			JOptionPane.showMessageDialog(null, "Introduce un número mayor que 0", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.BAJA_CLIENTE_OK:
			JOptionPane.showMessageDialog(null, "Cliente " + (Integer) object + " dado de baja correctamente.");
			break;
		case Evento.BAJA_CLIENTE_ERROR:
			JOptionPane.showMessageDialog(null, "El cliente seleccionado no existe o esta matriculado en clases",
					"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}