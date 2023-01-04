package com.comuto.androidtemplates.templates

import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.PackageNameWidget
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.impl.defaultPackageNameParameter
import com.android.tools.idea.wizard.template.stringParameter
import com.android.tools.idea.wizard.template.template
import com.comuto.androidtemplates.templates.standalone.standaloneFragmentTemplateRecipe

val feature
    get() = template {
        name = "Feature module"
        description = "Create a new feature module"
        minApi = 21
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.MenuEntry)

        val packageNameParam = defaultPackageNameParameter
        val moduleNameParam = stringParameter {
            name = "Feature Module Name"
            default = "Module"
            help = "E.G: messaging"
            constraints = listOf(Constraint.NONEMPTY)
        }

        widgets(
            PackageNameWidget(packageNameParam),
            TextFieldWidget(moduleNameParam),
        )

        recipe = { data: TemplateData ->
            standaloneFragmentTemplateRecipe(
                data as ModuleTemplateData,
                packageNameParam.value,
                moduleNameParam.value,
            )
        }
    }