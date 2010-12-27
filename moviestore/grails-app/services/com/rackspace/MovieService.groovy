package com.rackspace

import grails.converters.*

class MovieService {

    static transactional = false
	static restEndpoint = "http://localhost:8081/movie-rest/"

	def listMovies() {
		def response
		withRest(uri: restEndpoint){
			response = get(path:"movie").data.toString()
		}
		JSON.parse(response)
	}
	
	def show(id){
		def response 
		withRest(uri: restEndpoint){
			response = get(path:"movie/$id")?.data.toString()
		}
		
		JSON.parse(response)
	}
}
