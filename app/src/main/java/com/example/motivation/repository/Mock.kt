package com.example.motivation.repository

import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import java.util.*

data class Phrase(val description: String, val imagesrc: Int, val category: Int)

fun Int.random(): Int {
    return Random().nextInt(this)
}

class Mock {

    private val ALL = MotivationConstants.PHRASEFILTER.ALL
    private val HAPPY = MotivationConstants.PHRASEFILTER.HAPPY
    private val MORNING = MotivationConstants.PHRASEFILTER.MORNING

    private val mListPhrases: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", R.drawable.imagem,  HAPPY),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", R.drawable.imagem, MORNING)
    )

    fun getPhrase(value: Int): Phrase {



        val filtered = mListPhrases.filter { (it.category == value || value == ALL) }

        // Número aleatório de 0 ao tamanho da lista retornada do filtro
        val rand = (filtered.size).random()

        // Retorna string
        return filtered[rand]

    }

}