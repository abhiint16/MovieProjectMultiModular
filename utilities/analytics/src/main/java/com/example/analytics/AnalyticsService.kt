package com.example.analytics

interface AnalyticsService {
    fun sendEvent(key: String, params: Map<String, Any>)

    fun sendEvents(events: List<Pair<String, Map<String, Any>>>)
}