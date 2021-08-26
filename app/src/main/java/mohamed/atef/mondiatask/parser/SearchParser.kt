package mohamed.atef.mondiatask.parser

import mohamed.atef.mondiatask.models.SearchResults
import org.json.JSONArray

class SearchParser() {
    fun parse(jsonResponse: String): ArrayList<SearchResults>?{
        val mainArray= JSONArray(jsonResponse)
        val dataArrayList=ArrayList<SearchResults>()
        for (i in 0 until mainArray.length()) {
            val oneDataObject = mainArray.getJSONObject(i)
            val ITEM_TYPE_STR = oneDataObject.getString("type")
            val SONG_TITLE_STR = oneDataObject.getString("name")
            val coverJsonObject = oneDataObject.getJSONObject("cover")
            val ImageUrl_STR = coverJsonObject.getString("default")
            val mainArtistJsonObject=oneDataObject.getJSONObject("mainArtist")
            val MainArtist_STR= mainArtistJsonObject.getString("name")

            val searchResults = SearchResults(ImageUrl_STR, SONG_TITLE_STR,ITEM_TYPE_STR,MainArtist_STR)
            dataArrayList.add(searchResults)
        }
        return dataArrayList
    }
}