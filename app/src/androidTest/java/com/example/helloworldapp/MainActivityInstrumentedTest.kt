package com.example.helloworldapp

import android.content.Intent
import android.util.Log
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    var mRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        val intent = Intent()
        mRule.launchActivity(intent)
        val activity = mRule.activity
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.helloworldapp", appContext.packageName)
    }

    @Test
    fun textEditForInput1IsExist() {
        Espresso.onView(ViewMatchers.withId(R.id.input1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun textEditForInput2IsExist() {
        Espresso.onView(ViewMatchers.withId(R.id.input2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun buttonForPerformAdditionIsExist() {
        Espresso.onView(ViewMatchers.withId(R.id.addition)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun buttonForPerformSubtractionIsExist() {
        Espresso.onView(ViewMatchers.withId(R.id.subtraction)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun buttonForPerformMultiplicationIsExist() {
        Espresso.onView(ViewMatchers.withId(R.id.multiplication)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun buttonForPerformDivisionIsExist() {
        Espresso.onView(ViewMatchers.withId(R.id.division)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun textViewForShowingResultIsExist() {
        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun textViewShowStringResultAtInitialState() {
        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("Result")))
    }

    @Test
    fun textViewShowAskingValidNumberIfUserNotProvideValidNumber() {
        Espresso.onView(ViewMatchers.withId(R.id.addition)).perform((click()))
        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("Please provide valid number")))
    }

    @Test
    fun textViewShowCorrectValueFromAdditionOperation() {
        Espresso.onView(ViewMatchers.withId(R.id.input1)).perform(replaceText("2"))
        Espresso.onView(ViewMatchers.withId(R.id.input2)).perform(replaceText("3"))
        Espresso.onView(ViewMatchers.withId(R.id.addition)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("5.0")))
    }

    @Test
    fun textViewShowCorrectValueFromSubtractionOperation() {
        Espresso.onView(ViewMatchers.withId(R.id.input1)).perform(replaceText("2"))
        Espresso.onView(ViewMatchers.withId(R.id.input2)).perform(replaceText("3"))
        Espresso.onView(ViewMatchers.withId(R.id.subtraction)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("-1.0")))
    }

    @Test
    fun textViewShowCorrectValueFromMultiplicationOperation() {
        Espresso.onView(ViewMatchers.withId(R.id.input1)).perform(replaceText("2"))
        Espresso.onView(ViewMatchers.withId(R.id.input2)).perform(replaceText("3"))
        Espresso.onView(ViewMatchers.withId(R.id.multiplication)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("6.0")))
    }

    @Test
    fun textViewShowCorrectValueFromDivisionOperation() {
        Espresso.onView(ViewMatchers.withId(R.id.input1)).perform(replaceText("2"))
        Espresso.onView(ViewMatchers.withId(R.id.input2)).perform(replaceText("3"))
        Espresso.onView(ViewMatchers.withId(R.id.division)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0.6666666666666666")))
    }

    @Test
    fun textViewShowErrorMessageFromByZeroDivisionOperation() {
        Espresso.onView(ViewMatchers.withId(R.id.input1)).perform(replaceText("2"))
        Espresso.onView(ViewMatchers.withId(R.id.input2)).perform(replaceText("0"))
        Espresso.onView(ViewMatchers.withId(R.id.division)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("Cannot perform operation division by zero.")))
    }
}