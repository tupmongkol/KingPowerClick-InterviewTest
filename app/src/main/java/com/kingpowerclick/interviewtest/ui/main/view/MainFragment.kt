package com.kingpowerclick.interviewtest.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kingpowerclick.interviewtest.R
import com.kingpowerclick.interviewtest.data.api.ApiHelper
import com.kingpowerclick.interviewtest.data.api.ApiServiceImpl
import com.kingpowerclick.interviewtest.data.model.Photo
import com.kingpowerclick.interviewtest.ui.base.ViewModelFactory
import com.kingpowerclick.interviewtest.ui.main.adapter.MainAdapter
import com.kingpowerclick.interviewtest.ui.main.viewmodel.MainViewModel
import com.kingpowerclick.interviewtest.ui.preview.view.PreviewFragment
import com.kingpowerclick.interviewtest.utils.SpacesItemDecoration
import com.kingpowerclick.interviewtest.utils.Status
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    companion object {
        private const val SPAN_COUNT = 2
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        val supportActionBar = (activity as AppCompatActivity).supportActionBar
        supportActionBar?.title = getString(R.string.title_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = MainAdapter(arrayListOf(), onItemClicked = { photo ->
            (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.container, PreviewFragment.newInstance(photo))
                .addToBackStack(null)
                .commit()
        })

        val gridLayoutManager = GridLayoutManager(activity, SPAN_COUNT, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.fragment_main_grid_spacing)
        recyclerView.addItemDecoration(SpacesItemDecoration(SPAN_COUNT, spacingInPixels, true))
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getPhotos().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(photos: List<Photo>) {
        adapter.addData(photos)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(
            MainViewModel::class.java
        )
    }
}