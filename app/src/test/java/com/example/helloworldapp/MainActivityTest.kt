package com.example.helloworldapp

import org.junit.Assert.*
import org.junit.Test

class MainActivityTest {

    @Test
    fun additionReturnCorrectResult() {
        val actualResult = MainActivity().add(2.0, 3.0)
        assertEquals(5.0, actualResult, 0.0)
    }

    @Test
    fun subtractionReturnCorrectResult() {
        val actualResult = MainActivity().subtract(2.0, 3.0)
        assertEquals(-1.0, actualResult, 0.0)
    }
    @Test
    fun multiplyReturnCorrectResult() {
        val actualResult = MainActivity().multiply(2.0, 3.0)
        assertEquals(6.0, actualResult, 0.0)
    }

    @Test
    fun divisionReturnCorrectResult() {
        val actualResult = MainActivity().divide(2.0, 3.0)
        assertEquals(.6666666666666666, actualResult, 0.0)
    }


}