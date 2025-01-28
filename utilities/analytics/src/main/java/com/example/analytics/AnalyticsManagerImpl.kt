package com.example.analytics

class AnalyticsManager {

    private val serviceList = mutableListOf<AnalyticsService>()

    companion object {
        @Volatile
        private var INSTANCE: AnalyticsManager? = null

        fun getInstance(): AnalyticsManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AnalyticsManager().also { INSTANCE = it }
            }
        }
    }

    fun addService(analyticsService: AnalyticsService) {
        serviceList.add(analyticsService)
    }

    fun sendEvent(key: String, params: Map<String, Any>) {
        serviceList.map {
            it.sendEvent(key, params)
        }
    }

    fun sendEvents(events: List<Pair<String, Map<String, Any>>>) {
        serviceList.map {
            it.sendEvents(events)
        }
    }
}