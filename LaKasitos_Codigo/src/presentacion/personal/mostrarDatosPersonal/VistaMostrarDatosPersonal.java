/**
 * 
 */
package presentacion.personal.mostrarDatosPersonal;

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
import negocio.personal.TEntrenador;
import negocio.personal.TMantenimiento;
import negocio.personal.TPersonal;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

public class VistaMostrarDatosPersonal extends JFrame implements Vista {

	private JButton botonOK;
	private JButton botonReset;
	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JLabel labelId;
	private JTextField textoId;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;

	public VistaMostrarDatosPersonal() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.MOSTRAR_PERSONAL_POR_ID_OK:
			TPersonal p = (TPersonal) object;
			String textoAMostrar = "";
			try {
				TEntrenador entrenador = (TEntrenador) p;
				textoAMostrar = entrenador.toString();
			} catch (ClassCastException ex) {
				System.err.println(ex.getMessage());
			}

			try {
				TMantenimiento mantenimiento = (TMantenimiento) p;
				textoAMostrar = mantenimiento.toString();
			} catch (ClassCastException ex) {
				System.err.println(ex.getMessage());
			}

			JOptionPane.showMessageDialog(null, textoAMostrar);
			break;
		case Evento.MOSTRAR_PERSONAL_POR_ID_ERROR:
			JOptionPane.showMessageDialog(null, "No existe Personal con ese ID", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

	@Override
	public void initComponents() {
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelDatos = new JPanel();
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Mostrar datos de Personal por ID");
		labelId = new JLabel("Introduce ID del Personal: ");
		textoId = new JTextField(10);
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
		panelDatos.setLayout(new FlowLayout());
		panelDatos.add(labelId);
		panelDatos.add(textoId);
		panelSur.setLayout(new FlowLayout());
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
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
					int id = Integer.valueOf(textoId.getText());
					if (id <= 0)
						throw new NumberFormatException();
					Controlador.getInstancia().accion(Evento.MOSTRAR_PERSONAL_POR_ID, new TPersonal(id));
				} catch (NumberFormatException ex) {
					textoId.setText("");
					JOptionPane.showMessageDialog(null, "Introduce un número positivo", "Mensaje de Error",
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
				FactoriaPresentacion.getInstancia().generaVistaPersonal();
				dispose();
			}
		});
	}

}