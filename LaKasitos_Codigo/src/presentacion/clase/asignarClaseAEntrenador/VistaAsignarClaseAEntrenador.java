package presentacion.clase.asignarClaseAEntrenador;

import java.awt.BorderLayout;
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

import negocio.clase.TClase;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

public class VistaAsignarClaseAEntrenador extends JFrame implements Vista {

	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;
	private JLabel labelBienvenida;
	private JLabel labelId;
	private JTextField textoId;
	private JLabel labelIdEntrenador;
	private JTextField textoIdEntrenador;
	private JButton botonAtras;
	private JButton botonReset;
	private JButton botonOK;

	public VistaAsignarClaseAEntrenador() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.ASIGNAR_CLASE_A_ENTRENADOR_OK:
			JOptionPane.showMessageDialog(null,
					"La clase se ha asignado correctamente al entrenador " + (Integer) object);
			break;
		case Evento.ASIGNAR_CLASE_A_ENTRENADOR_ERROR:
			JOptionPane.showMessageDialog(null, "No se ha podido asignar la clase al entrenador", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

	@Override
	public void initComponents() {
		panelPrincipal = new JPanel(new BorderLayout());
		panelBienvenida = new JPanel();
		panelDatos = new JPanel(new GridLayout(2, 2));
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Asignar Clase a Entrenador");
		labelId = new JLabel("ID de la clase:");
		labelIdEntrenador = new JLabel("ID del entrenador:");
		textoId = new JTextField(10);
		textoIdEntrenador = new JTextField(10);
		botonOK = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
	}

	@Override
	public void inicializarVista() {
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelDatos.setLayout(new GridLayout(2, 2));
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(labelId);
		panelDatos.add(textoId);
		panelDatos.add(labelId);
		panelDatos.add(textoId);
		panelDatos.add(labelIdEntrenador);
		panelDatos.add(textoIdEntrenador);
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
					int idEntrenador = Integer.valueOf(textoIdEntrenador.getText());
					if (id <= 0 || idEntrenador <= 0)
						throw new NumberFormatException();
					Controlador.getInstancia().accion(Evento.ASIGNAR_CLASE_A_ENTRENADOR,
							new TClase(id, null, -1, idEntrenador, false));
				} catch (NumberFormatException nEx) {
					JOptionPane.showMessageDialog(null, "Introduce un número positivo en ambos ID", "Mensaje de Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoId.setText("");
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
	}
}
