package clinicApp.ui.views;

import javax.swing.table.AbstractTableModel;

public class AppointmentTableModel extends AbstractTableModel{
	
	private String[] columns = {"Firstname", "Lastname" , "Physician", "Date"};
	private String[][] dummyData = {
			{"Amy", "Johnson", "Harry G", "24-2-2025"},
			{"Ken", "Potter", "Harry G", "8-2-2025"}
	};

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dummyData.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columns[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return dummyData[rowIndex][columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
