package com.hamza.ieeechallenge.data.repositories

import com.google.firebase.firestore.QuerySnapshot
import com.hamza.ieeechallenge.data.firebase.FirebaseSourceUtil
import javax.inject.Inject

class UtilRepository @Inject constructor(private val firebase: FirebaseSourceUtil) {

    suspend fun getCountryCodes(code: String?): QuerySnapshot = firebase.getCountryCodes(code)

    suspend fun getCountryList(): QuerySnapshot = firebase.getCountryList()
}