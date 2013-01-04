package org.motechproject.whp.reports.contract.enums;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class YesNoTest {

    @Test
    public void shouldReturnYesNoValueFromBoolean() {
        assertEquals(YesNo.Yes, YesNo.value(true));
        assertEquals(YesNo.No, YesNo.value(false));
        assertNull(YesNo.value(null));
    }

}
