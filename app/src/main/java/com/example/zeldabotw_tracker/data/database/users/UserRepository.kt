package com.example.zeldabotw_tracker.data.database.users

class UserRepository (
    private val userDAO: UserDAO
) {
    suspend fun addUser(user: User): Long {
        return userDAO.addUser(user)
    }

    suspend fun getUserId(username: String): Int? {
        return userDAO.getUserId(username)
    }

    suspend fun validateLogin(inUserName: String, inPass: String): Boolean {
        val dbPass: String? = userDAO.login(inUserName.trim())
        return dbPass != null && dbPass == inPass.trim()
    }
}