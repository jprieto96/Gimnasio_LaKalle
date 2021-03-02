/**
 * 
 */
package presentacion.matricula.listarMatriculas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import negocio.matricula.TMatricula;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

public class VistaListarMatriculas extends JFrame implements Vista {
	
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelInfo;
	private JPanel panelSur;
	private JLabel labelBienvenida;
	private JLabel labelClase;
	private JScrollPane scrollPane;
	private JButton botonAtras;
	
	public VistaListarMatriculas() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	public void inicializarVista() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void actualizar(int evento, Object object) {
		// begin-user-code
		switch (evento) {
		case Evento.LISTAR_MATRICULAS_OK:
			Vector<TMatricula> listaMatriculas = (Vector<TMatricula>) object;
			labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
			panelBienvenida.add(labelBienvenida);
			panelSur.add(botonAtras);
			panelInfo.setLayout(new GridLayout(listaMatriculas.size(), 1));

			for (TMatricula m : listaMatriculas) {
				labelClase = new JLabel(m.toString());
				labelClase.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
				labelClase.setFont(new Font("Verdana", Font.PLAIN, 18));
				panelInfo.add(labelClase);
			}

			scrollPane = new JScrollPane(panelInfo);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			panelPrincipal.add(panelBienvenida, BorderLayout.NORTH);
			panelPrincipal.add(scrollPane, BorderLayout.CENTER);
			panelPrincipal.add(panelSur, BorderLayout.SOUTH);

			getContentPane().add(panelPrincipal);
			setResizable(false);
			pack();
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);

			botonAtras.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					FactoriaPresentacion.getInstancia().generaVistaMatricula();
					dispose();
				}
			});
			break;
		case Evento.LISTAR_MATRICULAS_ERROR:
			JOptionPane.showMessageDialog(null, "No hay Matriculas actualmente", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			FactoriaPresentacion.getInstancia().generaVistaMatricula();
			dispose();
			break;
		default:
			break;
		}
		// end-user-code
	}

	public void initComponents() {
		// begin-user-code
		botonAtras = new JButton("Atrás");
		labelBienvenida = new JLabel("Matriculas");
		panelPrincipal = new JPanel(new BorderLayout());
		panelBienvenida = new JPanel();
		panelSur = new JPanel();
		panelInfo = new JPanel();
		// end-user-code
	}
}