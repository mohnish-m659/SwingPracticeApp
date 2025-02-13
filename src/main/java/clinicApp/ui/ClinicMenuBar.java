package clinicApp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import clinicApp.ui.views.AddPatientDialogSwt;

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
//				JDialog createPatientDialog = new CreatePatientDialog(ViewResolver.getRoot());
//				createPatientDialog.setVisible(true);
				try {
					SwingUtilities.invokeLater(() -> {
                    Display display = new Display();
                    Shell shell = new Shell(display, SWT.ON_TOP);
                    shell.addListener(SWT.Show, event -> shell.forceFocus());
                    shell.addDisposeListener(new DisposeListener() {
						
						@Override
						public void widgetDisposed(DisposeEvent e) {
							display.dispose();
						}
					});
                    AddPatientDialogSwt dialog = new AddPatientDialogSwt(shell);
                    dialog.open();
                });} catch (Exception e2) {
					e2.printStackTrace();
				}
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
