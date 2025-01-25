package clinicApp.ui.views;

import javax.swing.table.AbstractTableModel;

import clinicApp.data.PatientService;
import clinicApp.ui.model.Patient;

public class PatientTableModel extends AbstractTableModel {
	
	PatientService service = PatientService.getInstance();
	private String[] columns = {"Firstname", "Lastname", "Age", "Gender", "DOB"};

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return service.getPatientCount();
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
		try {
			Patient patient = service.getPatientWithId(rowIndex+1);
			switch (columnIndex) {
			case 0:
				return patient.getFirstName();
			case 1:
				return patient.getLastName();
			case 2:
				return patient.getAge();
			case 3:
				return patient.getGender().toString();
			case 4:
				return patient.getDateOfBirth().toString();
			default:
				return null;
			}
		}catch (Exception e) {
			System.out.println("could not find data");
			return null;
		}
	}

	public void refresh() {
		fireTableDataChanged();
	}

}
