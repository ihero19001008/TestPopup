package com.example.testpopup.model

import com.google.gson.annotations.SerializedName

data class PhotoRespone(
    @SerializedName("images")
    var getList: List<Photo>
)