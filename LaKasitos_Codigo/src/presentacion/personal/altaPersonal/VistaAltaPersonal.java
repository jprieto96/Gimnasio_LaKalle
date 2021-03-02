/**
 * 
 */
package presentacion.personal.altaPersonal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import negocio.aula.TAula;
import negocio.personal.TEntrenador;
import negocio.personal.TMantenimiento;
import negocio.personal.TPersonal;
import presentacion.Vista;
import presentacion.VistaMain;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.factoria.FactoriaPresentacion;
import utils.ValidarDNI;

@SuppressWarnings("serial")
public class VistaAltaPersonal extends JFrame implements Vista {

	private JButton botonOK;
	private JButton botonReset;
	private JButton botonAtras;
	private JButton botonReactivar;
	private JButton botonDarDeAlta;
	private JLabel labelBienvenida;
	private JLabel labelNombre;
	private JLabel labelDni;
	private JLabel labelTelefono;
	private JLabel labelCuentaBancaria;
	private JLabel labelSalario;
	private JLabel labelClasesDiaEntrenador;
	private JLabel labelTurnoMantenimiento;
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
	private JPanel panelInfo;
	private JPanel panelInfoAdicional;
	private JRadioButton entrenador;
	private JRadioButton mantenimiento;
	private ButtonGroup grupoBotones;
	private JList<TPersonal> personalNoDisponible;

	public VistaAltaPersonal() {
		super(VistaMain.TITULO_APP);
		initComponents();
	}

	@Override
	public void initComponents() {
		panelPrincipal = new JPanel();
		panelBienvenida = new JPanel();
		panelDatos = new JPanel();
		panelSur = new JPanel();
		panelInfo = new JPanel();
		entrenador = new JRadioButton("Entrenador", true);
		mantenimiento = new JRadioButton("Mantenimiento", false);
		grupoBotones = new ButtonGroup();
		panelInfoAdicional = new JPanel();
		labelBienvenida = new JLabel("Alta de Personal");
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
		botonReactivar = new JButton("Reactivar Personal");
		botonDarDeAlta = new JButton("Dar De Alta");
	}

	@Override
	public void inicializarVista() {
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelBienvenida.setLayout(new FlowLayout());
		panelInfo.setLayout(new GridLayout(6, 2));
		panelInfoAdicional.setLayout(new GridLayout(3, 1));
		panelDatos.setLayout(new GridLayout(2, 1));
		panelSur.setLayout(new FlowLayout());
		labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		panelBienvenida.add(labelBienvenida);
		panelInfo.add(labelNombre);
		panelInfo.add(textoNombre);
		panelInfo.add(labelDni);
		panelInfo.add(textoDni);
		panelInfo.add(labelTelefono);
		panelInfo.add(textoTelefono);
		panelInfo.add(labelCuentaBancaria);
		panelInfo.add(textoCuentaBancaria);
		panelInfo.add(labelSalario);
		panelInfo.add(textoSalario);
		panelInfoAdicional.add(entrenador);
		panelInfoAdicional.add(mantenimiento);
		grupoBotones.add(entrenador);
		grupoBotones.add(mantenimiento);

		if (entrenador.isSelected())
			mostrarDatosEntrenador();
		else
			mostrarDatosMantenimiento();

		panelInfoAdicional.add(botonReactivar);
		panelDatos.add(panelInfo);
		panelDatos.add(panelInfoAdicional);
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
		eventoReactivarPersonal();
		eventosBotonesPanelSur();
	}

	private void eventoReactivarPersonal() {
		botonReactivar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Evento.LISTAR_PERSONAL_NO_DISPONIBLE, null);
				dispose();
			}
		});
	}

	private void eventosBotonesPanelSur() {
		botonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
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
						evento = Evento.ALTA_ENTRENADOR;
						personal = new TEntrenador(textoNombre.getText(), textoDni.getText(),
								textoCuentaBancaria.getText(), textoTelefono.getText(), salario, clasesDiaEntrenador,
								true);
					} else {
						evento = Evento.ALTA_MANTENIMIENTO;
						personal = new TMantenimiento(textoNombre.getText(), textoDni.getText(),
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
		panelInfo.remove(labelTurnoMantenimiento);
		panelInfo.remove(textoTurnoMantenimiento);
		panelInfo.add(labelClasesDiaEntrenador);
		panelInfo.add(textoClasesDiaentrenador);
		panelInfo.revalidate();
		panelInfo.repaint();
	}

	private void mostrarDatosMantenimiento() {
		panelInfo.remove(labelClasesDiaEntrenador);
		panelInfo.remove(textoClasesDiaentrenador);
		panelInfo.add(labelTurnoMantenimiento);
		panelInfo.add(textoTurnoMantenimiento);
		panelInfo.revalidate();
		panelInfo.repaint();
	}

	@Override
	public void actualizar(int evento, Object object) {
		switch (evento) {
		case Evento.ALTA_ENTRENADOR_OK:
			JOptionPane.showMessageDialog(null, "Entrenador " + (Integer) object + " dado de alta.");
			break;
		case Evento.ALTA_ENTRENADOR_ERROR:
			JOptionPane.showMessageDialog(null, "El Entrenador no ha podido darse de alta", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.ALTA_MANTENIMIENTO_OK:
			JOptionPane.showMessageDialog(null, "Personal de Mantenimiento " + (Integer) object + " dado de alta.");
			break;
		case Evento.ALTA_MANTENIMIENTO_ERROR:
			JOptionPane.showMessageDialog(null, "El Personal de Mantenimiento no ha podido darse de alta",
					"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			break;
		case Evento.LISTAR_PERSONAL_NO_DISPONIBLE_OK:
			Vector<TPersonal> listaPersonal = (Vector<TPersonal>) object;
			panelPrincipal.setLayout(new BorderLayout());
			panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			labelBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
			panelBienvenida.add(labelBienvenida);

			personalNoDisponible = new JList<TPersonal>(listaPersonal);
			JScrollPane scrollLista = new JScrollPane(personalNoDisponible);

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
					if(listaPersonal.get(personalNoDisponible.getSelectedIndex()) instanceof TEntrenador) {
						Controlador.getInstancia().accion(Evento.ALTA_ENTRENADOR,
								listaPersonal.get(personalNoDisponible.getSelectedIndex()));
					}
					else {
						Controlador.getInstancia().accion(Evento.ALTA_MANTENIMIENTO,
								listaPersonal.get(personalNoDisponible.getSelectedIndex()));
					}
					FactoriaPresentacion.getInstancia().generaVistaAltaPersonal().inicializarVista();
					dispose();
				}
			});
			botonAtras.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					FactoriaPresentacion.getInstancia().generaVistaAltaPersonal().inicializarVista();
					dispose();
				}
			});
			break;
		case Evento.LISTAR_PERSONAL_NO_DISPONIBLE_ERROR:
			JOptionPane.showMessageDialog(null, "No hay personal existente que no este disponible", "Mensaje de Error",
					JOptionPane.ERROR_MESSAGE);
			FactoriaPresentacion.getInstancia().generaVistaAltaPersonal().inicializarVista();
			dispose();
			break;
		default:
			break;
		}
	}
}