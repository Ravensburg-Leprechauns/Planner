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

        var name: String?
        var email: String?
        var number: Int?
        var group: String?
        var role: String?

        cmdSaveUserSettings.setOnClickListener(View.OnClickListener {
            saveUserSettings()
        })

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

                    if(role == "") role = Constants.str_role_user
                    if(group == "") group = Constants.str_group_general

                    txtSettingsMail.setText(email)
                    txtSettingsName.setText(name)
                    txtSettingsNumber.setText(number.toString())
                    txtSettingsRole.setText(role)
                    txtSettingsGroup.setText(group)
                }
            }

            override fun onCancelled(firebaseError: DatabaseError) {

            }
        })

        // txtSettingsMail.setText(firebaseAuth?.currentUser?.email)

    }

    private fun saveUserSettings() {
        val name = txtSettingsName.text.toString().trim()
        val mail = txtSettingsMail.text.toString().trim()
        val shirt = txtSettingsNumber.text.toString().toInt()
        val group = txtSettingsGroup.text.toString().trim()
        val role = txtSettingsRole.text.toString().trim()

        /*
        var group: Int = -1

        when(group_str) {
            Constants.str_group_general -> group = Constants.id_group_general
            Constants.str_group_team -> group = Constants.id_group_team
            Constants.str_group_youth -> group = Constants.id_group_youth
        }

        var role: Int = -1

        when(role_str) {
            Constants.str_role_user -> role = Constants.id_role_user
            Constants.str_role_admin -> role = Constants.id_role_admin
        }
        */

        val user = User(mail, name, shirt, group, role)

        val firebaseUser = firebaseAuth.currentUser

        databaseReference.child(Constants.str_db_child_user).child(firebaseUser?.uid).setValue(user)

        Toast.makeText(getView().getContext(), "SAVED", Toast.LENGTH_SHORT).show();
    }
}
