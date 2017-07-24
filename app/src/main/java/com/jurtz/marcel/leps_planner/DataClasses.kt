package com.jurtz.marcel.leps_planner

data class User(
        val email: String,
        val name: String,
        val shirt_number: Int,
        val group: String,
        val role: String
)

// Roles:
// 0: user
// 1: admin

// Group:
// 0: general (not specifically given, has everyone)
// 1: team
// 2: youth

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
