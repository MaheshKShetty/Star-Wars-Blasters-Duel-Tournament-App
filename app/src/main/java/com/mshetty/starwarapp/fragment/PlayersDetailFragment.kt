package com.mshetty.starwarapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mshetty.starwarapp.adapter.PlayersDetailAdapter
import com.mshetty.starwarapp.databinding.FragmentPlayersDetailBinding
import com.mshetty.starwarapp.viewmodel.PlayersViewModel

class PlayersDetailFragment : Fragment() {

    private lateinit var binding: FragmentPlayersDetailBinding
    private val viewModel : PlayersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayersDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupViews()
    }

    private fun setupViews() {
        binding.tvPlayerName.text = viewModel.selectedPlayerId.value?.let {
            viewModel.getPlayersName(
                it
            )
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupAdapter() {
        val details = viewModel.selectedPlayerId.value?.let { viewModel.fetchPlayerDetails(it) }
        binding.rvPlayers.layoutManager = LinearLayoutManager(context)
        binding.rvPlayers.adapter =
            viewModel.selectedPlayerId.value?.let { PlayersDetailAdapter(details, it) }
    }

}