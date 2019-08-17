package de.apkgrabber.test.steps


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import cucumber.api.CucumberOptions
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import de.apkgrabber.R
import de.apkgrabber.activity.MainActivity_
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule


@CucumberOptions(features = arrayOf("features/Settings.feature"))
class SettingsSteps
    : TestCase() {


    @Rule
    var mActivityRule = ActivityTestRule(MainActivity_::class.java, false, true)


    @Before
    public override fun setUp(
    ) {

    }


    @Given("I start the app")
    fun i_start_the_app(
    ) {
        mActivityRule.launchActivity(null)
    }


    @When("I go to settings")
    fun i_go_to_settings(
    ) {
        onView(withId(R.id.action_settings)).perform(click())
    }


    @Then("I see the settings")
    fun i_see_the_settings(
    ) {
        onView(withText("Settings")).check(matches(isDisplayed()))
    }


}

