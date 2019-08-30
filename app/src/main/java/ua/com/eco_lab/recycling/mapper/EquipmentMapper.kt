package ua.com.eco_lab.recycling.mapper

import ua.com.eco_lab.recycling.data.entity.EquipmentEntity
import ua.com.eco_lab.recycling.model.Equipment

object EquipmentMapper {

    fun parse(equipment: Equipment): EquipmentEntity {
        return EquipmentEntity(equipment.id, equipment.innerId, equipment.name, equipment.weight, equipment.comments, equipment.receiptId)
    }

    fun parse(entity: EquipmentEntity): Equipment {
        return Equipment(entity.id, entity.innerId, entity.name, entity.weight, entity.comments, entity.receiptId)
    }
}