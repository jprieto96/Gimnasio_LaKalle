/**
 * 
 */
package presentacion.clase.mostrarClasePorEntrenador;

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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class VistaMostrarClasePorEntrenador extends JFrame implements Vista {

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

	public VistaMostrarClasePorEntrenador() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	public void inicializarVista() {
		// begin-user-code
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
				try {
					int id = Integer.valueOf(textoId.getText());
					if (id <= 0)
						throw new NumberFormatException();
					Controlador.getInstancia().accion(Evento.MOSTRAR_CLASE_POR_ENTRENADOR, new TClase(-1, id));
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
				FactoriaPresentacion.getInstancia().generaVistaClase();
				dispose();
			}
		});
		// end-user-code
	}

	public void actualizar(int evento, Object object) {
		// begin-user-code
		switch (evento) {
		case Evento.MOSTRAR_CLASE_POR_ENTRENADOR_OK:
			List<TClase> clases = (List<TClase>) object;
			String datosClases = "";
			String estado;
			for (TClase c : clases) {
				estado = (c.getEstado()) ? "Disponible" : "No Disponible";
				datosClases += "CLASE " + c.getId() + ",   Hora: " + c.getHora() + ",   ID Entrenador: "
						+ c.getIdEntrenador() + ",   ID Aula: " + c.getIdAula() + ",   Estado: " + estado + '\n';
			}
			JOptionPane.showMessageDialog(null, datosClases, "Clases por Entrenador", JOptionPane.INFORMATION_MESSAGE);
			break;
		case Evento.MOSTRAR_CLASE_POR_ENTRENADOR_ERROR:
			JOptionPane.showMessageDialog(null, "No hay clases impartidas por el entrenador " + (Integer) object,
					"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
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
		panelDatos = new JPanel(new GridLayout(1, 2));
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Mostrar datos de Clase por Entrenador");
		labelId = new JLabel("Introduce ID del Entrenador: ");
		textoId = new JTextField(10);
		botonOk = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		// end-user-code
	}
}