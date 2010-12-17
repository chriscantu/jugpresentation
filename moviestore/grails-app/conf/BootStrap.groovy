import com.rackspace.*

class BootStrap {

    def init = { servletContext ->
		new Genre(type: "Action").save()
		new Genre(type: "Comedy").save()
		new Media(type: "DVD").save()
		new Media(type: "Blu-Ray").save()
    }
    def destroy = {
    }
}
