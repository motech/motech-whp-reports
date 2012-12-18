package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.repository.MotechJpaRepository;


public class BaseService<Repository extends MotechJpaRepository<Domain>, Domain> {

    private Repository repository;

    public BaseService() {
    }

    public BaseService(Repository repository) {
        this.repository = repository;
    }

    public void save(Domain record) {
        repository.save(record);
    }
}

