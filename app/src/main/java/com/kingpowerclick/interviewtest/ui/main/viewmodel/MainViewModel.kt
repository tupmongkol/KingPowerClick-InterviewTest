package com.kingpowerclick.interviewtest.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kingpowerclick.interviewtest.data.model.Photo
import com.kingpowerclick.interviewtest.data.repository.MainRepository
import com.kingpowerclick.interviewtest.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val photos = MutableLiveData<Resource<List<Photo>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        photos.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ photoList ->
                    photos.postValue(Resource.success(photoList))
                }, { _ ->
                    photos.postValue(Resource.error("Error", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getPhotos(): LiveData<Resource<List<Photo>>> {
        return photos
    }

}