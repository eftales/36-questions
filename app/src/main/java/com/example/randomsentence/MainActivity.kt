package com.example.randomsentence

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sentences = resources.getStringArray(R.array.sentences)
        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            textView.text = sentences[Random.nextInt(sentences.size)]
        }

        // 启动时先随机展示一句
        textView.text = sentences[Random.nextInt(sentences.size)]
    }
}
