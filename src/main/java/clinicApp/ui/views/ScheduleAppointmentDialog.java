package clinicApp.ui.views;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class ScheduleAppointmentDialog extends JDialog {

	@Override
	protected JRootPane createRootPane() {
		JPanel content = createContent();
		JRootPane root = new JRootPane();
		root.getContentPane().add(content);
		return root;
	}

	private JPanel createContent() {
		
		return null;
	}
	
	

}
