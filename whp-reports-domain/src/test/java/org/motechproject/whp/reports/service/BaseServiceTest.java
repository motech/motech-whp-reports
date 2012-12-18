package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.repository.MotechJpaRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class BaseServiceTest {

    @Mock
    private MotechJpaRepository<Object> repository;

    private BaseService<MotechJpaRepository<Object>, Object> service;

    @Before
    public void setUp() {
        initMocks(this);
        service = new BaseService<>(repository);
    }

    @Test
    public void shouldSaveDomainObject() {
        String object = "someObject";
        service.save(object);
        verify(repository).save(object);
    }
}
