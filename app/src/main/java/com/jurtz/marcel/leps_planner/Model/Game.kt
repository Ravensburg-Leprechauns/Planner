package com.jurtz.marcel.leps_planner.Model

/*

    Data class to represent a game
    Dates in the format: YYYY-MM-DD
    Times in the format: hh-mm-ss

    For lineup, only leps are relevant, not the other team.

 */

data class Game(
        var gamedayDate: String,
        var startingTime: String,
        var home: Team,
        var guest: Team,
        var lineup: Array<Player>,
        var driversNeeded: Int,
        var umpiresNeeded: Int,
        var scorersNeeded: Int
)