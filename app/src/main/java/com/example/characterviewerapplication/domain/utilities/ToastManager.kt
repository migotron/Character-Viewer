package com.example.characterviewerapplication.domain.utilities

import android.content.Context
import android.widget.Toast
import com.example.characterviewerapplication.presentation.ui.fragments.ListFragment

/**
 * ToastManager Helper Class
 */
object ToastManager {
    fun showShortToast(context: Context, message: String) {
       Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
