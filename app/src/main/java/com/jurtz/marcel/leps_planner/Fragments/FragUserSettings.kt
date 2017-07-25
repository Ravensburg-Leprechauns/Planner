package com.jurtz.marcel.leps_planner.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.jurtz.marcel.leps_planner.Constants
import com.jurtz.marcel.leps_planner.R
import com.jurtz.marcel.leps_planner.User
import kotlinx.android.synthetic.main.fragment_user_settings.*


class FragUserSettings : Fragment() {

    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_user_settings, container, false)
    }

    override fun onStart() {
        super.onStart()
        txtSettingsMail.isEnabled = false
        txtSettingsRole.isEnabled = false
        txtSettingsGroup.isEnabled = false

        var name: String? = null
        var email: String? = null
        var number: Int? = null
        var group: String? = null
        var role: String? = null

        // Get user data from firebase database
        databaseReference.child(Constants.str_db_child_user).child(firebaseAuth?.currentUser?.uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // var userMap = dataSnapshot.value as User?
                val map = dataSnapshot.getValue() as Map<String, Any>?
                if(map != null) {
                    email = map.get("email").toString()
                    if(email == "") {
                        email = firebaseAuth.currentUser?.email
                    }
                    name = map.get("name").toString()
                    number = map.get("shirt_number").toString().toInt()
                    group = map.get("group").toString()
                    role = map.get("role").toString()

                    if(role == "") role = getString(R.string.usettings_role_user)
                    if(group == "") group = getString(R.string.usettings_group_general)

                    txtSettingsMail.setText(email)
                    txtSettingsName.setText(name)
                    txtSettingsNumber.setText(number.toString())

                    if(role == Constants.dbstr_role_admin) {
                        txtSettingsRole.setText(getString(R.string.usettings_role_admin))
                    } else {
                        txtSettingsRole.setText(getString(R.string.usettings_role_user))
                    }

                    if(group == Constants.dbstr_group_team) {
                        txtSettingsGroup.setText(getString(R.string.usettings_group_team))
                    } else if(group == Constants.dbstr_group_youth){
                        txtSettingsGroup.setText(getString(R.string.usettings_group_youth))
                    }
                    else {
                        txtSettingsGroup.setText(getString(R.string.usettings_group_general))
                    }
                }
            }

            override fun onCancelled(firebaseError: DatabaseError) {

            }
        })

        cmdSaveUserSettings.setOnClickListener(View.OnClickListener {
            saveUserSettings(group, role)
        })

        // txtSettingsMail.setText(firebaseAuth?.currentUser?.email)

    }

    // Store user settings to database
    private fun saveUserSettings(group: String?, role: String?) {
        val name = txtSettingsName.text.toString().trim()
        val mail = txtSettingsMail.text.toString().trim()
        val shirt = txtSettingsNumber.text.toString().toInt()

        // Don't allow overriding of group and role
        // val group = txtSettingsGroup.text.toString().trim()
        // val role = txtSettingsRole.text.toString().trim()

        if(group != null && role != null) {

            val user = User(mail, name, shirt, group, role)
            val firebaseUser = firebaseAuth.currentUser

            databaseReference.child(Constants.str_db_child_user).child(firebaseUser?.uid).setValue(user)
            Toast.makeText(getView().getContext(), getString(R.string.msg_saved_changes), Toast.LENGTH_SHORT).show();
        }
    }
}
