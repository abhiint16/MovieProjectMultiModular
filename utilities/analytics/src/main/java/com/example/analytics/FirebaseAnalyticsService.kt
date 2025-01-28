package com.example.analytics

import android.os.Bundle

class FirebaseAnalyticsService : AnalyticsService {

//    private val firebaseAnalytics = FireBase

    override fun sendEvent(eventName: String, params: Map<String, Any>) {
        val bundle = Bundle()
        params.forEach { (key, value) ->
            when (value) {
                is String -> bundle.putString(key, value)
                is Int -> bundle.putInt(key, value)
                is Float -> bundle.putFloat(key, value)
                is Double -> bundle.putDouble(key, value)
                is Boolean -> bundle.putBoolean(key, value)
            }
        }

        // log event to firebase/cleverTap/MoEngage
    }

    override fun sendEvents(events: List<Pair<String, Map<String, Any>>>) {
        events.forEach { (eventName, params) ->
            sendEvent(eventName, params)
        }
    }

}