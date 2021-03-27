package com.kingpowerclick.interviewtest.data.api

import com.kingpowerclick.interviewtest.data.model.Photo
import io.reactivex.Single

interface ApiService {

    fun getPhotos(): Single<List<Photo>>

}