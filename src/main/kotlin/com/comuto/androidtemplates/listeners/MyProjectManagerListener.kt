package com.comuto.androidtemplates.listeners

import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener

internal class MyProjectManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
        projectInstance = project
    }

    override fun projectClosing(project: Project) {
        projectInstance = null
    }

    companion object {
        var projectInstance: Project? = null
    }
}
