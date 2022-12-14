package com.comuto.androidtemplates.manager

fun addPackageName(packageName: String, applicationPackageName: String) =
    PackageManager.setPackageName(packageName, applicationPackageName)

object PackageManager {

    private var _packageName: String = ""
    val packageName by lazy { _packageName }

    private var _applicationPackageName: String = ""
    val applicationPackageName by lazy { _applicationPackageName }

    fun setPackageName(packageName: String, applicationPackageName: String) {
        this._packageName = packageName
        this._applicationPackageName = applicationPackageName
    }
}