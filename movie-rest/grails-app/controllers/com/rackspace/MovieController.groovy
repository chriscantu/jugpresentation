package com.rackspace

import grails.converters.deep.*
import javax.servlet.http.*

class MovieController {

    static allowedMethods = [show: "GET", update: "PUT", delete: "DELETE", save: "POST"]

    def show = {
		if (params.id) {
			if(Movie.exists(params.id)) {
				render Movie.get(params.id) as JSON
			} else {
				render(text:"No such entity", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
			}
		} else {
			params.max = Math.min(params.max ? params.int('max') : 10, 100)
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_OK) { 
				[
					total: Movie.count(),
					list: Movie.list(params)
				]
			}
		}
	}

	def update = {
		def movieInstance = Movie.get(params.id)
		
		movieInstance.properties = request.JSON
		
		if (movieInstance) {
			if (movieInstance.validate() && movieInstance.save()) {
				render(text:"Updated successfully", status:HttpServletResponse.SC_OK, contentType:"application/json")
			} else {
				render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_BAD_REQUEST) { movieInstance.errors }
			}
		} else {
			render(text:"No such entity", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
		}
	}
	
	def save = {
		def movieInstance = new Movie()

		movieInstance.properties = request.JSON

		if (movieInstance.validate() && movieInstance.save()) {
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_CREATED) { movieInstance }
		} else {
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_BAD_REQUEST) { movieInstance.errors }
		}
	}
	
	def delete = {
		def movieInstance = Movie.get(params.id)
		
		if (movieInstance) {
			movieInstance.delete()
			render(text:"Delete successful", status:HttpServletResponse.SC_OK, contentType:"application/json")
		} else {
			render(text:"No such entity", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
		}
	}
}
