package jroslar.infinitenel.remindnotes.core.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import jroslar.infinitenel.remindnotes.R
import java.util.Calendar

class TimePickerDialog: DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private lateinit var onSelectTime: (time: String) -> Unit

    companion object {
        fun create(
            listener: (time: String) -> Unit
        ): jroslar.infinitenel.remindnotes.core.dialogs.TimePickerDialog = TimePickerDialog().apply {
            onSelectTime = listener
        }
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        onSelectTime("%02d:%02d".format(hour, minute))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity as Context, R.style.PickerDialogTheme, this, hour, minute, true)
    }
}