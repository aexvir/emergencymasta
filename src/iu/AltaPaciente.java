package iu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import logica.Controlador;
import logica.Paciente;
import excepciones.LogicaExcepcion;
import java.awt.Window.Type;

public class AltaPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFdni;
	private JTextField textFnombre;
	private JTextField textFapellidos;
	private JTextField textFdireccion;
	private JTextField textFtlf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaPaciente dialog = new AltaPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Se crea el diálogo
	
	public AltaPaciente() {
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Formulario Alta Paciente");
		setBounds(100, 100, 300, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblDni = new JLabel("DNI:");
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		
		JLabel lblEdad = new JLabel("Edad:");
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		
		JLabel lblSexo = new JLabel("Sexo:");
		
		final JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		buttonGroup.add(rdbtnHombre);
		
		final JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		buttonGroup.add(rdbtnMujer);
		
		textFdni = new JTextField();
		textFdni.setColumns(10);
		
		textFnombre = new JTextField();
		textFnombre.setColumns(10);
		
		textFapellidos = new JTextField();
		textFapellidos.setColumns(10);
		
		textFdireccion = new JTextField();
		textFdireccion.setColumns(10);
		
		textFtlf = new JTextField();
		textFtlf.setColumns(10);
		
		final JSpinner spinnerEdad = new JSpinner();
		spinnerEdad.setModel(new SpinnerNumberModel(0, 0, 150, 1));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDni, Alignment.TRAILING)
						.addComponent(lblSexo, Alignment.TRAILING)
						.addComponent(lblNombre, Alignment.TRAILING)
						.addComponent(lblApellidos, Alignment.TRAILING)
						.addComponent(lblDireccin, Alignment.TRAILING)
						.addComponent(lblEdad, Alignment.TRAILING)
						.addComponent(lblTelfono, Alignment.TRAILING))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textFdni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textFnombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textFdireccion)
							.addComponent(textFapellidos, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
							.addComponent(textFtlf)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(rdbtnHombre)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(rdbtnMujer)))
						.addComponent(spinnerEdad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(textFdni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textFnombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellidos)
						.addComponent(textFapellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDireccin)
						.addComponent(textFdireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEdad)
						.addComponent(spinnerEdad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelfono)
						.addComponent(textFtlf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSexo)
						.addComponent(rdbtnHombre)
						.addComponent(rdbtnMujer))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
									JOptionPane.showMessageDialog(null, "Paciente añadido a la BD correctamente", "",	JOptionPane.INFORMATION_MESSAGE);
									dispose();
								} catch (LogicaExcepcion e) {
									JOptionPane.showMessageDialog(null, "Error, no se ha podido crear el paciente","", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
