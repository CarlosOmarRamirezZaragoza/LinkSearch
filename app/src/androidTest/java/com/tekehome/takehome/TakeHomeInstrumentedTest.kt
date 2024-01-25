package com.tekehome.takehome

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.tekehome.takehome.data.datasource.local.TakeHomeDataBase
import com.tekehome.takehome.presentation.view.TakeHomeActivity
import com.tekehome.takehome.presentation.viewmodel.TakeHomeViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
class TakeHomeInstrumentedTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityScenarioRule: ActivityScenarioRule<TakeHomeActivity> =
        ActivityScenarioRule(TakeHomeActivity::class.java)

    private lateinit var takeHomeViewModel: TakeHomeViewModel

    private lateinit var dataBase: TakeHomeDataBase

    companion object {
        private val appContext = getInstrumentation().targetContext
        private const val NU_URL =
            "https://nu.com.mx/?cq_plac=&cq_net=g&cq_pos=&cq_med=&cq_plt=gp&utm_source=google&utm_medium=cpc&utm_campaign=15501076442&utm_term=129518566045&utm_word=nubank&utm_content=650819243402&ad_position=&match_type=e&location=9133506&device=c&utm_keyword_id=kwd-306018933097&gad_source=1&gclid=Cj0KCQiA7aSsBhCiARIsALFvovyGKIG5Rbq6zFEJSR6HXtVo-BxXdUGK1ksc9QzIGj-i0aqpQjQQ6A4aApgXEALw_wcB"
        private const val NO_URL = ""
        private const val ALIAS = "50741931"
        private const val SHORTED_URL =
            "https://url-shortener-server.onrender.com/api/alias/50741931"
        private const val DATABASE_NAME = "TakeHomeDatabase"
        private const val MIN_ITEMS = 1
        private const val DELAY = 400L
    }

    @Before
    fun setUp() {
        hiltRule.inject()
        activityScenarioRule.scenario.onActivity { activity ->
            takeHomeViewModel = ViewModelProvider(activity)[TakeHomeViewModel::class.java]
        }
        IdlingRegistry.getInstance().register(takeHomeViewModel.idlingResource)
        appContext.cacheDir.deleteRecursively()
        appContext.filesDir.deleteRecursively()
        dataBase = Room.inMemoryDatabaseBuilder(
            getInstrumentation().targetContext,
            TakeHomeDataBase::class.java,
        ).build()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(takeHomeViewModel.idlingResource)
        try {
            dataBase.clearAllTables()
            dataBase.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        getInstrumentation().targetContext.deleteDatabase(DATABASE_NAME)
    }

    @Test
    fun hintEditTextViewValidation() {
        onView(withId(R.id.searchEditText)).check(
            matches(
                withHint(
                    appContext.getString(
                        R.string.shortened_url,
                    ),
                ),
            ),
        )
    }

    @Test
    fun recentTextViewValidation() {
        onView(withId(R.id.recentShortedUrlTextView)).check(
            matches(
                withText(
                    appContext.getString(
                        R.string.recently_shortened_url,
                    ),
                ),
            ),
        )
    }

    @Test
    fun searchUrlAliasesRecyclerViewIsDisplayed() {
        onView(withId(R.id.searchEditText)).perform(
            typeText(ALIAS),
        )
        onView(withId(R.id.searchButton)).perform(click())
        getInstrumentation().waitForIdleSync()
        onView(isRoot()).perform(wait(DELAY))
        onView(withId(R.id.shortedUrlRecyclerView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun searchUrlAliasesRecyclerViewHasItems() {
        onView(withId(R.id.searchEditText)).perform(
            typeText(ALIAS),
        )
        onView(withId(R.id.searchButton)).perform(click())
        getInstrumentation().waitForIdleSync()
        onView(isRoot()).perform(wait(DELAY))
        onView(withId(R.id.shortedUrlRecyclerView))
            .check(matches(hasMinimumChildCount(MIN_ITEMS)))
    }

    @Test
    fun searchUrlAliasesValidation() {
        onView(withId(R.id.searchEditText)).perform(
            typeText(NU_URL),
        )
        onView(withId(R.id.searchButton)).perform(click())

        getInstrumentation().waitForIdleSync()
        onView(isRoot()).perform(wait(DELAY))
        onView(withId(R.id.shortedUrlRecyclerView))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.aliasServiceTextView),
                            withText(ALIAS),
                        ),
                    ),
                ),
            )

        onView(withId(R.id.shortedUrlRecyclerView))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.selfServiceTextView),
                            withText(NU_URL),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun searchAliasesValidation() {
        onView(withId(R.id.searchEditText)).perform(
            typeText(ALIAS),
        )
        onView(withId(R.id.searchButton)).perform(click())
        getInstrumentation().waitForIdleSync()
        onView(isRoot()).perform(wait(DELAY))
        onView(withId(R.id.shortedUrlRecyclerView))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.aliasServiceTextView),
                            withText(ALIAS),
                        ),
                    ),
                ),
            )

        onView(withId(R.id.shortedUrlRecyclerView))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.selfServiceTextView),
                            withText(NO_URL),
                        ),
                    ),
                ),
            )
        onView(withId(R.id.shortedUrlRecyclerView))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withId(R.id.shortServiceTextView),
                            withText(SHORTED_URL),
                        ),
                    ),
                ),
            )
    }

    // For some reason RV is not getting childs and I used different ways to wait until child is visible
    // I used CountingIdlingResource, that is the best approach,
    // Then I used getInstrumentation().waitForIdleSync()
    // Finally I implement wait, I now it is not the best but for the time, was the only one working
    private fun wait(milliseconds: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for $milliseconds milliseconds"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(milliseconds)
            }
        }
    }
}
