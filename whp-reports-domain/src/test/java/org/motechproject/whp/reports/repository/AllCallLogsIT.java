package org.motechproject.whp.reports.repository;

import org.hamcrest.core.Is;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.domain.CallLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportingDomainContext.xml")
public class AllCallLogsIT {

    @Autowired
    AllCallLogs allCallLogs;
    
    @Autowired
    PlatformTransactionManager transactionManager;
    
    @Test
    public void shouldSaveCallLog() {
        final CallLog callLog = new CallLog();
        callLog.setCalledBy("provider");
        callLog.setEndTime(new DateTime().plusMinutes(20).toDate());
        callLog.setStartTime(new DateTime().plusMinutes(20).toDate());
        callLog.setProviderId("providerId");

        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(new TransactionCallback<CallLog>() {
            @Override
            public CallLog doInTransaction(TransactionStatus transactionStatus) {
                allCallLogs.save(callLog);
                return callLog;
            }
        });


    }

    @Test
    public void shouldRetrieveSavedCallLog() throws Exception {
        final CallLog callLog = new CallLog();
        callLog.setCalledBy("provider");
        callLog.setEndTime(new DateTime().plusMinutes(20).toDate());
        callLog.setStartTime(new DateTime().plusMinutes(20).toDate());
        callLog.setProviderId("providerId");

        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(new TransactionCallback<CallLog>() {
            @Override
            public CallLog doInTransaction(TransactionStatus transactionStatus) {
                allCallLogs.save(callLog);
                return callLog;
            }
        });

        CallLog callLogFromDatabase = transactionTemplate.execute(new TransactionCallback<CallLog>() {
            @Override
            public CallLog doInTransaction(TransactionStatus transactionStatus) {
                allCallLogs.get(callLog.getId());
                return callLog;
            }
        });
        assertThat(callLogFromDatabase, is(callLog));
    }
}
