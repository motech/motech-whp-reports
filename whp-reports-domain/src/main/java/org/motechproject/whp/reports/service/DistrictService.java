package org.motechproject.whp.reports.service;


import org.motechproject.whp.reports.domain.dimension.District;
import org.motechproject.whp.reports.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DistrictService {

    DistrictRepository districtRepository;

    public DistrictService() {
    }

    @Autowired
    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }
}
