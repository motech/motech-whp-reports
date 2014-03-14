package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.contract.adherence.AdherenceRecordDTO;
import org.motechproject.whp.reports.domain.adherence.AdherenceRecord;
import org.motechproject.whp.reports.mapper.AdherenceRecordMapper;
import org.motechproject.whp.reports.repository.AdherenceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdherenceRecordService {

    private AdherenceRecordMapper adherenceRecordMapper;
    private AdherenceRecordRepository adherenceRecordRepository;

    AdherenceRecordService() {
        //for spring proxy
    }

    @Autowired
    public AdherenceRecordService(AdherenceRecordMapper adherenceRecordMapper, AdherenceRecordRepository adherenceRecordRepository) {
        this.adherenceRecordMapper = adherenceRecordMapper;
        this.adherenceRecordRepository = adherenceRecordRepository;
    }

    public void save(AdherenceRecordDTO adherenceRecordDTO) {
        AdherenceRecord adherenceRecord = adherenceRecordMapper.map(adherenceRecordDTO);
        adherenceRecordRepository.save(adherenceRecord);
    }

    public void delete(List<AdherenceRecordDTO> adherenceRecordDTOs) {
        for (AdherenceRecordDTO adherenceRecordDTO : adherenceRecordDTOs) {
            AdherenceRecord adherenceRecord = adherenceRecordMapper.map(adherenceRecordDTO);
            adherenceRecordRepository.delete(adherenceRecord);
        }
    }
}
