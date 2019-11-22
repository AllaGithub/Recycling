package ua.com.eco_lab.recycling.mapper

import ua.com.eco_lab.recycling.data.entity.EquipmentEntity
import ua.com.eco_lab.recycling.model.Equipment

object EquipmentMapper {

    fun parse(equipment: Equipment): EquipmentEntity {
        return EquipmentEntity(equipment.inventoryId, equipment.innerId, equipment.name, equipment.weight, equipment.comments, equipment.receiptId)
    }

    fun parse(entity: EquipmentEntity): Equipment {
        val eq = Equipment()
        eq.id = entity.id
        eq.comments = entity.comments
        eq.inventoryId = entity.inventoryId
        eq.innerId = entity.innerId
        eq.name = entity.name
        eq.weight = entity.weight
        eq.receiptId = entity.receiptId
        return eq
    }
}