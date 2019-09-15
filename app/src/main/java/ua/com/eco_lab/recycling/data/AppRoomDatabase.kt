package ua.com.eco_lab.recycling.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ua.com.eco_lab.recycling.data.AppRoomDatabase.Companion.DATABASE_VERSION
import ua.com.eco_lab.recycling.data.dao.EquipmentDao
import ua.com.eco_lab.recycling.data.dao.ReceiptDao
import ua.com.eco_lab.recycling.data.entity.EquipmentEntity
import ua.com.eco_lab.recycling.data.entity.ReceiptEntity

@Database(entities = [ReceiptEntity::class, EquipmentEntity::class], version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppRoomDatabase : RoomDatabase() {


    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        const val DATABASE_VERSION = 1

        private const val DATABASE_NAME = "APP_DATA"

        fun getDatabase(context: Context): AppRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


    abstract fun receiptDao(): ReceiptDao

    abstract fun equipmentDao(): EquipmentDao


}