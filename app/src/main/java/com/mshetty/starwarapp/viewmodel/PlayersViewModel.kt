package com.mshetty.starwarapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mshetty.starwarapp.model.DataItem
import com.mshetty.starwarapp.model.ItemInfo
import com.mshetty.starwarapp.model.PlayerStatus
import com.mshetty.starwarapp.repo.PlayersRepo
import kotlinx.coroutines.launch

class PlayersViewModel(val context: Application) : AndroidViewModel(context) {

    private var repo: PlayersRepo = PlayersRepo()
    var playersList: MutableLiveData<List<DataItem?>?> = MutableLiveData()
    var selectedPlayerId: MutableLiveData<Int?> = MutableLiveData()

    init {
        getAllPlayers() // Load players on initialization
    }

    // Fetch and sort the list of players, sorting by both totalPlay and name.
    fun getAllPlayers() {
        viewModelScope.launch {
            val sortedPlayers = repo.getPlayersList(context.applicationContext)?.data
                ?.sortedWith(compareByDescending<DataItem?> { it?.totalPlay }.thenBy { it?.name })

            playersList.postValue(sortedPlayers)
        }
    }

    private fun findPlayerById(id: Int): DataItem? {
        return playersList.value?.find { it?.id == id }
    }

    fun fetchPlayerDetails(id: Int): List<ItemInfo?>? {
        return findPlayerById(id)?.listGamePlay
    }

    fun getPlayersName(id: Int): String? {
        return findPlayerById(id)?.name
    }

    fun getPlayerStatus(): PlayerStatus {
        return PlayerStatus.WINNER(29) // Dummy value
    }
}
