package com.tsimbalyukstudio.cardgameanimals.ui.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.tsimbalyukstudio.cardgameanimals.R
import com.tsimbalyukstudio.cardgameanimals.USER

class MainActivity : AppCompatActivity() {

    val score = USER.TOTAL_SCORE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialiseBtns()

        USER.LEVEL = 1;
    }

    @SuppressLint("SetTextI18n")
    fun initialiseBtns() {
        var easyBtn = findViewById<Button>(R.id.easy_btn)
        var narmalBtn = findViewById<Button>(R.id.normal_btn)
        var hardBtn = findViewById<Button>(R.id.hard_btn)
        var menuBtn = findViewById<Button>(R.id.menu_btn)
        var rateBtn = findViewById<Button>(R.id.rate_btn)
        var textView = findViewById<TextView>(R.id.text_score_main)



        easyBtn.setOnClickListener { view ->
            easyBtn.setBackgroundResource(R.drawable.ic_btn_blue_pressed)
            val intent = Intent(applicationContext, EasyActivity::class.java)
            startActivity(intent)
            finish()
            easyBtn.setBackgroundResource(R.drawable.ic_btn_blue_nonpressed)
        }
        narmalBtn.setOnClickListener { view ->
            narmalBtn.setBackgroundResource(R.drawable.ic_btn_org_pressed)
            val intent = Intent(applicationContext, NormalActivity::class.java)
            startActivity(intent)
            finish()
            narmalBtn.setBackgroundResource(R.drawable.ic_btn_org_nonpressed)
        }
        hardBtn.setOnClickListener { view ->
            hardBtn.setBackgroundResource(R.drawable.ic_btn_red_pressed)
            val intent = Intent(applicationContext, HardActivity::class.java)
            startActivity(intent)
            finish()
            hardBtn.setBackgroundResource(R.drawable.ic_btn_red_nonpressed)
        }
        menuBtn.setOnClickListener { view ->
            var mBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
            var mView: View = layoutInflater.inflate(R.layout.dialog_options, null)
            mBuilder.setView(mView)
            var dialog: AlertDialog = mBuilder.create()
            dialog.show()

            var btnCloseDialog = mView.findViewById<Button>(R.id.btn_close_dialog)
            btnCloseDialog.setOnClickListener { view ->
                dialog.dismiss()
            }
        }
        rateBtn.setOnClickListener { view ->
            var mBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
            var mView: View = layoutInflater.inflate(R.layout.dialog_rate, null)
            mBuilder.setView(mView)
            var dialog: AlertDialog = mBuilder.create()
            dialog.show()

            var btnCloseDialog = mView.findViewById<Button>(R.id.btn_close_dialog)
            btnCloseDialog.setOnClickListener { view ->
                dialog.dismiss()
            }

            var btnRateDialog = mView.findViewById<Button>(R.id.rate_dialog_btn)
            btnRateDialog.setOnClickListener { view ->
                var appPackageName = packageName
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)))
            }
        }

        textView.text = "Your total score is \n $score"
    }

    override fun onBackPressed() {
        System.exit(0)
    }
}
