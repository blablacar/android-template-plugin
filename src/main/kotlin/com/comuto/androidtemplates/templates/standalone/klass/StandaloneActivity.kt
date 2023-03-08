package com.comuto.androidtemplates.templates.standalone.klass

import android.databinding.tool.ext.toCamelCase
import com.comuto.androidtemplates.manager.PackageManager

fun createStandaloneActivity(
    packageName: String = PackageManager.packageName,
    activityName: String,
    layoutName: String,
) = """
package $packageName

import ${packageName}.databinding.${layoutName.toCamelCase().replace("_", "")}Binding
import com.comuto.di.InjectHelper
import android.os.Bundle
import com.comuto.coreui.PixarActivityV2

@MetricTag(
    owner = TODO(),
    designSystem = TODO(),
    dependencyInjection = TODO(),
    architecture = TODO(),
    backgroundTaskManagement = TODO(),
    modularized = TODO(),
    flow = TODO()
)
class $activityName() : PixarActivityV2() {

    private lateinit var binding: ${layoutName.toCamelCase().replace("_", "")}Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        TODO() //Add this activity to a manifest
        super.onCreate(savedInstanceState)
        binding = ${layoutName.toCamelCase().replace("_", "")}Binding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(toolbar)
        displayActionBarUp()
    }

    override fun inject() {
        InjectHelper.getOrCreateSubcomponent(this, TODO())
            .TODO()
            .bind(this)
            .build()
            .inject(this)
    }
    
    override fun getScreenName(): String = TODO()
    
    companion object {}
}
""".trimIndent()