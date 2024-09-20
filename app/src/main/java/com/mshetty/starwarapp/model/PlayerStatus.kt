package com.mshetty.starwarapp.model

sealed class PlayerStatus(point:Int) {
     class WINNER(point: Int) :PlayerStatus(point =point)
     class LOSE(point: Int) :PlayerStatus(point = point )
}