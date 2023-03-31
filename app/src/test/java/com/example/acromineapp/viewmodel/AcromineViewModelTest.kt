package com.example.acromineapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.acromineapp.data.repository.AcromineRepository
import com.example.acromineapp.util.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class AcromineViewModelTest {

    @get:Rule val rule = InstantTaskExecutorRule()

    /**
     * Setup
     * 1.Test object
     * 2.Values in constructor
     */

    lateinit var testObject: AcromineViewModel
    val mockRepository = mockk<AcromineRepository>(relaxed = true)
    val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        //Create the instance of the testObject
        testObject = AcromineViewModel(mockRepository,testDispatcher)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        //Clear mocks and dispatchers
        clearAllMocks()
        Dispatchers.resetMain()
    }


    @Test
    fun `get acromine result when the repository retrieves a list of results  returns SUCCESS state`(){
        //AAA

        //Assignment
        every { mockRepository.getAcromineFlow("ASAP") } returns flowOf(
            UIState.SUCCESS(listOf(mockk(),mockk(),mockk()))
        )

        //Action
        testObject.resultSF.observeForever {
            //Assertion
            if (it is UIState.SUCCESS){
                assertEquals(3,it.response.size)
            }
        }
        testObject.flowGetAcromineFlow("ASAP")
    }

    @Test
    fun `get acromine result when the repository retrieves a null list returns SUCCESS state`(){
        //AAA

        //Assignment
        every { mockRepository.getAcromineFlow("ASAP") } returns flowOf(
            UIState.ERROR(Exception("error"))
        )

        //Action
        testObject.resultSF.observeForever {
            //Assertion
            if (it is UIState.ERROR){
                assertEquals("error", it.error.localizedMessage)
            }
        }
        testObject.flowGetAcromineFlow("ASAP")
    }
}
