package ua.com.eco_lab.recycling.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "receipt_table")
data class ReceiptEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: String?,

    @ColumnInfo(name = "donor_name")
    val donorName: String?,

    @ColumnInfo(name = "date_received")
    val dateReceived: Date?
)