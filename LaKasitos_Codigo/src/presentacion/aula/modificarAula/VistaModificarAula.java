/**
 * 
 */
package presentacion.aula.modificarAula;

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

import negocio.aula.TAula;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

public class VistaModificarAula extends JFrame implements Vista {

	private JButton botonOK;
	private JButton botonReset;
	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JLabel labelId;
	private JLabel labelAforo;
	private JTextField textoAforo;
	private JTextField textoId;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelInfoId;
	private JPanel panelInfoAforo;
	private JPanel panelSur;

	public VistaModificarAula() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void initComponents() {
		// begin-user-code
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelDatos = new JPanel();
		panelInfoId = new JPanel();
		panelInfoAforo = new JPanel();
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Modificar Aula");
		labelId = new JLabel("ID del aula:");
		labelAforo = new JLabel("Aforo máximo del aula:");
		textoAforo = new JTextField(10);
		textoId = new JTextField(10);
		botonOK = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		// end-user-code
	}

	@Override
	public void inicializarVista() {
		// begin-user-code
		labelAforo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelBienvenida.setLayout(new FlowLayout());
		panelInfoId.setLayout(new FlowLayout());
		panelInfoAforo.setLayout(new FlowLayout());
		panelDatos.setLayout(new GridLayout(2, 1));
		panelSur.setLayout(new FlowLayout());
		panelInfoId.add(labelId);
		panelInfoId.add(textoId);
		panelInfoAforo.add(labelAforo);
		panelInfoAforo.add(textoAforo);
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(panelInfoId);
		panelDatos.add(panelInfoAforo);
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
					Controlador.getInstancia().accion(Evento.MODIFICAR_AULA,
							new TAula(Integer.valueOf(textoId.getText()), Integer.valueOf(textoAforo.getText())));
				}
			}
		});
		botonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoAforo.setText("");
				textoId.setText("");
			}
		});
		botonAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaAula();
				dispose();
			}
		});
		// end-user-code
	}

	private boolean comprobacionSintacticaDatos() {
		try {
			Integer.valueOf(textoId.getText());
			Integer.valueOf(textoAforo.getText());
			return true;
		} catch (NumberFormatException e) {
			textoAforo.setText("");
			textoId.setText("");
			JOptionPane.showMessageDialog(null, "Error en el formato de los datos introducidos", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.MODIFICAR_AULA_OK:
			JOptionPane.showMessageDialog(null, "El aula " + (Integer) object + " ha sido modificada correctamente");
			break;
		case Evento.MODIFICAR_AULA_ERROR:
			JOptionPane.showMessageDialog(null, "El aula no existe", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}