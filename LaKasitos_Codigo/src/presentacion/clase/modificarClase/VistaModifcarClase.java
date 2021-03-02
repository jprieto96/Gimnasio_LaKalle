/**
 * 
 */
package presentacion.clase.modificarClase;

import javax.swing.JFrame;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class VistaModifcarClase extends JFrame implements Vista {

	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;
	private JLabel labelBienvenida;
	private JLabel labelId;
	private JTextField textoId;
	private JLabel labelHora;
	private JTextField textoHora;
	private JButton botonAtras;
	private JButton botonReset;
	private JButton botonOk;

	public VistaModifcarClase() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	public void inicializarVista() {
		// begin-user-code
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelDatos.setLayout(new GridLayout(2, 2));
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(labelId);
		panelDatos.add(textoId);
		panelDatos.add(labelHora);
		panelDatos.add(textoHora);
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
				try {
					int id = Integer.valueOf(textoId.getText());
					if (id <= 0)
						throw new NumberFormatException();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
					LocalTime time = LocalTime.parse(textoHora.getText(), formatter);
					Controlador.getInstancia().accion(Evento.MODIFICAR_CLASE, new TClase(id, time, -1, -1, false));
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
				textoId.setText("");
				textoHora.setText("");
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

	public void actualizar(int evento, Object object) {
		// begin-user-code
		switch (evento) {
		case Evento.MODIFICAR_CLASE_OK:
			JOptionPane.showMessageDialog(null, "La Clase " + (Integer) object + " ha sido modificada correctamente");
			break;
		case Evento.MODIFICAR_CLASE_ERROR:
			JOptionPane.showMessageDialog(null, "La clase no existe o no se puede poner a esa hora", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
		// end-user-code
	}

	public void initComponents() {
		// begin-user-code
		panelPrincipal = new JPanel(new BorderLayout());
		panelBienvenida = new JPanel();
		panelDatos = new JPanel();
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Modificar Clase");
		labelId = new JLabel("ID de la clase:");
		labelHora = new JLabel("Hora:");
		textoHora = new JTextField(10);
		textoId = new JTextField(10);
		botonOk = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		// end-user-code
	}
}