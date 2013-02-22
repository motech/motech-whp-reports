package org.motechproject.whp.reports.mapper;

import org.motechproject.whp.reports.contract.adherence.AdherenceRecordDTO;
import org.motechproject.whp.reports.domain.adherence.AdherenceRecord;
import org.springframework.stereotype.Component;

@Component
public class AdherenceRecordMapper {

    public AdherenceRecord map(AdherenceRecordDTO adherenceRecordDTO) {
        AdherenceRecord adherenceRecord = new AdherenceRecord();
        adherenceRecord.setDistrict(adherenceRecordDTO.getDistrict());
        adherenceRecord.setPatientId(adherenceRecordDTO.getPatientId());
        adherenceRecord.setPillDate(adherenceRecordDTO.getPillDate());
        adherenceRecord.setPillStatus(adherenceRecordDTO.getPillStatus());
        adherenceRecord.setProviderId(adherenceRecordDTO.getProviderId());
        adherenceRecord.setTbId(adherenceRecordDTO.getTbId());
        adherenceRecord.setTherapyId(adherenceRecordDTO.getTherapyId());

        return adherenceRecord;
    }
}
