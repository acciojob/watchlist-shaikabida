package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    @Autowired
    MovieRepository objectRepository;

    public void sendMovieToRepo(Movie movie){
        objectRepository.addMovieToRepo(movie);
    }
    public Movie getMovieFromRepo(String name){
        return objectRepository.sendMovieFromRepo(name);
    }
    public void sendDirectorToRepo(Director director){
        objectRepository.addDirectorToRepo(director);
    }

    public Director getDirectorFromRepo(String name){
        return objectRepository.sendDirectorFromRepo(name);
    }
    public String pairing(String movieName,String directorName){
        return objectRepository.pairMovieAndDirector(movieName,directorName);
    }
    public List<String> getMovieNamesListFromRepo(){
        return objectRepository.movieNamesList();
    }
    public void deleteTheDirectorFromRepo(String name){
        objectRepository.deleteTheDirector(name);
    }
    public void deleteAllDirectorsFromRepo(){
        objectRepository.deleteAllDirectors();
    }
    public List<String> getMoviesOfDirector(String name){
        List<String> out=objectRepository.getMoviesOfDirectorFromRepo(name);
        return out;
    }



    public HashMap<Movie,Director> sendMovieList(){
        return objectRepository.movieList();
    }
    public HashMap<Director,List<Movie>> sendDirectorList(){
        return objectRepository.directorList();
    }
}
