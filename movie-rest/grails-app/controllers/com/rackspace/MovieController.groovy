package com.rackspace

import grails.converters.deep.*
import javax.servlet.http.*

class MovieController {
	
	def show = {
		if (params.id && Movie.exists(params.id)) {
			render(contentType:"application/json", builder:"json") { Movie.get(params.id) }
		} else {
			render(contentType:"application/json", builder:"json") { Movie.list() }
		}
	}
	
	def update = {
		def m = Movie.get(params.id)
		
		if (m) {
			m.properties = request.JSON
			
			if (m.validate() && m.save()) {
				render(text:"$m updated successfully", status:HttpServletResponse.SC_OK, contentType:"application/json")
			} else {
				render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_BAD_REQUEST) { m.errors }
			}
		} else {
			render(text:"No such movie", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
		}
	}
	
	def save = {
		def m = new Movie()
		
		if(params.title){
			m.properties = params
			m.genreId = params.genreId
			m.mediaId = params.mediaId
			
		} else {
			m.properties = request.JSON
		}

		if (m.validate() && m.save()) {
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_CREATED) { m }
		} else {
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_BAD_REQUEST) { m.errors }
		}
	}
	
	def delete = {
		def m = Movie.get(params.id)
		if (m) {
			m.delete()
			render(text:"Movie deleted successfully", status:HttpServletResponse.SC_OK, contentType:"application/json")
		} else {
			render(text:"No such movie", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
		}
	}
}
