package iu;

import iu.ListadoPacientes.PacientesTableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;

import logica.Controlador;
import logica.Paciente;
import excepciones.LogicaExcepcion;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ListadoPacientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textFestado;
	
	//Clase interna
	class PacientesTableModel extends AbstractTableModel {
		 private static final long serialVersionUID = 1L;
		 private String[] columnNames = { "DNI", "Nombre","Apellidos","Dirección", "Edad","Teléfono","Sexo"};
	
		 private ArrayList<Paciente> data=new ArrayList<Paciente>();
	
		 public int getColumnCount() {
			 return columnNames.length;
		 }
		 public int getRowCount() {
			 return data.size();
		 }
		 public String getColumnName(int col) {
			 return columnNames[col];
		 }
		 public Object getValueAt(int row, int col) {
			 Paciente in =data.get(row);
			 switch(col){
			 	case 0: return in.getDni();
			 	case 1: return in.getNombre();
			 	case 2: return in.getApellidos();
			 	case 3: return in.getDireccion();
			 	case 4: return in.getEdad();
			 	case 5: return in.getTelefono();
			 	case 6: return in.getSexo();
			 	default: return null;
			 }
	
		 }
		 public void clear(){
			 data.clear();
		 }
		 public Class<? extends Object> getColumnClass(int c) {
			 return getValueAt(0, c).getClass();
		 }
		 public void addRow(Paciente row) {
			 data.add(row);
		 	this.fireTableDataChanged();
		 }	
		 public void delRow(int row) {
			 data.remove(row);
			 this.fireTableDataChanged();	
		 }
	 }
	////

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoPacientes dialog = new ListadoPacientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoPacientes() {
		setUndecorated(true);
		setResizable(false);
		setTitle("Listado de pacientes");
		setBounds(100, 100, 640, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				table = new JTable(new PacientesTableModel());
				scrollPane.setViewportView(table);
			}
		}
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
		cargaTabla();
	}
	
	private void cargaTabla(){
		double t1 = System.nanoTime()/1000000;
		try {
			ArrayList<Paciente> aux = Controlador.dameControlador().ListarPacientes();
			PacientesTableModel model=(PacientesTableModel) table.getModel();
			model.clear();
			for(int i=0; i<aux.size(); i++)
				model.addRow(aux.get(i));
			double t2 = System.nanoTime()/1000000;
			textFestado.setText("Información recuperada en "+(t2-t1)+" milisegundos.");
		} catch (LogicaExcepcion e) {e.printStackTrace();}
	}
}
