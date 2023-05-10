package com.example.gymroutines.ui.Home.routinesCatalog

import androidx.lifecycle.*
import com.example.gymroutines.data.routinesCatalog.RoutinesCatalogRepository
import com.example.gymroutines.model.Catalog
import com.example.gymroutines.model.CatalogType
import com.example.gymroutines.model.RoutinePreview
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutinesViewModel @Inject constructor(private val repository: RoutinesCatalogRepository): ViewModel() {

    private var _goToRoutineDetails = MutableLiveData<Event<String>>()
    val goToRoutineDetails: LiveData<Event<String>> get() = _goToRoutineDetails

    val routinesCatalog: LiveData<List<Catalog>> =
        repository.getRoutinesCatalog().catch { TODO() }.asLiveData()

    // Cuando routinesList es null, se muestra el catálogo (routinesCatalog)
    private var _routinesList = MutableLiveData<List<RoutinePreview>?>(null)
    val routinesList: LiveData<List<RoutinePreview>?> get() = _routinesList

    val isLoading: LiveData<Boolean> = routinesCatalog.map { it.isEmpty() }

    fun onRoutineClicked(routineId: String) {
        _goToRoutineDetails.value = Event(routineId)
    }

    fun onShowAllClicked(catalogTitle: CatalogType) {
        viewModelScope.launch {
            repository.getRoutinesByCatalog(catalogTitle).fold(onSuccess = {
                _routinesList.value = it
            }, onFailure = {
                TODO()
            })
        }
    }

    fun showCatalog() {
        _routinesList.value = null
    }
}