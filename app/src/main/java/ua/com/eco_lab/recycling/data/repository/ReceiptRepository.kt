package ua.com.eco_lab.recycling.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import ua.com.eco_lab.recycling.data.dao.EquipmentDao
import ua.com.eco_lab.recycling.data.dao.ReceiptDao
import ua.com.eco_lab.recycling.data.entity.EquipmentEntity
import ua.com.eco_lab.recycling.data.entity.ReceiptEntity

class ReceiptRepository(val receiptDao: ReceiptDao, val equipmentDao: EquipmentDao) {


    fun getAllReceipts(): Single<List<ReceiptEntity>> {
        return receiptDao.getAllReceipts()
    }

    fun getAllEquipmentByReceiptId(receiptId: Long): Single<List<EquipmentEntity>> {
        return equipmentDao.getAllEquipmentByReceiptId(receiptId)
    }

    fun getAllReceipt(): Single<List<ReceiptEntity>> {
        return receiptDao.getAllReceipts()
    }

    fun insertReceipt(receipt: ReceiptEntity): Single<Long> {
        return receiptDao.insert(receipt)
    }

    fun insertEquipment(equipment: EquipmentEntity): Completable {
        return equipmentDao.insertEquipment(equipment)
    }
}