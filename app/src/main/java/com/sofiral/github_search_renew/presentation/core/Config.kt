package com.sofiral.github_search_renew.presentation.core

import javax.inject.Inject

class Config @Inject constructor() {
    var uniqueId: String? = null
    val isAuthorized get() = uniqueId != null
}