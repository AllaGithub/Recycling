package ua.com.eco_lab.recycling.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import ua.com.eco_lab.recycling.data.entity.EquipmentEntity

@Dao
interface EquipmentDao {

    @Query("SELECT * FROM equipment_table WHERE receipt_id = :receiptId")
    fun getAllEquipmentByReceiptId(receiptId: String): Single<List<EquipmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(equipment: EquipmentEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEquipment(equipment: EquipmentEntity) : Completable
}