/**
 * 
 */
package presentacion.clase.bajaClase;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VistaBajaClase extends JFrame implements Vista {

	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;
	private JLabel labelBienvenida;
	private JLabel labelId;
	private JTextField textoId;
	private JButton botonAtras;
	private JButton botonReset;
	private JButton botonOk;

	public VistaBajaClase() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	public void inicializarVista() {
		// begin-user-code
		labelId.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);

		panelDatos.add(labelId);
		panelDatos.add(textoId);

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
					Controlador.getInstancia().accion(Evento.BAJA_CLASE, new TClase(id));
				} catch (NumberFormatException ex) {
					textoId.setText("");
					JOptionPane.showMessageDialog(null, "Introduce un número mayor que 0", "Mensaje de Error",
							JOptionPane.ERROR_MESSAGE);
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
				FactoriaPresentacion.getInstancia().generaVistaClase();
				dispose();
			}
		});
		// end-user-code
	}

	public void actualizar(int evento, Object object) {
		// begin-user-code
		switch (evento) {
		case Evento.BAJA_CLASE_OK:
			JOptionPane.showMessageDialog(null, "Clase " + (Integer) object + " dada de baja correctamente.");
			break;
		case Evento.BAJA_CLASE_ERROR:
			JOptionPane.showMessageDialog(null, "La clase indicada no existe o hay matriculas activas en esta clase", "Mensaje de Error",
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
		labelBienvenida = new JLabel("Baja de Clase");
		labelId = new JLabel("ID de la Clase: ");
		textoId = new JTextField(10);
		botonOk = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		// end-user-code
	}
}