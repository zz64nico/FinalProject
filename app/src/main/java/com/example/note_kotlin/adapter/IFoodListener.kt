package com.example.note_kotlin.adapter

import com.example.note_kotlin.entity.Food

interface IFoodListener {
    fun add(food: Food)
    fun jian(food: Food)
}