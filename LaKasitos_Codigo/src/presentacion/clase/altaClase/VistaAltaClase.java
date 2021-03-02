/**
 * 
 */
package presentacion.clase.altaClase;

import javax.swing.JFrame;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import negocio.clase.TClase;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class VistaAltaClase extends JFrame implements Vista {

	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelInfo;
	private JPanel panelSur;
	private JLabel labelBienvenida;
	private JLabel labelHora;
	private JLabel labelAula;
	private JLabel labelEntrenador;
	private JTextField textoHora;
	private JTextField textoIdAula;
	private JTextField textoIdEntrenador;
	private JButton botonReactivarClase;
	private JButton botonAtras;
	private JButton botonReset;
	private JButton botonOk;
	private JButton botonDarDeAlta;
	private JList<TClase> clasesNoDisponibles;

	public VistaAltaClase() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	public void inicializarVista() {
		// begin-user-code
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelInfo.add(labelHora);
		panelInfo.add(textoHora);
		panelInfo.add(labelAula);
		panelInfo.add(textoIdAula);
		panelInfo.add(labelEntrenador);
		panelInfo.add(textoIdEntrenador);
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(panelInfo);
		panelSur.add(botonOk);
		panelSur.add(botonReset);
		panelSur.add(botonAtras);
		panelSur.add(botonReactivarClase);
		panelPrincipal.add(panelBienvenida, BorderLayout.NORTH);
		panelPrincipal.add(panelDatos, BorderLayout.CENTER);
		panelPrincipal.add(panelSur, BorderLayout.SOUTH);

		getContentPane().add(panelPrincipal);
		setResizable(false);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		botonReactivarClase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Evento.LISTAR_CLASES_NO_DISPONIBLES, null);
				dispose();
			}
		});
		botonOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int idAula = Integer.valueOf(textoIdAula.getText());
					int idEntrenador = Integer.valueOf(textoIdEntrenador.getText());
					if (idAula <= 0 || idEntrenador <= 0)
						throw new NumberFormatException();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
					LocalTime time = LocalTime.parse(textoHora.getText(), formatter);
					Controlador.getInstancia().accion(Evento.ALTA_CLASE, new TClase(time, idAula, idEntrenador));
				} catch (NumberFormatException nEx) {
					JOptionPane.showMessageDialog(null, "Introduce un número positivo en ambos ID", "Mensaje de Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (DateTimeParseException tEx) {
					JOptionPane.showMessageDialog(null, "Formato de fecha erroneo, Introduce HH-mm", "Mensaje de Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoHora.setText("");
				textoIdAula.setText("");
				textoIdEntrenador.setText("");
			}
		});
		botonAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaClase();
				dispose();
			}
		});
		// end-user-code
	}

	public void initComponents() {
		// begin-user-code
		panelPrincipal = new JPanel(new BorderLayout());
		panelBienvenida = new JPanel();
		panelDatos = new JPanel();
		panelInfo = new JPanel(new GridLayout(3, 2));
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Alta de Clase");
		labelHora = new JLabel("Hora:");
		textoHora = new JTextField(10);
		labelAula = new JLabel("ID Aula:");
		textoIdAula = new JTextField(10);
		labelEntrenador = new JLabel("ID Entrenador:");
		textoIdEntrenador = new JTextField(10);
		botonOk = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		botonReactivarClase = new JButton("Reactivar una Clase");
		botonDarDeAlta = new JButton("Dar De Alta");
		// end-user-code
	}

	@Override
	public void actualizar(int evento, Object object) {
		// begin-user-code
		switch (evento) {
		case Evento.ALTA_CLASE_OK:
			JOptionPane.showMessageDialog(null, "Clase " + (Integer) object + " dada de alta.");
			break;
		case Evento.ALTA_CLASE_ERROR:
			JOptionPane.showMessageDialog(null, "La clase no ha podido darse de alta", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.LISTAR_CLASES_NO_DISPONIBLES_OK:
			Vector<TClase> listaClases = (Vector<TClase>) object;
			if (listaClases.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay clases existentes que no esten disponibles",
						"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
				FactoriaPresentacion.getInstancia().generaVistaAltaClase().inicializarVista();
				dispose();
			} else {
				panelPrincipal.setLayout(new BorderLayout());
				panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
				panelBienvenida.add(labelBienvenida);

				clasesNoDisponibles = new JList<TClase>(listaClases);
				JScrollPane scrollLista = new JScrollPane(clasesNoDisponibles);

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
						Controlador.getInstancia().accion(Evento.ALTA_CLASE,
								listaClases.get(clasesNoDisponibles.getSelectedIndex()));
						FactoriaPresentacion.getInstancia().generaVistaAltaClase().inicializarVista();
						dispose();
					}
				});
				botonAtras.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						FactoriaPresentacion.getInstancia().generaVistaAltaClase().inicializarVista();
						dispose();
					}
				});
			}
			break;
		default:
			break;
		}
		// end-user-code
	}
}