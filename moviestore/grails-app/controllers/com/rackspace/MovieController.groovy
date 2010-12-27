package com.rackspace

class MovieController {
	
	def movieService

    def scaffold = true

	def list = {
		def movies = movieService.listMovies()
		
		[movieInstanceList: movies, movieInstanceTotal: movies.count()]
	}
	
	def show = {
		[movieInstance: movieService.get(params.id)]
	}
}
