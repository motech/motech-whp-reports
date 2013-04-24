package org.motechproject.whp.reports.query;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.core.io.Resource;

public class CustomDataSetLoader extends AbstractDataSetLoader {
    protected IDataSet createDataSet(Resource resource) throws Exception {
        FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(resource.getURL());
        return dataSet;
    }
}