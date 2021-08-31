package mohamed.atef.mondiatask.utils

class AppUtils {
    companion object{
        val JsonResponse="[{\n" +
                "    \"id\": 73676458,\n" +
                "    \"type\": \"release\",\n" +
                "    \"title\": \"Magmamemoria MMXX (Deluxe Edition)\",\n" +
                "    \"publishingDate\": \"2020-02-06T00:00:00Z\",\n" +
                "    \"duration\": 7158,\n" +
                "    \"label\": \"Warner Music Group\",\n" +
                "    \"mainArtist\": {\n" +
                "      \"id\": 2691341,\n" +
                "      \"name\": \"Levante\"\n" +
                "    },\n" +
                "    \"streamableTracks\": 31,\n" +
                "    \"numberOfTracks\": 31,\n" +
                "    \"additionalArtists\": [],\n" +
                "    \"genres\": [\n" +
                "      \"POP\"\n" +
                "    ],\n" +
                "    \"idBag\": {\n" +
                "      \"ean\": \"0190295275242\",\n" +
                "      \"upc\": \"190295275242\"\n" +
                "    },\n" +
                "    \"licensorName\": \"Warner Music\",\n" +
                "    \"statistics\": {\n" +
                "      \"popularity\": \"1.0000\",\n" +
                "      \"estimatedRecentCount\": 1505,\n" +
                "      \"estimatedTotalCount\": 330434\n" +
                "    },\n" +
                "    \"streamable\": true,\n" +
                "    \"partialStreamable\": true,\n" +
                "    \"adfunded\": false,\n" +
                "    \"bundleOnly\": false,\n" +
                "    \"cover\": {\n" +
                "      \"tiny\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/57x57/73676458.jpg\",\n" +
                "      \"small\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/small/73676458\",\n" +
                "      \"medium\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/300x300/73676458.jpg\",\n" +
                "      \"large\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/big/73676458\",\n" +
                "      \"default\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/300x300/73676458.jpg\",\n" +
                "      \"template\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/{width}x{height}/73676458.{suffix}\"\n" +
                "    },\n" +
                "    \"variousArtists\": false\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 6168658,\n" +
                "    \"type\": \"song\",\n" +
                "    \"title\": \"Mmm Mmm Mmm Mmm\",\n" +
                "    \"publishingDate\": \"1993-04-05T00:00:00Z\",\n" +
                "    \"duration\": 233,\n" +
                "    \"mainArtist\": {\n" +
                "      \"id\": 10742,\n" +
                "      \"name\": \"Crash Test Dummies\"\n" +
                "    },\n" +
                "    \"statistics\": {\n" +
                "      \"popularity\": \"0.8821\",\n" +
                "      \"estimatedRecentCount\": 89,\n" +
                "      \"estimatedTotalCount\": 8184\n" +
                "    },\n" +
                "    \"release\": {\n" +
                "      \"id\": 6176772,\n" +
                "      \"title\": \"God Shuffled His Feet\"\n" +
                "    },\n" +
                "    \"volumeNumber\": 1,\n" +
                "    \"trackNumber\": 3,\n" +
                "    \"licensorName\": \"Sony Music\",\n" +
                "    \"genres\": [\n" +
                "      \"POP\"\n" +
                "    ],\n" +
                "    \"additionalArtists\": [],\n" +
                "    \"idBag\": {\n" +
                "      \"isrc\": \"CAA359300084\",\n" +
                "      \"grid\": \"A10328E00017392537\",\n" +
                "      \"roviId\": \"MT0007190736\",\n" +
                "      \"roviTrackId\": \"MT0007190736\"\n" +
                "    },\n" +
                "    \"adfunded\": false,\n" +
                "    \"streamable\": true,\n" +
                "    \"bundleOnly\": false,\n" +
                "    \"cover\": {\n" +
                "      \"tiny\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/57x57/6176772.jpg\",\n" +
                "      \"small\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/150x150/6176772.jpg\",\n" +
                "      \"medium\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/300x300/6176772.jpg\",\n" +
                "      \"large\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/600x600/6176772.jpg\",\n" +
                "      \"default\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/300x300/6176772.jpg\",\n" +
                "      \"template\": \"//staging-placebo.mondiamedia.com/api/fetch/image/article/{width}x{height}/6176772.{suffix}\"\n" +
                "    }\n" +
                "  }]"
        val baseUrl="http://staging-gateway.mondiamedia.com"
        var v2="/v2/api"
        var v1="/v0/api"
        var  searchEnd= baseUrl + v2 +"/sayt/flat"
        val clientTokenEnd= baseUrl + v1 +"/gateway/token/client"
        var includes=true;
        var limit=20
    }
}