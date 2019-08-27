package ua.com.eco_lab.recycling.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "equipment_table")
data class EquipmentEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: String?,

    @ColumnInfo(name = "inner_id")
    val innerId: String?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "weight")
    val weight: Double?,

    @ColumnInfo(name = "comments")
    val comments: String?,

    @ColumnInfo(name = "receipt_id")
    val receiptId: String?
)