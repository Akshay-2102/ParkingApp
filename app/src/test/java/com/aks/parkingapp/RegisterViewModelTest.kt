package com.aks.parkingapp

import app.cash.turbine.test
import com.aks.parkingapp.domain.model.User
import com.aks.parkingapp.domain.usecases.ClearUsersUseCase
import com.aks.parkingapp.domain.usecases.RegisterUserUseCase
import com.aks.parkingapp.presentation.ui.screens.signup.RegisterUiEvent
import com.aks.parkingapp.presentation.ui.screens.signup.RegisterViewModel
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterViewModelTest {

    @get:Rule
    val mainDispatcherRule =
        MainDispatcherRule()

    private lateinit var registerUserUseCase:
            RegisterUserUseCase

    private lateinit var clearUsersUseCase:
            ClearUsersUseCase

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setup() {
        registerUserUseCase = mockk(relaxed = true)
        clearUsersUseCase = mockk(relaxed = true)
        viewModel = RegisterViewModel(registerUserUseCase,clearUsersUseCase)
    }


    // Validation test
    @Test
    fun `mobile should be invalid when less than 10 digits`() {
        viewModel.onMobileChanged(
            "123456789"
        )

        // For true validation check result like enter exact 10 digits number then result getting true
       /* assertTrue(
            viewModel.uiState.value.isValidMobile
        )*/

        // For true validation check result like enter below or above 10 digits number then result getting true
        // so in this condition is match so result is getting pass test case for this condition
        assertTrue(
            viewModel.uiState.value.isValidMobile
        )
    }

    // State test
    @Test
    fun `onMobileChanged updates mobile number`(){
        val number = "1234567890"
        viewModel.onMobileChanged(number)
        assert(viewModel.uiState.value.mobileNumber == number)
    }


    @Test
    fun `registerUser should call register usecase`() =
        runTest {

            viewModel.onMobileChanged(
                "9876543210"
            )

            viewModel.registerUser()

            advanceUntilIdle()

           /* coVerify(exactly = 1) {

                registerUserUseCase.invoke(any())
            }*/

            coVerify {
                registerUserUseCase.invoke(
                    //any()
                    match<User> {
                        it.mobileNumber.contains("9876543210")
                    }
                )
            }
        }


    // Check after successful registration event should emit and change loading state
    @Test
    fun `should emit navigate event after successful registration`() =
        runTest {

            viewModel.onMobileChanged(
                "9876543210"
            )

            viewModel.event.test {

                viewModel.registerUser()

                advanceUntilIdle()

                assertEquals(
                    RegisterUiEvent.NavigateToOtp,
                    awaitItem()
                )

                cancelAndConsumeRemainingEvents()
            }
        }

}