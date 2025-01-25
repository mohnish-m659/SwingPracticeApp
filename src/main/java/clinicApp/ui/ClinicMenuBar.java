package clinicApp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import clinicApp.ui.views.CreatePatientDialog;

public class ClinicMenuBar extends JMenuBar{

	public ClinicMenuBar() {
		super();
		init();
	}

	private void init() {
		
		JMenu newMenu = new JMenu("New");
		
		JMenu scheduleSubMenu = new JMenu("Schedule");
		JMenuItem appointmentMenuItem = new JMenuItem("Appointment");
		
		appointmentMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAppointmentWindow();
			}
		});
		
		scheduleSubMenu.add(appointmentMenuItem);
		newMenu.add(scheduleSubMenu);
		
		JMenu createSubMenu = new JMenu("Create");
		JMenuItem createPatient = new JMenuItem("Patient");
		
		createPatient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog createPatientDialog = new CreatePatientDialog(ViewResolver.getRoot());
				createPatientDialog.setVisible(true);
			}
		});
		
		createSubMenu.add(createPatient);
		newMenu.add(createSubMenu);
		
		add(newMenu);
		
	}

	protected void openAppointmentWindow() {
		JDialog scheduleAppointmentDialog = null;
	}
	
	

}
