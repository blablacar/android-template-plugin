package com.comuto.androidtemplates.templates.standalone.klass

import com.comuto.androidtemplates.manager.PackageManager

fun createStandaloneViewModel(
    packageName: String = PackageManager.packageName,
    viewModelName: String
) = """
package $packageName

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comuto.coreui.livedata.SingleLiveEvent
import kotlinx.coroutines.launch

class $viewModelName(
    defaultState: ${viewModelName}State = ${viewModelName}State.InitialState
) : ViewModel() {

    private val _liveState = MutableLiveData(defaultState)
    val liveState: LiveData<${viewModelName}State> get () = _liveState

    private val _liveEvent = SingleLiveEvent<${ viewModelName}Event>()
    val liveEvent: LiveData<${viewModelName}Event > get () = _liveEvent

}

sealed class ${viewModelName}State {
    object InitialState: ${viewModelName}State()
}

sealed class ${viewModelName}Event {
}
""".trimIndent()

fun createViewModelFactory(
    packageName: String = PackageManager.packageName,
    viewModelName: String
) = """
package $packageName

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ${viewModelName}Factory @Inject constructor(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T =
    ${viewModelName}() as T
}
    
""".trimIndent()

