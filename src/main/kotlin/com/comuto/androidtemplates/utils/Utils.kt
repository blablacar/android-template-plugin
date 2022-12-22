package com.comuto.androidtemplates.utils

import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import org.jetbrains.kotlin.idea.KotlinLanguage

fun String.asKt() = "${this.capitalize()}.kt"

fun String.asXml() = "${this}.xml"

val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()
fun String.toSnakeCase() = camelRegex.replace(this) { "_${it.value}" }.lowercase()

fun save(srcDir: PsiDirectory, subDirPath: String, fileName: String, context :String) {
    try {
        val destDir = subDirPath.split('.').toDir(srcDir)
        val psiFile = PsiFileFactory
            .getInstance(srcDir.project)
            .createFileFromText(fileName, KotlinLanguage.INSTANCE, context)
        destDir.add(psiFile)
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
}
fun String.saveXML(srcDir: PsiDirectory, fileName: String) {
    val fullSubDir = "/res.layout"
    save(srcDir,fullSubDir,fileName,this)
}

fun String.saveClass(srcDir: PsiDirectory, subDirPath: String, fileName: String) {
    val fullSubDir = "/java.$subDirPath"
    save(srcDir,fullSubDir,fileName,this)
}

fun String.saveTestClass(srcDir: PsiDirectory,subDirPath: String,fileName: String){
    val fullSubDir="/kotlin.$subDirPath"
    save(srcDir,fullSubDir,fileName,this)
}


fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
    var result = srcDir
    forEach {
        result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
    }
    return result
}