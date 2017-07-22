package com.jurtz.marcel.leps_planner.Login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.jurtz.marcel.leps_planner.MainActivity
import com.jurtz.marcel.leps_planner.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.content_main.*

class SignUpActivity : AppCompatActivity() {

    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        cmdPerformSignUp.setOnClickListener(View.OnClickListener {
            click(cmdPerformSignUp)
        })

        lblSignUp.setOnClickListener(View.OnClickListener {
            click(lblSignUp)
        })
    }

    // Handle Button Clicks
    private fun click(view: View) {
        if (view == cmdPerformSignUp) {
            signUpUser()
        } else if(view == lblSignUp) {
            finish()
            var intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUpUser() {
        var email: String = txtSignUpEmail.text.toString().trim()
        var password: String = txtSignUpPassword.text.toString().trim()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, getString(R.string.login_empty), Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Fix visibility
        pbSignUp.visibility = View.VISIBLE

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {

                // Sign in if registration was successful
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        //Registration OK
                        //val firebaseUser = this.firebaseAuth.currentUser!!
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    } else {
                        // Login error
                        Toast.makeText(applicationContext, getString(R.string.login_sign_in_failed), Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                //Registration error
                Toast.makeText(applicationContext, getString(R.string.login_sign_up_failed), Toast.LENGTH_SHORT).show()
            }
        }

        pbSignUp.visibility = View.INVISIBLE
    }
}