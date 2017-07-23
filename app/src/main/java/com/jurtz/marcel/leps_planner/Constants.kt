package com.jurtz.marcel.leps_planner

class Constants {

    companion object {
        public val id_role_user = 0
        public val id_role_admin = 1

        public val str_role_user = "R_USER"
        public val str_role_admin = "R_ADMIN"

        public val list_roles = listOf(str_role_user, str_role_admin)


        public val id_group_general = 0
        public val id_group_team = 1
        public val id_group_youth = 2

        public val str_group_general = "G_GENERAL"
        public val str_group_team = "G_TEAM"
        public val str_group_youth = "G_YOUTH"

        public val list_groups = listOf(str_group_general, str_group_team, str_group_youth)


        val str_db_child_user = "users"
        val str_db_child_user_name = "name"
    }
}
