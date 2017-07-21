package com.jurtz.marcel.leps_planner

data class User(
        val name: String,
        val shirt_number: String,
        val groups: IntArray,
        val attended_events: IntArray
)

data class Group(
        val id: Int,
        val description: String
)

data class Event(
        val id: Int,
        val type: Int,
        val group: Int,
        val title: String,
        val description: String,
        val date: String,
        val location: String
)

data class EventType(
        val id: Int,
        val description: String
)

data class EventAttendees(
        val id: Int,
        val attendees: IntArray
)
