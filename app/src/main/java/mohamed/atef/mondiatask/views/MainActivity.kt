package mohamed.atef.mondiatask.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mohamed.atef.mondiatask.R
import mohamed.atef.mondiatask.parser.SearchParser
import mohamed.atef.mondiatask.viewModels.SearchViewModel

class MainActivity : AppCompatActivity() {
    lateinit var searchParser: SearchParser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchRequest()
    }

    private fun searchRequest() {
        val searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        Log.i("GameFragment", "Called ViewModelProvider.get")
        searchViewModel.searchApiCall("")
        searchViewModel.getQueryResponse().observe(this, Observer { queryResponse ->
            searchParser.parse(queryResponse)
        })
    }
}