package com.itech.start;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void sum1() {
        Assert.assertEquals(5, Main.sum(4, 1));
    }

    @Test
    public void sum2() {
        Assert.assertEquals(3, Main.sum(2, 1));
    }
}