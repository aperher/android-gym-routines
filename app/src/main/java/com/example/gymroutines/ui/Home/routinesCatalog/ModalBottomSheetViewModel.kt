package com.example.gymroutines.ui.Home.routinesCatalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymroutines.model.Equipment
import com.example.gymroutines.model.FilterType
import com.example.gymroutines.model.Level
import com.example.gymroutines.model.Muscles
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ModalBottomSheetViewModel @Inject constructor() : ViewModel() {
    private var _filterType = MutableLiveData<FilterType>()
    val filterType get() = _filterType

    private var _title = MutableLiveData<String>()
    val title get() = _title

    private var _list = MutableLiveData<List<String>>(emptyList())
    val list get() = _list

    private var _selectedItem = MutableLiveData<String>()
    val selectedItem get() = _selectedItem

    private var _goToRoutinesFragment = MutableLiveData<Event<Boolean>>()
    val goToRoutinesFragment get() = _goToRoutinesFragment

    fun setFilterType(filterType: FilterType?) {
        filterType?.let{
            _filterType.value = it
            initData()
        }
    }

    fun setSelectedItem(selectedItem: String?) {
        selectedItem?.let {
            _selectedItem.value = it
        }
    }

    fun onItemSelected(item: String) {
        _selectedItem.value = item
        _goToRoutinesFragment.value = Event(true)
    }

    private fun initData() {
        when (filterType.value) {
            FilterType.Muscles -> {
                _title.value = "Selecciona un músculo"; _list.value = Muscles.values().map { it.value }
            }
            FilterType.Equipment -> {
                _title.value = "Selecciona equipamiento "; _list.value = Equipment.values().map { it.value }
            }
            FilterType.Level -> {
                _title.value = "Selecciona un nivel"; _list.value = Level.values().map { it.value }
            }
            else -> _title.value = "An error ocurred"
        }
    }
}