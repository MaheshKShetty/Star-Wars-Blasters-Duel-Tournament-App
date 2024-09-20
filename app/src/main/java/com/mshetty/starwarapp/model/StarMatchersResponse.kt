package com.mshetty.starwarapp.model

data class StarMatchersResponse(
	val data: List<ItemInfo?>? = null
)

data class Player2(
	val score: Int? = null,
	val id: Int? = null,

	var name:String?= null
)

data class Player1(
	val score: Int? = null,
	val id: Int? = null,
	var name:String?= null
)

data class ItemInfo(
	val player1: Player1? = null,
	val player2: Player2? = null,
	val match: Int? = null,
)

