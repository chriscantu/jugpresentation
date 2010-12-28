package com.rackspace

import grails.converters.*
import groovyx.net.http.*

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
	
	def get(id){
		def response 
		withRest(uri: restEndpoint){
			response = get(path:"movie/$id")?.data.toString()
		}
		
		JSON.parse(response)
	}
	
	def save(movie) {
		def response
		 
		withRest(uri: restEndpoint){
			response = post(path: "movie", body: movie, requestContentType: ContentType.JSON)?.data.toString()
		}
		
		JSON.parse(response)
	}
	
	def update(movie) {
		def response
		withRest(uri: restEndpoint){
			response = put(path: "movie/${movie.id}", body: movie, requestContentType: ContentType.JSON)?.statusLine.statusCode
		}
		
		response
	}
	
	def delete(id) {
		def response
		
		withRest(uri: restEndpoint){
			response = delete(path: "movie/$id")?.statusLine?.statusCode
		}
		
		response
	}
}
