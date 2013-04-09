package org.motechproject.whp.reports.repository;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.dimension.ReasonForClosure;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ReasonForClosureRepositoryIT  extends IntegrationTest {

    @Autowired
    ReasonForClosureRepository reasonForClosureRepository;

    @Test
    public void shouldSeededFetchReasonForClosures() {
        List<ReasonForClosure> all = reasonForClosureRepository.findAll();
        assertThat(all.size(), greaterThan(0));
    }

    @Test
    public void shouldFetchReasonForClosureByCode() {
        ReasonForClosure sputumMappedToPatient = reasonForClosureRepository.findOne("0");

        assertNotNull(sputumMappedToPatient);
        assertThat(sputumMappedToPatient.getText(), is("Sputum container mapped to patient"));
    }


}
