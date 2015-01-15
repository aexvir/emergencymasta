package iu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;

import javax.swing.JSplitPane;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import excepciones.LogicaExcepcion;
import logica.Ambulancia;
import logica.Controlador;
import logica.Especialidad;
import logica.Hospital;
import logica.Paciente;
import logica.RegistroEmergencia;
import logica.Sintoma;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.UIManager;

public class Emergencia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JPanel panelDerecha = new JPanel();
	private JTextField textFestado;
	private JTextField textFdni;
	
	private CardLayout cl_panelDerecha;
	private JTextField textFnombre;
	private JTextField textFapellidos;
	private JTextField textFdireccion;
	private JTextField textFtlf;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFlatitud;
	private JTextField textFlongitud;
	private JTextField textFDuracion;
	private JComboBox comboBoxSintoma;
	private JEditorPane editorPresumenEstado;
	private JPanel panelPaciente;
	
	//RegistroEmergencia
	private float RElatitud;
	private float RElongitud;
	private String REfecha;
	private String REhora;
	private String REidRegistro;
	private String REdniPaciente;
	private String REidAmbulancia;
	private String REidHospital;
	private ArrayList<Sintoma> REsintomas = new ArrayList<Sintoma>();
	
	private boolean pacienteEsp, ubicacionEsp, estadoEsp;
	
	private JTextField textFresumenPaciente;
	private JTextField textFresumenDNI;
	private JTextField textFresumenLongitud;
	private JTextField textFresumenLatitud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Emergencia dialog = new Emergencia();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Emergencia() {
		setUndecorated(true);
		setTitle("Emergencia");
		setResizable(false);
		setBounds(100, 100, 640, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JPanel panelIzquierda = new JPanel();
			contentPanel.add(panelIzquierda);
			panelIzquierda.setLayout(null);
			{
				JLabel lblResumen = new JLabel("Resumen:");
				lblResumen.setBounds(124, 8, 66, 14);
				panelIzquierda.add(lblResumen);
			}
			
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			panel.setBounds(10, 33, 295, 273);
			panelIzquierda.add(panel);
			panel.setLayout(null);
			
			JLabel lblPaciente = new JLabel("Paciente:");
			lblPaciente.setBounds(10, 11, 62, 14);
			panel.add(lblPaciente);
			
			JLabel lblDni_1 = new JLabel("DNI:");
			lblDni_1.setBounds(10, 36, 62, 14);
			panel.add(lblDni_1);
			
			JLabel lblEstado = new JLabel("Ubicaci\u00F3n:");
			lblEstado.setBounds(10, 61, 62, 14);
			panel.add(lblEstado);
			
			textFresumenPaciente = new JTextField();
			textFresumenPaciente.setEditable(false);
			textFresumenPaciente.setBounds(82, 8, 203, 20);
			panel.add(textFresumenPaciente);
			textFresumenPaciente.setColumns(10);
			
			textFresumenDNI = new JTextField();
			textFresumenDNI.setEditable(false);
			textFresumenDNI.setBounds(82, 33, 203, 20);
			panel.add(textFresumenDNI);
			textFresumenDNI.setColumns(10);
			
			textFresumenLongitud = new JTextField();
			textFresumenLongitud.setEditable(false);
			textFresumenLongitud.setBounds(187, 58, 98, 20);
			panel.add(textFresumenLongitud);
			textFresumenLongitud.setColumns(10);
			
			textFresumenLatitud = new JTextField();
			textFresumenLatitud.setEditable(false);
			textFresumenLatitud.setBounds(82, 58, 95, 20);
			panel.add(textFresumenLatitud);
			textFresumenLatitud.setColumns(10);
			
			JLabel lblEstado_1 = new JLabel("Estado:");
			lblEstado_1.setBounds(10, 86, 46, 14);
			panel.add(lblEstado_1);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(UIManager.getColor("Viewport.foreground")));
			panel_1.setBounds(82, 86, 203, 176);
			panel.add(panel_1);
			panel_1.setLayout(new GridLayout(0, 1, 0, 0));
			
			editorPresumenEstado = new JEditorPane();
			panel_1.add(editorPresumenEstado);
			editorPresumenEstado.setBackground(UIManager.getColor("Button.background"));
			editorPresumenEstado.setEditable(false);
		}
		{
			contentPanel.add(panelDerecha);
			cl_panelDerecha = new CardLayout(0,0);
			panelDerecha.setLayout(cl_panelDerecha);
			
			JPanel Botones = new JPanel();
			panelDerecha.add(Botones, "name_95513827651376");
			Botones.setLayout(null);
			
			JButton btnAddPac = new JButton("A\u00F1adir Paciente");
			btnAddPac.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					panelPaciente.setVisible(false);
					cl_panelDerecha.next(panelDerecha);
				}
			});
			btnAddPac.setBounds(10, 11, 295, 40);
			Botones.add(btnAddPac);
			
			JButton btnAddSintom = new JButton("A\u00F1adir Estado");
			btnAddSintom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int i=0; i<2; i++)
						cl_panelDerecha.next(panelDerecha);
				}
			});
			btnAddSintom.setBounds(10, 62, 295, 40);
			Botones.add(btnAddSintom);
			
			JButton btnAddUbicacion = new JButton("A\u00F1adir Ubicaci\u00F3n");
			btnAddUbicacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int i=0; i<3; i++)
						cl_panelDerecha.next(panelDerecha);
				}
			});
			btnAddUbicacion.setBounds(10, 113, 295, 40);
			Botones.add(btnAddUbicacion);
			
			JButton btnEnvAmbulancia = new JButton("Enviar Ambulancia");
			btnEnvAmbulancia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(pacienteEsp && ubicacionEsp && estadoEsp){
						try {
							Hospital h = Controlador.dameControlador().calculaHospital(RElatitud, RElongitud, REsintomas);
							Ambulancia a = Controlador.dameControlador().calculaAmbulancia(RElatitud, RElongitud, h);
							Date dt = new Date();
							DateFormat ffecha = new SimpleDateFormat("dd/MM/yyyy");
							DateFormat fhora = new SimpleDateFormat("HH:mm:ss");
							REfecha = ffecha.format(dt).toString();
							REhora = fhora.format(dt).toString();
							REidHospital = h.getNombre();
							REidAmbulancia = a.getRegistro();							
							Controlador.dameControlador().addRegistroEmergencia(new RegistroEmergencia(REidRegistro, RElatitud, RElongitud, REdniPaciente, REfecha, REhora, REidAmbulancia, REidHospital, REsintomas));
						} catch (LogicaExcepcion e1) {e1.printStackTrace();}						
					}
				}
			});
			btnEnvAmbulancia.setBounds(10, 266, 295, 40);
			Botones.add(btnEnvAmbulancia);
			
			JPanel Paciente = new JPanel();
			panelDerecha.add(Paciente, "name_95564547970658");
			Paciente.setLayout(null);
			
			textFdni = new JTextField();
			textFdni.setBounds(52, 11, 154, 25);
			Paciente.add(textFdni);
			textFdni.setColumns(10);
			
			JLabel lblDni = new JLabel("DNI:");
			lblDni.setBounds(10, 16, 46, 14);
			Paciente.add(lblDni);
			
			JButton btnAtrs = new JButton("<");
			btnAtrs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl_panelDerecha.first(panelDerecha);
				}
			});
			btnAtrs.setBounds(10, 283, 46, 23);
			Paciente.add(btnAtrs);
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						Paciente p = Controlador.dameControlador().buscarPaciente(textFdni.getText());
						if(p!=null){
							REdniPaciente = p.getDni();
							textFresumenDNI.setText(p.getDni());
							textFresumenPaciente.setText((p.getApellidos()+", "+p.getNombre()));
							textFnombre.setText("");
							textFapellidos.setText("");
							textFdireccion.setText("");
							pacienteEsp=true;
						}else{panelPaciente.setVisible(true);}
					} catch (LogicaExcepcion e) {e.printStackTrace();}
				}
			});
			btnBuscar.setBounds(216, 12, 89, 25);
			Paciente.add(btnBuscar);
			
			panelPaciente = new JPanel();
			panelPaciente.setBounds(10, 47, 295, 225);
			Paciente.add(panelPaciente);
			panelPaciente.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Paciente no encontrado, introduzca los datos");
			lblNewLabel.setBounds(10, 11, 275, 14);
			panelPaciente.add(lblNewLabel);
			
			textFnombre = new JTextField();
			textFnombre.setBounds(76, 36, 209, 20);
			panelPaciente.add(textFnombre);
			textFnombre.setColumns(10);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 39, 56, 14);
			panelPaciente.add(lblNombre);
			
			textFapellidos = new JTextField();
			textFapellidos.setBounds(76, 67, 209, 20);
			panelPaciente.add(textFapellidos);
			textFapellidos.setColumns(10);
			
			JLabel lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBounds(10, 70, 56, 14);
			panelPaciente.add(lblApellidos);
			
			JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
			lblDireccin.setBounds(10, 101, 57, 14);
			panelPaciente.add(lblDireccin);
			
			textFdireccion = new JTextField();
			textFdireccion.setBounds(76, 98, 209, 20);
			panelPaciente.add(textFdireccion);
			textFdireccion.setColumns(10);
			
			final JSpinner spinnerEdad = new JSpinner();
			spinnerEdad.setBounds(76, 129, 98, 20);
			panelPaciente.add(spinnerEdad);
			
			JLabel lblEdad = new JLabel("Edad:");
			lblEdad.setBounds(10, 132, 56, 14);
			panelPaciente.add(lblEdad);
			
			textFtlf = new JTextField();
			textFtlf.setBounds(76, 160, 209, 20);
			panelPaciente.add(textFtlf);
			textFtlf.setColumns(10);
			
			JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
			lblTelfono.setBounds(10, 163, 56, 14);
			panelPaciente.add(lblTelfono);
			
			final JRadioButton rdbtnHombre = new JRadioButton("Hombre");
			buttonGroup.add(rdbtnHombre);
			rdbtnHombre.setBounds(76, 187, 74, 23);
			panelPaciente.add(rdbtnHombre);
			
			final JRadioButton rdbtnMujer = new JRadioButton("Mujer");
			buttonGroup.add(rdbtnMujer);
			rdbtnMujer.setBounds(160, 187, 109, 23);
			panelPaciente.add(rdbtnMujer);
			
			JLabel lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(10, 191, 46, 14);
			panelPaciente.add(lblSexo);
			
			JButton btnAadirPaciente = new JButton("A\u00F1adir Paciente");
			btnAadirPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					char s = ' ';
					if(rdbtnHombre.isSelected())
						s = 'h';
					else if(rdbtnMujer.isSelected())
						s = 'm';
					else
						JOptionPane.showMessageDialog(null, "Se debe indicar el sexo del paciente", "",	JOptionPane.ERROR_MESSAGE);
					if(s!=' '){
						if(textFdni.getText().length()==0 || textFnombre.getText().length()==0 || textFapellidos.getText().length()==0
								|| textFtlf.getText().length()==0|| textFdireccion.getText().length()==0)
							JOptionPane.showMessageDialog(null, "Todos los campos deben estar completados", "",	JOptionPane.ERROR_MESSAGE);
						else{
							try {
								Paciente p = new Paciente(
										textFdni.getText(),
										textFnombre.getText(),
										textFapellidos.getText(),
										s,
										(Integer) spinnerEdad.getValue(),
										textFtlf.getText(),
										textFdireccion.getText()
										);
								Controlador.dameControlador().AltaPaciente(p);
								REdniPaciente = textFdni.getText();
								textFestado.setText("Paciente añadido a la BD correctamente");
								
								textFresumenPaciente.setText(p.getApellidos()+", "+p.getNombre());
								textFresumenDNI.setText(p.getDni());
								pacienteEsp=true;
							} catch (LogicaExcepcion e1) {
								textFestado.setText("Error, no se ha podido crear el paciente");
							}
						}
					}
				}
			});
			btnAadirPaciente.setBounds(138, 283, 167, 23);
			Paciente.add(btnAadirPaciente);
		}
		
		JPanel Estado = new JPanel();
		panelDerecha.add(Estado, "name_98370380624490");
		Estado.setLayout(null);
		
		
		try {
			ArrayList<Especialidad> aux = Controlador.dameControlador().ListarEspecialidades();
			ArrayList<String> nombres = new ArrayList<String>();
			for(int i=0; i<aux.size(); i++)
				nombres.add(aux.get(i).getNombre());
			comboBoxSintoma = new JComboBox(nombres.toArray());
			comboBoxSintoma.setBounds(10, 5, 295, 20);
			Estado.add(comboBoxSintoma);
		} catch (LogicaExcepcion e1) {e1.printStackTrace();}
		
		String[] g = {"Leve", "Moderado", "Acusado", "Grave", "Muy grave", "Muerte"};
		final JComboBox comboBoxGravedad = new JComboBox(g);
		comboBoxGravedad.setBounds(10, 36, 295, 20);
		Estado.add(comboBoxGravedad);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 126, 295, 146);
		Estado.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		final JEditorPane editorPaneDescripcion = new JEditorPane();
		panel.add(editorPaneDescripcion);
		
		JLabel lblComentarios = new JLabel("Descripci\u00F3n:");
		lblComentarios.setBounds(10, 101, 78, 14);
		Estado.add(lblComentarios);
		
		JButton btnGuardarEstadoDel = new JButton("Guardar estado del Paciente");
		btnGuardarEstadoDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gr = comboBoxGravedad.getSelectedItem().toString();
				String du = textFDuracion.getText();
				String de = editorPaneDescripcion.getText();
				String rn="";
				try {
					rn = Integer.toString(Controlador.dameControlador().listarRegistrosEmergencias().size());
					REidRegistro = rn;
					String es = comboBoxSintoma.getSelectedItem().toString();
					if(gr!="" || du!="" || de!="" || rn!=""){
						System.out.println(gr+"\n"+du+"\n"+de+"\n"+rn+"\n"+es);
						REsintomas.add(new Sintoma(es, rn ,gr, du, de));
						textFestado.setText("Estado del paciente guardado correctamente");
						String aux = editorPaneDescripcion.getText();
						aux+=(es+": "+de+", gravedad: "+gr+", duración: "+du+"\n");
						editorPresumenEstado.setText(aux);
						estadoEsp=true;
					}
					else{
						textFestado.setText("Los campos no pueden estar vacíos");
					}
				} catch (LogicaExcepcion e1) {e1.printStackTrace();}
			}
		});
		btnGuardarEstadoDel.setBounds(88, 283, 217, 23);
		Estado.add(btnGuardarEstadoDel);
		
		JButton button_2 = new JButton("<");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panelDerecha.first(panelDerecha);
			}
		});
		button_2.setBounds(10, 283, 46, 23);
		Estado.add(button_2);
		
		textFDuracion = new JTextField();
		textFDuracion.setBounds(77, 67, 228, 20);
		Estado.add(textFDuracion);
		textFDuracion.setColumns(10);
		
		JLabel lblDuracin = new JLabel("Duraci\u00F3n:");
		lblDuracin.setBounds(10, 70, 57, 14);
		Estado.add(lblDuracin);
		
		JPanel Ubicación = new JPanel();
		panelDerecha.add(Ubicación, "name_98111343132247");
		Ubicación.setLayout(null);
		
		JButton button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panelDerecha.first(panelDerecha);
			}
		});
		button.setBounds(10, 283, 41, 23);
		Ubicación.add(button);
		
		textFlatitud = new JTextField();
		textFlatitud.setBounds(95, 11, 210, 20);
		Ubicación.add(textFlatitud);
		textFlatitud.setColumns(10);
		
		JLabel lblLatitud = new JLabel("Latitud:");
		lblLatitud.setBounds(10, 14, 75, 14);
		Ubicación.add(lblLatitud);
		
		textFlongitud = new JTextField();
		textFlongitud.setBounds(95, 42, 210, 20);
		Ubicación.add(textFlongitud);
		textFlongitud.setColumns(10);
		
		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setBounds(10, 45, 75, 14);
		Ubicación.add(lblLongitud);
		
		JButton btnAadirUbicacin = new JButton("A\u00F1adir Ubicaci\u00F3n");
		btnAadirUbicacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFlatitud.getText().length()==0 || textFlongitud.getText().length()==0){
					if(textFlatitud.getText().length()==0 && !(textFlongitud.getText().length()==0))
						textFestado.setText("El campo latitud no puede estar vacío");
					else if(!(textFlatitud.getText().length()==0) && textFlongitud.getText().length()==0)
						textFestado.setText("El campo longitud no puede estar vacío");
					else
						textFestado.setText("Rellene los campos de latitud y longitud");
				}					
				else{
					RElatitud = Float.parseFloat(textFlatitud.getText());
					RElongitud = Float.parseFloat(textFlongitud.getText());
					ubicacionEsp=true;
					textFresumenLatitud.setText(textFlatitud.getText());
					textFresumenLongitud.setText(textFlongitud.getText());
					textFestado.setText("Ubicación guardada correctamente");
				}
			}
		});
		btnAadirUbicacin.setBounds(138, 283, 167, 23);
		Ubicación.add(btnAadirUbicacin);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
			{
				textFestado = new JTextField();
				textFestado.setEditable(false);
				buttonPane.add(textFestado);
				textFestado.setColumns(10);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
