package com.rackspace

class Genre {
	
	String type
	
    static constraints = {
		type(blank:false)
    }

	String toString() {
		type
	}
}
