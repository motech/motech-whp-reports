package org.motechproject.whp.reports.domain.paging;

import org.springframework.data.domain.Sort;

public class ProviderReminderCallLogPageRequest extends PageRequest {

    public ProviderReminderCallLogPageRequest(int pageNumber, int pageSize) {
        super(pageNumber, pageSize);
    }

    @Override
    public Sort getSort() {
        return new Sort(new Sort.Order(Sort.Direction.DESC, "attemptTime"));
    }


}
