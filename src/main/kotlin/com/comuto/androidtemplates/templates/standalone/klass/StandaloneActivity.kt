package com.comuto.androidtemplates.templates.standalone.klass

import android.databinding.tool.ext.toCamelCase
import com.comuto.androidtemplates.manager.PackageManager

fun createStandaloneActivity(
    packageName: String = PackageManager.packageName,
    activityName: String,
    layoutName: String
) = """
package $packageName

import com.comuto.databinding.${layoutName.toCamelCase().replace("_", "")}Binding
import com.comuto.di.InjectHelper
import android.os.Bundle

TODO() //@MetricTag()
class $activityName() : PixarActivityV2() {

    private var _binding: ${layoutName.toCamelCase().replace("_", "")}Binding? = null
    private val binding get() = _binding!!
    override fun getScreenName(): String = TODO()

    override fun onCreate(savedInstanceState: Bundle?) {
        TODO() //Add this activity to a manifest
        super.onCreate(savedInstanceState)
        _binding = ${layoutName.toCamelCase().replace("_", "")} Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun inject() {
        InjectHelper.getOrCreateSubcomponent(this, TODO())
            .TODO()
            .bind(this)
            .build()
            .inject(this)
    }
}
""".trimIndent()