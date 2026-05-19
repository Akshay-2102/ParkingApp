package com.aks.parkingapp.utils

fun String.maskMobileNumber(): String {

    return if (length >= 10) {

        replaceRange(
            3,
            11,
            "*******"
        )

    } else {
        this
    }
}
