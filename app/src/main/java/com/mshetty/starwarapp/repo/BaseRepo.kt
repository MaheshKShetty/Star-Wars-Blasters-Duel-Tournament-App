package com.mshetty.starwarapp.repo

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.StandardCharsets

open class BaseRepo {

    fun <T> fetchDataFromAssets(context: Context, fileName: String, classOfT: Class<T>): T? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val byteArray = ByteArray(size)
            inputStream.read(byteArray)
            inputStream.close()
            Gson().fromJson(String(byteArray, StandardCharsets.UTF_8), classOfT)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}