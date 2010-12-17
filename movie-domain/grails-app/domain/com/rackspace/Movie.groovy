package com.rackspace

class Movie {
	
	String title
	Integer rating
	Date year
	Genre genre
	Media media

    static constraints = {
		title(blank:false, size: 3..100)
		rating(min:1, max:5)
		year(nullable:false)
    }	
} 
