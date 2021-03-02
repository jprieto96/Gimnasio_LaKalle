/**

 * 
 */
package presentacion.cliente.altaClientes;

import java.awt.BorderLayout;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import negocio.cliente.TCliente;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;
import utils.ValidarDNI;

@SuppressWarnings("serial")
public class VistaAltaCliente extends JFrame implements Vista {

	private JButton botonOK;
	private JButton botonReset;
	private JButton botonAtras;
	private JButton botonReactivar;
	private JButton botonDarDeAlta;
	private JLabel labelBienvenida;
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
	private JPanel panelInfo;
	private JPanel panelSur;
	private JList<TCliente> ClientesDeBaja;

	public VistaAltaCliente() {
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
		labelBienvenida = new JLabel("Alta de Cliente");
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
		botonOK = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		botonReactivar = new JButton("Reactivar un Cliente");
		botonDarDeAlta = new JButton("Dar De Alta");
		// end-user-code
	}

	@Override
	public void inicializarVista() {
		// begin-user-code
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelBienvenida.setLayout(new FlowLayout());
		panelInfo.setLayout(new GridLayout(6, 2));
		panelSur.setLayout(new FlowLayout());
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelInfo.add(labelNombre);
		panelInfo.add(textoNombre);
		panelInfo.add(labelDni);
		panelInfo.add(textoDni);
		panelInfo.add(labelTelefono);
		panelInfo.add(textoTelefono);
		panelInfo.add(labelCuentaBancaria);
		panelInfo.add(textoCuentaBancaria);
		panelInfo.add(labelDomicilio);
		panelInfo.add(textoDomicilio);
		panelInfo.add(labelCorreo);
		panelInfo.add(textoCorreo);
		panelDatos.add(panelInfo);
		panelSur.add(botonOK);
		panelSur.add(botonReset);
		panelSur.add(botonAtras);
		panelSur.add(botonReactivar);
		panelPrincipal.add(panelBienvenida, BorderLayout.NORTH);
		panelPrincipal.add(panelDatos, BorderLayout.CENTER);
		panelPrincipal.add(panelSur, BorderLayout.SOUTH);
		
		getContentPane().add(panelPrincipal);
		setResizable(false);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		botonReactivar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Evento.LISTAR_CLIENTES_NO_DISPONIBLES, null);
				dispose();
			}
		});
		botonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comprobacionSintacticaDatos())
					Controlador.getInstancia().accion(Evento.ALTA_CLIENTE,
							new TCliente(-1, textoNombre.getText(), textoDni.getText(), textoCuentaBancaria.getText(),
									textoTelefono.getText(), textoDomicilio.getText(), textoCorreo.getText()));
			}
		});
		botonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
			if (!ValidarDNI.validar(textoDni.getText()))
				throw new IllegalArgumentException();
			if (textoTelefono.getText().length() != 9)
				throw new IllegalArgumentException();
			Integer.valueOf(textoTelefono.getText());
			return true;
		} catch (Exception ex) {
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
		case Evento.ALTA_CLIENTE_OK:
			JOptionPane.showMessageDialog(null, "Cliente " + (Integer) object + " dada de alta.");
			break;
		case Evento.ALTA_CLIENTE_ERROR:
			JOptionPane.showMessageDialog(null, "El cliente no ha podido darse de alta", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.LISTAR_CLIENTES_NO_DISPONIBLES_OK:
			Vector<TCliente> listaClientes = (Vector<TCliente>) object;
			if (listaClientes.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay clientes existentes que no esten disponibles",
						"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
				FactoriaPresentacion.getInstancia().generaVistaAltaCliente().inicializarVista();
				dispose();
			} else {
				panelPrincipal.setLayout(new BorderLayout());
				panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
				panelBienvenida.add(labelBienvenida);

				ClientesDeBaja = new JList<TCliente>(listaClientes);
				JScrollPane scrollLista = new JScrollPane(ClientesDeBaja);

				panelSur.add(botonDarDeAlta);
				panelSur.add(botonAtras);
				panelPrincipal.add(panelBienvenida, BorderLayout.NORTH);
				panelPrincipal.add(scrollLista, BorderLayout.CENTER);
				panelPrincipal.add(panelSur, BorderLayout.SOUTH);

				getContentPane().add(panelPrincipal);
				setResizable(false);
				pack();
				setVisible(true);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(EXIT_ON_CLOSE);

				botonDarDeAlta.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Controlador.getInstancia().accion(Evento.ALTA_CLIENTE,
								listaClientes.get(ClientesDeBaja.getSelectedIndex()));
						FactoriaPresentacion.getInstancia().generaVistaAltaCliente().inicializarVista();
						dispose();
					}
				});
				botonAtras.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						FactoriaPresentacion.getInstancia().generaVistaAltaCliente().inicializarVista();
						dispose();
					}
				});
			}
			break;
		default:
			break;
		}
	}
}