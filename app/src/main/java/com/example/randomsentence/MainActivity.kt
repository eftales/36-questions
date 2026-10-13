package com.example.randomsentence

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var sentences: Array<String>
    private lateinit var prefs: SharedPreferences
    private lateinit var textView: TextView
    private lateinit var countView: TextView

    /** 已经展示过的句子（存句子内容而非序号，改句子顺序也不会出错） */
    private val shown = mutableSetOf<String>()

    /** 隐藏重置：连续快速点击句子的计数 */
    private var secretTapCount = 0
    private var lastTapTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sentences = resources.getStringArray(R.array.sentences)
        prefs = getPreferences(Context.MODE_PRIVATE)
        shown.addAll(prefs.getStringSet(KEY_SHOWN, emptySet()).orEmpty())

        textView = findViewById(R.id.textView)
        countView = findViewById(R.id.countView)

        findViewById<Button>(R.id.button).setOnClickListener { showNext() }
        // 隐藏重置：连续快速点击句子 7 次
        textView.setOnClickListener { onSentenceTapped() }

        updateCount()
        showNext()
    }

    /** 连续快速点击句子 SECRET_TAP_COUNT 次即重置；间隔超过 2 秒重新计数 */
    private fun onSentenceTapped() {
        val now = System.currentTimeMillis()
        secretTapCount = if (now - lastTapTime < TAP_INTERVAL_MS) secretTapCount + 1 else 1
        lastTapTime = now
        if (secretTapCount >= SECRET_TAP_COUNT) {
            secretTapCount = 0
            reset()
            Toast.makeText(this, R.string.reset_toast, Toast.LENGTH_SHORT).show()
        }
    }

    /** 按顺序展示下一个没出现过的句子；全部展示完则提示 */
    private fun showNext() {
        val next = sentences.firstOrNull { it !in shown }
        if (next == null) {
            textView.text = getString(R.string.all_shown)
            updateCount()
            return
        }
        shown.add(next)
        prefs.edit().putStringSet(KEY_SHOWN, shown).apply()
        textView.text = next
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
        private const val SECRET_TAP_COUNT = 7
        private const val TAP_INTERVAL_MS = 2000L
    }
}
