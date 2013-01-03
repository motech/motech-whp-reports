package org.motechproject.whp.reports.domain.paging;

import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@EqualsAndHashCode
public class MostRecentProviderReminderCallLog implements Pageable {

    @Override
    public int getPageNumber() {
        return 1;
    }

    @Override
    public int getPageSize() {
        return 1;
    }

    @Override
    public int getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return new Sort(new Sort.Order(Sort.Direction.DESC, "attemptTime"));
    }
}
