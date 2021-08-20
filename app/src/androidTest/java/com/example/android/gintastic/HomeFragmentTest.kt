package com.example.android.gintastic

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
class ButtonTest {

    //private lateinit var scenario: FragmentScenario<HomeFragment>

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )
    @Test
    fun areButtonsFunctional(){
        onView(withId(R.id.main_button_all))
            .check(matches(isEnabled()))
            .check(matches(isClickable()))
        onView(withId(R.id.main_button_fav))
            .check(matches(isEnabled()))
            .check(matches(isClickable()))
        onView(withId(R.id.main_button_new))
            .check(matches(isEnabled()))
            .check(matches(isClickable()))

    }
}