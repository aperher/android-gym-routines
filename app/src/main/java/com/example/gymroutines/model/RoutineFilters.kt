package com.example.gymroutines.model

enum class FilterType {
    Equipment,
    Level,
    Muscles
}

enum class Equipment(val value: String) {
    Barbell("Barra"),
    Machines("Máquinas"),
    Dumbbells("Mancuernas"),
    BodyWeight("Peso corporal"),
    Pulley("Polea")
}

enum class Level(val value: String) {
    Easy("Fácil"),
    Intermediate("Intermedio"),
    Advanced("Avanzado")
}

enum class Muscles(val value: String) {
    Quadriceps("Cuádriceps"),
    Shoulders("Hombros"),
    Triceps("Tríceps"),
    Trapezius("Trapecios"),
    Chest("Pectorales"),
    UpperBack("Espalda superior"),
    Abs("Abdominales"),
    Hamstrings("Isquiotibilaes"),
    Biceps("Bíceps"),
    Calves("Femorales"),
    Glutes("Glúteos"),
    Soleus("Gemelos"),
    LatissimusDorsi("Dorsales"),
    LowerBack("Espalda baja"),
    Forearms("Antebrazos")
}