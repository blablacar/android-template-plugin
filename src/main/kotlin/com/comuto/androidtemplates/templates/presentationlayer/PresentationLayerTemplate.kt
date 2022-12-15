package com.comuto.androidtemplates.templates.presentationlayer

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

val fragmentPresentationLayerTemplate
    get() = template {
        name = "Fragment Presentation Layer"
        description = "Create files for the presentation layer"
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
        val viewModelNameParam = stringParameter {
            name = "ViewModel Name"
            default = "ViewModel"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }
        val subComponentName = stringParameter {
            name = "SubComponent Name"
            default = "SubComponent"
            help = "Use the class name for prefix"
            constraints = listOf(Constraint.NONEMPTY)
        }
        widgets(
            PackageNameWidget(packageNameParam),
            TextFieldWidget(classNameParam),
            TextFieldWidget(layoutName),
            TextFieldWidget(viewModelNameParam),
            TextFieldWidget(subComponentName)
        )

        recipe = { data: TemplateData ->
            fragmentPresentationLayerTemplate(
                moduleData = data as ModuleTemplateData,
                packageName =  packageNameParam.value,
                className = classNameParam.value,
                layoutName = layoutName.value,
                viewModelName = viewModelNameParam.value,
                subComponentName = subComponentName.value
            )
        }
    }