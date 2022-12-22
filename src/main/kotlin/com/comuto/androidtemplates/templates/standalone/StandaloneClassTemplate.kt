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

val activity
    get() = template {
        name = "Activity Standalone"
        description = "Create only an Activity"
        minApi = 21
        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.MenuEntry)

        val packageNameParam = defaultPackageNameParameter
        val activityName = stringParameter {
            name = "Activity Name"
            default = "Activity"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }
        val layoutName = stringParameter {
            name = "Activity Layout Name"
            default = "activity_your_name"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val subComponentName = stringParameter {
            name = "Set the SubComponent Name, E.G MyFeatureSubComponent"
            default = "MyFeatureSubComponent"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }
        widgets(
            PackageNameWidget(packageNameParam),
            TextFieldWidget(activityName),
            TextFieldWidget(layoutName),
            TextFieldWidget(subComponentName)
        )

        recipe = { data: TemplateData ->
            standaloneActivityTemplateRecipe(
                data as ModuleTemplateData,
                packageNameParam.value,
                activityName.value,
                layoutName.value,
                subComponentName.value
            )
        }
    }




val test
    get()= template {
            name = "Test Standalone"
            description = "Create a test class with the necessary"
            minApi = 21
            category = Category.Fragment
            formFactor = FormFactor.Mobile
            screens = listOf(WizardUiContext.MenuEntry)

            val packageNameParam = defaultPackageNameParameter
            val className = stringParameter {
                name = "Test class name E.G MyFeatureTest"
                default = "MyFeatureTest"
                help = "Use the class name for prefix"
                constraints = listOf(Constraint.NONEMPTY)
            }
            widgets(
                PackageNameWidget(packageNameParam),
                TextFieldWidget(className),
            )

            recipe = { data: TemplateData ->
                standaloneTest(
                    data as ModuleTemplateData,
                    packageNameParam.value,
                    className.value
                )
            }
    }