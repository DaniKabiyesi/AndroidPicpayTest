package com.example.androidpicpaytest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidpicpaytest.domain.UserEntity

@Database(entities = [UserEntity::class], version=1, exportSchema=false)
abstract class ContactsDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object{
        private var DB_INSTANCE: ContactsDataBase? = null

        fun getDataBase(context: Context) : ContactsDataBase {
            val tempInstance = DB_INSTANCE
            if(tempInstance != null) return tempInstance

            synchronized(this){
                val instance = Room
                    .databaseBuilder(context.applicationContext,
                        ContactsDataBase::class.java,
                        "database_name")
                    .build()
                DB_INSTANCE = instance
                return instance
            }
        }
    }
}
