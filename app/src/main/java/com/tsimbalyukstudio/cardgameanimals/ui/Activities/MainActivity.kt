package com.tsimbalyukstudio.cardgameanimals.ui.Activities

import android.annotation.SuppressLint
import android.content.*
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.tsimbalyukstudio.cardgameanimals.R
import com.tsimbalyukstudio.cardgameanimals.USER
import com.appodeal.ads.Appodeal


class MainActivity : AppCompatActivity() {

    var score = 0
    var sPref: SharedPreferences? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadText()
        score = USER.TOTAL_SCORE
        setContentView(R.layout.activity_main)

        initialiseBtns()
        USER.LEVEL = 1

        val appKey = "c322fc8df6b4a029826dae0abade84e3ee856e7302d198fc"
        Appodeal.disableLocationPermissionCheck()
        Appodeal.disableWriteExternalStoragePermissionCheck()
        Appodeal.initialize(this, appKey, Appodeal.BANNER )
        Appodeal.initialize(this, appKey, Appodeal.NON_SKIPPABLE_VIDEO )
        Appodeal.disableLocationPermissionCheck()
        Appodeal.show(this, Appodeal.BANNER)


    }

    @SuppressLint("SetTextI18n")
    fun initialiseBtns() {
        var easyBtn = findViewById<Button>(R.id.easy_btn)
        var narmalBtn = findViewById<Button>(R.id.normal_btn)
        var hardBtn = findViewById<Button>(R.id.hard_btn)
        var customBtn = findViewById<Button>(R.id.custom_game_btn)
        var menuBtn = findViewById<Button>(R.id.menu_btn)
        var rateBtn = findViewById<Button>(R.id.rate_btn)
        var textView = findViewById<TextView>(R.id.text_score_main)



        easyBtn.setOnClickListener { view ->
            val intent = Intent(applicationContext, EasyActivity::class.java)
            startActivity(intent)
            finish()
        }
        narmalBtn.setOnClickListener { view ->
            val intent = Intent(applicationContext, NormalActivity::class.java)
            startActivity(intent)
            finish()
        }
        hardBtn.setOnClickListener { view ->
            val intent = Intent(applicationContext, HardActivity::class.java)
            startActivity(intent)
            finish()
        }
        customBtn.setOnClickListener { view ->
            val intent = Intent(applicationContext, CustomActivity::class.java)
            startActivity(intent)
            finish()
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

        textView.text = "Ваш счет \n $score"
    }

    @SuppressLint("ApplySharedPref")
    fun saveText() {
        sPref = getPreferences(Context.MODE_PRIVATE)
        val ed = sPref!!.edit()
        ed.putInt("Total_Score", USER.TOTAL_SCORE)
        ed.commit()
    }

    fun loadText() {
        sPref = getPreferences(Context.MODE_PRIVATE)
        val savedInt = sPref!!.getInt("Total_Score", 0)
        if (savedInt > USER.TOTAL_SCORE) {
            USER.TOTAL_SCORE = savedInt
        }
    }

    override fun onBackPressed() {
        saveText()
        System.exit(0)
    }

    override fun onDestroy() {
        saveText()
        super.onDestroy()
    }
}
