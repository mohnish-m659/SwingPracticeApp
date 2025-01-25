package clinicApp.ui.views;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class AppointmentView extends JPanel implements IView{
	
	JTable appointmentTable;

	public AppointmentView() {
		super(new BorderLayout());
		init();
	}

	private void init() {
		
		appointmentTable = new JTable(getTableModel());
		JScrollPane scrollPane = new JScrollPane(appointmentTable);
		add(scrollPane);
	}

	private TableModel getTableModel() {
		return new AppointmentTableModel();
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	

}
