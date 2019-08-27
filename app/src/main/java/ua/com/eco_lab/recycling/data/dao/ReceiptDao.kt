package ua.com.eco_lab.recycling.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ua.com.eco_lab.recycling.data.entity.ReceiptEntity

@Dao
interface ReceiptDao {

    @Query("SELECT * from receipt_table ORDER BY date_received ASC")
    fun getAllReceipts(): List<ReceiptEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(receipt: ReceiptEntity): Long
}