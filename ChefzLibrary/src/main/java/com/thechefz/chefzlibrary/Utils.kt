package com.thechefz.chefzlibrary

import android.app.Activity
import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import java.util.*

object Utils {



    fun datePicker(activity:Activity,result: ((res:String) -> Unit) = {}){
        val c = Calendar.getInstance()

        val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val calender = Calendar.getInstance()
            calender.set(Calendar.YEAR,year)
            calender.set(Calendar.MONTH,monthOfYear)
            calender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            // Display Selected date in textbox
            result.invoke(SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(calender.getTime()))

        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))

        dpd.show()
    }
}