package com.aditya.github

import com.aditya.github.utils.DateUtils
import org.junit.Assert
import org.junit.Test

/**
 * Unit test for DateUtils
 */
class DateUtilsTest {
    @Test
    fun getFormattedDate() {
        Assert.assertEquals("2020-02-29", DateUtils.getFormattedDate("2020-02-29T17:36:40Z"))
    }
}