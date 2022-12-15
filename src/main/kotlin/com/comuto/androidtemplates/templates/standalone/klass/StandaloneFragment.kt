package com.comuto.androidtemplates.templates.standalone.klass

import android.databinding.tool.ext.toCamelCase
import com.comuto.androidtemplates.manager.PackageManager

fun createStandaloneFragment(
    packageName: String = PackageManager.packageName,
    fragmentName: String,
    layountName: String
) = """
package $packageName

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import android.view.ViewGroup
import com.comuto.coreui.fragment.PixarFragmentV2
import com.comuto.databinding.${layountName.toCamelCase().replace("_", "")}Binding


class $fragmentName : PixarFragmentV2() {

    override val title: Int
        get() = TODO()

    private var _binding: ${layountName.toCamelCase().replace("_", "")}Binding? = null
    private val binding get() = _binding!!

    private val toolbar: Toolbar
        get() = binding.toolbar.root

    override fun getScreenName() = TODO()

    companion object {

        fun newInstance(): $fragmentName = ${fragmentName.toCamelCase()}()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ${layountName.toCamelCase().replace("_", "")}Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
""".trimIndent()