package mohamed.atef.mondiatask.models

data class ClientTokenModel(val expiresIn: String? = "",
                            val accessToken: String = "",
                            val tokenType: String = "")