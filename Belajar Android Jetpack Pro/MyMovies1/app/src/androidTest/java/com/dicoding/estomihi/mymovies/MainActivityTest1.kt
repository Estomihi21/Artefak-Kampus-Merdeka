package com.dicoding.estomihi.mymovies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.estomihi.mymovies.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class MainActivityTest1 {

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShows = DataDummy.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title_mtv)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_mtv)).check(matches(ViewMatchers.withText("Details Movie")))
        onView(withId(R.id.iv_poster)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.tv_name_director)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_name_director)).check(matches(ViewMatchers.withText("Director : ${dummyMovies[0].nameDirector}")))
        onView(withId(R.id.tv_rating)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(dummyMovies[0].rating.toString())))
        onView(withId(R.id.tv_storyline)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_storyline)).check(matches(withText(dummyMovies[0].descStory)))
        onView(withId(R.id.lottieShare)).perform(click())
    }

    @Test
    fun loadTvShows() {
        onView(ViewMatchers.withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(ViewMatchers.withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title_mtv)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_mtv)).check(matches(ViewMatchers.withText("Details TvShow")))
        onView(withId(R.id.iv_poster)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(withText(dummyTvShows[0].title)))
        onView(withId(R.id.tv_name_director)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_name_director)).check(matches(ViewMatchers.withText("Director : ${dummyTvShows[0].nameDirector}")))
        onView(withId(R.id.tv_rating)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(dummyTvShows[0].rating.toString())))
        onView(withId(R.id.tv_storyline)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_storyline)).check(matches(withText(dummyTvShows[0].descStory)))
        onView(withId(R.id.lottieShare)).perform(click())
    }
}