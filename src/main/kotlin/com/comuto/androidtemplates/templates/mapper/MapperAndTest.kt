package com.comuto.androidtemplates.templates.mapper

import com.comuto.androidtemplates.manager.PackageManager

fun createMapper(
    packageName: String = PackageManager.packageName,
    mapperName: String,
    fromType: String,
    toType: String
) ="""
package $packageName

import com.comuto.data.Mapper
import javax.inject.Inject

class $mapperName @Inject constructor() : Mapper<$fromType, $toType> {
    override fun map(from: $fromType): $toType =
        with(from) {
            $toType(
                TODO()
            )
        }
}
""".trimIndent()

fun createMapperTest(
    packageName: String = PackageManager.packageName,
    mapperName: String
) = """
package $packageName

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ${mapperName}Test {

    private lateinit var mapper: $mapperName

    @Before
    fun init() {
        mapper = $mapperName()
    }

    @Test
    fun TODO() {
        // Given
        TODO()
        
        // When
        val result = mapper.map(TODO())
        
        // Then
        TODO()
    }

    companion object {
           TODO() // create your object in here 
    }
}
""".trimIndent()