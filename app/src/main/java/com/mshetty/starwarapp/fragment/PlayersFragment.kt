package com.mshetty.starwarapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mshetty.starwarapp.R
import com.mshetty.starwarapp.adapter.PlayersAdapter
import com.mshetty.starwarapp.databinding.FragmentPlayersBinding
import com.mshetty.starwarapp.viewmodel.PlayersViewModel

class PlayersFragment : Fragment() {

    private lateinit var binding: FragmentPlayersBinding
    private val viewModel : PlayersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        viewModel.getAllPlayers()
        viewModel.playersList.observe(viewLifecycleOwner) { response ->
            response?.let {
                binding.rvPlayers.layoutManager = LinearLayoutManager(context)
                binding.rvPlayers.adapter = PlayersAdapter(it) {
                    viewModel.selectedPlayerId.value = it?.tag.toString().toInt()
                    findNavController().navigate(R.id.playersDetailFragment)
                }
            }
        }
    }
}