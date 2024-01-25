package com.tekehome.takehome.presentation.resources

import android.content.Context
import com.tekehome.takehome.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TakeHomeResourcesManager @Inject constructor(@ApplicationContext val context: Context) :
    TakeHomeResources {
    override fun getUrlErrorMessage() = context.getString(R.string.invalid_url)

    override fun getNoUrlSavedErrorMessage() = context.getString(R.string.no_url_saved)
}
