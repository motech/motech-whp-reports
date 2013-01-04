package org.motechproject.whp.reports.domain.paging;

import org.springframework.data.domain.Sort;

public class ContainerRecordPageRequest extends PageRequest {

    public ContainerRecordPageRequest(int pageNumber, int pageSize) {
        super(pageNumber, pageSize);
    }

    @Override
    public Sort getSort() {
        return new Sort(new Sort.Order(Sort.Direction.ASC, "issuedOn"));
    }
}
