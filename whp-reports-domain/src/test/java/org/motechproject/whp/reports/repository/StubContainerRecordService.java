package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.container.ContainerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StubContainerRecordService {

    @Autowired
    private ContainerRecordRepository containerRecordRepository;

    public List<ContainerRecord> allContainerRecords(){
        List<ContainerRecord> all = containerRecordRepository.findAll();
        for(ContainerRecord containerRecord : all){
            containerRecord.getReasonForClosure().getCode();   //load reason for closure
            containerRecord.getAlternateDiagnosis().getCode(); //load alternate diagnosis
        }

        return all;
    }

}
