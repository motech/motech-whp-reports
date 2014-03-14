package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.adherence.AdherenceRecordDTO;
import org.motechproject.whp.reports.domain.adherence.AdherenceRecord;
import org.motechproject.whp.reports.mapper.AdherenceRecordMapper;
import org.motechproject.whp.reports.repository.AdherenceRecordRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdherenceRecordServiceTest {

    AdherenceRecordService adherenceRecordService;
    @Mock
    AdherenceRecordMapper adherenceRecordMapper;
    @Mock
    AdherenceRecordRepository adherenceRecordRepository;

    @Before
    public void setUp() {
        initMocks(this);
        adherenceRecordService = new AdherenceRecordService(adherenceRecordMapper, adherenceRecordRepository);
    }

    @Test
    public void shouldPersistMappedAdherenceRecord() {
        AdherenceRecordDTO adherenceRecordDTO = new AdherenceRecordDTO();
        AdherenceRecord adherenceRecord = new AdherenceRecord();

        when(adherenceRecordMapper.map(adherenceRecordDTO)).thenReturn(adherenceRecord);

        adherenceRecordService.save(adherenceRecordDTO);

        verify(adherenceRecordMapper).map(adherenceRecordDTO);
        verify(adherenceRecordRepository).save(adherenceRecord);
    }

    @Test
    public void shouldDeleteMappedAdherenceRecord() {
        AdherenceRecordDTO adherenceRecordDTO = new AdherenceRecordDTO();
        AdherenceRecord adherenceRecord = new AdherenceRecord();
        List<AdherenceRecordDTO> adherenceRecordDTOs = new ArrayList<>();
        adherenceRecordDTOs.add(adherenceRecordDTO);
        when(adherenceRecordMapper.map(adherenceRecordDTO)).thenReturn(adherenceRecord);

        adherenceRecordService.delete(adherenceRecordDTOs);

        verify(adherenceRecordMapper).map(adherenceRecordDTO);
        verify(adherenceRecordRepository).delete(adherenceRecord);
    }
}
