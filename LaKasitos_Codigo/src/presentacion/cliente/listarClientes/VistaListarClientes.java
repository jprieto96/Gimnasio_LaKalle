/**
 * 
 */
package presentacion.cliente.listarClientes;

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

import negocio.cliente.TCliente;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;

public class VistaListarClientes extends JFrame implements Vista {

	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelInfo;
	private JPanel panelSur;
	private JScrollPane scrollPane;
	private JLabel labelCliente;

	public VistaListarClientes() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void initComponents() {
		// begin-user-code
		botonAtras = new JButton("Atrás");
		labelBienvenida = new JLabel("Clientes del gimnasio");
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelSur = new JPanel();
		panelInfo = new JPanel();
		// end-user-code
	}

	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.LISTAR_CLIENTES_OK:
			Vector<TCliente> listaClientes = (Vector<TCliente>) object;
			if (listaClientes.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay Clientes actualmente", "Mensaje de Error",
						JOptionPane.ERROR_MESSAGE);
				FactoriaPresentacion.getInstancia().generaVistaCliente();
				dispose();
			} else {
				panelPrincipal.setLayout(new BorderLayout());
				labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
				panelBienvenida.add(labelBienvenida);
				panelSur.add(botonAtras);
				panelInfo.setLayout(new GridLayout(listaClientes.size(), 1));

				for (TCliente cliente : listaClientes) {
					labelCliente = new JLabel(cliente.toString());
					labelCliente.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
					labelCliente.setFont(new Font("Verdana", Font.PLAIN, 18));
					panelInfo.add(labelCliente);
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
						FactoriaPresentacion.getInstancia().generaVistaCliente();
						dispose();
					}
				});
			}
			break;
		case Evento.LISTAR_CLIENTES_ERROR:
			JOptionPane.showMessageDialog(null, "No hay clientes actualmente", "Mensaje de Error",
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
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

}