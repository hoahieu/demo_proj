package com.hoahieu.demo.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun Fragment.safeLaunch(action: suspend () -> Unit) {
    viewLifecycleOwner.lifecycle.coroutineScope.launch { action.invoke() }
}

const val DEBOUNCE_DELAY = 200L // 200ms

fun View.clickFlow(): Flow<Unit> = callbackFlow {
    setOnClickListener { trySend(Unit) }
    awaitClose { setOnClickListener(null) }
}

fun View.setDebounceClickListener(debounceDelay: Long = DEBOUNCE_DELAY, clickCallback: () -> Unit) = clickFlow()
    .debounce(debounceDelay)
    .onEach {
        findViewTreeLifecycleOwner()?.lifecycle?.coroutineScope?.launch {
            clickCallback()
        }
    }
    .launchIn(GlobalScope)