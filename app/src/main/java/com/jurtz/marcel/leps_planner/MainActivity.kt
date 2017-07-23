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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import com.google.firebase.auth.FirebaseAuth
import com.jurtz.marcel.leps_planner.Fragments.*
import com.jurtz.marcel.leps_planner.Login.LoginActivity


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    //private var dbReference: DatabaseReference = database.getReference()

    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

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
            R.id.nav_events_new -> fragment = FragEventsNew()
            R.id.nav_events_all -> fragment = FragEventsAll()
            R.id.nav_events_mine -> fragment = FragEventsMine()
            R.id.nav_profile -> fragment = FragUserSettings()
            R.id.nav_about -> fragment = FragAbout()
            R.id.nav_sign_out -> signout()
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
