package de.adesso.termacare.database.services;

import de.adesso.termacare.data.entity.Doctor;
import de.adesso.termacare.data.entity.Gender;
import de.adesso.termacare.data.entity.Medication;
import de.adesso.termacare.data.entity.Patient;
import de.adesso.termacare.database.repo.DoctorRepo;
import de.adesso.termacare.database.repo.MedicationRepo;
import de.adesso.termacare.database.repo.PatientRepo;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by kaiser on 09.05.2017.
 */
public class DoctorService {
    private DoctorRepo doctors;
    private PatientRepo patients;
    private MedicationRepo medications;
    
    public List<Patient> getPatients() {
        return patients.list();
    }
    
    public void createDoctor(String title, Gender gender, String givenName, String familyName) {
        Doctor doctor = new Doctor();
        
        doctor.setTitle(title);
        doctor.setGender(gender);
        doctor.setGivenName(givenName);
        doctor.setFamilyName(familyName);
        
        doctors.add(doctor);
    }
    
    public List<Medication> getMedications(int doctorId) {
        Doctor doctor = doctors.getByID(doctorId);
        if (doctor == null)
            throw new IllegalArgumentException("doctor does not exist");
        
        return medications.list().stream()
                .filter(med -> med.getDoctors().contains(doctor))
                .collect(toList());
    }
    
    public List<Patient> getPatients(int doctorId) {
        return getMedications(doctorId).stream()
                .map(Medication::getPatient)
                .collect(toList());
    }
    
    public void deleteDoctor(int doctorId) {
        doctors.delete(doctorId);
    }
}
