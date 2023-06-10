package com.example.sokobaninterface

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)
    //    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.sokobaninterface", appContext.packageName)
//    }
    @Test
    fun testMovePosition(){
        Espresso.onView(withId(R.id.play))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.level1))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.down_arrow))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.right_arrow))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.down_arrow))
            .perform(ViewActions.click())
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.left_arrow))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.down_arrow))
            .perform(ViewActions.click())
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.right_arrow))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.up_arrow))
            .perform(ViewActions.click())
            .perform(ViewActions.click())
            .perform(ViewActions.click())
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.left_arrow))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.down_arrow))
            .perform(ViewActions.click())
            .perform(ViewActions.click())
        Espresso.onView(withId(android.R.id.button1))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.content))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun testBack() {
        Espresso.onView(withId(R.id.play))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.level1))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.down_arrow))
            .perform(ViewActions.click())
            .perform(ViewActions.click())
            .perform(ViewActions.click())
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.back))
            .perform(ViewActions.click())
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.moves_count))
            .check(ViewAssertions.matches(withText("2")))
    }
    @Test
    fun testRestart() {
        Espresso.onView(withId(R.id.play))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.level2))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.down_arrow))
            .perform(ViewActions.click())
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.left_arrow))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.up_arrow))
            .perform(ViewActions.click())
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.restart))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.pause))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.moves_count))
            .check(ViewAssertions.matches(withText("0")))
        Espresso.onView(withId(R.id.time_count))
            .check(ViewAssertions.matches(withText("00:00")))
    }

}