/**
 * 
 */
package presentacion.matricula.altaMatricula;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import negocio.matricula.TMatricula;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;
import presentacion.matricula.Nivel;

public class VistaAltaMatricula extends JFrame implements Vista {
	
	private JButton botonOK;
	private JButton botonReset;
	private JButton botonAtras;
	private JButton botonReactivar;
	private JButton botonDarDeAlta;
	private JLabel labelBienvenida;
	private JLabel labelIdCliente;
	private JTextField textoIdCliente;
	private JLabel labelIdClase;
	private JTextField textoIdClase;
	private JLabel labelNivel;
	private Nivel[] valoresNivel = { Nivel.FACIL, Nivel.MEDIO, Nivel.DIFICIL };
	private JComboBox<Nivel> seleccionableNivel;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelInfo;
	private JPanel panelSur;
	private JList<TMatricula> matriculasDesactivadas;
	
	public VistaAltaMatricula() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	public void inicializarVista() {
		// begin-user-code
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelInfo.add(labelIdCliente);
		panelInfo.add(textoIdCliente);
		panelInfo.add(labelIdClase);
		panelInfo.add(textoIdClase);
		panelInfo.add(labelNivel);
		panelInfo.add(seleccionableNivel);
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(panelInfo);
		panelSur.add(botonOK);
		panelSur.add(botonReset);
		panelSur.add(botonAtras);
		panelSur.add(botonReactivar);
		panelPrincipal.add(panelBienvenida, BorderLayout.NORTH);
		panelPrincipal.add(panelDatos, BorderLayout.CENTER);
		panelPrincipal.add(panelSur, BorderLayout.SOUTH);

		getContentPane().add(panelPrincipal);
		setResizable(false);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		botonReactivar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Evento.LISTAR_MATRICULAS_NO_DISPONIBLES, null);
				dispose();
			}
		});
		botonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int idCliente = Integer.valueOf(textoIdCliente.getText());
					int idClase = Integer.valueOf(textoIdClase.getText());
					if(idCliente <= 0 || idClase <= 0) throw new NumberFormatException();
					Controlador.getInstancia().accion(Evento.ALTA_MATRICULA, new TMatricula(idCliente, idClase, seleccionableNivel.getSelectedItem().toString()));
				}
				catch(NumberFormatException ex) {
					textoIdClase.setText("");
					textoIdCliente.setText("");
					JOptionPane.showMessageDialog(null, "Introduce un número mayor que 0 para los Ids", "Mensaje de Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoIdClase.setText("");
				textoIdCliente.setText("");
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
		case Evento.ALTA_MATRICULA_OK:
			JOptionPane.showMessageDialog(null, "Matricula " + (Integer) object + " dada de alta.");
			break;
		case Evento.ALTA_MATRICULA_ERROR:
			JOptionPane.showMessageDialog(null, "La Matricula no ha podido darse de alta", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.LISTAR_MATRICULAS_NO_DISPONIBLES_OK:
			Vector<TMatricula> listaMatriculas = (Vector<TMatricula>) object;
			panelPrincipal.setLayout(new BorderLayout());
			panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
			panelBienvenida.add(labelBienvenida);

			matriculasDesactivadas = new JList<TMatricula>(listaMatriculas);
			JScrollPane scrollLista = new JScrollPane(matriculasDesactivadas);

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
					Controlador.getInstancia().accion(Evento.ALTA_MATRICULA,
							listaMatriculas.get(matriculasDesactivadas.getSelectedIndex()));
					FactoriaPresentacion.getInstancia().generaVistaAltaMatricula().inicializarVista();
					dispose();
				}
			});
			botonAtras.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					FactoriaPresentacion.getInstancia().generaVistaAltaMatricula().inicializarVista();
					dispose();
				}
			});
			break;
		case Evento.LISTAR_MATRICULAS_NO_DISPONIBLES_ERROR:
			JOptionPane.showMessageDialog(null, "No hay matriculas existentes que no esten disponibles",
					"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			FactoriaPresentacion.getInstancia().generaVistaAltaMatricula().inicializarVista();
			dispose();
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
		panelDatos = new JPanel(new GridLayout(1, 1));
		panelInfo = new JPanel(new GridLayout(3, 2));
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Alta de Matricula");
		labelIdClase = new JLabel("Id de la clase: ");
		labelIdCliente = new JLabel("Id del cliente: ");
		labelNivel = new JLabel("Nivel: ");
		textoIdClase = new JTextField(10);
		textoIdCliente = new JTextField(10);
		seleccionableNivel = new JComboBox<Nivel>(valoresNivel);
		botonOK = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		botonReactivar = new JButton("Reactivar una Matricula");
		botonDarDeAlta = new JButton("Dar De Alta");
		// end-user-code
	}
}