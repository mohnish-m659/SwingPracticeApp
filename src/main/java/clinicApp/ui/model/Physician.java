package clinicApp.ui.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Physician {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String firstName;
	String lastName;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(
			name = "doctor_patients",
			joinColumns = @JoinColumn(name = "doctor_id"),
			inverseJoinColumns = @JoinColumn(name = "patient_id")
			)
	List<Patient> patients;
	
	public Physician() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	
	public boolean addPatient(Patient patient) {
		return this.patients.add(patient);
	}
	

}
