package org.motechproject.whp.reports.bigquery.response;

import java.util.List;
import java.util.Map;

public class QueryResult {
    List<Map<String,Object>> rows;

    public QueryResult(List<Map<String, Object>> rows) {
        this.rows = rows;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }
}
