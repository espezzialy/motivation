package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhraseFilter : Int = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (supportActionBar != null) {
//            supportActionBar!!.hide()
//        }

        mSecurityPreferences = SecurityPreferences(this)
        val name =  mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        textName.text = "Olá, $name!"

        imageAll.setColorFilter(resources.getColor(R.color.white))


        textName.setOnClickListener(this)
        buttonNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val id = view.id
        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)

        if (id == R.id.buttonNewPhrase) {
            handleNewPhrase()
        } else if (id in listFilter) {
            handleFilter(id)
        }
        
        else if(id == R.id.textName){
            var name = ""
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)statico
            finish()
        }
    }

    private fun handleFilter(id: Int) {
        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
        imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))

        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.white))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.white))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.white))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }

    }

    private fun handleNewPhrase() {
        textPhrase.text = Mock().getPhrase(mPhraseFilter).description
        imagemTeste.setImageResource(Mock().getPhrase(mPhraseFilter).imagesrc)
    }
}