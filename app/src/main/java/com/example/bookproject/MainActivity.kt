package com.example.bookproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Es para conectar este codigo con la interfaz

        val nameBookEditText: EditText = findViewById(R.id.name_book_edit_text)
        val NameAuthorEditText:EditText = findViewById(R.id.name_author_edit_text)
        val NumberPages: EditText = findViewById(R.id.pages_edit_text)
        val saved_button : Button = findViewById(R.id.save_button)
        val infoTextView : TextView = findViewById(R.id.info_text_view)

        saved_button.setOnClickListener(){
            val DisplayText : String = "Libro: " + nameBookEditText.text.toString() + " Autor: " + NameAuthorEditText.text.toString() + " Paginas: " + NumberPages.text.toString()
            infoTextView.text= DisplayText
        }
    }
}