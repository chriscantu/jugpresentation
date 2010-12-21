package com.rackspace

import grails.converters.*

class MovieController {

	def show = {
		if (params.id && Movie.exists(params.id)) {
			def m = Movie.get(id)
			render m as JSON
		} else {
			render Movie.list() as JSON
		}
	}
	
	def update = {
		if (params.id && Movie.exists(params.id)) {
			def m = Movie.get(params.id)
			m.properties = params
			m.save()
			render "$m updated successfully"
		} else {
			render "No such movie"
		}
	}
	
	def save = {
		def m = new Movie(title: params.title, genre: Genre.get(params.genre), 
			media: Media.get(params.media), year: new Date(year: params.year as Integer), rating: params.rating)
			
		if (m.validate() && m.save()) {
			render "$m created successfully" 
		} else {
			println "${m.errors}"
			render "Error creating movie"
		}
	}
	
	def delete = {
		def m = Movie.get(params.id)
		
		if (m.delete()) {
			render "Movie deleted successfully"
		} else {
			render "Unable to delete movie"
		}
	}
}
