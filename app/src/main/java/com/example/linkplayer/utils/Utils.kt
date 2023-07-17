package com.example.linkplayer.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

inline fun Fragment.observe(
    crossinline block: suspend CoroutineScope.() -> Unit,
    state: Lifecycle.State = Lifecycle.State.RESUMED,
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(state) {
            block.invoke(this)
        }
    }
}