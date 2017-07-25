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
import kotlinx.android.synthetic.main.content_main.*
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_reset_password.*


class ResetPasswordActivity : AppCompatActivity() {

    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        cmdResetPassword.setOnClickListener(View.OnClickListener {
            click(cmdResetPassword)
        })
    }

    private fun click(view: View) {
        if(view == cmdResetPassword) {
            resetPassword()
        }
    }

    private fun resetPassword() {
        pbResetPassword.visibility = View.VISIBLE
        var emailAddress:String = txtResetPasswordEmail.text.toString().trim()
        if(emailAddress != null) {
            // TODO: Check if string is email
            // TODO: Progressbar fix
            firebaseAuth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(OnCompleteListener<Void> { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, getString(R.string.msg_success_password_reset), Toast.LENGTH_SHORT).show()
                            txtResetPasswordEmail.setText("")
                        } else {
                            Toast.makeText(applicationContext, getString(R.string.msg_failure_password_reset), Toast.LENGTH_SHORT).show()
                        }
                    })
        }
        pbResetPassword.visibility = View.INVISIBLE
    }

}
