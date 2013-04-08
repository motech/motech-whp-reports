package org.motechproject.whp.reports.service;

import org.motechproject.crud.service.JpaCrudEntity;
import org.motechproject.whp.reports.domain.dimension.District;
import org.motechproject.whp.reports.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class DistrictCrudService extends JpaCrudEntity<District, Long>{

    @Autowired
    public DistrictCrudService(DistrictRepository districtRepository) {
        super(districtRepository, Long.class);
    }

    @Override
    public List<String> getDisplayFields() {
        return asList("name");
    }

    @Override
    public List<String> getFilterFields() {
        return asList("name");
    }

    @Override
    public String getIdFieldName() {
        return "id";
    }

    @Override
    public Class getEntityType() {
        return District.class;
    }
}
