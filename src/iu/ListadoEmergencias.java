package iu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;

import logica.Controlador;
import logica.RegistroEmergencia;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoEmergencias extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFestado;
	private JTable table;
	
	//Clase interna
	class EmergenciaTableModel extends AbstractTableModel {
		 private static final long serialVersionUID = 1L;
		 private String[] columnNames = { "ID", "LATITUD","LONGITUD","FECHA",
		"HORA","PACIENTE","AMBULANCIA", "HOSPITAL", "SÍNTOMAS"};
	
		 private ArrayList<RegistroEmergencia> data=new ArrayList<RegistroEmergencia>();
	
		
		 public int getColumnCount() {
			 return columnNames.length;
		 }
		 public int getRowCount() {
			 return data.size();
		 }
		 public String getColumnName(int col) {
			 return columnNames[col];
		 }
		 
		 public void clear(){
			 data.clear();
		 }
		 
		 public void addRow(RegistroEmergencia row) {
			 data.add(row);
		 	this.fireTableDataChanged();
		 }
		 
		 public Object getValueAt(int row, int col) {
			 RegistroEmergencia in = data.get(row);
			 String s = "";
			System.out.println("CANTIDAD DE SINTOMAS: "+in.getSintomas().size());
			 switch(col){
			 	case 0: return in.getIdRegistro();
			 	case 1: return in.getLatitud(); 
			 	case 2: return in.getLongitud();
			 	case 3: return in.getFecha();
			 	case 4: return in.getHora();
			 	case 5: return in.getDniPaciente();
			 	case 6: return in.getIdAmbulancia();
			 	case 7: return in.getIdHospital();
			 	case 8: for(int i = 0; i<in.getSintomas().size(); i++){
			 		s+= in.getSintomas().get(i).getDescripcion();
			 		if(i!=in.getSintomas().size()-1) s+=", ";			 		
			 	}return s;

			 	default: return null;
			 }
	
		 }
	}
		 ////

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoEmergencias dialog = new ListadoEmergencias();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoEmergencias() {
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 640, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 620, 305);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable(new EmergenciaTableModel());
		scrollPane.setViewportView(table);
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargaTabla();
	}
	
public void cargaTabla(){
		
		try{
			ArrayList<RegistroEmergencia> listaEmer = Controlador.dameControlador().listarRegistrosEmergencias();
			System.out.println(listaEmer.get(0).getIdRegistro());
			Iterator<RegistroEmergencia> it = listaEmer.iterator();
			
			RegistroEmergencia emer;
			EmergenciaTableModel model = (EmergenciaTableModel) table.getModel();
			model.clear();
			while (it.hasNext()){
				emer = it.next();
				model.addRow(emer);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
