/**
 * 
 */
package presentacion.matricula.mostrarMatriculasPorClase;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

public class VistaMostrarMatriculasPorClase extends JFrame implements Vista {
	
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
	
	public VistaMostrarMatriculasPorClase() {
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
					Controlador.getInstancia().accion(Evento.MOSTRAR_MATRICULA_POR_ID_CLASE, new Integer(id));
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
				FactoriaPresentacion.getInstancia().generaVistaMatricula();
				dispose();
			}
		});
		// end-user-code
	}

	public void actualizar(int evento, Object object) {
		// begin-user-code
		switch (evento) {
		case Evento.MOSTRAR_MATRICULA_POR_ID_CLASE_OK:
			List<TMatricula> listaMatriculas = (List<TMatricula>) object;
			String datosMatriculas = "";
			for (TMatricula m : listaMatriculas) {
				datosMatriculas += m.toString() + '\n';
			}
			JOptionPane.showMessageDialog(null, datosMatriculas, "Matriculas Por Clase", JOptionPane.INFORMATION_MESSAGE);
			break;
		case Evento.MOSTRAR_MATRICULA_POR_ID_CLASE_ERROR:
			JOptionPane.showMessageDialog(null, "No hay matriculas en la clase " + (Integer) object,
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
		labelBienvenida = new JLabel("Mostrar Matriculas por Clase");
		labelId = new JLabel("Introduce ID de la clase: ");
		textoId = new JTextField(10);
		botonOk = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		// end-user-code
	}
}