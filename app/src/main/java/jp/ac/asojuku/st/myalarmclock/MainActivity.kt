package jp.ac.asojuku.st.myalarmclock

import android.annotation.TargetApi
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), SimpleAlertDialog.OnClickListner {
    override fun onPositiveClick(){
        finish()
    }

    override fun onNegativeClick() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND,5)
        setAlarmManager(calendar)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        setAlarm.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()

            calendar.add(Calendar.SECOND, 5)

            this.setAlarmManager(calendar)

        }

        cancel.setOnClickListener {
            this.cancelAlarmManager()
        }


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setAlarmManager(calendar: Calendar) {
        //アラームマネージャのインスタンスをOSから取得
        val am = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //連携に使うインテントの作成
        val intent = Intent(this, AlarmBroadcastReciever::class.java)
        //遅れて通知してもらうためのインテントを生成
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        //Androidversionの判定
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val info = AlarmManager.AlarmClockInfo(calendar.timeInMillis, null)
                am.setAlarmClock(info, pendingIntent)
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                am.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            }
            else -> {
                am.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            }
        }
    }

    private fun cancelAlarmManager() {
        val am = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcastReciever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

    }
}
