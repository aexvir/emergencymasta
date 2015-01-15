package iu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;

import logica.Ambulancia;
import logica.Controlador;
import excepciones.LogicaExcepcion;


public class CambiarDisponibilidad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFid;
	private JTextField textFnreg;
	private JTextField textFtipo;
	private JTextField textFlatitud;
	private JTextField textFlongitud;
	private JTextField textFestado;
	
	private Ambulancia a;
	private boolean yaGuardado;
	private JTextField textFieldDisponibilidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiarDisponibilidad dialog = new CambiarDisponibilidad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CambiarDisponibilidad() {
		setUndecorated(true);
		setTitle("Cambiar disponibilidad");
		setResizable(false);
		setBounds(100, 100, 640, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblIntroducirIdentificadorDe = new JLabel("Introducir identificador de la ambulancia:");
		
		textFid = new JTextField();
		textFid.setColumns(10);
		
		JButton btnConsultarDatos = new JButton("Consultar datos");
		btnConsultarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					a = Controlador.dameControlador().BuscarAmbulancia(textFid.getText());
					textFnreg.setText(a.getRegistro());
					textFtipo.setText(Integer.toString(a.getTipo()));
					textFlatitud.setText(Float.toString(a.getLatitud()));
					textFlongitud.setText(Float.toString(a.getLongitud()));
					if(a.getDisponibilidad()==0)
						textFieldDisponibilidad.setText("No disponible");
					else
						textFieldDisponibilidad.setText("Disponible");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "El ID debe ser un entero", "",	JOptionPane.ERROR_MESSAGE);
				} catch (LogicaExcepcion e1) {
					JOptionPane.showMessageDialog(null, "Ambulancia no encontrada", "",	JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JLabel lblinformacin = new JLabel("-Informaci\u00F3n-");
		
		JLabel lblNRegistro = new JLabel("N\u00BA Registro:");
		
		JLabel lblTipo = new JLabel("Tipo:");
		
		JLabel lblLatitud = new JLabel("Latitud:");
		
		JLabel lblLongitud = new JLabel("Longitud:");
		
		textFnreg = new JTextField();
		textFnreg.setEditable(false);
		textFnreg.setColumns(10);
		
		textFtipo = new JTextField();
		textFtipo.setEditable(false);
		textFtipo.setColumns(10);
		
		textFlatitud = new JTextField();
		textFlatitud.setEditable(false);
		textFlatitud.setColumns(10);
		
		textFlongitud = new JTextField();
		textFlongitud.setEditable(false);
		textFlongitud.setColumns(10);
		
		JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
		
		JButton btnCambiarDisponibilidad = new JButton("Cambiar disponibilidad");
		btnCambiarDisponibilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a!=null){
					try {
						Controlador.dameControlador().CambiarDisponibilidad(textFid.getText());
					} catch (LogicaExcepcion e1) {
						e1.printStackTrace();
					}
					if(a.getDisponibilidad()==0)
						textFieldDisponibilidad.setText("No disponible");
					else
						textFieldDisponibilidad.setText("Disponible");
					yaGuardado = true;
					textFestado.setText("Disponibilidad de la ambulancia "+a.getRegistro()+" cambiada correctamente.");
				}
			}
		});
		
		textFieldDisponibilidad = new JTextField();
		textFieldDisponibilidad.setEditable(false);
		textFieldDisponibilidad.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblIntroducirIdentificadorDe)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFid, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(9)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNRegistro, Alignment.TRAILING)
												.addComponent(lblTipo, Alignment.TRAILING)
												.addComponent(lblLatitud, Alignment.TRAILING)
												.addComponent(lblLongitud, Alignment.TRAILING)))
										.addComponent(lblDisponibilidad))
									.addGap(4)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(textFieldDisponibilidad, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
											.addComponent(btnCambiarDisponibilidad))
										.addComponent(textFnreg, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
										.addComponent(textFtipo, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
										.addComponent(textFlatitud, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
										.addComponent(textFlongitud, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE))))
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblinformacin)
							.addGap(297))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnConsultarDatos)
							.addGap(251))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIntroducirIdentificadorDe)
						.addComponent(textFid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnConsultarDatos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblinformacin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNRegistro)
						.addComponent(textFnreg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipo)
						.addComponent(textFtipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLatitud)
						.addComponent(textFlatitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLongitud)
						.addComponent(textFlongitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCambiarDisponibilidad)
						.addComponent(lblDisponibilidad)
						.addComponent(textFieldDisponibilidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(80))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
			
			textFestado = new JTextField();
			textFestado.setEditable(false);
			buttonPane.add(textFestado);
			textFestado.setColumns(45);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
