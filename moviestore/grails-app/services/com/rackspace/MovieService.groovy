package com.rackspace

import grails.converters.*

class MovieService {

    static transactional = false
	static restEndpoint = "http://localhost:8082/movie-rest/"

	def listMovies() {
		def response
		withRest(uri: restEndpoint){
			response = get(path:"movie").data.toString()
		}
		JSON.parse(response)
	}
}
