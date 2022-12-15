package com.comuto.androidtemplates.templates.standalone.klass

import android.databinding.tool.ext.toCamelCase
import com.comuto.androidtemplates.manager.PackageManager

fun createStandaloneFragment(
    packageName: String = PackageManager.packageName,
    fragmentName: String,
    layoutName: String
) = """
package $packageName

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comuto.coreui.fragment.PixarFragmentV2
import com.comuto.databinding.${layoutName.toCamelCase().replace("_", "")}Binding


class $fragmentName : PixarFragmentV2() {

    override val title: Int
        get() = TODO()

    private var _binding: ${layoutName.toCamelCase().replace("_", "")}Binding? = null
    private val binding get() = _binding!!

    override fun getScreenName() = TODO()

    companion object {
        fun newInstance(): $fragmentName = TODO()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ${layoutName.toCamelCase().replace("_", "")}Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
""".trimIndent()