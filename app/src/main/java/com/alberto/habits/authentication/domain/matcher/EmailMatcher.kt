package com.alberto.habits.authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}