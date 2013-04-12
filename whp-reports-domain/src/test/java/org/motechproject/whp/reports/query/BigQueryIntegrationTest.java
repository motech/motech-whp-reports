package org.motechproject.whp.reports.query;

import org.motechproject.bigquery.response.QueryResult;
import org.motechproject.whp.reports.IntegrationTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BigQueryIntegrationTest extends IntegrationTest {

    abstract Map<String, Object> row(String dimension, int count);

    protected QueryResult buildExpectedQueryResult(List<Map<String, Object>> expectations, List<String> dimensions) {
        List<Map<String, Object>> rows = new ArrayList<>();
        for(int i =0, j=0 ; i < dimensions.size(); i++ ){
            String district = dimensions.get(i);
            if(expectations.size() > j && expectations.get(j).containsValue(district)){
                rows.add(expectations.get(j));
                j++;
            } else {
                rows.add(row(district, 0));
            }
        }
        return new QueryResult(rows);
    }

}
