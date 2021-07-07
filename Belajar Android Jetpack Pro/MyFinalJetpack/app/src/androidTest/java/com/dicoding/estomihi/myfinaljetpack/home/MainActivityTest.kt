package com.dicoding.estomihi.myfinaljetpack.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.estomihi.myfinaljetpack.R
import com.dicoding.estomihi.myfinaljetpack.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

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
        pressBack()
    }

    @Test
    fun insertAndDeleteFavoriteMovie() {
        onView(withId(R.id.rv_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()

        //insert
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withId(R.id.rv_favorite_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_favorite_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.iv_poster)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_movie_title)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_vote)).check(matches(ViewMatchers.isDisplayed()))

        //delete
        onView(withId(R.id.rv_favorite_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()
        onView(withId(R.id.rv_favorite_movie)).check(matches(ViewMatchers.isEnabled()))
        onView(withId(R.id.layout_error)).check(matches(ViewMatchers.isDisplayed()))
        pressBack()
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
        pressBack()
    }

    @Test
    fun insertAndDeleteFavoriteTvShow() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()

        //insert
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_favorite_tvShow)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_favorite_tvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.iv_poster)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_movie_title)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_vote)).check(matches(ViewMatchers.isDisplayed()))

        //delete
        onView(withId(R.id.rv_favorite_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_favorite_tvShow)).check(matches(ViewMatchers.isEnabled()))
        onView(withId(R.id.layout_error)).check(matches(ViewMatchers.isDisplayed()))
        pressBack()
    }
}