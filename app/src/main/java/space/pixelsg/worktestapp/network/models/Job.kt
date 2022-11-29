package space.pixelsg.worktestapp.network.models

import com.google.gson.annotations.SerializedName

data class Job(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    var img: String
)
