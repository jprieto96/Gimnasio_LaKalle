/**
 * 
 */
package presentacion.matricula.modificarMatricula;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.matricula.TMatricula;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;
import presentacion.matricula.Nivel;

public class VistaModificarMatricula extends JFrame implements Vista {
	
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;
	private JLabel labelBienvenida;
	private JLabel labelId;
	private JTextField textoId;
	private JLabel labelNivel;
	private Nivel[] valoresNivel = { Nivel.FACIL, Nivel.MEDIO, Nivel.DIFICIL };
	private JComboBox<Nivel> seleccionableNivel;
	private JButton botonAtras;
	private JButton botonReset;
	private JButton botonOk;

	public VistaModificarMatricula() {
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
		panelDatos.add(labelNivel);
		panelDatos.add(seleccionableNivel);
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
					Controlador.getInstancia().accion(Evento.MODIFICAR_MATRICULA, new TMatricula(id, -1, -1, seleccionableNivel.getSelectedItem().toString(), false));
				} catch (NumberFormatException nEx) {
					JOptionPane.showMessageDialog(null, "Introduce un número positivo en el ID", "Mensaje de Error",
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
				FactoriaPresentacion.getInstancia().generaVistaMatricula();
				dispose();
			}
		});
		// end-user-code
	}

	public void actualizar(int evento, Object object) {
		// begin-user-code
		switch (evento) {
		case Evento.MODIFICAR_MATRICULA_OK:
			JOptionPane.showMessageDialog(null, "Matricula " + (Integer) object + " ha sido modificada correctamente");
			break;
		case Evento.MODIFICAR_MATRICULA_ERROR:
			JOptionPane.showMessageDialog(null, "La Matricula no existe", "Mensaje de Error",
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
		labelBienvenida = new JLabel("Modificar Matricula");
		labelId = new JLabel("ID Matricula:");
		labelNivel = new JLabel("Nivel: ");
		seleccionableNivel = new JComboBox<Nivel>(valoresNivel);
		textoId = new JTextField(10);
		botonOk = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		// end-user-code
	}
	
}