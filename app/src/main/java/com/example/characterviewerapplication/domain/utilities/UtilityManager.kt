package com.example.characterviewerapplication.domain.utilities

import android.content.res.Resources
import com.example.characterviewerapplication.R

/**
 * Class containing all utility methods to be used throughout the application
 */
object UtilityManager {

    fun isTablet(resources: Resources): Boolean {
        return resources.getBoolean(R.bool.is_tablet)
    }
}
