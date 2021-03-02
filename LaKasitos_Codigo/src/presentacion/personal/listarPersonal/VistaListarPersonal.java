/**
 * 
 */
package presentacion.personal.listarPersonal;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import negocio.personal.TEntrenador;
import negocio.personal.TMantenimiento;
import negocio.personal.TPersonal;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

public class VistaListarPersonal extends JFrame implements Vista {

	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelInfo;
	private JPanel panelSur;
	private JScrollPane scrollPane;
	private JLabel labelPersonal;

	public VistaListarPersonal() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void initComponents() {
		botonAtras = new JButton("Atrás");
		labelBienvenida = new JLabel("Personal del gimnasio");
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelSur = new JPanel();
		panelInfo = new JPanel();
	}

	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.LISTAR_PERSONAL_OK:
			List<TPersonal> lista = (List<TPersonal>) object;
			panelPrincipal.setLayout(new BorderLayout());
			labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
			panelBienvenida.add(labelBienvenida);
			panelSur.add(botonAtras);
			panelInfo.setLayout(new GridLayout(lista.size(), 1));

			for (TPersonal p : lista) {
				try {
					TEntrenador entrenador = (TEntrenador) p;
					labelPersonal = new JLabel(entrenador.toString());
					labelPersonal.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
					labelPersonal.setFont(new Font("Verdana", Font.PLAIN, 18));
					panelInfo.add(labelPersonal);
				} catch (ClassCastException ex) {
					System.err.println(ex.getMessage());
				}

				try {
					TMantenimiento mantenimiento = (TMantenimiento) p;
					labelPersonal = new JLabel(mantenimiento.toString());
					labelPersonal.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
					labelPersonal.setFont(new Font("Verdana", Font.PLAIN, 18));
					panelInfo.add(labelPersonal);
				} catch (ClassCastException ex) {
					System.err.println(ex.getMessage());
				}
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
					FactoriaPresentacion.getInstancia().generaVistaPersonal();
					dispose();
				}
			});
			break;
		case Evento.LISTAR_PERSONAL_ERROR:
			JOptionPane.showMessageDialog(null, "No hay Personal actualmente", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			FactoriaPresentacion.getInstancia().generaVistaPersonal();
			dispose();
			break;
		default:
			break;
		}
	}

	@Override
	public void inicializarVista() {
		// TODO Auto-generated method stub

	}

}