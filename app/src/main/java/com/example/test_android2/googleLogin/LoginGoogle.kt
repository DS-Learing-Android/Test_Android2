package com.example.test_android2.googleLogin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginGoogle(context: Context) {

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("990677384481-pr02jubof69av8ponrt46eok0c7grmrg.apps.googleusercontent.com")
        .requestServerAuthCode("990677384481-pr02jubof69av8ponrt46eok0c7grmrg.apps.googleusercontent.com")
        .requestEmail()
        .build()

    private val googleSignInClient = GoogleSignIn.getClient(context, gso)

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val authCode: String? =
                completedTask.getResult(ApiException::class.java)?.serverAuthCode
            LoginRepository().getAccessToken(authCode!!)
        } catch (e: ApiException) {
            Log.w(TAG, "handleSignInResult: error" + e.statusCode)
        }
    }

    fun signIn(activity: Activity) {
        val signInIntent: Intent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, 1000)

    }

    fun signOut(context: Context) {
        googleSignInClient.signOut()
            .addOnCompleteListener {
                Toast.makeText(context, "로그아웃 되셨습니다!", Toast.LENGTH_SHORT).show()
            }
    }

    fun isLogin(context: Context): Boolean {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        return if (account == null) false else (true)
    }

    companion object {
        const val TAG = "GoogleLoginService"
    }
}