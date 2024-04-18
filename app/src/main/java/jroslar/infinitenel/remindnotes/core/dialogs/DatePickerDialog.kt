package jroslar.infinitenel.remindnotes.core.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import jroslar.infinitenel.remindnotes.R
import jroslar.infinitenel.remindnotes.core.utils.DateUtils
import java.util.Calendar

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
        onSelectDate(DateUtils.intsToDateFormat(year, month, dayOfMonth))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dialogDate = DatePickerDialog(activity as Context, R.style.PickerDialogTheme, this, year, month, day)

        dialogDate.datePicker.minDate = c.timeInMillis
        return dialogDate
    }
}