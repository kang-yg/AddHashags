package com.tagplus.addhashtags.View.Mine

sealed class MineRecyclerViewItemEvent

object FAVORITE: MineRecyclerViewItemEvent()
object COPY: MineRecyclerViewItemEvent()
object DELETE: MineRecyclerViewItemEvent()
