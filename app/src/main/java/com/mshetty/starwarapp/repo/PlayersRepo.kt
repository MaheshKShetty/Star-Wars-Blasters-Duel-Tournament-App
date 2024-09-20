package com.mshetty.starwarapp.repo

import android.content.Context
import com.google.gson.Gson
import com.mshetty.starwarapp.model.ItemInfo
import com.mshetty.starwarapp.model.PlayersList
import com.mshetty.starwarapp.model.StarMatchersResponse

class PlayersRepo : BaseRepo() {

    fun getPlayersList(context: Context): PlayersList? {
        val playersList = fetchDataFromAssets(context, "StarWarsPlayers.json", PlayersList::class.java)
        val playerMatchInfo = fetchStarMatchers(context)

        val playersMap = playersList?.data?.associateBy { it?.id }

        playersList?.data?.forEach { playerInfo ->
            playerInfo?.let { info ->
                val filteredList = playerMatchInfo?.filter {
                    it?.player1?.id == info.id || it?.player2?.id == info.id
                }

                var totalPlay = 0

                filteredList?.forEach { match ->
                    match?.let { m ->
                        when {
                            m.player1?.id == info.id -> {
                                m.player1?.name = info.name
                                totalPlay += calculatePoints(m.player1?.score, m.player2?.score)
                            }
                            m.player2?.id == info.id -> {
                                m.player2?.name = playersMap?.get(m.player2?.id)?.name
                                totalPlay += calculatePoints(m.player2?.score, m.player1?.score)
                            }
                        }
                    }
                }

                info.totalPlay = totalPlay
                info.listGamePlay = filteredList
            }
        }

        return playersList
    }

    private fun calculatePoints(score1: Int?, score2: Int?): Int {
        return when {
            (score1 ?: 0) > (score2 ?: 0) -> 3
            (score1 ?: 0) == (score2 ?: 0) -> 1
            else -> 0
        }
    }

    private fun fetchStarMatchers(context: Context) : List<ItemInfo?>? {
        return fetchDataFromAssets(context,"StarWarsMatches.json",StarMatchersResponse::class.java)?.data
    }

}