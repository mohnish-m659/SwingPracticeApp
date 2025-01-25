package clinicApp.ui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

import org.h2.tools.Server;

import clinicApp.data.PatientService;
import clinicApp.ui.model.Patient;
import clinicApp.ui.model.Patient.Gender;

public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			createGui();
		});
	}

	private static void createGui() {
		JFrame root = new JFrame("ABC Clinic");
		root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		root.setSize(800, 600);
		try {
			//Access H2 console on localhost:8082
            Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            initDummyData();
        } catch (Exception e) {
            e.printStackTrace();
        }
		JMenuBar menuBar = new ClinicMenuBar();
		
		root.setJMenuBar(menuBar);
		root.setVisible(true);
		ViewResolver.setRoot(root);
		ViewResolver.setDefaultView();
	}

	private static void initDummyData() {
		Patient p = new Patient();
		p.setFirstName("Mark");
		p.setLastName("Buck");
		p.setAge(45);
		p.setGender(Gender.MALE);
		
		PatientService.getInstance().savePatient(p);
		
		p.setFirstName("Joanna");
		p.setLastName("Wright");
		p.setAge(34);
		p.setGender(Gender.FEMALE);
		
		PatientService.getInstance().savePatient(p);
	}

}
