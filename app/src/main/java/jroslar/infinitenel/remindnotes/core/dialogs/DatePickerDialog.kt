package jroslar.infinitenel.remindnotes.core.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class DatePickerDialog: DialogFragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var onSelectDate: (date: String) -> Unit

    companion object {
        fun create(
            listener: (date: String) -> Unit
        ): jroslar.infinitenel.remindnotes.core.dialogs.DatePickerDialog = DatePickerDialog().apply {
            onSelectDate = listener
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val fechaLocalDate = LocalDate.of(year, month + 1, dayOfMonth)
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())
            onSelectDate(fechaLocalDate.format(formatter))
        } else {
            onSelectDate("%d-%d-%d".format(dayOfMonth, month + 1, year))
        }
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