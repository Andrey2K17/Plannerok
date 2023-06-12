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

fun getZodiacSign(day: Int, month: Int): String {
    return when (month) {
        0 -> if (day < 20) Capricorn else Aquarius
        1 -> if (day < 20) Aquarius else Pisces
        2 -> if (day < 20) Pisces else Aries
        3 -> if (day < 20) Aries else Taurus
        4 -> if (day < 20) Taurus else Gemini
        5 -> if (day < 20) Gemini else Cancer
        6 -> if (day < 20) Cancer else Leo
        7 -> if (day < 20) Leo else Virgo
        8 -> if (day < 20) Virgo else Libra
        9 -> if (day < 20) Libra else Scorpio
        10 -> if (day < 20) Scorpio else Sagittarius
        11 -> if (day < 20) Sagittarius else Capricorn
        else -> "Not found"
    }
}