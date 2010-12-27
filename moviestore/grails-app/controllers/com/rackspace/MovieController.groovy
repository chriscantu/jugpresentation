package com.rackspace

class MovieController {
	
	def movieService

    def scaffold = true

	def list = {
		def movies = movieService.listMovies()
		
		[movieInstances: movies, totalInstances: movies.count()]
	}
}
