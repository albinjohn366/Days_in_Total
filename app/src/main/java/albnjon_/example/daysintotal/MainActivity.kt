package albnjon_.example.daysintotal

import android.app.Application
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class ThisApplication: Application(){
    companion object {
        var firstDate: String? = null
        var secondDate: String? = null
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DateSelect1.setOnClickListener {
            DatePickerFun(false)
        }
        DateSelect2.setOnClickListener {
            DatePickerFun(true)
        }

    }

    fun DatePickerFun(num: Boolean){
        val cal = Calendar.getInstance()
        val day = cal.get(Calendar.DAY_OF_WEEK)
        val month = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                if (num){
                    ThisApplication.secondDate = "${String.format("%04d" ,year)}-" +
                            "${String.format("%02d", month + 1)}-${String.format("%02d", dayOfMonth)}"
                    DateSelect2.text = ThisApplication.secondDate
                }else{
                    ThisApplication.firstDate = "${String.format("%04d" ,year)}-" +
                            "${String.format("%02d", month + 1)}-${String.format("%02d", dayOfMonth)}"
                    DateSelect1.text = ThisApplication.firstDate
                }

                if (ThisApplication.firstDate != null && ThisApplication.secondDate != null){
                    SelectedDates.text = "${ThisApplication.firstDate} to ${ThisApplication.secondDate}"
                    val first_date = LocalDate.parse(ThisApplication.firstDate)
                    val second_date = LocalDate.parse(ThisApplication.secondDate)
                    val dayTemp = Math.abs(ChronoUnit.DAYS.between(first_date, second_date).toInt())
                    InDays.text = "${dayTemp.toString()}\nDAYS"
                }
            },
            year, month, day
        ).show()
    }
}
