package jp.ac.asojuku.st.myalarmclock

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import org.jetbrains.anko.toast


class SimpleAlertDialog : DialogFragment() {

    interface OnClickListner {
        //起きるを選んだ時
        fun onPositiveClick()

        //あと5分を選んだ時の処理
        fun onNegativeClick()

    }

    private lateinit var listner:OnClickListner

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is SimpleAlertDialog.OnClickListner) {
            listner = context;
        }

    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return super.onCreateDialog(savedInstanceState)

        val context = this.context
        if (context == null) {
            //親画面との整合性が取れないとき
            return super.onCreateDialog(savedInstanceState)
        }

        val builder = AlertDialog.Builder(context).apply {
            setMessage("時間になりました")
            setPositiveButton("起きる") {
//                dialog, witch ->
//                context.toast("起きるボタンがクリックされました")
                dialog, witch ->
                listner.onPositiveClick();
            }
            setNegativeButton("後5分") {
//                dialog, which ->
//                context.toast("あと5分がクリックされました")

                dialog, witch ->
                listner.onNegativeClick()


            }
        }

        return builder.create()

    }
}