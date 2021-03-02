/**
 * 
 */
package presentacion.personal.modificarPersonal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import negocio.personal.TEntrenador;
import negocio.personal.TMantenimiento;
import negocio.personal.TPersonal;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;
import utils.ValidarDNI;

public class VistaModificarPersonal extends JFrame implements Vista {

	private JButton botonOK;
	private JButton botonReset;
	private JButton botonAtras;
	private JLabel labelBienvenida;
	private JLabel labelId;
	private JLabel labelNombre;
	private JLabel labelDni;
	private JLabel labelTelefono;
	private JLabel labelCuentaBancaria;
	private JLabel labelSalario;
	private JLabel labelClasesDiaEntrenador;
	private JLabel labelTurnoMantenimiento;
	private JTextField textoId;
	private JTextField textoNombre;
	private JTextField textoDni;
	private JTextField textoTelefono;
	private JTextField textoCuentaBancaria;
	private JTextField textoSalario;
	private JTextField textoClasesDiaentrenador;
	private JTextField textoTurnoMantenimiento;
	private JPanel panelPrincipal;
	private JPanel panelBienvenida;
	private JPanel panelDatos;
	private JPanel panelSur;
	private JRadioButton entrenador;
	private JRadioButton mantenimiento;
	private ButtonGroup grupoBotones;

	public VistaModificarPersonal() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void initComponents() {
		panelPrincipal = new JPanel(new BorderLayout());
		panelBienvenida = new JPanel();
		panelDatos = new JPanel(new GridLayout(8, 2));
		panelSur = new JPanel();
		entrenador = new JRadioButton("Entrenador", true);
		mantenimiento = new JRadioButton("Mantenimiento", false);
		grupoBotones = new ButtonGroup();
		labelBienvenida = new JLabel("Modificar Personal");
		labelId = new JLabel("ID del personal:");
		textoId = new JTextField(10);
		labelNombre = new JLabel("Nombre:");
		textoNombre = new JTextField(10);
		labelDni = new JLabel("DNI:");
		textoDni = new JTextField(10);
		labelCuentaBancaria = new JLabel("Cuenta Bancaria:");
		textoCuentaBancaria = new JTextField(10);
		labelTelefono = new JLabel("Teléfono:");
		textoTelefono = new JTextField(10);
		labelSalario = new JLabel("Salario:");
		textoSalario = new JTextField(10);
		labelClasesDiaEntrenador = new JLabel("Clases/dia maximas:");
		textoClasesDiaentrenador = new JTextField(10);
		labelTurnoMantenimiento = new JLabel("Turno:");
		textoTurnoMantenimiento = new JTextField(10);
		botonOK = new JButton("Enviar");
		botonReset = new JButton("Reset");
		botonAtras = new JButton("Atrás");
	}

	@Override
	public void inicializarVista() {
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelSur.setLayout(new FlowLayout());
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelDatos.add(labelId);
		panelDatos.add(textoId);
		panelDatos.add(labelNombre);
		panelDatos.add(textoNombre);
		panelDatos.add(labelDni);
		panelDatos.add(textoDni);
		panelDatos.add(labelTelefono);
		panelDatos.add(textoTelefono);
		panelDatos.add(labelCuentaBancaria);
		panelDatos.add(textoCuentaBancaria);
		panelDatos.add(labelSalario);
		panelDatos.add(textoSalario);
		panelDatos.add(entrenador);
		panelDatos.add(mantenimiento);

		if (entrenador.isSelected())
			mostrarDatosEntrenador();
		else
			mostrarDatosMantenimiento();

		grupoBotones.add(entrenador);
		grupoBotones.add(mantenimiento);

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

		eventosTipoDePersonal();
		eventosBotonesPanelSur();
	}

	private void eventosBotonesPanelSur() {
		botonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.valueOf(textoId.getText());
					if (id <= 0)
						throw new NumberFormatException();
					if (!ValidarDNI.validar(textoDni.getText()))
						throw new IllegalArgumentException();
					if (textoTelefono.getText().length() != 9)
						throw new IllegalArgumentException();
					Integer.valueOf(textoTelefono.getText());
					int salario = Integer.valueOf(textoSalario.getText());
					int clasesDiaEntrenador = 0;
					if (entrenador.isSelected())
						clasesDiaEntrenador = Integer.valueOf(textoClasesDiaentrenador.getText());

					TPersonal personal;
					int evento;
					if (entrenador.isSelected()) {
						evento = Evento.MODIFICAR_ENTRENADOR;
						personal = new TEntrenador(id, textoNombre.getText(), textoDni.getText(),
								textoCuentaBancaria.getText(), textoTelefono.getText(), salario, clasesDiaEntrenador,
								true);
					} else {
						evento = Evento.MODIFICAR_MANTENIMIENTO;
						personal = new TMantenimiento(id, textoNombre.getText(), textoDni.getText(),
								textoCuentaBancaria.getText(), textoTelefono.getText(), salario,
								textoTurnoMantenimiento.getText(), true);
					}
					Controlador.getInstancia().accion(evento, personal);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error de formato en alguno de los campos introducidos",
							"Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoNombre.setText("");
				textoDni.setText("");
				textoCuentaBancaria.setText("");
				textoTelefono.setText("");
				textoSalario.setText("");
			}
		});
		botonAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstancia().generaVistaPersonal();
				dispose();
			}
		});
	}

	private void eventosTipoDePersonal() {
		entrenador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarDatosEntrenador();
			}
		});
		mantenimiento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarDatosMantenimiento();
			}
		});
	}

	private void mostrarDatosEntrenador() {
		panelDatos.remove(labelTurnoMantenimiento);
		panelDatos.remove(textoTurnoMantenimiento);
		panelDatos.add(labelClasesDiaEntrenador);
		panelDatos.add(textoClasesDiaentrenador);
		panelDatos.revalidate();
		panelDatos.repaint();
	}

	private void mostrarDatosMantenimiento() {
		panelDatos.remove(labelClasesDiaEntrenador);
		panelDatos.remove(textoClasesDiaentrenador);
		panelDatos.add(labelTurnoMantenimiento);
		panelDatos.add(textoTurnoMantenimiento);
		panelDatos.revalidate();
		panelDatos.repaint();
	}

	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.MODIFICAR_ENTRENADOR_OK:
			JOptionPane.showMessageDialog(null, "Entrenador modificado correctamente.");
			break;
		case Evento.MODIFICAR_ENTRENADOR_ERROR:
			JOptionPane.showMessageDialog(null, "El Entrenador no ha podido ser modificado", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.MODIFICAR_MANTENIMIENTO_OK:
			JOptionPane.showMessageDialog(null, "Personal de Mantenimiento modificado correctamente.");
			break;
		case Evento.MODIFICAR_MANTENIMIENTO_ERROR:
			JOptionPane.showMessageDialog(null, "El Personal de Mantenimiento no ha podido ser modificado",
					"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}