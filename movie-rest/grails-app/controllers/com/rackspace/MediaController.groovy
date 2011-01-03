package com.rackspace

import grails.converters.deep.*
import javax.servlet.http.*

class MediaController {
	
	def show = {
		if (params.id && Media.exists(params.id)) {
			def m = Media.get(params.id)
			render(contentType: "application/json", text: m as JSON)
		} else {
			def list = Media.list() 
			render(contentType: "application/json", text: list as JSON)
		}
	}
	
	def update = {
		def m = Media.get(params.id)
		
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
			render "No such media"
		}
	}
	
	def save = {
		def m = new Media()
		println " params: ${params}"
		
		if(params.title){
			m.properties = params
			m.genreId = params.genreId
			m.mediaId = params.mediaId
			
		} else {
			m.properties = request.JSON
		}

		if (m.validate() && m.save()) {
			response.status = HttpServletResponse.SC_CREATED
			render(contentType: "application/json", text: m as JSON)
		} else {
			response.status = HttpServletResponse.SC_BAD_REQUEST
			render "Error creating media - Error(s): ${m.errors}"
		}
	}
	
	def delete = {
		def m = Media.get(params.id)
		if (m) {
			m.delete()
			render "Media deleted successfully"
		} else {
			response.status = HttpServletResponse.SC_NOT_FOUND
			render "Unable to delete media"
		}
	}
}
