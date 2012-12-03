package org.motechproject.whp.reports.domain.paging;

import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@EqualsAndHashCode
public class ContainerRecordPageable implements Pageable {
    private int pageNumber;
    private  int pageSize;
    private int offset;

    public ContainerRecordPageable(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.offset = offset;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getOffset() {
        return (pageNumber - 1) * pageSize;
    }

    @Override
    public Sort getSort() {
        return new Sort(new Sort.Order(Sort.Direction.ASC, "issuedOn"));
    }

}
