package org.motechproject.whp.reports.query;

import org.motechproject.bigquery.response.QueryResult;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryResultBuilder {

    private String[] columns;
    private List<Object[]> values = new ArrayList<>();

    public QueryResultBuilder(String... columns){
        this.columns = columns;
    }

    public QueryResultBuilder row(Object... rowValues){
        this.values.add(rowValues);
        return this;
    }

    public QueryResult build(List<String> dimensions){
        List<Map<String, Object>> rows = new ArrayList<>();
        for(int i=0, j=0; i < dimensions.size(); i++){
            Map<String, Object> row1 = new LinkedCaseInsensitiveMap<>();
            String primaryColumn = dimensions.get(i);
            Object[] rowValues;
            if(values.size() > j && primaryColumn.equals(values.get(j)[0])){
                rowValues = values.get(j++);
            } else {
                rowValues = defaultValues(primaryColumn, columns.length);
            }
            for(int k=0;k<columns.length; k++){
                row1.put(columns[k], rowValues[k]);
            }
            rows.add(row1);

        }
        return new QueryResult(rows);
    }

    public QueryResult build(){
        List<Map<String, Object>> rows = new ArrayList<>();
        for(int i = 0 ; i < values.size(); i ++){
            Map<String, Object> row1 = new LinkedCaseInsensitiveMap<>();
            Object[] rowValues = values.get(i);
            for(int j=0;j<columns.length; j++){
                row1.put(columns[j], rowValues[j]);
            }
            rows.add(row1);
        }
        return new QueryResult(rows);
    }

    private Object[] defaultValues(String primaryColumn, int length) {
        Object [] values = new Object[length];
        values[0] = primaryColumn;
        for(int i=1;i<length;i++) {
            values[i] = Long.valueOf(0);
        }
        return values;
    }

}
