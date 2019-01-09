package jp.ac.asojuku.st.myalarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.jetbrains.anko.toast

class AlarmBroadcastReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val intent = Intent(context, MainActivity::class.java).putExtra("onReceive", true).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(intent)

//        context?.toast("アラームを受信しました")
    }

}