package com.example.nbc__neomarket.data

class UserDataSource {
    companion object {
        private var INSTANCE: UserDataSource? = null

        fun getUserDataSource() : UserDataSource {
            return synchronized(UserDataSource::class) {
                val newInstance = INSTANCE ?: UserDataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }

    fun getUserList() : List<User> {
        return userList()
    }
}