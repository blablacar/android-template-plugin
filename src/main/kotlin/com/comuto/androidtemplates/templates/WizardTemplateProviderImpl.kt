package com.comuto.androidtemplates.templates

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.comuto.androidtemplates.templates.standalone.fragment
import com.comuto.androidtemplates.templates.standalone.viewModel

class WizardTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        fragment,
        viewModel)
}