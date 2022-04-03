package com.hamza.ieeechallenge.ui.repositories

import com.google.firebase.firestore.QuerySnapshot
import com.hamza.ieeechallenge.ui.data.firebase.SourceUtil
import javax.inject.Inject

class UtilRepository @Inject constructor(private val firebase: SourceUtil) {

    suspend fun getCountryCodes(code: String?): QuerySnapshot = firebase.getCountryCodes(code)

    suspend fun getCountryList(): QuerySnapshot = firebase.getCountryList()
}