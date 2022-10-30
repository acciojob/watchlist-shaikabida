package com.driver;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MovieRepository {
    HashMap<Movie,Director> movies=new HashMap<>();
    HashMap<Director,List<Movie>> directors=new HashMap<>();

    public void addMovieToRepo(Movie movie){
        movies.put(movie,null);
    }
    public Movie sendMovieFromRepo(String movieName){
        for(Map.Entry<Movie,Director> e:movies.entrySet()){
            Movie currMovie=e.getKey();
            if(currMovie.getName().equals(movieName)){
                return currMovie;
            }
        }
        return null;
    }
    public void addDirectorToRepo(Director director){
        List<Movie> listOfMovies=new ArrayList<>();
        directors.put(director,listOfMovies);
    }

    public Director sendDirectorFromRepo(String directorName){
        for(Map.Entry<Director,List<Movie>> e:directors.entrySet()){
            Director currDirector=e.getKey();
            if(currDirector.getName().equals(directorName)){
                return currDirector;
            }
        }
        return null;
    }

    public String pairMovieAndDirector(String movieName,String directorName){
        Movie movie=sendMovieFromRepo(movieName);
        Director director=sendDirectorFromRepo(directorName);
        if(movie==null){
            return "Movie not found";
        }
        if(director==null){
            return "Director not found";
        }
        movies.put(movie,director);
        List<Movie> listOfMovies=directors.get(director);
        boolean check=false;
        for(int i=0;i< listOfMovies.size();i++){
            String currMovieName=listOfMovies.get(i).getName();
            if(currMovieName.equals(movieName)){
                check=true;
                break;
            }
        }
        if(!check){
            listOfMovies.add(movie);
            directors.put(director,listOfMovies);
        }
        return "Paired";
    }
    public HashMap<Movie,Director> movieList(){
        return movies;
    }
    public HashMap<Director,List<Movie>> directorList(){
        return directors;
    }

    public List<String> movieNamesList(){
        List<String> list=new ArrayList<>();
        for(Map.Entry<Movie,Director> e:movies.entrySet()){
            Movie currMovie=e.getKey();
            list.add(currMovie.getName());
        }
        return list;
    }
    public void deleteTheDirector(String directorName){
        Director d=sendDirectorFromRepo(directorName);
        if(d==null){
            return;
        }
        List<Movie> list=directors.get(d);
        for(int i=0;i<list.size();i++){
            Movie m=list.get(i);
            movies.remove(m);
        }
        directors.remove(d);
    }
    public void deleteAllDirectors(){
        for(Map.Entry<Director,List<Movie>> d:directors.entrySet()){
            Director d1=d.getKey();
            List<Movie> list=directors.get(d1);
            for(int i=0;i<list.size();i++){
                Movie m=list.get(i);
                movies.remove(m);
            }
        }
        directors.clear();
    }

    public List<String> getMoviesOfDirectorFromRepo(String name){
        List<String> list=new ArrayList<>();
        Director d=sendDirectorFromRepo(name);
        if(d!=null){
            List<Movie> movieList=directors.get(d);
            for(int i=0;i<movieList.size();i++){
                list.add(movieList.get(i).getName());
            }
        }
        return list;
    }





}
