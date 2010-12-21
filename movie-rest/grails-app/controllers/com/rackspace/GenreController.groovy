package com.rackspace

import grails.converters.*

class GenreController {

	def show = {
		if (params.id && Genre.exists(params.id)) {
			def m = Genre.get(id)
			render m as JSON
		} else {
			render Genre.list() as JSON
		}
	}
	
	def update = {
		if (params.id && Genre.exists(params.id)) {
			def m = Genre.get(params.id)
			m.properties = params
			m.save()
			render "$m updated successfully"
		} else {
			render "No such genre"
		}
	}
	
	def save = {
		def m = new Genre(params)
		if (m.validate() && m.save()) {
			render "$m created successfully" 
		} else {
			render "Error creating genre"
		}
	}
	
	def delete = {
		def m = Genre.get(params.id)
		
		if (m.delete()) {
			render "Genre deleted successfully"
		} else {
			render "Unable to delete genre"
		}
	}
}
