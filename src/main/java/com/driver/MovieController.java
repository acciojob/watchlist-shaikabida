package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService objectService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody()Movie movie){
        objectService.sendMovieToRepo(movie);
        return new ResponseEntity<>("Movie succussfully added", HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name){
        Movie movie=objectService.getMovieFromRepo(name);
        if(movie==null){
            return new ResponseEntity<>("Movie not found",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody()Director director){
        objectService.sendDirectorToRepo(director);
        return new ResponseEntity<>("Director succussfully added", HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        Director director=objectService.getDirectorFromRepo(name);
        if(director==null){
            return new ResponseEntity<>("Director not found",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(director,HttpStatus.FOUND);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity  addMovieDirectorPair(@RequestParam("movieName") String movieName,
                                                @RequestParam("directorName") String directorName){
        String status= objectService.pairing(movieName,directorName);
        if(status.equals("Paired")){
            return new ResponseEntity<>("Paired Sucessfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(status,HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity  findAllMovies(){
        List<String> list=objectService.getMovieNamesListFromRepo();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }



    @GetMapping("/movie-list")
    public ResponseEntity movieList(){
        HashMap<Movie,Director> list=objectService.sendMovieList();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/director-list")
    public ResponseEntity directorList(){
        HashMap<Director,List<Movie>> list=objectService.sendDirectorList();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("directorName") String directorName){
        objectService.deleteTheDirectorFromRepo(directorName);
        return new ResponseEntity<>("Successfully deleted the director details",HttpStatus.OK);
    }
    @DeleteMapping("delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        objectService.deleteAllDirectorsFromRepo();
        return new ResponseEntity<>("Successfully deleted all director details",HttpStatus.OK);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String director){
        List<String> listOfMovies=objectService.getMoviesOfDirector(director);
        return new ResponseEntity<>(listOfMovies,HttpStatus.OK);
    }



}
