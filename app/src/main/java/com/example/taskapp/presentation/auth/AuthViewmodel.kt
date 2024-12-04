package com.example.taskapp.presentation.auth
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.data.repositary_impl.AuthRepositoryImpl
import com.example.taskapp.domain.entity.UserEntity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewmodel(
    val authRepository: AuthRepositoryImpl
): ViewModel(
) {
    private val auth:FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    var authState:LiveData<AuthState> = _authState

    init {
        isLoggedIn()
    }
    fun isLoggedIn(){
        if (auth.currentUser==null){
            _authState.value = AuthState.UnAuthenticated
            }
        else{
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email: String, password: String) {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            try {
                val result = authRepository.login(email, password)
                if (result.isSuccess){
                    _authState.value = AuthState.Authenticated
                }
                else{
                    val errorMessage = result.exceptionOrNull()?.message ?: "An unknown error occurred"
                    _authState.value = AuthState.Error(message = errorMessage)
                }
            } catch (exception: Exception) {
                _authState.value = AuthState.Error(message =exception.message ?: "An error occurred")
            }
        }
    }

    fun signUp(userEntity: UserEntity,password: String,cpassword:String) {
        if ( password.isBlank() || password!=cpassword){
            _authState.value = AuthState.Error(message = "conform password have to similar with password")
            return
        }
        if ( userEntity.email.isBlank()){
            _authState.value = AuthState.Error(message = "email must not empty")
            return
        }
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            try {
                val result = authRepository.signUp(userEntity, password)
                if (result.isSuccess){
                    _authState.value = AuthState.Authenticated
                }
                else{
                    val errorMessage = result.exceptionOrNull()?.message ?: "An unknown error occurred"
                    _authState.value = AuthState.Error(message = errorMessage)
                }
            } catch (exception: Exception) {
                _authState.value = AuthState.Error(message =exception.message ?: "An error occurred")
            }
        }
    }

}

sealed class  AuthState(){
    class Error(val message: String):AuthState()
    object Authenticated:AuthState()
    object UnAuthenticated:AuthState()
    object Loading:AuthState()
}