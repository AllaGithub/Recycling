package ua.com.eco_lab.recycling.data.repository

import ua.com.eco_lab.recycling.data.dao.EquipmentDao
import ua.com.eco_lab.recycling.data.dao.ReceiptDao
import ua.com.eco_lab.recycling.data.entity.EquipmentEntity
import ua.com.eco_lab.recycling.data.entity.ReceiptEntity

class ReceiptRepository(val receiptDao: ReceiptDao, val equipmentDao: EquipmentDao) {

    val allReceipts: List<ReceiptEntity> = receiptDao.getAllReceipts()


    fun getAllEquipmentByReceiptId(receiptId: String): List<EquipmentEntity> {
        return equipmentDao.getAllEquipmentByReceiptId(receiptId)
    }

    fun getAllReceipt(): List<ReceiptEntity> {
        return receiptDao.getAllReceipts()
    }

    fun insertReceipt(receipt: ReceiptEntity): Long {
        return receiptDao.insert(receipt)
    }

    fun insertEquipment(equipmentList: List<EquipmentEntity>) {
        return equipmentDao.insertAllEquipment(equipmentList)
    }
}