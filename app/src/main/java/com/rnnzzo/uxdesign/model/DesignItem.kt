package com.rnnzzo.uxdesign.model

class DesignItem (
    val id: Int = -1,
    val iconResource: Int = -1,
    val title: String = "",
    val action: Int = -1,
    val classType: Class<*>? = null
)