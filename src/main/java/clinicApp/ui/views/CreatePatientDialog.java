package clinicApp.ui.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import clinicApp.data.PatientService;
import clinicApp.ui.ViewResolver;
import clinicApp.ui.model.Patient;
import clinicApp.ui.model.Patient.Gender;

public class CreatePatientDialog extends JDialog{
	
	ActionListener saveBtnActionListener;

	public CreatePatientDialog(JFrame parent) {
		super(parent, "Add Patient", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		
		initListeners();
		
		add(new JScrollPane(createContent()), BorderLayout.CENTER);
		add(createButtons(), BorderLayout.PAGE_END);
		setSize(300, 250);
	}

	private void initListeners() {
		saveBtnActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Patient p = new Patient();
				
				p.setFirstName(firstName.getText());
				p.setLastName(lastName.getText());
				//p.setAge((Double) age.getValue());
				
				p.setDateOfBirth(getSelectedDate());
				if(maleBtn.isSelected()) {
					p.setGender(Gender.MALE);
				}else if(femaleBtn.isSelected()){
					p.setGender(Gender.FEMALE);
				}else {
					p.setGender(Gender.NOT_SELECTED);
				}
				
				System.out.println("Saving " + p.toString());
				
				PatientService.getInstance().savePatient(p);
				ViewResolver.refresh();
				
				dispose();
			}
		};
	}
	
	private LocalDate getSelectedDate() {
        Date selectedDate = (Date) dob.getModel().getValue();
        if (selectedDate != null) {
            Instant instant = selectedDate.toInstant();
            return instant.atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }

	private Component createButtons() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 15));
		JButton save = new JButton("Save");
		JButton cancel = new JButton("Cancel");
		
		save.addActionListener(saveBtnActionListener);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		buttonPanel.add(cancel);
		buttonPanel.add(save);
		return buttonPanel;
	}

	private JPanel createContent() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JLabel firstName = new JLabel("Firstname");
		JLabel lastName = new JLabel("Lastname");
		JLabel age = new JLabel("Age");
		JLabel dob = new JLabel("DOB");
		JLabel gender = new JLabel("Gender");
		
		JPanel labelPanel = new JPanel(new GridLayout(0, 1, 0, 8));
		labelPanel.add(firstName);
		labelPanel.add(lastName);
		labelPanel.add(age);
		labelPanel.add(dob);
		labelPanel.add(gender);
		
		JPanel entryFieldPanel = createEntryFieldPanel();
		
		panel.add(labelPanel);
		panel.add(entryFieldPanel);
		return panel;
	}
	
	JTextField firstName;
	JTextField lastName;
	JSpinner age;
	JRadioButton maleBtn;
	JRadioButton femaleBtn;
	JDatePickerImpl dob;

	private JPanel createEntryFieldPanel() {
		JPanel entryFields = new JPanel(new GridLayout(0, 1));
		
		int columnSpan = 15;
		
		firstName = new JTextField(columnSpan);
		lastName = new JTextField(columnSpan);
		
		SpinnerNumberModel ageModel = new SpinnerNumberModel(0, 0, 150, 0.1);
		age = new JSpinner(ageModel);
		
		maleBtn = new JRadioButton(Gender.MALE.toString());
		femaleBtn = new JRadioButton(Gender.FEMALE.toString());
		
		ButtonGroup genderButton = new ButtonGroup();
		genderButton.add(maleBtn);
		genderButton.add(femaleBtn);
		JPanel genderButtons = new JPanel(new GridLayout(1, 0));
		genderButtons.add(maleBtn);
		genderButtons.add(femaleBtn);
		
		UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        dob = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		
		entryFields.add(firstName);
		entryFields.add(lastName);
		entryFields.add(age);
		entryFields.add(dob);
		entryFields.add(genderButtons);
		
		return entryFields;
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Dialog Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDialog content = new CreatePatientDialog(frame);
		content.setVisible(true);
		
		boolean isDisposed = false;
		while(!isDisposed) {
			if(!content.isVisible()) {
				isDisposed = true;
			}
		}
		frame.dispose();
	}

}
