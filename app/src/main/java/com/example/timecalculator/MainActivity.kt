package com.example.timecalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var firstOperandET: EditText
    private lateinit var secondOperandET: EditText

    private lateinit var buttonSumBTN: Button
    private lateinit var buttondDifBTN: Button

    private lateinit var resultTV: TextView

    private lateinit var buttonResetBTN: Button
    private lateinit var buttondExitBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firstOperandET = findViewById(R.id.firstOperandET)
        secondOperandET = findViewById(R.id.secondOperandET)

        buttonSumBTN = findViewById(R.id.buttonSumBTN)
        buttondDifBTN = findViewById(R.id.buttonDifBTN)

        resultTV = findViewById(R.id.resultTV)

        buttonResetBTN = findViewById(R.id.buttonResetBTN)
        buttondExitBTN = findViewById(R.id.buttonExitBTN)

        buttonSumBTN.setOnClickListener(this)
        buttondDifBTN.setOnClickListener(this)
        buttonResetBTN.setOnClickListener(this)
        buttondExitBTN.setOnClickListener(this)
    }

    override fun onClick(v: View){

        var check = true

        if (firstOperandET.text.isEmpty() || secondOperandET.text.isEmpty()){
            return
        }

        try {
            var first = firstOperandET.text
            var second = secondOperandET.text

            val operation = Operation(first.toString(), second.toString())
            var result = when(v.id){
                R.id.buttonSumBTN -> operation.operationResult(operation.sum())
                R.id.buttonDifBTN -> operation.operationResult(operation.dif())
                R.id.buttonResetBTN -> {
                    firstOperandET.text.clear()
                    secondOperandET.text.clear()
                    check = false
                }

                R.id.buttonExitBTN -> finish()
                else -> 0.0
            }
            if (!check) resultTV.text = "Результат" else resultTV.text = result.toString()
        } catch (e: DataFormatEditTextException){
            resultTV.text = e.message
        }

    }
}