package com.comuto.androidtemplates.templates.mapper

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

val mapperAndTest
    get() = template {
        name = "Mapper and Test"
        description = "Create the Mapper class with the testClass"
        minApi = 21
        category = Category.Fragment
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.MenuEntry)

        val packageNameParam = defaultPackageNameParameter
        val mapperName = stringParameter {
            name = "Mapper Name"
            default = "MapperName"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }
        val fromTypeName = stringParameter {
            name = "From Type Name"
            default = "fromType"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }
        val toTypeName = stringParameter {
            name = "To Type Name"
            default = "toType"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }

        widgets(
            PackageNameWidget(packageNameParam),
            TextFieldWidget(mapperName),
            TextFieldWidget(fromTypeName),
            TextFieldWidget(toTypeName)
        )

        recipe = { data: TemplateData ->
            mapperTemplateRecipe(
                data as ModuleTemplateData,
                packageNameParam.value,
                mapperName.value,
                fromTypeName.value,
                toTypeName.value
            )
        }
    }