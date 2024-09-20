package com.mshetty.starwarapp.model

data class PlayersList(
	val data: List<DataItem?>? = null
)

data class DataItem(
	val name: String? = null,
	val icon: String? = null,
	val id: Int? = null,

	var totalPlay:Int? = null,
	var listGamePlay:  List<ItemInfo?>? = ArrayList()
)

