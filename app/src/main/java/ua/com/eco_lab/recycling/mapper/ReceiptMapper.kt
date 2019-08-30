package ua.com.eco_lab.recycling.mapper

import ua.com.eco_lab.recycling.data.entity.ReceiptEntity
import ua.com.eco_lab.recycling.model.Receipt

object ReceiptMapper {

    fun parse(receipt: Receipt): ReceiptEntity {
        return ReceiptEntity(receipt.id, receipt.donorName, receipt.dateReceived)
    }

    fun parse(entity: ReceiptEntity): Receipt {
        val receipt = Receipt()
        receipt.id = entity.id
        receipt.donorName = entity.donorName
        receipt.dateReceived = entity.dateReceived
        return receipt
    }
}