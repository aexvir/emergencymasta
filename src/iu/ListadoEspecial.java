package iu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Action;

import excepciones.LogicaExcepcion;

import javax.swing.JTextField;

import java.awt.CardLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.event.ActionListener;

import logica.Controlador;
import logica.Especialidad;
import javax.swing.BoxLayout;
import java.awt.Dialog.ModalityType;


public class ListadoEspecial extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private final Action action = new SwingAction();
	private JTextField textFHospital;
	private JScrollPane scrollPane;
	private JButton btnBuscarHospital;
	private JTextField textFestado;
	
	//Clase interna
	class EspecialidadTableModel extends AbstractTableModel {
		 private static final long serialVersionUID = 1L;
		 private String[] columnNames = {"Especialidad"};
	
		 private ArrayList<Especialidad> data=new ArrayList<Especialidad>();
	
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
			 Especialidad es =data.get(row);
			 switch(col){
			 	case 0: return es.getNombre();
			 	default: return null;
			 }
	
		 }
	
		 public void clear(){
			 data.clear();
		 }
		 public Class<? extends Object> getColumnClass(int c) {
			 return getValueAt(0, c).getClass();
		 }
		 public void addRow(Especialidad row) {
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
			ListadoEspecial dialog = new ListadoEspecial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoEspecial() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setUndecorated(true);
		setTitle("Listado especial");
		setResizable(false);
		setBounds(100, 100, 640, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			textFHospital = new JTextField();
			textFHospital.setBounds(10, 11, 453, 25);
			textFHospital.setPreferredSize(new Dimension(640,20));
			textFHospital.setColumns(10);
		}
		contentPanel.setLayout(null);
		contentPanel.add(textFHospital);
		{
			btnBuscarHospital = new JButton("Buscar Hospital");
			btnBuscarHospital.setBounds(473, 11, 157, 25);
			btnBuscarHospital.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					double t1,t2 = 0;
					t1 = System.nanoTime()/1000000;
					try {
						ArrayList<Especialidad> aux = Controlador.dameControlador().ListarEspecialidades(textFHospital.getText());
						EspecialidadTableModel model=(EspecialidadTableModel) table.getModel();
						model.clear();
						for(int i=0; i<aux.size(); i++)
							model.addRow(aux.get(i));
						t2 = System.nanoTime()/1000000;
					} catch (LogicaExcepcion e) {e.printStackTrace();}
					textFestado.setText("Información recuperada en "+(t2-t1)+" milisegundos.");
				}
			});
			{
				scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 47, 620, 269);
				{
					table = new JTable(new EspecialidadTableModel());
					scrollPane.setViewportView(table);
				}
			}
			contentPanel.add(scrollPane);
			contentPanel.add(btnBuscarHospital);
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
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setAction(action);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Salir");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	
	/*private void cargaTabla(String hosp){
		try {
			ArrayList<Especialidad> aux = Controlador.dameControlador().ListarEspecialidades(hosp);
			EspecialidadTableModel model=(EspecialidadTableModel) table.getModel();
			model.clear();
			for(int i=0; i<aux.size(); i++)
				model.addRow(aux.get(i));
		} catch (LogicaExcepcion e) {e.printStackTrace();}
	}*/
}
