package com.example.brandon.reactiveproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.brandon.reactiveproject.models.User
import com.example.brandon.reactiveproject.service.ApiService
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val LOG_TAG: String = MainActivity::class.java.simpleName

    private val apiServe by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun login(view: View) {
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordInput)
        val username = findViewById<EditText>(R.id.nameEditText).text.toString()
        val password = findViewById<EditText>(R.id.passwordEditText).text.toString()

        val call = apiServe.getUsers()

        call.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e(LOG_TAG, "Error getting Users info", t)
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.body()?.any { it.name == username && it.password == password }!!) {
                    Log.d(LOG_TAG, username)
                    passwordLayout.error = null
                } else {
                    passwordLayout.error = "Password does not match an account"
                }
            }
        })

    }
}
