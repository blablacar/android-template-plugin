package com.comuto.androidtemplates.templates.standalone.klass

import com.comuto.androidtemplates.manager.PackageManager

fun createActivitySubComponent(
    packageName: String = PackageManager.packageName,
    injectionPackageName: String = PackageManager.packageName,
    subComponentName :String,
    screenName: String
) = """
package $injectionPackageName

import ${packageName}.${screenName}
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent()
interface $subComponentName {

    fun inject(activity: $screenName)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bind(activity: $screenName): Builder

        fun build(): $subComponentName
    }
}
    
""".trimIndent()
