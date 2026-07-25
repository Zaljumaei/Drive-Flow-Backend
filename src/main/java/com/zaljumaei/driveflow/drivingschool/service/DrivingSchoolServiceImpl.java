package com.zaljumaei.driveflow.drivingschool.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolMapper;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolRequest;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolResponse;
import com.zaljumaei.driveflow.drivingschool.repository.DrivingSchoolRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class DrivingSchoolServiceImpl implements DrivingSchoolService {

    private final DrivingSchoolRepository drivingSchoolRepository;

    private final DrivingSchoolMapper drivingSchoolMapper;

    /**
     * TODO Creating drivingSchool with same name should be later allowed (like same school but another branchOffice),it must be then compared if the address the same or not.
     * Create new DrivingSchool entity and save it in the database after checking if there is not the same DrivingSchool .
     *
     * @param request the infos of drivingSchool to be created.
     * @return
     */
    @Override
    public DrivingSchoolResponse create(DrivingSchoolRequest request) {
        checkIfExistsByName(request.name());
        DrivingSchool drivingSchool = this.drivingSchoolMapper.toDrivingSchool(request);
        DrivingSchoolResponse drivingSchoolResponse = this.drivingSchoolMapper.toDrivingSchoolResponse(this.drivingSchoolRepository.save(drivingSchool));
        return drivingSchoolResponse;
    }

    /**
     * update the infos of drivingSchool that are defined in request,
     * another data like Student, instructor... have another methode.
     *
     * @param id      the id of the school
     * @param request the request with information, that should be updated.
     * @return
     */
    @Override
    public DrivingSchoolResponse update(Long id, DrivingSchoolRequest request) {
        DrivingSchool existedDrivingSchool = checkIfExistsById(id);
        DrivingSchool updatedDrivingSchool =  this.drivingSchoolMapper.updateMapperDrivingSchool(request, existedDrivingSchool);
        DrivingSchoolResponse drivingSchoolResponse = this.drivingSchoolMapper.toDrivingSchoolResponse(this.drivingSchoolRepository.save(updatedDrivingSchool));
        return drivingSchoolResponse;
    }

    /**
     * find a DrivingSchool by its id and return the Dto for drivingSchool, not the entity itself.
     * @param id the id of drivingSchool
     * @return drivingSchoolResponse
     */
    @Override
    public DrivingSchoolResponse findById(Long id) {
        DrivingSchool drivingSchool = checkIfExistsById(id);
        return this.drivingSchoolMapper.toDrivingSchoolResponse(drivingSchool);
    }

    /**
     * Find a DrivingSchool by its name
     * @param drivingSchoolName name of DrivingSchool
     * @return dto of drivingSchool
     */
    @Override
    public DrivingSchoolResponse findByName(String drivingSchoolName) {
        Optional<DrivingSchool> drivingSchool = this.drivingSchoolRepository.findByName(drivingSchoolName);
        if (drivingSchool.isPresent()) {
            return drivingSchoolMapper.toDrivingSchoolResponse(drivingSchool.get());
        }else {
            log.error("DrivingSchool Not Found with name {}", drivingSchoolName);
            throw new EntityNotFoundException("DrivingSchool not found with name.");
        }
    }

    @Override
    public List<DrivingSchoolResponse> findAllDrivingSchools() {
        List<DrivingSchool> drivingSchools = this.drivingSchoolRepository.findAll();
        if (drivingSchools.isEmpty()) {
            log.error("DrivingSchool Not Found");
            throw new EntityNotFoundException("DrivingSchool not found");
        }else  {
            log.info("DrivingSchools Found: "+drivingSchools.size());
            return drivingSchools.stream().map(drivingSchoolMapper::toDrivingSchoolResponse).collect(Collectors.toList());
        }
    }

    /**
     * delete method
     * @param id of the drivingSchool, that will be deleted.
     */
    @Override
    public void delete(Long id) {
        DrivingSchool drivingSchool = checkIfExistsById(id);
        this.drivingSchoolRepository.delete(drivingSchool);
    }

    //-------------------------------Helper Methods-------------------------------
    /**
     * check if DrivingSchool exist by name, because we do not have id from request.
     * @param drivingSchoolName the name, which will search after it.
     */
    private void checkIfExistsByName(String  drivingSchoolName) {
        Optional<DrivingSchool> drivingSchool = this.drivingSchoolRepository.findByName(drivingSchoolName);
        if (drivingSchool.isPresent()) {
            log.debug("Driving school already exists with name {}", drivingSchoolName);
            throw new EntityExistsException("Driving school already exists");
        }
    }

    /**
     * check if the drivingSchool exist by id and return it otherwise exception will be arisen.
     * @param id of DrivingSchool.
     * @return the drivingSchool if founded.
     */
    private DrivingSchool checkIfExistsById(Long id) {
        Optional<DrivingSchool> drivingSchool = this.drivingSchoolRepository.findById(id);
        if (drivingSchool.isEmpty()) {
            log.debug("No driving school found with id {}", id);
            throw new EntityNotFoundException("No driving school found with id " + id);
        }
        return drivingSchool.get();
    }
}
