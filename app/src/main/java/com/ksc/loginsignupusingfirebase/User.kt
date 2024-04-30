package com.ksc.loginsignupusingfirebase


data class User(
    var name: String? = "",
    var email: String? = "",
    var number: String? = "",
    var userName : String? = ""
) {
    constructor() : this("", "", "","")
}

