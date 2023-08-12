package com.example.questionsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import java.util.Locale

class Second : AppCompatActivity() {
    var index : Int = 0
    var questions : Array<String>? = null
    var answers : Array<String>? = null
    val defauleAnswer = "Press QA button to answer"
    var textToSpeach : TextToSpeech? = null
    var resultSound : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.second)

        textToSpeach = TextToSpeech(this , object : TextToSpeech.OnInitListener{
            override fun onInit(p0: Int) {
                resultSound = textToSpeach!!.setLanguage(Locale.ENGLISH)
            }

        })
        questions = resources.getStringArray(R.array.questions)
        answers = resources.getStringArray(R.array.answers)
        findViewById<TextView>(R.id.answerTextView).text = defauleAnswer
        findViewById<TextView>(R.id.questionsTextView).text = questions!![index!!]
        findViewById<TextView>(R.id.xx_textView).text = "${index + 1}/"
        findViewById<TextView>(R.id.yy_textView).text = "${questions!!.size}"
    }

    fun showAction(v : View?)
    {
        when(v?.id)
        {
            R.id.back ->{
                index -=1
                if(index >= 0)
                {
                    findViewById<TextView>(R.id.answerTextView).text = defauleAnswer
                    findViewById<TextView>(R.id.xx_textView).text = "${index + 1}/"
                    findViewById<TextView>(R.id.questionsTextView).text = questions!![index!!]
                }
                else{
                    index+=1
                    Toast.makeText(this , "You are in the first question" , Toast.LENGTH_LONG).show()
                }
            }
            R.id.forward ->{
                index +=1
                if(index < questions!!.size)
                {
                    findViewById<TextView>(R.id.answerTextView).text = defauleAnswer
                    findViewById<TextView>(R.id.xx_textView).text = "${index + 1}/"
                    findViewById<TextView>(R.id.questionsTextView).text = questions!![index!!]
                }
                else{
                    index-=1
                    Toast.makeText(this , "You have finished the questions" , Toast.LENGTH_LONG).show()
                }

            }
            R.id.answer ->{
                findViewById<TextView>(R.id.answerTextView).text = answers!![index!!]
            }

            R.id.listen ->{
                if(resultSound == TextToSpeech.LANG_NOT_SUPPORTED || resultSound == TextToSpeech.LANG_MISSING_DATA)
                {
                    Toast.makeText(this , "Error" , Toast.LENGTH_LONG).show()
                }
                else
                {
                    textToSpeach!!.speak(findViewById<TextView>(R.id.questionsTextView).text , TextToSpeech.QUEUE_FLUSH , null , null)
                }
            }

            R.id.listen_ans ->{
                if(resultSound == TextToSpeech.LANG_NOT_SUPPORTED || resultSound == TextToSpeech.LANG_MISSING_DATA)
                {
                    Toast.makeText(this , "Error" , Toast.LENGTH_LONG).show()
                }
                else
                {
                    textToSpeach!!.speak(findViewById<TextView>(R.id.answerTextView).text , TextToSpeech.QUEUE_FLUSH , null , null)
                }
            }

        }
    }
}