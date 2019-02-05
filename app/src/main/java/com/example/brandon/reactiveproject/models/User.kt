package com.example.brandon.reactiveproject.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class User(@field:SerializedName("id") val id: Int,
                @field:SerializedName("name") val name: String,
                @field:SerializedName("password") val password: String,
                @field:SerializedName("birth") val birth: Date)