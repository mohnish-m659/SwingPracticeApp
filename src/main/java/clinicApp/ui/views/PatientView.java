package clinicApp.ui.views;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

public class PatientView extends JPanel implements IView{

	public PatientView() {
		super(new BorderLayout());
		init();
	}
	
	JTable table;

	private void init() {
		
		table = new JTable(getTableModel());
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private TableModel getTableModel() {
		return new PatientTableModel();
	}

	@Override
	public void refresh() {
		SwingUtilities.invokeLater(()->{
			table.setModel(getTableModel());
		});
	}
	
	

}
