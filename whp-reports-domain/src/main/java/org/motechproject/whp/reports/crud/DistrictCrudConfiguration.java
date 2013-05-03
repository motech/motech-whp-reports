package org.motechproject.whp.reports.crud;

import org.motechproject.crud.builder.CrudModelBuilder;
import org.motechproject.crud.service.CrudEntity;
import org.motechproject.whp.reports.domain.dimension.District;
import org.motechproject.whp.reports.repository.DistrictRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.motechproject.crud.builder.CrudEntityBuilder.newCrudEntity;

@Configuration
public class DistrictCrudConfiguration {

    @Bean
    CrudEntity<District> build(DistrictRepository districtRepository){
        return newCrudEntity(District.class)
                .jpaCrudRepository(districtRepository, String.class)
                .model(CrudModelBuilder.jpaCrudModel()
                        .displayFields("name")
                        .filterFields("name")
                        .idFieldName("name"))
                .build();
    }
}
