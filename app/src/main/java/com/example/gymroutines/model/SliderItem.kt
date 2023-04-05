package com.example.gymroutines.model

data class SliderItem(val id: String, val title: String, val routinesList : List<RoutineItem>) {
    data class RoutineItem(val id: String, val title: String, val image: String)
}
