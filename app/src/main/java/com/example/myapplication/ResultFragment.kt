package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {
    private lateinit var resultMessageView: TextView
    private val jokenpoEngine = JokenpoEngine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logLifecycle("onCreateView")
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logLifecycle("onViewCreated")
        resultMessageView = view.findViewById(R.id.tvResultWinner)
        val selectedPlay = arguments?.getString(ARG_CURRENT_PLAY) ?: "Pedra"

        val resultMessage = savedInstanceState?.getString(KEY_RESULT_MESSAGE) ?: run {
            when (jokenpoEngine.calculateResult(selectedPlay)) {
                JokenpoEngine.Result.WIN -> getString(R.string.result_win)
                JokenpoEngine.Result.DRAW -> getString(R.string.result_draw)
                JokenpoEngine.Result.LOSS -> getString(R.string.result_loss)
            }
        }
        resultMessageView.text = resultMessage
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logLifecycle("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_RESULT_MESSAGE, resultMessageView.text.toString())
        logLifecycle("onSaveInstanceState")
    }

    private fun logLifecycle(event: String) {
        Log.d(TAG, "ResultFragment -> $event")
    }

    companion object {
        private const val TAG = "Lifecycle"
        private const val KEY_RESULT_MESSAGE = "key_result_message"
        const val ARG_CURRENT_PLAY = "arg_current_play"
    }
}
