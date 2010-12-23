package com.rackspace

class Movie {
	
	String title
	Integer rating
	Integer year
	Genre genre
	Media media

    static constraints = {
		title(unique: true, blank:false, size: 3..100)
		rating(min:1, max:5)
		year(nullable:false)
    }

	static transients = ['genreId', "mediaId"]

	void setGenreId(genreId) {
		genre = Genre.get(genreId)
	}
	
	Integer getGenreId() {
		genre?.id
	}
	
	void setMediaId(mediaId) {
		media = Media.get(mediaId)
	}
	
	Integer getMediaId(){
		media?.id
	}
	
	String toString() { "Movie - '$title'" }
} 
