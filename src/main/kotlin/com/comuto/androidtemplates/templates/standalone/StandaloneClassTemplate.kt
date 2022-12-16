package com.comuto.androidtemplates.templates.standalone

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

val fragment
    get() = template {
        name = "Fragment Standalone"
        description = "Create the Fragment and his layout"
        minApi = 21
        category = Category.Fragment
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.MenuEntry)

        val packageNameParam = defaultPackageNameParameter
        val classNameParam = stringParameter {
            name = "Fragment Name"
            default = "Fragment"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }
        val layoutName = stringParameter {
            name = "Layout Name"
            default = "layout_fragment_name"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }

        widgets(
            PackageNameWidget(packageNameParam),
            TextFieldWidget(classNameParam),
            TextFieldWidget(layoutName)
        )

        recipe = { data: TemplateData ->
            standaloneFragmentTemplateRecipe(
                data as ModuleTemplateData,
                packageNameParam.value,
                classNameParam.value,
                layoutName.value
            )
        }
    }

val viewModel
    get() = template {
        name = "ViewModel Standalone"
        description = "Create the viewModel"
        minApi = 21
        category = Category.Fragment
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.MenuEntry)

        val packageNameParam = defaultPackageNameParameter
        val viewModelName = stringParameter {
            name = "ViewModel Name but without ViewModel as a suffix, E.G MyFeature not MyFeatureViewModel"
            default = "MyFeature"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }
        widgets(
            PackageNameWidget(packageNameParam),
            TextFieldWidget(viewModelName),
        )

        recipe = { data: TemplateData ->
            standaloneViewModelTemplateRecipe(
                data as ModuleTemplateData,
                packageNameParam.value,
                viewModelName.value
            )
        }
    }