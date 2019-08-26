package ua.com.eco_lab.recycling.model


class Equipment (
    var id: String,
    var innerId: String,
    var name: String,
    var weight: Double,
    var missingComponents: MutableList<Component>,
    var existingComponents: MutableList<Component>,
    var comments: String,
    var clientId: Long,
    var receiptId: Long
)