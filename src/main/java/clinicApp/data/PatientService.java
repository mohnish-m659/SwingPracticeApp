package clinicApp.data;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import clinicApp.ui.model.Patient;
import clinicApp.util.HibernateUtil;

public class PatientService {
	
	private static PatientService instance;
	
	private static final String getPatientCountQuery = "from Patient";
	private static final String getPatientById = "select p from Patient p where p.id = :patient_id";
	
	private PatientService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void savePatient(Patient patient) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(patient);
			transaction.commit();
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public int getPatientCount() {
		List<Patient> patients = getAllPatients();
		return patients.size();
	}
	
	public Patient getPatientWithId(int id) {
		Transaction transaction = null;
		Patient patient = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			Query query = session.createQuery(getPatientById);
			query.setParameter("patient_id", id);
			patient = (Patient) query.getSingleResult();
			transaction.commit();
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return patient;
	}
	
	public List<Patient> getAllPatients(){
		Transaction transaction = null;
		List<Patient> patients = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			Query query = session.createQuery(getPatientCountQuery);
			patients = query.getResultList();
			transaction.commit();
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return patients;
	}
	
	public static PatientService getInstance() {
		if(instance == null) {
			instance = new PatientService();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		 PatientService patientService = new PatientService();

	        Patient patient = new Patient();
	        patient.setId(0);
	        patient.setFirstName("John");
	        patient.setLastName("Doe");
	        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));

	        patientService.savePatient(patient);

	        System.out.println("Patient saved successfully!");
	        
	        int n = patientService.getPatientCount();
	        System.out.println(n);
	        
	        Patient pat = patientService.getPatientWithId(1);
	        System.out.println(pat == null ? "Null result" : pat.toString());
	}

}
