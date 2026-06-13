package com.aks.parkingapp

import app.cash.turbine.test
import com.aks.parkingapp.data.mapper.toRegisterResult
import com.aks.parkingapp.data.remote.registerDTO.RegisterResponseDTO
import com.aks.parkingapp.domain.model.register.RegisterRequestModel
import com.aks.parkingapp.domain.model.register.RegisterResultModel
import com.aks.parkingapp.domain.repository.RegistrationRepository
import com.aks.parkingapp.domain.usecases.RegisterUserUseCase
import com.aks.parkingapp.presentation.ui.screens.register.RegisterUiEvent
import com.aks.parkingapp.presentation.ui.screens.register.RegisterViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    private val repository =
        mockk<RegistrationRepository>()

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setup() {
        registerUserUseCase =
            RegisterUserUseCase(repository)
        viewModel = RegisterViewModel(registerUserUseCase)
    }

    // Validation test

    @Test
    fun `full name should be invalid when less than 6 latter`(){
        viewModel.onFullNameChange(
            "Aksha"
        )

        assertFalse(
            viewModel.uiState.value.isValidFullName
        )
    }

    @Test
    fun `onFullNameChanged update full name`(){
        val fullName = "Akshay Chikhalekar"
        viewModel.onFullNameChange(fullName)
        assert(viewModel.uiState.value.fullName == fullName)
    }

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
        assertFalse(
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

    // Use case test


    @Test
    fun `invoke should call repository`() = runTest {

        val request = RegisterRequestModel(
            name = "Akshay",
            email = "akshay@gmail.com",
            mobileNo = "1234567890",
            password = "123"
        )

        val result = RegisterResultModel(
            id = 1,
            success = true,
            responseCode = "00",
            responseMessage = "Success"
        )

        coEvery {
            repository.registerUser(any())
        } returns Result.success(result)

        val response = registerUserUseCase(request)

        assertEquals(
            result,
            response.getOrNull()
        )

        coVerify(exactly = 1) {
            repository.registerUser(request)
        }
    }

    // Mapper Test
    @Test
    fun `toRegisterResult should map dto correctly`() {

        val dto = RegisterResponseDTO(
            id = 1,
            success = true,
            responseCode = "00",
            responseMessage = "Success"
        )

        val result = dto.toRegisterResult()

        assertEquals(1, result.id)
        assertEquals("Success", result.responseMessage)
    }

    // Network Error
    @Test
    fun `should emit error event when network error occurs`() =
        runTest {

            coEvery {
                registerUserUseCase(any())
            } returns Result.failure(
                Exception("Network Error")
            )

            viewModel.onFullNameChange("Akshay")
            viewModel.onEmailChange("akshay@gmail.com")
            viewModel.onMobileChanged("1234567890")
            viewModel.onPasswordChange("Akshay@21")

            viewModel.event.test {

                viewModel.registerUser()

                assertEquals(
                    RegisterUiEvent.ShowError(
                        "Network Error"
                    ),
                    awaitItem()
                )
            }
        }

    // Socket Timeout exception
    @Test
    fun `should emit error event when request timeout occurs`() =
        runTest {

            coEvery {
                registerUserUseCase(any())
            } returns Result.failure(
                java.net.SocketTimeoutException(
                    "Request timeout"
                )
            )

            viewModel.event.test {

                viewModel.registerUser()

                assertEquals(
                    RegisterUiEvent.ShowError(
                        "Request timeout"
                    ),
                    awaitItem()
                )
            }
        }

    // 404 Not Found Error
    @Test
    fun `should emit error event when server returns 404`() =
        runTest {

            coEvery {
                registerUserUseCase(any())
            } returns Result.failure(
                Exception("404 Not Found")
            )

            viewModel.event.test {

                viewModel.registerUser()

                assertEquals(
                    RegisterUiEvent.ShowError(
                        "404 Not Found"
                    ),
                    awaitItem()
                )
            }
        }

    // HTTP 500 Error
    @Test
    fun `should emit error event when server returns 500`() =
        runTest {

            coEvery {
                registerUserUseCase(any())
            } returns Result.failure(
                Exception("500 Internal Server Error")
            )

            viewModel.event.test {

                viewModel.registerUser()

                assertEquals(
                    RegisterUiEvent.ShowError(
                        "500 Internal Server Error"
                    ),
                    awaitItem()
                )
            }
        }

    // Check after successful registration event should emit and change loading state
    @Test
    fun `should emit navigate event after successful registration`() =
        runTest {

            val result = RegisterResultModel(
                id = 1,
                success = true,
                responseCode = "00",
                responseMessage = "Success"
            )

            coEvery {
                registerUserUseCase(any())
            } returns Result.success(result)

            viewModel.onFullNameChange("Akshay")
            viewModel.onEmailChange("akshay@gmail.com")
            viewModel.onMobileChanged("1234567890")
            viewModel.onPasswordChange("Akshay@21")

            viewModel.event.test {

                viewModel.registerUser()

                assertEquals(
                    RegisterUiEvent.ShowSuccess("Success"),
                    awaitItem()
                )

                assertEquals(
                    RegisterUiEvent.NavigateToLogin,
                    awaitItem()
                )
            }
        }

    // Check after failed registration event  should emit and change loading state
    @Test
    fun `should emit navigate event after failed registration`() =
        runTest {

            val result = RegisterResultModel(
                id = 0,
                success = false,
                responseCode = "01",
                responseMessage = "Failed"
            )

            coEvery {
                registerUserUseCase(any())
            } returns Result.success(result)

            viewModel.onFullNameChange("Akshay")
            viewModel.onEmailChange("akshay@gmail.com")
            viewModel.onMobileChanged("1234567890")
            viewModel.onPasswordChange("Akshay@21")

            viewModel.event.test {

                viewModel.registerUser()

                assertEquals(
                    RegisterUiEvent.ShowError("Failed"),
                    awaitItem()
                )

            }
        }


}