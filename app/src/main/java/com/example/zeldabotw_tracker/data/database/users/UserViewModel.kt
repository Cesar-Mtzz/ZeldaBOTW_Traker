package com.example.zeldabotw_tracker.data.database.users

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.zeldabotw_tracker.data.database.AppDatabase
import com.example.zeldabotw_tracker.data.database.progress.ProgressRepository
import kotlinx.coroutines.launch

class UserViewModel (
    application: Application
) : AndroidViewModel(application) {
    private val dao = AppDatabase
        .getInstance(application)
        .userDAO()

    private val repository = UserRepository(dao)
    private val progressDao = AppDatabase.getInstance(getApplication()).progressDAO()
    private val progressRepository = ProgressRepository(progressDao)

    fun save(user: User, onSave: () -> Unit){
        viewModelScope.launch {
            val generatedId = repository.addUser(user).toInt()
            progressRepository.newProgress(generatedId)
            onSave()
        }
    }

    var errorMessage by mutableStateOf("")
        private set

    fun login(user: String, pass: String, onSuccess: () -> Unit){
        viewModelScope.launch {
            errorMessage = ""

            val loginValid = repository.validateLogin(user, pass)

            if (loginValid){
                currentUserId = repository.getUserId(user)
                onSuccess()
            } else {
                errorMessage = "Usuario o contraseña incorrectos"
            }
        }
    }

    var currentUserId by mutableStateOf<Int?>(null)
        private set

    fun loadUserId(userName: String) {
        viewModelScope.launch {
            currentUserId = repository.getUserId(userName)
        }
    }
}
