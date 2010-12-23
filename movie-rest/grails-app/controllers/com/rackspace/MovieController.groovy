package com.rackspace

import grails.converters.*
import javax.servlet.http.*

class MovieController {

	def show = {
		if (params.id && Movie.exists(params.id)) {
			def m = Movie.get(params.id)
			render m as JSON
		} else {
			render Movie.list() as JSON
		}
	}
	
	def update = {
		def m = Movie.get(params.id)
		
		if (m) {
			m.properties = request.JSON
			
			def message
			if (m.validate() && m.save()) {
				response.status = HttpServletResponse.SC_OK
				message = "$m updated successfully"
			} else {
				response.status = HttpServletResponse.SC_BAD_REQUEST
				message = "$m - Error:  ${m.errors}"
			}
			
			render message
		} else {
			response.status = HttpServletResponse.SC_NOT_FOUND
			render "No such movie"
		}
	}
	
	def save = {
		def m = new Movie(params)
		m.mediaId = params.mediaId
		m.genreId = params.genreId

		if (m.validate() && m.save()) {
			response.status = HttpServletResponse.SC_CREATED
			render("Movie successfully created")
		} else {
			response.status = HttpServletResponse.SC_BAD_REQUEST
			render "Error creating movie - Error(s): ${m.errors}"
		}
	}
	
	def delete = {
		def m = Movie.get(params.id)
		if (m) {
			m.delete()
			render "Movie deleted successfully"
		} else {
			response.status = HttpServletResponse.SC_NOT_FOUND
			render "Unable to delete movie"
		}
	}
}
