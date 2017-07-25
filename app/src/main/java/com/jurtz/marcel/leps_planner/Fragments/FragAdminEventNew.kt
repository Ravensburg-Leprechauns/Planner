package com.jurtz.marcel.leps_planner.Fragments

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.app.TimePickerDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.jurtz.marcel.leps_planner.Constants
import com.jurtz.marcel.leps_planner.MainActivity
import com.jurtz.marcel.leps_planner.R
import com.jurtz.marcel.leps_planner.User
import kotlinx.android.synthetic.main.fragment_admin_event_new.*
import java.util.*
import android.widget.ArrayAdapter
import android.widget.Spinner


class FragAdminEventNew : Fragment() {

    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference()

    var datePicker: DatePickerDialog? = null
    var timePicker: TimePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun click(view:View) {
        if(view == lbl_ce_selected_date) {
            // Show Date Picker
        } else if(view == lbl_ce_selected_time) {
            // Show Time Picker
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_admin_event_new, container, false)
    }

    override fun onStart() {
        super.onStart()

        // datePicker = new DatePickerDialog(g)
        /*
        lbl_ce_selected_date.setOnClickListener(View.OnClickListener {

        })

        lbl_ce_selected_time.setOnClickListener(View.OnClickListener {

        })
        */

        var groups = arrayOf(getString(R.string.usettings_group_general),getString(R.string.usettings_group_team), getString(R.string.usettings_group_youth))
        val adapterGroup = ArrayAdapter<String>(view.context,
                android.R.layout.simple_spinner_item, groups)
        sp_ce_group.setAdapter(adapterGroup)

        var types = arrayOf(getString(R.string.type_game_home),getString(R.string.type_game_away), getString(R.string.type_other))
        val adapterType = ArrayAdapter<String>(view.context,
                android.R.layout.simple_spinner_item, types)
        sp_ce_type.setAdapter(adapterType)
    }
}
