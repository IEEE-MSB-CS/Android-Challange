package com.hamza.ieeechallenge.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseSourceUtil @Inject constructor() {
    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    suspend fun getCountryCodes(code: String?): QuerySnapshot {
        return db.collection("country_codes")
            .whereEqualTo("code", code)
            .get()
            .await()
    }

    suspend fun getCountryList(): QuerySnapshot {
        return db.collection("country_codes")
            .orderBy("name")
            .get()
            .await()
    }
}