package com.tinhtx.customapplication.model

import android.text.InputType

data class DialogDto(
    var title: String? = null,
    var description: String? = null,
    var textHint: String? = null,
    var titleOk: String = "OK",
    var titleCancel: String = "Cancel",
    var textType: Int = InputType.TYPE_CLASS_TEXT,
    var errorText: String = "An error occurred!",
    var onClickOk: ((String) -> Unit)? = null,
    var onClickCancel: ((String) -> Unit)? = null
)