package com.jurtz.marcel.leps_planner.Login

import android.content.Intent
import android.opengl.Visibility
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
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Check if user is already signed in
        if(firebaseAuth.currentUser != null ) {
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }

        cmdPerformLogin.setOnClickListener(View.OnClickListener {
            click(cmdPerformLogin)
        })

        lblLogin.setOnClickListener(View.OnClickListener {
            click(lblLogin)
        })

        lblForgotPassword.setOnClickListener(View.OnClickListener {
            click(lblForgotPassword)
        })
    }

    private fun click(view: View) {
        if(view == cmdPerformLogin) {
            loginUser()
        } else if(view == lblLogin) {
            finish()
            var intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        } else if(view == lblForgotPassword) {
            var intent = Intent(applicationContext, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {

        var email: String = txtLoginEmail.text.toString().trim()
        var password: String = txtLoginPassword.text.toString().trim()

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, getString(R.string.login_empty), Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Fix visibility
        pbLogin.visibility = View.VISIBLE

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                //Registration OK
                //val firebaseUser = this.firebaseAuth.currentUser!!
                startActivity(Intent(applicationContext,MainActivity::class.java))
            } else {
                // Login error
                Toast.makeText(applicationContext, getString(R.string.login_sign_in_failed), Toast.LENGTH_LONG).show()
            }
        }
        pbLogin.visibility = View.INVISIBLE

    }

}
