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
import com.comuto.coreui.PixarActivityV2
import com.comuto.annotation.MetricTag
import com.comuto.annotation.Architecture
import com.comuto.annotation.BackgroundTaskManagement
import com.comuto.annotation.DependencyInjection
import com.comuto.annotation.DesignSystem
import com.comuto.annotation.Flow
import com.comuto.annotation.Modularized
import com.comuto.annotation.Owner
import android.os.Bundle

@MetricTag(
    owner = Owner.CHAPTER,
    designSystem = DesignSystem.PIXAR,
    dependencyInjection = DependencyInjection.DAGGER,
    architecture = Architecture.NOT_APPLICABLE,
    backgroundTaskManagement = BackgroundTaskManagement.NOT_APPLICABLE,
    modularized = Modularized.NO,
    flow = Flow.NOT_APPLICABLE
)
class $activityName() : PixarActivityV2() {

    private var _binding: ${layoutName.toCamelCase().replace("_", "")}Binding? = null
    private val binding get() = _binding!!
    override fun getScreenName(): String = TODO()

    override fun onCreate(savedInstanceState: Bundle?) {
        TODO() //Add this activity to a manifest
        super.onCreate(savedInstanceState)
        _binding = ${layoutName.toCamelCase().replace("_", "")} Binding . inflate (layoutInflater)
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