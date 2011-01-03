package com.rackspace

import grails.converters.deep.*
import javax.servlet.http.*

class MediaController {
	
	def show = {
		if (params.id && Media.exists(params.id)) {
			render(contentType:"application/json", builder:"json") { Media.get(params.id) }
		} else {
			render(contentType:"application/json", builder:"json") { Media.list() }
		}
	}
	
	def update = {
		def m = Media.get(params.id)
		
		if (m) {
			m.properties = request.JSON
			
			def message
			if (m.validate() && m.save()) {
				render(text:"$m updated successfully", status:HttpServletResponse.SC_OK, contentType:"application/json")
			} else {
				render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_BAD_REQUEST) { m.errors }
			}
		} else {
			render(text:"No such media", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
		}
	}
	
	def save = {
		def m = new Media()
		
		if(params.title){
			m.properties = params
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
		def m = Media.get(params.id)
		if (m) {
			m.delete()
			render(text:"Media deleted successfully", status:HttpServletResponse.SC_OK, contentType:"application/json")
		} else {
			render(text:"No such media", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
		}
	}
}
