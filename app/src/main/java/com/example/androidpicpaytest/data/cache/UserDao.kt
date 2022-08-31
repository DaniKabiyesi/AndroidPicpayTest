package com.example.androidpicpaytest.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpicpaytest.domain.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun setContactsListDataBase(userEntityList: List<UserEntity>)

    @Query("SELECT * FROM user")
    fun getContactsListDataBase(): Flow<List<UserEntity>>
}