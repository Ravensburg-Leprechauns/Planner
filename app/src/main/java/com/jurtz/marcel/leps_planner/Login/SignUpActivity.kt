package com.jurtz.marcel.leps_planner.Login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
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
    }

    // Handle Button Clicks
    private fun click(view: View) {
        if(view == cmdPerformSignUp) {
            signUpUser()
        }
    }

    private fun signUpUser() {
        var email: String = txtSignUpEmail.text.toString().trim()
        var password: String = txtSignUpPassword.text.toString().trim()

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, getString(R.string.login_empty), Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Progressdialog Alternative
        //progressDialog?.setMessage(getString(R.string.login_user_progress))
        //progressDialog?.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                //Registration OK
                //val firebaseUser = this.firebaseAuth.currentUser!!
                Toast.makeText(applicationContext, "Registered successfully!", Toast.LENGTH_SHORT).show()
            } else {
                //Registration error
                Toast.makeText(applicationContext, "Failed registering", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
