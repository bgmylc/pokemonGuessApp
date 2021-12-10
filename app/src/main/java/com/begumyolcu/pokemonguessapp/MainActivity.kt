package com.begumyolcu.pokemonguessapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var pokemonList: List<Pokemon>
    private lateinit var pokemonImageView: ImageView
    private lateinit var guessButton: Button
    private lateinit var startButton: Button
    private lateinit var retryButton: Button
    private lateinit var livesTextView: TextView
    private lateinit var pokemonTextInput: TextInputLayout
    private lateinit var gameDescriptionText: TextView
    private lateinit var randomPokemon: Pokemon
    private lateinit var guessEditText: EditText
    private var lives = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guessButton = findViewById(R.id.buttonGuess)
        startButton = findViewById(R.id.buttonStart)
        retryButton = findViewById(R.id.buttonRetry)

        pokemonImageView = findViewById(R.id.pokemonImageView)
        livesTextView = findViewById(R.id.textViewLives)
        pokemonTextInput = findViewById(R.id.pokemonTextInputLayout)
        gameDescriptionText = findViewById(R.id.descriptionTextView)
        guessEditText = findViewById(R.id.pokemonEditText)

        homeScreen()


        startButton.setOnClickListener {
            livesTextView.text = String.format(getString(R.string.life_string), lives.toString())
            gameScreen()

        }

        for (i in 0..lives) {
            if (lives > 0) {
                randomPokemon = getRandomPokemon()
                guessButton.setOnClickListener {
                    guessPokemon(randomPokemon)
                }
            }

        }

    }

    private fun homeScreen() {
        guessButton.setVisibility(View.INVISIBLE)
        retryButton.setVisibility(View.INVISIBLE)
        pokemonTextInput.setVisibility(View.INVISIBLE)
        livesTextView.setVisibility(View.INVISIBLE)

        pokemonImageView.setImageResource(R.drawable.pokeball)

    }

    private fun gameScreen() {
        startButton.setVisibility(View.INVISIBLE)
        gameDescriptionText.setVisibility(View.INVISIBLE)

        guessButton.setVisibility(View.VISIBLE)
        pokemonTextInput.setVisibility(View.VISIBLE)
        livesTextView.setVisibility(View.VISIBLE)
    }

    private fun lostScreen() {
        retryButton.setVisibility(View.VISIBLE)
        gameDescriptionText.setVisibility(View.VISIBLE)

        guessButton.setVisibility(View.INVISIBLE)
        pokemonTextInput.setVisibility(View.INVISIBLE)
        livesTextView.setVisibility(View.INVISIBLE)

        pokemonImageView.setImageResource(R.drawable.pokeball)
        gameDescriptionText.text = String.format(getString(R.string.retry_text))

    }

    private fun getRandomPokemon(): Pokemon {
        pokemonList = PokemonList.pokemonList

        val pokemonSize = pokemonList.size
        val randomPokemonIndex = (0 until pokemonSize).random()

        val getRandomPokemon = when (randomPokemonIndex) {
            0 -> pokemonList[0]
            1 -> pokemonList[1]
            2 -> pokemonList[2]
            3 -> pokemonList[3]
            4 -> pokemonList[4]
            5 -> pokemonList[5]
            6 -> pokemonList[6]
            7 -> pokemonList[7]
            8 -> pokemonList[8]
            9 -> pokemonList[9]
            10 -> pokemonList[10]
            11 -> pokemonList[11]
            12 -> pokemonList[12]
            13 -> pokemonList[13]
            14 -> pokemonList[14]
            15 -> pokemonList[15]
            16 -> pokemonList[16]
            17 -> pokemonList[17]
            18 -> pokemonList[18]
            19 -> pokemonList[19]
            20 -> pokemonList[20]
            21 -> pokemonList[21]
            22 -> pokemonList[22]
            23 -> pokemonList[23]
            24 -> pokemonList[24]
            else -> pokemonList[25]

        }

        val pokemonImage = getRandomPokemon.imageSource
        pokemonImageView.setImageResource(pokemonImage)

        return getRandomPokemon
    }

    private fun guessPokemon(pokemon: Pokemon) {
        val guess = guessEditText.text.toString().lowercase()
        val pokemonName = pokemon.name.lowercase()

        when (guess) {
            pokemonName -> {
                Snackbar.make(
                    findViewById(R.id.constraintLayoutHome),
                    getString(R.string.correct),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            else -> {
                Snackbar.make(
                    findViewById(R.id.constraintLayoutHome),
                    getString(R.string.wrong),
                    Snackbar.LENGTH_SHORT
                ).show()
                lives -= 1
                livesTextView.text =
                    String.format(getString(R.string.life_string), lives.toString())
                if(lives == 0){
                    lostScreen()
                    retryButton.setOnClickListener {
                        gameScreen()
                    }
                }

            }
        }

    }

}




