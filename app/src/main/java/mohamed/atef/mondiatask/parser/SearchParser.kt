package mohamed.atef.mondiatask.parser

import mohamed.atef.mondiatask.models.ClientTokenModel
import mohamed.atef.mondiatask.models.SearchResults
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class SearchParser() {
    private lateinit var TokenType: String
    private lateinit var AccessToken: String
    private lateinit var ImageUrl_STR: String
    private lateinit var MainArtist_STR: String
    private lateinit var SONG_TITLE_STR: String
    private lateinit var ITEM_TYPE_STR: String

    fun parseQueryResults(jsonResponse: String): ArrayList<SearchResults>?{
        ImageUrl_STR=""
        MainArtist_STR=""
        SONG_TITLE_STR=""
        ITEM_TYPE_STR=""
        val mainArray= JSONArray(jsonResponse)
        val dataArrayList=ArrayList<SearchResults>()
        for (i in 0 until mainArray.length()) {
            val oneDataObject = mainArray.getJSONObject(i)
            if (oneDataObject.has("type")){
                ITEM_TYPE_STR = oneDataObject.getString("type")
            }
            if (oneDataObject.has("name")){
                SONG_TITLE_STR = oneDataObject.getString("name")
            }
            if (oneDataObject.has("cover")){
                val coverJsonObject = oneDataObject.getJSONObject("cover")
                if (coverJsonObject.has("default")){
                    ImageUrl_STR = coverJsonObject.getString("default")
                }
            }
            if (oneDataObject.has("mainArtist")){
                val mainArtistJsonObject=oneDataObject.getJSONObject("mainArtist")
                if (mainArtistJsonObject.has("name")){
                    MainArtist_STR= mainArtistJsonObject.getString("name")
                }
            }
            if (ITEM_TYPE_STR==null){
                ITEM_TYPE_STR=""
            }
            if (SONG_TITLE_STR==null){
                SONG_TITLE_STR=""
            }
            if (ImageUrl_STR==null){
                ImageUrl_STR=""
            }
            if (MainArtist_STR==null){
                MainArtist_STR=""
            }
            val searchResults = SearchResults(ImageUrl_STR, SONG_TITLE_STR,ITEM_TYPE_STR,MainArtist_STR)
            dataArrayList.add(searchResults)
        }
        return dataArrayList
    }

    fun parseClientToken(jsonResponse: String): ClientTokenModel?{
        try {
            val jsonObject = JSONObject(jsonResponse)
            if (jsonObject!!.has("accessToken")){
                AccessToken=jsonObject.getString("accessToken")
            }
            if (jsonObject!!.has("tokenType")){
                TokenType=jsonObject.getString("tokenType")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return ClientTokenModel(null,AccessToken,TokenType)
    }
}