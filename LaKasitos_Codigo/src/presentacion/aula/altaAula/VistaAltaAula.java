/**

 * 
 */
package presentacion.aula.altaAula;

import java.awt.BorderLayout;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import negocio.aula.TAula;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

public class VistaAltaAula extends JFrame implements Vista {

	private JButton botonOK;
	private JButton botonReset;
	private JButton botonAtras;
	private JButton botonReactivar;
	private JButton botonDarDeAlta;
	private JLabel labelBienvenida;
	private JLabel labelAforo;
	private JTextField textoAforo;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelInfo;
	private JPanel panelSur;
	private JList<TAula> aulasDesactivadas;

	public VistaAltaAula() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void initComponents() {
		// begin-user-code
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelDatos = new JPanel();
		panelInfo = new JPanel();
		panelSur = new JPanel();
		labelBienvenida = new JLabel("Alta de Aula");
		labelAforo = new JLabel("Aforo máximo del aula:");
		textoAforo = new JTextField(10);
		botonOK = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
		botonReactivar = new JButton("Reactivar un Aula");
		botonDarDeAlta = new JButton("Dar De Alta");
		// end-user-code
	}

	@Override
	public void inicializarVista() {
		// begin-user-code
		labelAforo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelBienvenida.setLayout(new FlowLayout());
		panelInfo.setLayout(new FlowLayout());
		panelDatos.setLayout(new GridLayout(2, 1));
		panelSur.setLayout(new FlowLayout());
		panelInfo.add(labelAforo);
		panelInfo.add(textoAforo);
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(panelInfo);
		panelDatos.add(botonReactivar);
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

		botonReactivar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Evento.LISTAR_AULAS_NO_DISPONIBLES, null);
				dispose();
			}
		});
		botonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comprobacionSintacticaDatos())
					Controlador.getInstancia().accion(Evento.ALTA_AULA,
							new TAula(Integer.valueOf(textoAforo.getText())));
			}
		});
		botonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoAforo.setText("");
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
			if (Integer.valueOf(textoAforo.getText()) <= 0)
				throw new NumberFormatException();
			return true;
		} catch (NumberFormatException e) {
			textoAforo.setText("");
			JOptionPane.showMessageDialog(null, "Introduce un número mayor que 0", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.ALTA_AULA_OK:
			JOptionPane.showMessageDialog(null, "Aula " + (Integer) object + " dada de alta.");
			break;
		case Evento.ALTA_AULA_ERROR:
			JOptionPane.showMessageDialog(null, "El Aula no ha podido darse de alta", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.LISTAR_AULAS_NO_DISPONIBLES_OK:
			Vector<TAula> listaAulas = (Vector<TAula>) object;
			if (listaAulas.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay aulas existentes que no esten disponibles",
						"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
				FactoriaPresentacion.getInstancia().generaVistaAltaAula().inicializarVista();
				dispose();
			} else {
				panelPrincipal.setLayout(new BorderLayout());
				panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
				panelBienvenida.add(labelBienvenida);

				aulasDesactivadas = new JList<TAula>(listaAulas);
				JScrollPane scrollLista = new JScrollPane(aulasDesactivadas);

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
						Controlador.getInstancia().accion(Evento.ALTA_AULA,
								listaAulas.get(aulasDesactivadas.getSelectedIndex()));
						FactoriaPresentacion.getInstancia().generaVistaAltaAula().inicializarVista();
						dispose();
					}
				});
				botonAtras.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						FactoriaPresentacion.getInstancia().generaVistaAltaAula().inicializarVista();
						dispose();
					}
				});
			}
			break;
		default:
			break;
		}
	}
}