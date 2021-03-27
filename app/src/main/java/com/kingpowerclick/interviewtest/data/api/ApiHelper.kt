package com.kingpowerclick.interviewtest.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getPhotos() = apiService.getPhotos()

}