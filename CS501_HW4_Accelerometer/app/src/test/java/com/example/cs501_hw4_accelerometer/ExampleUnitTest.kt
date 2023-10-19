package com.example.cs501_hw4_accelerometer

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_companionValue() {
        assertEquals(MainActivity.threshold, 1.0f)
        // no unit test, nothing to be tested
    }
}