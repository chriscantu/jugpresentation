import com.rackspace.*

class BootStrap {

    def init = { servletContext ->
        
        def blu = new Media(type:"BluRay")
        def dvd = new Media(type: "DVD")
        
        blu.save()
        dvd.save()
        
        def act = new Genre(type: "Action")
        def com = new Genre(type: "Comedy")
        
        act.save()
        com.save()
        
        createMovie( ["Iron Man", 2008, 4, act, blu ] )
        createMovie( ["Thor", 2011, 3, act, blu ] )
        createMovie( ["3 Amigos", 1986, 5, com, dvd ] )
        createMovie( ["300", 2006, 5, act, blu ] )
        createMovie( ["Topgun", 1986, 4, act, dvd] )
        createMovie( ["The Lion King", 1994, 4, com, dvd ] )
        createMovie( ["Godfather II", 1972, 5, com, dvd ] )
        createMovie( ["Pulp Fiction", 1994, 4, act, dvd ] )
        createMovie( ["The Dark Knight", 2008, 5, act, dvd ] )
        createMovie( ["Fight Club", 1999, 5, act, dvd ] )
        createMovie( ["Aliens", 1986, 5, act, dvd ] )
    }
    
    def createMovie(list) {
        new Movie( title: list.get(0), year: list.get(1), rating: list.get(2),
            genre: list.get(3), media: list.get(4) ).save()
    }

    def destroy = {
    }
}