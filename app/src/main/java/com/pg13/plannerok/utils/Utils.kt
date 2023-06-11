package com.pg13.plannerok.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

inline fun LifecycleOwner.launchOnLifecycle(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend (CoroutineScope) -> Unit,
): Job {
    return lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(state) {
            block.invoke(this)
        }
    }
}

fun String.userNameValidation(): Boolean = matches("^[0-9а-яА-Я-a-zA-Z-_]*$".toRegex())