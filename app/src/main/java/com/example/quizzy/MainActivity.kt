package com.example.quizzy

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf(
        "What is the full form of OOP in programming?",
        "Which of the following is not a programming language?",
        "What is the time complexity of Kadane's Algorithm?",
        "What is the result of typeof null in JavaScript?",
        "Which of the following will not throw an error in JavaScript?",
        "What does SQL stand for?",
        "In SQL, which command is used to remove a table?",
        "Which of the following is not an example of a loop in programming?",
        "Which of the following is a valid comment in Java?",
        "Which of the following sorting algorithms has the best worst-case time complexity?",
        "Which of the following is not a valid Java keyword?"
    )

    private val options = arrayOf(
        arrayOf("Objective-Oriented Programming", "Object-Oriented Programming", "Operational-Oriented Programming"),
        arrayOf("Javascript", "PHP", "HTML"),
        arrayOf("O(n^)","O(n)", "O(n log n)"),
        arrayOf("undefined", "object", "function"),
        arrayOf("5 == \"5\"", "NaN === NaN", "5 + null"),
        arrayOf("Simple Query Language", "Structured Question Language", "Structured Query Language"),
        arrayOf("DROP TABLE", "DELETE TABLE", "REMOVE TABLE"),
        arrayOf("for", "repeat", "do-while"),
        arrayOf("//This is a comment", "/*/This is a comment", "*//This is a comment"),
        arrayOf("Quick Sort", "Merge Sort", "Bubble Sort"),
        arrayOf("transient", "volatile", "delegate")
    )

    private val correctAnswers = arrayOf(1,2,1,1,0,2,0,1,0,1,2)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.option1Button.setOnClickListener{
            checkAnswer(0)
        }

        binding.option2Button.setOnClickListener{
            checkAnswer(1)
        }

        binding.option3Button.setOnClickListener{
            checkAnswer(2)
        }

        binding.restartButton.setOnClickListener{
            restartQuiz()
        }
    }

    private fun correctButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }

    private fun incorrectButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors() {
        binding.option1Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option2Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option3Button.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    private fun showResults() {
        Toast.makeText(this, "Your score is $score out of ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }

    private fun displayQuestion() {
        binding.questionText.text = questions[currentQuestionIndex]
        binding.option1Button.text = options[currentQuestionIndex][0]
        binding.option2Button.text = options[currentQuestionIndex][1]
        binding.option3Button.text = options[currentQuestionIndex][2]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]
        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            incorrectButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }

        // Proceed to next question or show result if it's the last question
        if (currentQuestionIndex == questions.size - 1) {
            showResults()
            binding.restartButton.isEnabled = true
        } else {
            currentQuestionIndex++
            binding.questionText.postDelayed({ displayQuestion() }, 1000)
        }
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
    }
}
