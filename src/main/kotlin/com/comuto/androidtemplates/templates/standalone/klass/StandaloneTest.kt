package com.comuto.androidtemplates.templates.standalone.klass

import android.databinding.tool.ext.toCamelCase
import com.comuto.androidtemplates.manager.PackageManager

fun createStandaloneTest(
    packageName: String = PackageManager.packageName,
    testClassName: String
) = """
package $packageName

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class $testClassName {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun init() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    
    @After
    fun clean() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

   

    companion object {
    }
}
""".trimIndent()