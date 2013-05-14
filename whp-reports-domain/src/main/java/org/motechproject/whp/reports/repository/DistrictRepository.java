package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.dimension.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, String> {
    List<District> findByName(String name);
}
