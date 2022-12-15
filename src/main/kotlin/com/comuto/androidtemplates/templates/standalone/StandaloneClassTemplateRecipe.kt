package com.comuto.androidtemplates.templates.standalone

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.comuto.androidtemplates.listeners.MyProjectManagerListener
import com.comuto.androidtemplates.manager.ProjectFileManager
import com.comuto.androidtemplates.manager.addPackageName
import com.comuto.androidtemplates.templates.standalone.klass.createStandaloneFragment
import com.comuto.androidtemplates.templates.standalone.klass.createStandaloneLayoutXML
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
    val project = MyProjectManagerListener.projectInstance ?: return
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())

    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createStandaloneFragment(fragmentName = className, layountName = layoutName).saveClass(
        pfm.getPath(),
        packageName,
        className.asKt()
    )

    createStandaloneLayoutXML().saveXML(pfm.getPath(),layoutName.asXml())

}