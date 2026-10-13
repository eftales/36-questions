package com.example.randomsentence

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : Activity() {

    private lateinit var sentences: Array<String>
    private lateinit var prefs: SharedPreferences
    private lateinit var textView: TextView
    private lateinit var countView: TextView

    /** 已经展示过的句子（存句子内容而非序号，改句子顺序也不会出错） */
    private val shown = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sentences = resources.getStringArray(R.array.sentences)
        prefs = getPreferences(Context.MODE_PRIVATE)
        shown.addAll(prefs.getStringSet(KEY_SHOWN, emptySet()).orEmpty())

        textView = findViewById(R.id.textView)
        countView = findViewById(R.id.countView)

        findViewById<Button>(R.id.button).setOnClickListener { showNext() }
        findViewById<Button>(R.id.resetButton).setOnClickListener { reset() }

        updateCount()
        showNext()
    }

    /** 随机展示一句没出现过的句子；全部展示完则提示 */
    private fun showNext() {
        val remaining = sentences.filter { it !in shown }
        if (remaining.isEmpty()) {
            textView.text = getString(R.string.all_shown)
            updateCount()
            return
        }
        val sentence = remaining[Random.nextInt(remaining.size)]
        shown.add(sentence)
        prefs.edit().putStringSet(KEY_SHOWN, shown).apply()
        textView.text = sentence
        updateCount()
    }

    /** 清空历史记录，所有句子重新参与随机 */
    private fun reset() {
        shown.clear()
        prefs.edit().remove(KEY_SHOWN).apply()
        showNext()
    }

    private fun updateCount() {
        countView.text =
            getString(R.string.progress_format, sentences.size - shown.size, sentences.size)
    }

    companion object {
        private const val KEY_SHOWN = "shown_sentences"
    }
}
