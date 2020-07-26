package com.am;

import com.am.profile.platform.comm.Utils;
import org.junit.Test;

public class TestMain {

    @Test
    public void testGetLabelId() {
        System.out.println(Utils.getLabelId("1","1","03", 101020311l));
    }

}
