package com.rackspace

import grails.converters.*

class MediaController {

	def show = {
		if (params.id && Media.exists(params.id)) {
			def m = Media.get(id)
			render m as JSON
		} else {
			render Media.list() as JSON
		}
	}
	
	def update = {
		if (params.id && Media.exists(params.id)) {
			def m = Media.get(params.id)
			m.properties = params
			m.save()
			render "$m updated successfully" as JSON
		} else {
			render "No such media" as JSON
		}
	}
	
	def save = {
		def m = new Media(params)
		println "xxxxxx $params"
		if (m.validate() && m.save()) {
			render "$m created successfully" as JSON
		} else {
			render "Error creating media"
		}
	}
	
	def delete = {
		def m = Media.get(params.id)
		
		if (m.delete()) {
			render "Media deleted successfully" as JSON
		} else {
			render "Unable to delete media" as JSON
		}
	}
}
