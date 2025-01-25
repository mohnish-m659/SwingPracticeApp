package clinicApp.ui.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	Patient patient;
	Physician physician;
	LocalDate date;
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Physician getPhysician() {
		return physician;
	}
	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	

}
