package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment

class PlayerFragment : Fragment() {
    private lateinit var spinnerMove: Spinner

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
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logLifecycle("onViewCreated")
        spinnerMove = view.findViewById(R.id.spinnerMove)
        val options = listOf("Pedra", "Tesoura", "Papel")
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner_selected,
            options
        )
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinnerMove.adapter = adapter
        val selectedPosition = savedInstanceState?.getInt(KEY_SELECTED_MOVE) ?: 0
        spinnerMove.setSelection(selectedPosition)
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
        outState.putInt(KEY_SELECTED_MOVE, spinnerMove.selectedItemPosition)
        logLifecycle("onSaveInstanceState")
    }

    private fun logLifecycle(event: String) {
        Log.d(TAG, "PlayerFragment -> $event")
    }

    companion object {
        private const val TAG = "Lifecycle"
        private const val KEY_SELECTED_MOVE = "key_selected_move"
    }
}
