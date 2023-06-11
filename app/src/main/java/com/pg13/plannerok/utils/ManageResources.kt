package com.pg13.plannerok.utils

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ManageResources {

    fun getString(@StringRes id: Int): String

    class Base @Inject constructor(@ApplicationContext private val context: Context) :
        ManageResources {
        override fun getString(id: Int): String = context.getString(id)
    }
}