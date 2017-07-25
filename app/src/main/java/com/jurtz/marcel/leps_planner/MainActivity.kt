package com.jurtz.marcel.leps_planner

import android.content.Intent
import android.os.Bundle
import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.jurtz.marcel.leps_planner.Fragments.*
import com.jurtz.marcel.leps_planner.Login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    //private var dbReference: DatabaseReference = database.getReference()

    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var databaseReference = FirebaseDatabase.getInstance().getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // setSupportActionBar(toolbar)

        // TODO: Fab for event-creation?
        /*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        toolbar.setTitle(getString(R.string.drawer_title_main))

        /*
        dbReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                // Ausgeführt, wenn Firebase Datenbank verändert wird
                //var x:String = dataSnapshot?.value.toString()
                //txtFirebaseSample.text = x
            }

            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        */

        databaseReference.child(Constants.str_db_child_user).child(firebaseAuth?.currentUser?.uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // var userMap = dataSnapshot.value as User?
                val map = dataSnapshot.getValue() as Map<String, Any>?
                if(map != null) {
                    var role = map.get("role").toString()

                    // Activate Admin Controls if User is Admin
                    if (role == Constants.dbstr_role_admin) {
                        nav_view.menu.findItem(R.id.mcAdministration).setVisible(true)
                    }
                }
            }

            override fun onCancelled(firebaseError: DatabaseError) {

            }
        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            // super.onBackPressed()
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        var fragment: Fragment? = null

        when (id) {
            R.id.nav_event_create -> {
                fragment = FragAdminEventNew()
                toolbar.setTitle(getString(R.string.drawer_title_admin_create_event))
            }
            R.id.nav_events_new -> {
                fragment = FragEventsNew()
            }
            R.id.nav_events_all -> {
                fragment = FragEventsAll()
            }
            R.id.nav_events_mine -> {
                fragment = FragEventsMine()
            }
            R.id.nav_profile -> {
                fragment = FragUserSettings()
                toolbar.setTitle(getString(R.string.drawer_title_user_settings))
            }
            R.id.nav_about -> {
                fragment = FragAbout()
            }
            R.id.nav_sign_out -> {
                signout()
            }
        }

        if (fragment != null) {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.content_main, fragment)
            // TODO: ft.addToBackStack("main")
            ft.commit()
        }


        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun signout() {
        firebaseAuth.signOut()
        finish()
        startActivity(Intent(applicationContext, LoginActivity::class.java))
    }
}
