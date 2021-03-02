/**
 * 
 */
package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.factoria.FactoriaPresentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VistaMain extends JFrame implements Vista {

	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelModulos;
	private JButton botonAula;
	private JLabel labelBienvenida;
	private JButton botonClase;
	private JButton botonPersonal;
	private JButton botonCliente;
	private JButton botonMatricula;

	public static final String TITULO_APP = "Gimnasio LaKalle";

	public VistaMain() {
		super(TITULO_APP);
		initComponents();
		inicializarVista();
	}

	public void inicializarVista() {
		// begin-user-code
		panelPrincipal.setLayout(new BorderLayout());
		panelBienvenida.setLayout(new FlowLayout());
		panelModulos.setLayout(new GridLayout(2, 3));
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 22));
		panelBienvenida.add(labelBienvenida);
		panelModulos.add(botonAula);
		panelModulos.add(botonClase);
		panelModulos.add(botonPersonal);
		panelModulos.add(botonCliente);
		panelModulos.add(botonMatricula);
		panelPrincipal.add(panelBienvenida, BorderLayout.NORTH);
		panelPrincipal.add(panelModulos, BorderLayout.CENTER);

		getContentPane().add(panelPrincipal);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		botonAula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaAula();
				dispose();
			}
		});
		botonClase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaClase();
				dispose();
			}
		});
		botonPersonal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaPersonal();
				dispose();
			}
		});
		botonCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaCliente();
				dispose();
			}
		});
		botonMatricula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaMatricula();
				dispose();
			}
		});
		// end-user-code
	}

	@Override
	public void actualizar(int evento, Object lista) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initComponents() {
		// begin-user-code
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelModulos = new JPanel();
		labelBienvenida = new JLabel("Gestión Digital LaKalle");
		botonAula = new JButton("Aula");
		botonClase = new JButton("Clase");
		botonPersonal = new JButton("Personal");
		botonCliente = new JButton("Cliente");
		botonMatricula = new JButton("Matricula");
		// end-user-code
	}

	public static void main(String[] args) {
		FactoriaPresentacion.getInstancia().generaVistaMain();
	}
}