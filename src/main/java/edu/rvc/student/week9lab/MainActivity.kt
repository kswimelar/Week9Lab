package edu.rvc.student.week9lab

import android.content.res.Resources
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeImageTransform
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timerTask


class MainActivity : AppCompatActivity() {
    var y:Float = 0.00F
    var x:Float = 0.00F
    var random = Random()
    var score:Int = 0
    var timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnImgButton = findViewById<ImageButton>(R.id.imgMole)
        var btnControl = findViewById<Button>(R.id.btnControl)
        var GameBackGround = findViewById<ConstraintLayout>(R.id.GameCanvas)
        //
        btnImgButton.setTranslationX(-500F)
        btnImgButton.setTranslationY(-500F)
        //
        btnControl.setOnClickListener{
            if (btnControl.text == "Start"){
                Toast.makeText(this, "Tap Android To Score", 2).show()
                btnControl.text = "Stop"
                score = 0
                txtScore.text = "Score: " + score.toString()
                btnImgButton.setTranslationX(-500F)
                btnImgButton.setTranslationY(-500F)
                timer = Timer()
                timer.schedule(timerTask{ChangeImage()}, 5000)
            }else {
                btnControl.text = "Start"
                btnImgButton.setTranslationX(-500F)
                btnImgButton.setTranslationY(-500F)
                timer.cancel()
            }
        }

        btnImgButton.setOnClickListener {
            score += 100
            if (score == 1000){
                timer.cancel()
                txtScore.text = "You Have Won!"
                score = 0
                btnControl.text = "Start"
                btnImgButton.setTranslationX(-500F)
                btnImgButton.setTranslationY(-500F)
            }else {
                txtScore.text = "Score: " + score.toString()
            }
        }

        GameBackGround.setOnClickListener {
            println("Clik")
            score -= 100
            txtScore.text = "Score: " + score.toString()
            btnImgButton.setTranslationX(-500F)
            btnImgButton.setTranslationY(-500F)
            timer.cancel()
            btnControl.text = "Start"
        }
    }
    fun ChangeImage(){
        y = ((Math.random() * getScreenHeight() + 50)) .toFloat()
        x = ((Math.random() * getScreenWidth() + 50)) .toFloat()
        imgMole.setTranslationX(x)
        imgMole.setTranslationY(y)
        timer.schedule(timerTask { ChangeImage() }, 1000)
    }

    fun getScreenWidth(): Float {
        return Resources.getSystem().getDisplayMetrics().widthPixels / 3.0F
    }

    fun getScreenHeight(): Float {
        return Resources.getSystem().getDisplayMetrics().heightPixels / 3.0F
    }
}
