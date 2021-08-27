package mohamed.atef.mondiatask.views

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mohamed.atef.mondiatask.AppUtils
import mohamed.atef.mondiatask.R
import mohamed.atef.mondiatask.parser.SearchParser
import mohamed.atef.mondiatask.viewModels.SearchViewModel
import mohamed.atef.mondiatask.viewModels.SearchViewModelFactory


class MainActivity : AppCompatActivity()  {
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
    }

    private fun initViewModel() {
        viewModelFactory= SearchViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SearchViewModel::class.java)
    }

    private fun initViews() {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = SearchMusicAdapter(applicationContext,searchParser.parse(AppUtils.JsonResponse))
        populateRecycler()
    }

    private fun searchRequest(newText: String?) {
        Log.i("GameFragment", "Called ViewModelProvider.get")
        viewModel.searchApiCall(newText)
        viewModel.queryResult.observe(this, Observer { queryResponse ->
            if (queryResponse!=null){
                adapter = SearchMusicAdapter(applicationContext,searchParser.parse(queryResponse))
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
                searchRequest(query)
                return false
            }
        })
        return true
    }
}