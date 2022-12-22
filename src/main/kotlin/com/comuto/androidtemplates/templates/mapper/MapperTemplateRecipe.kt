package com.comuto.androidtemplates.templates.mapper

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.comuto.androidtemplates.listeners.TemplatePluginManagerListener
import com.comuto.androidtemplates.manager.ProjectFileManager
import com.comuto.androidtemplates.manager.addPackageName
import com.comuto.androidtemplates.templates.standalone.klass.createStandaloneViewModel
import com.comuto.androidtemplates.templates.standalone.klass.createViewModelFactory
import com.comuto.androidtemplates.utils.asKt
import com.comuto.androidtemplates.utils.saveClass
import com.comuto.androidtemplates.utils.saveTestClass

fun RecipeExecutor.mapperTemplateRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    mapperName: String,
    fromTypeName: String,
    toTypeName: String
) {
    val (projectData, _, _,) = moduleData
    val project = TemplatePluginManagerListener.projectInstance ?: return
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())

    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createMapper(packageName = packageName, mapperName = mapperName, fromType =  fromTypeName, toType = toTypeName).saveClass(
        pfm.getPath(),
        packageName,
        mapperName.asKt()
    )
    createMapperTest(packageName=packageName, mapperName = mapperName).saveTestClass(
        pfm.getTestPath(),
        packageName,
        "${mapperName}Test".asKt()
    )
}