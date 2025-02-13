package clinicApp.ui.views;

import java.time.LocalDate;
import java.time.Period;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import clinicApp.data.PatientService;
import clinicApp.ui.ViewResolver;
import clinicApp.ui.model.Patient;
import clinicApp.ui.model.Patient.Gender;

public class AddPatientDialogSwt extends Dialog{
	
	Text firstNameText;
	Label firstNameLabel;
	Text lastNameText;
	Label lastNameLabel;
	Label ageLabel;
	Text ageText;
	Label genderLabel;
	Combo genderCombo;
	Label dobLabel;
	DateTime dobPicker;
	
	PatientService service = PatientService.getInstance();

	public AddPatientDialogSwt(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Add Patient");
		super.configureShell(newShell);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Add", true);
		createButton(parent, IDialogConstants.CANCEL_ID, "Cancel", false);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		switch (buttonId) {
		case IDialogConstants.OK_ID:
			service.savePatient(getPatient());
		case IDialogConstants.CANCEL_ID:
			refreshUI();
			close();
			break;
		}
	}

	private void refreshUI() {
		ViewResolver.refresh();
	}

	private Patient getPatient() {
		Patient patient = new Patient();
		patient.setFirstName(firstNameText.getText());
		patient.setLastName(lastNameText.getText());
		patient.setGender(getGender(genderCombo.getText()));
		LocalDate date = getSelectedDate();
		patient.setDateOfBirth(date);
		String age = calculateAge(date);
        
        patient.setAge(age);
        System.out.println("Patient Added: " + patient.toString());
		return patient;
	}

	private LocalDate getSelectedDate() {
		return LocalDate.of(dobPicker.getYear(), dobPicker.getMonth() + 1, dobPicker.getDay());
	}

	private String calculateAge(LocalDate date) {
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(date, currentDate);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        return (years + "Y-" + months + "M-" + days + "D");
	}

	private Gender getGender(String text) {
		if(text.equals(Gender.MALE.name())) {
			return Gender.MALE;
		}else if(text.equals(Gender.FEMALE.name())) {
			return Gender.FEMALE;
		}
		return Gender.NOT_SELECTED;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = 10;
		layout.marginWidth = 15;
		composite.setLayout(layout);
		initPatientForm(composite);
		
		return composite;
	}

	private void initPatientForm(Composite composite) {
		Composite container = new Composite(composite, SWT.None);
		container.setLayout(new GridLayout(2, false));
		
		firstNameLabel = new Label(container, SWT.None);
		firstNameLabel.setText("First Name");
		
		firstNameText = new Text(container, SWT.SINGLE);
		firstNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		lastNameLabel = new Label(container, SWT.None);
		lastNameLabel.setText("Last Name");
		
		lastNameText = new Text(container, SWT.SINGLE);
		lastNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		ageLabel = new Label(container, SWT.None);
		ageLabel.setText("Age");
		
		ageText = new Text(container, SWT.READ_ONLY);
		ageText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		genderLabel = new Label(container, SWT.None);
		genderLabel.setText("Gender");
		
		genderCombo = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		genderCombo.setItems(new String[]{Gender.MALE.name(), Gender.FEMALE.name()});
		genderCombo.select(0);
		
		dobLabel = new Label(container, SWT.None);
		dobLabel.setText("Date of Birth");
		
		dobPicker = new DateTime(container, SWT.CALENDAR | SWT.LONG);
		dobPicker.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				ageText.setText(calculateAge(getSelectedDate()));
			}
			
		});
		
		container.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
	}
	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		AddPatientDialogSwt d = new AddPatientDialogSwt(shell);
		d.open();
	}

}
