package com.jurtz.marcel.leps_planner.Model

/*
    This file contains data classes that are used for maintaining users
    A user is either admin or regular user.
    Different kinds of users exist, a user can be one or more of the following:
    * Player
    * Scorer
    * Umpire
    * Coach
 */

enum class Group {
    User,
    Administrator
}

enum class LepsTeam {
    Mens,
    Youth
}

abstract class User {
    abstract var name: String
    abstract var mail: String
    abstract var location: String
    abstract var group: Group
    abstract var active: Boolean
}

data class Player(
        override var name: String,
        override var mail: String,
        override var location: String,
        override var group: Group,
        override var active: Boolean,
        var team: LepsTeam,
        var number: Int
) : User()

data class Scorer(
        override var name: String,
        override var mail: String,
        override var location: String,
        override var group: Group = Group.User,
        override var active: Boolean
) : User()

data class Umpire(
        override var name: String,
        override var mail: String,
        override var location: String,
        override var group: Group = Group.User,
        override var active: Boolean
) : User()

data class Coach(
        override var name: String,
        override var mail: String,
        override var location: String,
        override var group: Group = Group.Administrator,
        var supervisedTeam: LepsTeam,
        override var active: Boolean
) : User()