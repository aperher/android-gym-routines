package com.example.gymroutines.model



data class RoutineDetail(
    var userID: String,
    var title: String,
    var level: String,
    var isPublic: Boolean,
    var imageURL: String,
    var description: String,
    var exercises: List<ExercisesDetail>,
    var equipment: List<String>
) {

    data class ExercisesDetail(
        var name: String,
        var equipment: String,
        var primaryMuscles: String,
        var secondaryMuscles: List<String>,
        var series: List<Int>
    )
}
