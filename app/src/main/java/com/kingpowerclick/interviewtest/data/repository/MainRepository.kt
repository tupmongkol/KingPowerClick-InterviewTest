package com.kingpowerclick.interviewtest.data.repository

import com.kingpowerclick.interviewtest.data.api.ApiHelper
import com.kingpowerclick.interviewtest.data.model.Photo
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getPhotos(): Single<List<Photo>> {
        return apiHelper.getPhotos()
    }

}