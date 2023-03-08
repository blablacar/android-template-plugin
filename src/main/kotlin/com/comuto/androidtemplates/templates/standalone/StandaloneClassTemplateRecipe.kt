package com.comuto.androidtemplates.templates.standalone

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.comuto.androidtemplates.listeners.TemplatePluginManagerListener
import com.comuto.androidtemplates.manager.ProjectFileManager
import com.comuto.androidtemplates.manager.addPackageName
import com.comuto.androidtemplates.templates.standalone.klass.createStandaloneActivity
import com.comuto.androidtemplates.templates.standalone.klass.createStandaloneFragment
import com.comuto.androidtemplates.templates.standalone.klass.createStandaloneFragmentLayoutXML
import com.comuto.androidtemplates.templates.standalone.klass.createStandaloneViewModel
import com.comuto.androidtemplates.templates.standalone.klass.createActivitySubComponent
import com.comuto.androidtemplates.templates.standalone.klass.createStandaloneActivityLayoutXML
import com.comuto.androidtemplates.templates.standalone.klass.createViewModelFactory
import com.comuto.androidtemplates.utils.asKt
import com.comuto.androidtemplates.utils.asXml
import com.comuto.androidtemplates.utils.saveClass
import com.comuto.androidtemplates.utils.saveXML

fun RecipeExecutor.standaloneFragmentTemplateRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    className: String,
    layoutName: String
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = TemplatePluginManagerListener.projectInstance ?: return
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())

    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createStandaloneFragment(fragmentName = className, layoutName = layoutName).saveClass(
        pfm.getPath(),
        packageName,
        className.asKt()
    )

    createStandaloneFragmentLayoutXML().saveXML(pfm.getPath(), layoutName.asXml())
}

fun RecipeExecutor.standaloneViewModelTemplateRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    viewModelName: String,
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = TemplatePluginManagerListener.projectInstance ?: return
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())
    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createStandaloneViewModel(packageName = packageName, viewModelName = viewModelName).saveClass(
        pfm.getPath(),
        packageName,
        " ${viewModelName}ViewModel".asKt()
    )
    createViewModelFactory(packageName = packageName, viewModelName = viewModelName).saveClass(
        pfm.getPath(),
        packageName,
        "${viewModelName}ViewModelFactory".asKt()
    )
}

fun RecipeExecutor.standaloneActivityTemplateRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    activityName: String,
    layoutName: String,
    subComponentName: String
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = TemplatePluginManagerListener.projectInstance ?: return
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())
    val injectionPackage = "$packageName.di"

    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createStandaloneActivity(
        packageName = packageName,
        activityName = activityName,
        layoutName = layoutName
    ).saveClass(
        pfm.getPath(),
        packageName,
        activityName.asKt()
    )
    createStandaloneActivityLayoutXML().saveXML(pfm.getPath(), layoutName.asXml())

    createActivitySubComponent(
        packageName = packageName,
        injectionPackageName = injectionPackage,
        subComponentName = subComponentName,
        screenName = activityName
    ).saveClass(pfm.getPath(), injectionPackage, subComponentName.asKt())
}

