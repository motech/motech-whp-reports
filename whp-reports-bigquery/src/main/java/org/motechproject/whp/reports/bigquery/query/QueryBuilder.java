package org.motechproject.whp.reports.bigquery.query;

import org.motechproject.whp.reports.bigquery.model.FilterParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryBuilder {

    private VelocityTemplateBuilder velocityTemplateBuilder;

    @Autowired
    public QueryBuilder(VelocityTemplateBuilder velocityTemplateBuilder) {
        this.velocityTemplateBuilder = velocityTemplateBuilder;
    }

    public String build(String sqlTemplate, FilterParams filterParams) throws Exception {
        return velocityTemplateBuilder.evaluate(sqlTemplate, filterParams);
    }
}
