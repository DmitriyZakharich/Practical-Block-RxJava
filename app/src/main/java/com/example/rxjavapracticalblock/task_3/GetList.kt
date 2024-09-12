package com.example.rxjavapracticalblock.task_3

fun getList(): List<String> {
    var i = 0
    return List(20){ "Item ${i++}" }
}
