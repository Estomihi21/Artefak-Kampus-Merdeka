package com.dicoding.estomihi.mymovies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.estomihi.mymovies.utils.DataDummy
import com.dicoding.estomihi.mymovies.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest1 {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withId(R.id.iv_backDrop)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_storyline)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
    }

    @Test
    fun loadTvShows() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withId(R.id.iv_backDrop)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_storyline)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
    }
}