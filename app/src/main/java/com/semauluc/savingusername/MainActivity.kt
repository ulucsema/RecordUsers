package com.semauluc.savingusername

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    private lateinit var editText: EditText
    private lateinit var textView: TextView

    var alinanKullaniciAdi : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences = this.getSharedPreferences("com.semauluc.savingusername",
            Context.MODE_PRIVATE)
        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        alinanKullaniciAdi = sharedPreferences.getString("kullanici","")
         if (alinanKullaniciAdi != null) {
             textView.text = "KAydedilen Kullaniıcı Adı : ${alinanKullaniciAdi}"
         }
    }

    fun kaydet(view: View) {
        val kullaniciAdi = editText.text.toString()
        if (kullaniciAdi == "") {
            Toast.makeText(this, "Lütfen bir değer girin", Toast.LENGTH_LONG).show()
        } else {
            sharedPreferences.edit().putString("kullanici", kullaniciAdi).apply()
            textView.text = "Kaydedilen Kullanıcı Adı : $kullaniciAdi"
        }
    }

    fun sil(view: View) {
       alinanKullaniciAdi = sharedPreferences.getString("kullanici","")
        if (alinanKullaniciAdi !=null) {
            textView.text = "Kaydedilen Kullanıcı adı :"
            sharedPreferences.edit().remove("kullanici").apply()
        }
    }
}
