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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;

import persistencia.AmbulanciaDAOImp;
import persistencia.ConnectionManager;
import persistencia.DAL;
import logica.Ambulancia;
import logica.Controlador;
import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;


public class CambiarCoordenadas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFid;
	private JTextField textFnreg;
	private JTextField textFtipo;
	private JTextField textFlatitud;
	private JTextField textFlongitud;
	private JTextField textFdisp;
	private JTextField textFnuevalatitud;
	private JTextField textFnuevalongitud;
	private JTextField textFestado;
	
	private Ambulancia a;
	private boolean yaGuardado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiarCoordenadas dialog = new CambiarCoordenadas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CambiarCoordenadas() {
		setTitle("Cambiar coordenadas");
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
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "El ID debe ser un entero", "",	JOptionPane.ERROR_MESSAGE);
				} catch (LogicaExcepcion e1) {
					JOptionPane.showMessageDialog(null, "Ambulancia no encontrada", "",	JOptionPane.ERROR_MESSAGE);
				}
				if(a!=null){
					textFnreg.setText(a.getRegistro());
					textFtipo.setText(Integer.toString(a.getTipo()));
					textFlatitud.setText(Float.toString(a.getLatitud()));
					textFlongitud.setText(Float.toString(a.getLongitud()));
					if(a.getDisponibilidad()==0)
						textFdisp.setText("No disponible");
					else
						textFdisp.setText("Disponible");
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
		
		textFdisp = new JTextField();
		textFdisp.setEditable(false);
		textFdisp.setColumns(10);
		
		JLabel lblIntroducirNuevasCoordenadas = new JLabel("Introducir nuevas coordenadas:");
		
		JLabel lblLatitud_1 = new JLabel("Latitud:");
		
		textFnuevalatitud = new JTextField();
		textFnuevalatitud.setColumns(10);
		
		JLabel lblLongitud_1 = new JLabel("Longitud:");
		
		textFnuevalongitud = new JTextField();
		textFnuevalongitud.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFestado.setText("Ambulancia enviada correctamente");
				Float lat = Float.parseFloat(textFnuevalatitud.getText());
				Float lon = Float.parseFloat(textFnuevalongitud.getText());
				try {
					Controlador.dameControlador().CambiarCoordenadas(textFid.getText(), lat, lon);
					textFnreg.setText(a.getRegistro());
					textFtipo.setText(Integer.toString(a.getTipo()));
					textFlatitud.setText(Float.toString(a.getLatitud()));
					textFlongitud.setText(Float.toString(a.getLongitud()));
					if(a.getDisponibilidad()==0)
						textFdisp.setText("No disponible");
					else
						textFdisp.setText("Disponible");
					yaGuardado = true;
				} catch (LogicaExcepcion e) {
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblIntroducirIdentificadorDe)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFid, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPanel.createSequentialGroup()
												.addGap(9)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
													.addComponent(lblNRegistro, Alignment.TRAILING)
													.addComponent(lblTipo, Alignment.TRAILING)
													.addComponent(lblLatitud, Alignment.TRAILING)
													.addComponent(lblLongitud, Alignment.TRAILING)))
											.addComponent(lblDisponibilidad)
											.addComponent(lblLatitud_1, Alignment.TRAILING))
										.addGap(4)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(textFnreg, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
											.addComponent(textFtipo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
											.addComponent(textFlatitud, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
											.addComponent(textFlongitud, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
											.addComponent(textFdisp, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
											.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(textFnuevalatitud, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblLongitud_1)
												.addGap(8)
												.addComponent(textFnuevalongitud, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)))))
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
								.addComponent(lblinformacin)
								.addGap(297))
							.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
								.addComponent(btnConsultarDatos)
								.addGap(251)))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblIntroducirNuevasCoordenadas)
							.addGap(230))))
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
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDisponibilidad)
						.addComponent(textFdisp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblIntroducirNuevasCoordenadas)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLatitud_1)
						.addComponent(textFnuevalatitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFnuevalongitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGuardar)
						.addComponent(lblLongitud_1))
					.addGap(25))
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
