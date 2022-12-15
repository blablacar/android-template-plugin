package com.comuto.androidtemplates.templates.presentationlayer.klass

import android.databinding.tool.ext.toCamelCase
import com.comuto.androidtemplates.manager.PackageManager

fun createPresentationFragment(
    presentationPackageName: String = PackageManager.packageName,
    fragmentName: String,
    layoutName: String,
    viewModelName: String,
) = """
package $presentationPackageName

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comuto.R
import com.comuto.coreui.fragment.PixarFragmentV2
import com.comuto.di.InjectHelper
import com.comuto.databinding.${layoutName.toCamelCase().replace("_", "")}Binding
import javax.inject.Inject

class $fragmentName : PixarFragmentV2() {

    override val title: Int
        get() = TODO()

    @Inject
    lateinit var viewModel: $viewModelName

    private var _binding: ${layoutName.toCamelCase().replace("_", "")}Binding? = null
    private val binding get() = _binding!!

    override fun getScreenName() = TODO()

    companion object {
        fun newInstance(): $fragmentName = TODO()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ${ layoutName.toCamelCase().replace("_", "") }Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.liveEvent.observe(viewLifecycleOwner) { event -> onNewEvent(event) }
        viewModel.liveState.observe(viewLifecycleOwner) { state -> onStateUpdated(state) }
    }

    private fun onStateUpdated(state: ${viewModelName}State){
        when (state) {
            else -> TODO()
        }
    }

    private fun onNewEvent(event: ${viewModelName}Event){
        when (event) {
            else -> TODO()
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    override fun injectFragment() {
        InjectHelper.getOrCreateSubcomponent(requireContext(), TODO())
            .TODO()
            .bind(this)
            .bind(requireActivity())
            .build()
            .inject(this)
    }
}
""".trimIndent()


fun createPresentationViewModelFactory(
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


fun createPresentationSubComponent(
    presentationPackageName: String = PackageManager.packageName,
    injectionPackageName: String = PackageManager.packageName,
    subComponentName :String,
    fragmentName: String,
    viewModelName: String
) = """
package $injectionPackageName


import androidx.fragment.app.FragmentActivity
import ${presentationPackageName}.${fragmentName}
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(modules = [${viewModelName}Module::class])
interface $subComponentName {

    fun inject(fragment: $fragmentName)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bind(fragment: $fragmentName): Builder

        @BindsInstance
        fun bind(activity: FragmentActivity): Builder

        fun build(): $subComponentName
    }
}
    
""".trimIndent()


fun createPresentationModule(
    presentationPackageName: String = PackageManager.packageName,
    injectionPackageName: String = PackageManager.packageName,
    viewModelName: String,
    fragmentName: String
) = """
package $injectionPackageName

import androidx.lifecycle.ViewModelProvider
import ${presentationPackageName}.$fragmentName
import ${presentationPackageName}.$viewModelName
import ${presentationPackageName}.${viewModelName}Factory
import dagger.Module
import dagger.Provides

@Module
class ${viewModelName}Module {

    @Provides
    fun provide${viewModelName}(
        fragment: $fragmentName,
        factory: ${viewModelName}Factory
    ): $viewModelName=
        ViewModelProvider(fragment, factory)[${viewModelName}::class.java]
}
    
""".trimIndent()
