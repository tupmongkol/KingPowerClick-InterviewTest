package com.kingpowerclick.interviewtest.data.api

import com.kingpowerclick.interviewtest.data.model.Photo
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getPhotos(): Single<List<Photo>> {
        return Rx2AndroidNetworking.get("https://jsonplaceholder.typicode.com/albums/1/photos")
            .build()
            .getObjectListSingle(Photo::class.java)
    }

}