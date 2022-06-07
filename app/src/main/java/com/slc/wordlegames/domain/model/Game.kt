package com.slc.wordlegames.domain.model

data class Game(
    val id: Int = 0,
    val name: String = "",
    val image: Int = 0,
    val url: String = "",
    var status: Boolean? = null
)
