package org.motechproject.whp.reports.repository;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.dimension.AlternateDiagnosis;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AlternateDiagnosisRepositoryIT  extends IntegrationTest {

    @Autowired
    AlternateDiagnosisRepository alternateDiagnosisRepository;

    @Test
    public void shouldFetchSeededAlternateDiagnosis() {
        List<AlternateDiagnosis> all = alternateDiagnosisRepository.findAll();
        assertThat(all.size(), greaterThan(0));
    }

    @Test
    public void shouldFetchAlternateDiagnosisByCode() {
        AlternateDiagnosis otherBacterialDisease = alternateDiagnosisRepository.findOne("1027");

        assertNotNull(otherBacterialDisease);
        assertThat(otherBacterialDisease.getText(), is("Other bacterial diseases,not elsewhere classified"));
    }


}
