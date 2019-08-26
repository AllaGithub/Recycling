package ua.com.eco_lab.recycling.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ua.com.eco_lab.recycling.data.entity.EquipmentEntity

@Dao
interface EquipmentDao {

    @Query("SELECT * FROM equipment_table WHERE receipt_id = :receiptId")
    fun getAllEquipmentByReceiptId(receiptId: String): List<EquipmentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(equipment: EquipmentEntity)
}