package com.tekehome.takehome.presentation.resources

import android.content.Context
import com.tekehome.takehome.R
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class TakeHomeResourcesTest {

    private val context = mock<Context> {
        on { getString(any()) } doReturn TAKE_HOME_TEST_STRING
    }

    companion object {
        private const val TAKE_HOME_TEST_STRING = "TAKE_HOME_TEST_STRING"
    }

    private val resources = TakeHomeResourcesManager(context)

    @Test
    fun `get service category options texts`() {
        resources.getUrlErrorMessage()
        verify(context).getString(R.string.invalid_url)
    }

    @Test
    fun `get no url saved texts`() {
        resources.getNoUrlSavedErrorMessage()
        verify(context).getString(R.string.no_url_saved)
    }
}
