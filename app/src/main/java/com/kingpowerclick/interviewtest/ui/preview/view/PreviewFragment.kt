package com.kingpowerclick.interviewtest.ui.preview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kingpowerclick.interviewtest.R
import com.kingpowerclick.interviewtest.data.model.Photo
import com.kingpowerclick.interviewtest.ui.preview.viewmodel.PreviewViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_preview.*


class PreviewFragment : Fragment() {

    private lateinit var viewModel: PreviewViewModel
    private lateinit var photo: Photo

    companion object {
        private const val EXTRA_PHOTO_KEY = "com.kingpowerclick.interviewtest.EXTRA_PHOTO_KEY"

        @JvmStatic
        fun newInstance(photo: Photo) = PreviewFragment().apply {
            arguments = Bundle().apply {
                putSerializable(EXTRA_PHOTO_KEY, photo)
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PreviewViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photo = arguments?.getSerializable(EXTRA_PHOTO_KEY) as Photo

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }


    private fun setupUI() {
        val supportActionBar = (activity as AppCompatActivity).supportActionBar
        supportActionBar?.title = photo.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Picasso.get().load(photo.url).placeholder(R.drawable.ic_placeholder).into(imagePreview);
    }

}