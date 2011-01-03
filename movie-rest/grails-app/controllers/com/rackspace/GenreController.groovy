package com.rackspace

import grails.converters.deep.*
import javax.servlet.http.*

class GenreController {

    static allowedMethods = [show: "GET", update: "PUT", delete: "DELETE", save: "POST"]

    def show = {
		if (params.id && Genre.exists(params.id)) {
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_OK) { Genre.get(params.id) }
		} else {
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_OK) { Genre.list(params) }
		}
	}

	def update = {
		def genreInstance = Genre.get(params.id)
		
		genreInstance.properties = request.JSON
		
		if (genreInstance) {
			if (genreInstance.validate() && genreInstance.save()) {
				render(text:"Updated successfully", status:HttpServletResponse.SC_OK, contentType:"application/json")
			} else {
				render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_BAD_REQUEST) { genreInstance.errors }
			}
		} else {
			render(text:"No such entity", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
		}
	}
	
	def save = {
		def genreInstance = new Genre()
		
		genreInstance.properties = params
		
		if (!genreInstance.validate()) {
			genreInstance.properties = request.JSON
		}
		
		if (genreInstance.validate() && genreInstance.save()) {
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_CREATED) { genreInstance }
		} else {
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_BAD_REQUEST) { genreInstance.errors }
		}
	}
	
	def delete = {
		def genreInstance = Genre.get(params.id)
		
		if (genreInstance) {
			genreInstance.delete()
			render(text:"Delete successful", status:HttpServletResponse.SC_OK, contentType:"application/json")
		} else {
			render(text:"No such entity", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
		}
	}
}
