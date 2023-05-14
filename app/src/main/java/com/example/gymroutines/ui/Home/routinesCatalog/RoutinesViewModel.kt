package com.example.gymroutines.ui.Home.routinesCatalog

import androidx.lifecycle.*
import com.example.gymroutines.data.routinesCatalog.RoutinesCatalogRepository
import com.example.gymroutines.model.*
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutinesViewModel @Inject constructor(private val repository: RoutinesCatalogRepository) :
    ViewModel() {

    private var _goToRoutineDetails = MutableLiveData<Event<String>>()
    val goToRoutineDetails: LiveData<Event<String>> get() = _goToRoutineDetails

    private var _goToBottomSheetFilter = MutableLiveData<Event<Boolean>>()
    val goToBottomSheetFilter: LiveData<Event<Boolean>> get() = _goToBottomSheetFilter

    // Cuando routinesList es null, se muestra el catálogo
    val routinesCatalog: LiveData<List<Catalog>> =
        repository.getRoutinesCatalog().catch { TODO() }.asLiveData()

    val isLoading: LiveData<Boolean> = routinesCatalog.map { it.isEmpty() }

    // Cuando routinesList es distinto de null, se muestra el listado de rutinas (filtros o VerTodo)
    private var _routinesList = MutableLiveData<List<RoutinePreview>?>(null)
    val routinesList: LiveData<List<RoutinePreview>?> get() = _routinesList

    //BottomSheet

    private lateinit var filterType: FilterType
    private var bsSelectedItems : MutableMap<FilterType, MutableList<String>> = mutableMapOf()
    val selectedItems get() = bsSelectedItems[filterType] ?: emptyList()

    private var _bsTitle = MutableLiveData<String>()
    val title: LiveData<String> get() = _bsTitle

    private var _bsList = MutableLiveData<List<String>>(emptyList())
    val list: LiveData<List<String>> get() = _bsList

    private var _exception = MutableLiveData<Throwable?>(null)
    val exception get() = _exception

    fun onRoutineClicked(routineId: String) {
        _goToRoutineDetails.value = Event(routineId)
    }

    fun onShowAllClicked(catalogTitle: CatalogType) {
        viewModelScope.launch {
            repository.getRoutinesByCatalog(catalogTitle).fold(onSuccess = {
                _routinesList.value = it
            }, onFailure = {
                exception.value = it
            })
        }
    }

    fun openFilter(filterType: FilterType) {
        this.filterType = filterType
        when (filterType) {
            FilterType.Muscles -> {
                _bsTitle.value = "Selecciona músculos"; _bsList.value =
                    Muscles.values().map { it.value }
            }
            FilterType.Equipment -> {
                _bsTitle.value = "Selecciona equipamiento "; _bsList.value =
                    Equipment.values().map { it.value }
            }
            FilterType.Level -> {
                _bsTitle.value = "Selecciona niveles"; _bsList.value =
                    Level.values().map { it.value }
            }
        }
        _goToBottomSheetFilter.value = Event(true)
    }

    fun addFilter(filter: String) {
        if (bsSelectedItems[filterType]?.contains(filter) == true) {
            bsSelectedItems[filterType]?.remove(filter)
        } else {
            bsSelectedItems[filterType]?.add(filter) ?: run {
                bsSelectedItems[filterType] = mutableListOf(filter)
            }
        }
    }

    fun showCatalog() {
        _routinesList.value = null
    }

    fun resetError(){
        exception.value = null
    }
}