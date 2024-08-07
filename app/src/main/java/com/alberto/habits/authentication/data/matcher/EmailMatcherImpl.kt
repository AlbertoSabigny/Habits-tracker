package com.alberto.habits.authentication.data.matcher

import android.util.Patterns
import com.alberto.habits.authentication.domain.matcher.EmailMatcher

class EmailMatcherImpl : EmailMatcher {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}