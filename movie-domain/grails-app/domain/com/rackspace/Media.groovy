package com.rackspace

class Media {
	
	String type

    static constraints = {
		type(blank:false)
    }
	
	String toString() {
		type
	}
}
