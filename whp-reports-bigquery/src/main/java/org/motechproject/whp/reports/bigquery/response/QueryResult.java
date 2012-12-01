package org.motechproject.whp.reports.bigquery.response;

import java.util.List;
import java.util.Map;

public class QueryResult {
    List<Map<String,Object>> content;

    public QueryResult(List<Map<String, Object>> content) {
        this.content = content;
    }

    public List<Map<String, Object>> getContent() {
        return content;
    }
}
