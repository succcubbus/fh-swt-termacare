package de.adesso.termacare.service;

import de.adesso.termacare.database.entity.Address;
import de.adesso.termacare.database.entity.Gender;
import de.adesso.termacare.database.entity.Medication;
import de.adesso.termacare.database.entity.Patient;
import de.adesso.termacare.database.dao.MedicationDao;
import de.adesso.termacare.database.dao.PatientDao;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by kaiser on 09.05.2017.
 */
public class PatientService {
    private static PatientService INSTANCE;
    
    public static PatientService getInstance() {
        return INSTANCE == null ? INSTANCE = new PatientService() : INSTANCE;
    }
    
    private PatientService() {
    
    }
    
    private PatientDao patients;
    private MedicationDao medications;
    
    public List<Patient> getPatients() {
        return patients.list();
    }
    
    public void createOrUpdatePatient(long id, String title, Gender gender, String givenName, String familyName, Address billing, Address living) {
        Patient patient = new Patient();
        
        patient.setId(id);
        patient.setTitle(title);
        patient.setGender(gender);
        patient.setGivenName(givenName);
        patient.setFamilyName(familyName);
        patient.setBillingAddress(billing);
        patient.setLivingAddress(living);
        
        patients.save(patient);
    }
    
    public List<Medication> getMedications(long patientId) {
        return medications.list().stream()
                .filter(med -> patientId == med.getPatient().getId())
                .collect(toList());
    }
    
    public void deletePatient(long patientId) {
        patients.delete(patientId);
    }
}
