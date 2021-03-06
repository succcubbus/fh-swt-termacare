package de.adesso.termacare.gui.controller;

import de.adesso.termacare.database.entity.Doctor;
import de.adesso.termacare.gui.construct.AbstractOverviewController;
import de.adesso.termacare.gui.dto.DtoAbstractData;
import de.adesso.termacare.gui.dto.DtoDoctor;
import de.adesso.termacare.gui.view.DoctorOverview;
import de.adesso.termacare.service.DoctorService;
import de.adesso.termacare.service.PatientService;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static de.adesso.termacare.util.DependencyInjector.getInstance;
import static java.util.stream.Collectors.toList;

@Slf4j
public class DoctorOverviewController extends AbstractOverviewController<DoctorOverview, DoctorEditController>{

	private DoctorService doctorService;
	private PatientService patientService;

	@Override
	public void init(Stage stage, Scene scene){
		super.init(new DoctorOverview(this, stage, scene));
		fillTableWithColumns();
		loadDoctorsToTable();
	}

    @Override
    public <I extends DtoAbstractData> DoctorEditController initEditController(I focusedItem){
	    DoctorEditController controller = initEditController();
	    controller.setDoctor((DtoDoctor) focusedItem);
        controller.init(stage, scene);
	    return controller;
    }

    @Override
    public DoctorEditController initEditController(){
        return getInstance(DoctorEditController.class);
    }

    private void fillTableWithColumns() {
        view.getTableViewController().generateColumnFor("gender");
        view.getTableViewController().generateColumnFor("givenName", 100, 0);
        view.getTableViewController().generateColumnFor("familyName", 100, 0);
        view.getTableViewController().generateColumnFor("amountOfPatients", 200, 0);
    }

    private void loadDoctorsToTable() {
        List<DtoDoctor> doctors = doctorService.getDoctors().stream().map(this::doctorToDao).collect(toList());

        log.debug("loaded " + doctors.size() + " doctorIds");

        view.getTableViewController().addAll(doctors);
    }

    private DtoDoctor doctorToDao(Doctor doctor) {
        DtoDoctor dao = doctor.toDAO();
        dao.setAmountOfPatients(patientService.getPatients(dao.getId()).size());
        return dao;
    }

    public void backToOverview() {
        MedicationOverviewController oc = getInstance(MedicationOverviewController.class);
        oc.init(stage, scene);
        oc.show();
    }
}
