/**
 * 
 */
package presentacion.cliente.modificarCliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.cliente.TCliente;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;
import utils.ValidarDNI;

@SuppressWarnings("serial")
public class VistaModificarCliente extends JFrame implements Vista {

	private JButton botonOK;
	private JButton botonReset;
	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JLabel labelId;
	private JTextField textoId;
	private JLabel labelNombre;
	private JLabel labelDni;
	private JLabel labelTelefono;
	private JLabel labelCuentaBancaria;
	private JLabel labelDomicilio;
	private JLabel labelCorreo;
	private JTextField textoNombre;
	private JTextField textoDni;
	private JTextField textoTelefono;
	private JTextField textoCuentaBancaria;
	private JTextField textoDomicilio;
	private JTextField textoCorreo;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;

	public VistaModificarCliente() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void initComponents() {
		// begin-user-code
		panelPrincipal = new JPanel(new BorderLayout());
		panelBienvenida = new JPanel();
		panelDatos = new JPanel(new GridLayout(8, 2));
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Modificar Cliente");
		labelId = new JLabel("ID del Cliente:");
		labelNombre = new JLabel("Nombre:");
		textoNombre = new JTextField(10);
		labelDni = new JLabel("DNI:");
		textoDni = new JTextField(10);
		labelCuentaBancaria = new JLabel("Cuenta Bancaria:");
		textoCuentaBancaria = new JTextField(10);
		labelTelefono = new JLabel("Teléfono:");
		textoTelefono = new JTextField(10);
		labelDomicilio = new JLabel("Domicilio:");
		textoDomicilio = new JTextField(10);
		labelCorreo = new JLabel("Correo:");
		textoCorreo = new JTextField(10);
		textoId = new JTextField(10);
		botonOK = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		// end-user-code
	}

	@Override
	public void inicializarVista() {
		// begin-user-code
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelSur.setLayout(new FlowLayout());
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(labelId);
		panelDatos.add(textoId);
		panelDatos.add(labelNombre);
		panelDatos.add(textoNombre);
		panelDatos.add(labelDni);
		panelDatos.add(textoDni);
		panelDatos.add(labelCuentaBancaria);
		panelDatos.add(textoCuentaBancaria);
		panelDatos.add(labelTelefono);
		panelDatos.add(textoTelefono);
		panelDatos.add(labelDomicilio);
		panelDatos.add(textoDomicilio);
		panelDatos.add(labelCorreo);
		panelDatos.add(textoCorreo);
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
				if (comprobacionSintacticaDatos()) {
					Controlador.getInstancia().accion(Evento.MODIFICAR_CLIENTE,
							new TCliente(Integer.valueOf(textoId.getText()), textoNombre.getText(), textoDni.getText(),
									textoCuentaBancaria.getText(), textoTelefono.getText(), textoDomicilio.getText(), textoCorreo.getText()));
				}
			}
		});
		botonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoId.setText("");
				textoNombre.setText("");
				textoDni.setText("");
				textoCuentaBancaria.setText("");
				textoTelefono.setText("");
				textoDomicilio.setText("");
				textoCorreo.setText("");
				
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
			int id = Integer.valueOf(textoId.getText());
			if (id <= 0)
				throw new NumberFormatException();
			if (!ValidarDNI.validar(textoDni.getText()))
				throw new IllegalArgumentException();
			if (textoTelefono.getText().length() != 9)
				throw new IllegalArgumentException();
			Integer.valueOf(textoTelefono.getText());
			return true;
		} catch (Exception ex) {
			textoId.setText("");
			textoNombre.setText("");
			textoDni.setText("");
			textoCuentaBancaria.setText("");
			textoTelefono.setText("");
			textoDomicilio.setText("");
			textoCorreo.setText("");
			JOptionPane.showMessageDialog(null, "Error de formato en alguno de los campos introducidos",
					"Error message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.MODIFICAR_CLIENTE_OK:
			JOptionPane.showMessageDialog(null, "El cliente " + (Integer) object + " ha sido modificada correctamente");
			break;
		case Evento.MODIFICAR_CLIENTE_ERROR:
			JOptionPane.showMessageDialog(null, "El cliente no existe", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}