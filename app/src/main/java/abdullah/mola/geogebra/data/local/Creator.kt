package abdullah.mola.geogebra.data.local


import com.google.gson.annotations.SerializedName


data class Creator(
  @SerializedName("id")
  val creator_id: Int,
  @SerializedName("name")
  val creator_name: String,
  @SerializedName("profile")
  val creator_profile: String,
)