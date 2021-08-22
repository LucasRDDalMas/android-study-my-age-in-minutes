package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDataPicker = findViewById<Button>(R.id.btnDataPicker)
        btnDataPicker.setOnClickListener {
            clickDatePicker(it)
        }
    }

    private fun clickDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "${
                    selectedDayOfMonth.toString().padStart(2, '0')
                }/${(selectedMonth + 1).toString().padStart(2, '0')}/$selectedYear"
                val txtSelectedDate = findViewById<TextView>(R.id.txtSelectDate)
                txtSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time / 60000

                val myAgeInMinutes = currentDateInMinutes - selectedDateInMinutes

                val txtAgeInMinutes = findViewById<TextView>(R.id.txtAgeInMinutes)
                txtAgeInMinutes.text = myAgeInMinutes.toString()
            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}