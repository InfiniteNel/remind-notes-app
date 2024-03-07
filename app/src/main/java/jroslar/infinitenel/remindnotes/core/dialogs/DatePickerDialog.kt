package jroslar.infinitenel.remindnotes.core.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePickerDialog: DialogFragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var onSelectDate: (day:Int, month:Int, year:Int) -> Unit

    companion object {
        fun create(
            listener: (day:Int, month:Int, year:Int) -> Unit
        ): jroslar.infinitenel.remindnotes.core.dialogs.DatePickerDialog = DatePickerDialog().apply {
            onSelectDate = listener
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        onSelectDate(dayOfMonth, month, year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dialogDate = DatePickerDialog(activity as Context, this, year, month, day)

        dialogDate.datePicker.minDate = c.timeInMillis
        return dialogDate
    }
}