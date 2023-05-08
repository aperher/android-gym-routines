package com.example.gymroutines.model



data class RoutineDetail(
    var userId: String ="",
    var title: String ="",
    var level: String= "",
    var isPublic: Boolean = true,
    var imageURL: String = "",
    var description: String ="",
    var exercises: List<ExercisesDetail> = listOf(),
    var equipment: List<String> = listOf(),
) {

    data class ExercisesDetail(
        var name: String ="",
        var equipment: String ="",
        var primaryMuscles: String= "",
        var secondaryMuscles: List<String> = listOf(),
        var series: List<Int> = listOf()
    )
}
