/**
 * 
 */
package presentacion.cliente.mostrarDatosCliente;

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

import negocio.cliente.TDatosCompletosCliente;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

@SuppressWarnings("serial")
public class VistaMostrarDatosCliente extends JFrame implements Vista {

	private JButton botonOk;
	private JButton botonReset;
	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JLabel labelId;
	private JTextField textoId;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;

	public VistaMostrarDatosCliente() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.MOSTRAR_CLIENTE_POR_ID_OK:
			TDatosCompletosCliente cliente = (TDatosCompletosCliente) object;
			JOptionPane.showMessageDialog(null, cliente.toString());
			break;
		case Evento.MOSTRAR_CLIENTE_POR_ID_ERROR:
			JOptionPane.showMessageDialog(null, "No existen Clientes con ese ID", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

	@Override
	public void initComponents() {
		// begin-user-code
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelDatos = new JPanel();
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Mostrar datos de Cliente por ID");
		labelId = new JLabel("Introduce ID del Cliente:");
		textoId = new JTextField(10);
		botonOk = new JButton("Enviar");
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
		panelDatos.setLayout(new FlowLayout());
		panelDatos.add(labelId);
		panelDatos.add(textoId);
		panelSur.setLayout(new FlowLayout());
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelSur.add(botonOk);
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

		botonOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comprobacionSintacticaDatos())
					Controlador.getInstancia().accion(Evento.MOSTRAR_CLIENTE_POR_ID,
							Integer.valueOf(textoId.getText()));
			}

			private boolean comprobacionSintacticaDatos() {
				try {
					if (Integer.valueOf(textoId.getText()) < 0)
						throw new NumberFormatException();
					return true;
				} catch (NumberFormatException e) {
					textoId.setText("");
					JOptionPane.showMessageDialog(null, "Introduce un número positivo", "Mensaje de Error",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
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

}