package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment

class JogadorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_jogador, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinnerMove = view.findViewById<Spinner>(R.id.spinnerMove)
        val options = listOf("Pedra", "Tesoura", "Papel")
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner_selected,
            options
        )
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinnerMove.adapter = adapter
    }
}
