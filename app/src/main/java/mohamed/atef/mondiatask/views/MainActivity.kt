package mohamed.atef.mondiatask.views

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mohamed.atef.mondiatask.utils.AppUtils
import mohamed.atef.mondiatask.R
import mohamed.atef.mondiatask.models.ClientTokenModel
import mohamed.atef.mondiatask.parser.SearchParser
import mohamed.atef.mondiatask.viewModels.SearchViewModel
import mohamed.atef.mondiatask.viewModels.SearchViewModelFactory


class MainActivity : AppCompatActivity()  {
    private var clientObject: ClientTokenModel?=null
    private lateinit var adapter: SearchMusicAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    lateinit var searchParser: SearchParser
    lateinit var viewModel: SearchViewModel
    lateinit var viewModelFactory: SearchViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerView)
        searchParser=SearchParser()
        initViewModel()
        initViews()
        getClientToken()
    }

    private fun getClientToken() {
        Log.i("MainActivity", "Called ViewModel")
        viewModel.callClientToken()
        viewModel.clientToken.observe(this, Observer { tokenResponse->
            if (tokenResponse!=null){
                clientObject= searchParser.parseClientToken(tokenResponse)
                Log.e("my_Token:",clientObject!!.tokenType)
                searchRequest("oo", clientObject!!)
            }
        })
    }

    private fun initViewModel() {
        viewModelFactory= SearchViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SearchViewModel::class.java)
    }

    private fun initViews() {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = SearchMusicAdapter(applicationContext,searchParser.parseQueryResults(AppUtils.JsonResponse))
        populateRecycler()
    }

    private fun searchRequest(newText: String?, clientObject: ClientTokenModel) {
        Log.i("MainActivity", "Called ViewModel")
        viewModel.searchApiCall(newText,clientObject)
        viewModel.queryResult.observe(this, Observer { queryResponse ->
            if (queryResponse!=null){
                adapter = SearchMusicAdapter(applicationContext,searchParser.parseQueryResults(queryResponse))
                populateRecycler()
            }
        })
    }

    private fun populateRecycler() {
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.search_btn)
        val searchView = searchItem?.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint = "Search music..."
        searchView?.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchRequest(query, clientObject!!)
                return false
            }
        })
        return true
    }
}