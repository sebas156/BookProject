package com.example.bookproject

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookproject.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    private var cal = Calendar.getInstance()
    private var publicationDate = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root) // Es para conectar este codigo con la interfaz

        val dateSetListener = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR,year)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            var format = "dd/MM/yyyy"
            var simpleDateFormat = SimpleDateFormat(format)
            publicationDate = simpleDateFormat.format(cal.time).toString()
            mainBinding.publicationDateButton.text=publicationDate
        }

        with(mainBinding){

            publicationDateButton.setOnClickListener(){
                DatePickerDialog(
                    this@MainActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            saveButton.setOnClickListener(){
                if(nameBookEditText.text?.isEmpty() == true || nameAuthorEditText.text?.isEmpty() == true || pagesEditText.text?.isEmpty() == true){
                    Toast.makeText(
                        applicationContext,
                        "Debe digitar nombre, autor y numero de paginas",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{

                    val author : String = nameAuthorEditText.text.toString()
                    val nameBook = nameBookEditText.text.toString()
                    val pages = pagesEditText.text.toString().toInt()
                    val abstract = abstractEditText.text.toString()

                    var gender = ""
                    if(SuspenseCheckBox.isChecked) gender = "Suspenso "
                    if(TerrorCheckBox.isChecked) gender += "Terror "
                    if(ChildCheckBox.isChecked) gender += "Infantil "
                    if(FictionCheckBox.isChecked) gender += "Ficción"

                    /*
                    var puntaje = 5
                    if(FirstRadioButton.isChecked) puntaje = 1
                    else if(SecondRadioButton.isChecked) puntaje = 2
                    if(ThirdRadioButton.isChecked) puntaje = 3
                    else if(ForthRadioButton.isChecked) puntaje = 4
                    else puntaje = 5
                    */

                    var puntaje = when{
                        FirstRadioButton.isChecked -> 1
                        SecondRadioButton.isChecked -> 2
                        ThirdRadioButton.isChecked -> 3
                        ForthRadioButton.isChecked -> 4
                        else -> 5
                    }
                    infoTextView.text= getString(R.string.info,nameBook,author,pages,abstract,gender,puntaje,publicationDate)
                }

            }
        }
    }
}