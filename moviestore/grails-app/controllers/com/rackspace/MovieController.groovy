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
	
	def edit = {
		[movieInstance: movieService.get(params.id)]
	}
	
	def update = {
		movieService.update(params)
		redirect(action: show, id: params.id)
	}
	
	def delete = {
		movieService.delete(params.id)
		redirect(action: list)
	}

	def save = {
		def movie = movieService.save(params)
		redirect(action: show, id: movie.id)
	}
}
