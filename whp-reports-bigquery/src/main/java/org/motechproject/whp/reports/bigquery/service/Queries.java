package org.motechproject.whp.reports.bigquery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class Queries {

    @Autowired
    @Qualifier("bigQueryProperties")
    private Properties queryProperties;


    public String getQuery(String queryName){
        return queryProperties.getProperty(queryName);
    }

    public List<String> all(){
        List<String> queries = new ArrayList<>();
        for(String queryName : queryProperties.stringPropertyNames()){
            queries.add(queryProperties.getProperty(queryName));
        }

        return queries;
    }

}
