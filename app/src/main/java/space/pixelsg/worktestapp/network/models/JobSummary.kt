package space.pixelsg.worktestapp.network.models

import com.google.gson.annotations.SerializedName

data class JobSummary(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    var img: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("www")
    val website: String,
    @SerializedName("phone")
    val phone: String
)
