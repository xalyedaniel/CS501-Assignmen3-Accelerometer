package com.example.cs501_hw4_accelerometer


import android.view.View
import android.widget.SeekBar
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class TestThreshold {


    private lateinit var scenario: ActivityScenario<MainActivity>
    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }
    @After
    fun tearDown() {
        scenario.close()
    }

    //source: https://stackoverflow.com/questions/65390086/androidx-how-to-test-slider-in-ui-tests-espresso

    private fun withValue(expectedValue: Int): Matcher<View?> {
        return object : BoundedMatcher<View?, SeekBar>(SeekBar::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("expected: $expectedValue")
            }

            override fun matchesSafely(seekBar: SeekBar?): Boolean {
                return seekBar?.progress == expectedValue
            }
        }
    }


    //source: https://stackoverflow.com/questions/23659367/espresso-set-seekbar


    fun setValue(progress: Int): ViewAction? {
        return object : ViewAction {
            override fun perform(uiController: UiController, view: View) {
                val seekBar = view as SeekBar
                seekBar.progress = progress
            }

            override fun getDescription(): String {
                return "Set a progress on a SeekBar"
            }

            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(SeekBar::class.java)
            }
        }
    }

    @Test
    fun testThresholdValue(){
        scenario.use{
            val test = 10
            onView(withId(R.id.sensitivitySeekBar)).perform(setValue(test))
            assertEquals(MainActivity.threshold, test/10.0f)
        }

    }

}