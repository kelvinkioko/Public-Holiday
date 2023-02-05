package com.holiday.util

import android.view.View
import androidx.core.view.isVisible
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

object ViewUtils {
    fun setVisibility(visible: Boolean): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Show / Hide View"
            }

            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(View::class.java)
            }

            override fun perform(uiController: UiController?, view: View) {
                view.isVisible = visible
            }
        }
    }
}
