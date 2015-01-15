package iu;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import excepciones.LogicaExcepcion;

import java.awt.event.ActionListener;

import logica.Controlador;


public class Emergencias {

	private JFrame frame;
	private final Action action = new SwingAction();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	private final Action action_4 = new SwingAction_4();
	private final Action action_5 = new SwingAction_5();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Emergencias window = new Emergencias();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Emergencias() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 640, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnOperador = new JMenu("Operador");
		menuBar.add(mnOperador);
		
		JMenuItem mntmAltaPaciente = new JMenuItem("Alta Paciente");
		mntmAltaPaciente.setAction(action_5);
		mnOperador.add(mntmAltaPaciente);
		
		JMenuItem mntmNuevaEmergencia = new JMenuItem("Nueva emergencia");
		mntmNuevaEmergencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Emergencia dialog = new Emergencia();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					//
					dialog.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		mnOperador.add(mntmNuevaEmergencia);
		
		JMenu mnConductor = new JMenu("Conductor");
		menuBar.add(mnConductor);
		
		JMenuItem mntmCambiarDisponibilidad = new JMenuItem("Cambiar disponibilidad");
		mntmCambiarDisponibilidad.setAction(action_3);
		mnConductor.add(mntmCambiarDisponibilidad);
		
		JMenuItem mntmCambiarCoordenadas = new JMenuItem("Cambiar coordenadas");
		mntmCambiarCoordenadas.setAction(action_4);
		mnConductor.add(mntmCambiarCoordenadas);
		
		JMenu mnPersonalComisin = new JMenu("Personal Comisi\u00F3n");
		menuBar.add(mnPersonalComisin);
		
		JMenuItem mntmListadoEspecialidades = new JMenuItem("Listado especialidades");
		mntmListadoEspecialidades.setAction(action_2);
		mnPersonalComisin.add(mntmListadoEspecialidades);
		
		JMenuItem mntmListadoPacientes = new JMenuItem("Listado pacientes");
		mntmListadoPacientes.setAction(action);
		mnPersonalComisin.add(mntmListadoPacientes);
		
		JMenuItem mntmListadoLlamadasEmergencia = new JMenuItem("Listado llamadas emergencia");
		mntmListadoLlamadasEmergencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoEmergencias lem = new ListadoEmergencias();
				lem.cargaTabla();
				lem.setVisible(true);
			}
		});
		mnPersonalComisin.add(mntmListadoLlamadasEmergencia);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Saliendo");
					Controlador.dameControlador().exit();
					System.exit(0);
				} catch (LogicaExcepcion e) {e.printStackTrace();}
			}
		});
		mnOpciones.add(mntmSalir);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Listado pacientes");
			putValue(SHORT_DESCRIPTION, "Ventana que lista los pacientes de la Base de Datos");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				ListadoPacientes dialog = new ListadoPacientes();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Salir");
			putValue(SHORT_DESCRIPTION, "Cierra la aplicación Emergencias");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Listado especialidades");
			putValue(SHORT_DESCRIPTION, "Ventana que lista las especialidades de la Base de Datos");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				ListadoEspecial dialog = new ListadoEspecial();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Cambiar disponibilidad");
			putValue(SHORT_DESCRIPTION, "Ventana para cambiar la disponibilidad de una ambulancia");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				CambiarDisponibilidad dialog = new CambiarDisponibilidad();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Cambiar coordenadas");
			putValue(SHORT_DESCRIPTION, "Ventana para cambiar las coordenadas de una ambulancia");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				CambiarCoordenadas dialog = new CambiarCoordenadas();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "Alta paciente");
			putValue(SHORT_DESCRIPTION, "Ventana para dar de alta a un paciente");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				AltaPaciente dialog = new AltaPaciente();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
