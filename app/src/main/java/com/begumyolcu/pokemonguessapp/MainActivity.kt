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

        //Define the views
        guessButton = findViewById(R.id.buttonGuess)
        startButton = findViewById(R.id.buttonStart)
        retryButton = findViewById(R.id.buttonRetry)

        pokemonImageView = findViewById(R.id.pokemonImageView)

        livesTextView = findViewById(R.id.textViewLives)
        gameDescriptionText = findViewById(R.id.descriptionTextView)
        guessEditText = findViewById(R.id.pokemonEditText)
        pokemonTextInput = findViewById(R.id.pokemonTextInputLayout)

        //Get the settings for the first screen
        homeScreen()

        startButton.setOnClickListener {
            livesTextView.text = String.format(getString(R.string.life_string), lives.toString())

            gameScreen()
            randomPokemon = getRandomPokemon()

        }

        guessButton.setOnClickListener {
            guessPokemon(randomPokemon)
            randomPokemon = getRandomPokemon()
        }

        retryButton.setOnClickListener {
            retryButton.visibility = View.INVISIBLE

            lives = 3
            livesTextView.text = String.format(getString(R.string.life_string), lives.toString())

            gameScreen()
            randomPokemon = getRandomPokemon()

        }

    }

    //view visibility settings for the home screen
    private fun homeScreen() {
        guessButton.visibility = View.INVISIBLE
        retryButton.visibility = View.INVISIBLE
        pokemonTextInput.visibility = View.INVISIBLE
        livesTextView.visibility = View.INVISIBLE
    }

    //view visibility settings for the game screen
    private fun gameScreen() {
        startButton.visibility = View.INVISIBLE
        gameDescriptionText.visibility = View.INVISIBLE

        guessButton.visibility = View.VISIBLE
        pokemonTextInput.visibility = View.VISIBLE
        livesTextView.visibility = View.VISIBLE
    }

    //view visibility settings for the game lost screen
    private fun lostScreen() {
        retryButton.visibility = View.VISIBLE
        gameDescriptionText.visibility = View.VISIBLE

        guessButton.visibility = View.INVISIBLE
        pokemonTextInput.visibility = View.INVISIBLE
        livesTextView.visibility = View.INVISIBLE

        //set default image (which is the pokeball) for the game lost screen
        pokemonImageView.setImageResource(R.drawable.pokeball)

        //set description for the game lost screen
        gameDescriptionText.text = String.format(getString(R.string.retry_text))
    }

    private fun getRandomPokemon(): Pokemon {
        //get the pokemon list
        pokemonList = PokemonList.pokemonList

        //Get a random index to choose a pokemon and the pokemon with the corresponding index
        val getRandomPokemon = when ((pokemonList.indices).random()) {
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

        //change the image view source for the random pokemon
        pokemonImageView.setImageResource(getRandomPokemon.imageSource)

        return getRandomPokemon
    }

    private fun guessPokemon(pokemon: Pokemon) {
        val guess = guessEditText.text.toString().lowercase()
        val pokemonName = pokemon.name.lowercase()

        println(guess)
        println(pokemonName)
        when (guess) {
            pokemonName -> {
                //show a snackbar the notify the user for the correct answer
                Snackbar.make(
                    findViewById(R.id.constraintLayoutHome),
                    getString(R.string.correct),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            else -> {
                //show a snackbar the notify the user for the wrong answer
                Snackbar.make(
                    findViewById(R.id.constraintLayoutHome),
                    getString(R.string.wrong),
                    Snackbar.LENGTH_SHORT
                ).show()

                lives -= 1
                livesTextView.text =
                    String.format(getString(R.string.life_string), lives.toString())

                //get the settings for the game lost screen if lives are equal to 0
                if (lives == 0) {
                    lostScreen()
                }
            }
        }
    }
}