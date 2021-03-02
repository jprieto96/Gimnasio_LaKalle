/**
 * 
 */
package presentacion.personal.bajaPersonal;

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
import negocio.aula.TAula;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

public class VistaBajaPersonal extends JFrame implements Vista {

	private JButton botonOK;
	private JButton botonReset;
	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JLabel labelId;
	private JTextField textoId;
	private JTextField textoID;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelInfo;
	private JPanel panelSur;

	public VistaBajaPersonal() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void initComponents() {
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelDatos = new JPanel();
		panelInfo = new JPanel();
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Baja de Personal");
		labelId = new JLabel("ID del Personal: ");
		textoID = new JTextField(10);
		botonOK = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
	}

	@Override
	public void inicializarVista() {
		labelId.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelBienvenida.setLayout(new FlowLayout());
		panelInfo.setLayout(new FlowLayout());
		panelDatos.setLayout(new FlowLayout());
		panelSur.setLayout(new FlowLayout());
		panelInfo.add(labelId);
		panelInfo.add(textoID);
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
				try {
					int id = Integer.valueOf(textoID.getText());
					if (id <= 0)
						throw new NumberFormatException();
					Controlador.getInstancia().accion(Evento.BAJA_PERSONAL, new Integer(id));
				} catch (NumberFormatException ex) {
					textoID.setText("");
					JOptionPane.showMessageDialog(null, "Introduce un número mayor que 0", "Mensaje de Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoID.setText("");
			}
		});
		botonAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaPersonal();
				dispose();
			}
		});
	}

	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.BAJA_PERSONAL_OK:
			JOptionPane.showMessageDialog(null, "Personal con ID " + (Integer) object + " dado de baja correctamente.");
			break;
		case Evento.BAJA_PERSONAL_ERROR:
			JOptionPane.showMessageDialog(null,
					"El Personal seleccionado no existe o es un entrenador que esta impartiendo clases",
					"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}