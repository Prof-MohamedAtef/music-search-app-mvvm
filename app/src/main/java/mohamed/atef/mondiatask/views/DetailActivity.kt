package mohamed.atef.mondiatask.views

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import mohamed.atef.mondiatask.R
import mohamed.atef.mondiatask.WorkerThread.LoadImageAsyncTask
import mohamed.atef.mondiatask.models.SearchResults

class DetailActivity : AppCompatActivity() {
    lateinit var posterImage:ImageView
    lateinit var tvSongName:TextView
    lateinit var tvArtistName:TextView
    lateinit var tvItemType:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val searchResults= intent.getParcelableExtra<SearchResults>("DetailExtra")
        posterImage=findViewById(R.id.poster_image)
        tvSongName=findViewById(R.id.tvSongTitle)
        tvSongName.setText(searchResults?.Title)
        tvArtistName=findViewById(R.id.tvMainArtistVal)
        tvArtistName.setText(searchResults?.ArtistName)
        tvItemType=findViewById(R.id.tvItemTypeVal)
        tvItemType.setText(searchResults?.Type)
        val loadImageAsyncTask = LoadImageAsyncTask("http:" + searchResults!!.Image, posterImage)
        loadImageAsyncTask.execute()
    }
}