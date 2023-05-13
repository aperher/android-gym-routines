package com.example.gymroutines.model



data class RoutineDetail(
    var userId: String ="",
    var title: String ="",
    var level: Level = Level.Easy,
    var isPublic: Boolean = true,
    var imageURL: String = "",
    var description: String ="",
    var durationMin : Int = 0,
    var popularity: Int = 0,
    var isFavourite: Boolean = false,
    var exercises: List<ExercisesDetail> = listOf(),
    var equipment: List<Equipment> = listOf(),
) {

    data class ExercisesDetail(
        var name: String ="",
        var equipment: Equipment = Equipment.BodyWeight,
        var primaryMuscles: Muscles = Muscles.Abs,
        var secondaryMuscles: List<Muscles> = listOf(),
        var series: List<Int> = listOf()
    )
}
