package com.comuto.androidtemplates.templates.presentationlayer

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.comuto.androidtemplates.listeners.TemplatePluginManagerListener.Companion.projectInstance
import com.comuto.androidtemplates.manager.ProjectFileManager
import com.comuto.androidtemplates.manager.addPackageName
import com.comuto.androidtemplates.templates.presentationlayer.klass.createPresentationFragment
import com.comuto.androidtemplates.templates.presentationlayer.klass.createPresentationModule
import com.comuto.androidtemplates.templates.presentationlayer.klass.createPresentationSubComponent
import com.comuto.androidtemplates.templates.presentationlayer.klass.createPresentationViewModelFactory
import com.comuto.androidtemplates.templates.standalone.klass.createStandaloneLayoutXML
import com.comuto.androidtemplates.templates.standalone.klass.createStandaloneViewModel
import com.comuto.androidtemplates.utils.asKt
import com.comuto.androidtemplates.utils.asXml
import com.comuto.androidtemplates.utils.saveClass
import com.comuto.androidtemplates.utils.saveXML

fun RecipeExecutor.fragmentPresentationLayerTemplate(
    moduleData: ModuleTemplateData,
    packageName: String,
    className: String,
    layoutName: String,
    viewModelName: String,
    subComponentName: String
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = projectInstance ?: return
    val diPackageName = "$packageName.di"
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())

    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createPresentationFragment(fragmentName = className, layoutName = layoutName, viewModelName = viewModelName).saveClass(
        pfm.getPath(),
        packageName,
        className.asKt()
    )

    createStandaloneViewModel(packageName = packageName, viewModelName = viewModelName).saveClass(
        pfm.getPath(),
        packageName,
        viewModelName.asKt()
    )
    createPresentationViewModelFactory(packageName = packageName, viewModelName = viewModelName).saveClass(
        pfm.getPath(),
        packageName,
        "${viewModelName}Factory".asKt()
    )
    createPresentationSubComponent(
        presentationPackageName = packageName,
        injectionPackageName = diPackageName,
        subComponentName = subComponentName,
        fragmentName = className,
        viewModelName = viewModelName
    ).saveClass(pfm.getPath(), diPackageName, subComponentName.asKt())

    createPresentationModule(
        presentationPackageName = packageName,
        injectionPackageName = diPackageName,
        fragmentName = className,
        viewModelName = viewModelName,
    ).saveClass(pfm.getPath(), diPackageName, "${viewModelName}Module".asKt())


    createStandaloneLayoutXML().saveXML(pfm.getPath(), layoutName.asXml())
}