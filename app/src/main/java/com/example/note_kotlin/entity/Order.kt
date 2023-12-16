package com.example.note_kotlin.entity

import org.xutils.db.annotation.Column
import org.xutils.db.annotation.Table
import java.io.Serializable

@Table(name = "Order")
class Order : Serializable {

    @Column(name = "id", autoGen = true, isId = true)
    var id: String? = null

    @Column(name = "title")
    var title: String? = null

    @Column(name = "time")
    var time: String? = null

    @Column(name = "time_p")
    var time_p: String? = null

    @Column(name = "picUrl")
    var picUrl: String? = null

    @Column(name = "des")
    var des: String? = null

    @Column(name = "detail")
    var detail: String? = null

    @Column(name = "money")
    var money: String? = null

    @Column(name = "address")
    var address: String? = null

    @Column(name = "price")
    var price: String? = null

    @Column(name = "tebie")
    var tebie: String? = null

    @Column(name = "num")
    var num = 0
}