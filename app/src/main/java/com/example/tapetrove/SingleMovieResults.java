package com.example.tapetrove;

import java.util.List;

public class SingleMovieResults {

  /**
   * adult : false
   * backdrop_path : /xf1rEQRi9pZxoN8HfggVnhjOaBb.jpg
   * belongs_to_collection : {"id":1280074,"name":"Kong Collection","poster_path":"/lhyEUeOihbKf7ll8RCIE5CHTie3.jpg","backdrop_path":"/qHY4ZMIDSmElhiykjhh40Q5qMJl.jpg"}
   * budget : 150000000
   * genres : [{"id":878,"name":"Science Fiction"},{"id":28,"name":"Action"},{"id":12,"name":"Adventure"}]
   * homepage : https://www.godzillaxkongmovie.com
   * id : 823464
   * imdb_id : tt14539740
   * origin_country : ["US"]
   * original_language : en
   * original_title : Godzilla x Kong: The New Empire
   * overview : Following their explosive showdown, Godzilla and Kong must reunite against a colossal undiscovered threat hidden within our world, challenging their very existence – and our own.
   * popularity : 4619.309
   * poster_path : /z1p34vh7dEOnLDmyCrlUVLuoDzd.jpg
   * production_companies : [{"id":923,"logo_path":"/8M99Dkt23MjQMTTWukq4m5XsEuo.png","name":"Legendary Pictures","origin_country":"US"},{"id":174,"logo_path":"/zhD3hhtKB5qyv7ZeL4uLpNxgMVU.png","name":"Warner Bros. Pictures","origin_country":"US"}]
   * production_countries : []
   * release_date : 2024-03-27
   * revenue : 558503759
   * runtime : 115
   * spoken_languages : [{"english_name":"English","iso_639_1":"en","name":"English"}]
   * status : Released
   * tagline : Rise together or fall alone.
   * title : Godzilla x Kong: The New Empire
   * video : false
   * vote_average : 7.283
   * vote_count : 2114
   */

  private boolean adult;
  private String backdrop_path;
  private BelongsToCollectionBean belongs_to_collection;
  private int budget;
  private String homepage;
  private int id;
  private String imdb_id;
  private String original_language;
  private String original_title;
  private String overview;
  private double popularity;
  private String poster_path;
  private String release_date;
  private int revenue;
  private int runtime;
  private String status;
  private String tagline;
  private String title;
  private boolean video;
  private double vote_average;
  private int vote_count;
  private List<GenresBean> genres;
  private List<String> origin_country;
  private List<ProductionCompaniesBean> production_companies;
  private List<?> production_countries;
  private List<SpokenLanguagesBean> spoken_languages;

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  public String getBackdrop_path() {
    return backdrop_path;
  }

  public void setBackdrop_path(String backdrop_path) {
    this.backdrop_path = backdrop_path;
  }

  public BelongsToCollectionBean getBelongs_to_collection() {
    return belongs_to_collection;
  }

  public void setBelongs_to_collection(BelongsToCollectionBean belongs_to_collection) {
    this.belongs_to_collection = belongs_to_collection;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  public String getHomepage() {
    return homepage;
  }

  public void setHomepage(String homepage) {
    this.homepage = homepage;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getImdb_id() {
    return imdb_id;
  }

  public void setImdb_id(String imdb_id) {
    this.imdb_id = imdb_id;
  }

  public String getOriginal_language() {
    return original_language;
  }

  public void setOriginal_language(String original_language) {
    this.original_language = original_language;
  }

  public String getOriginal_title() {
    return original_title;
  }

  public void setOriginal_title(String original_title) {
    this.original_title = original_title;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public double getPopularity() {
    return popularity;
  }

  public void setPopularity(double popularity) {
    this.popularity = popularity;
  }

  public String getPoster_path() {
    return poster_path;
  }

  public void setPoster_path(String poster_path) {
    this.poster_path = poster_path;
  }

  public String getRelease_date() {
    return release_date;
  }

  public void setRelease_date(String release_date) {
    this.release_date = release_date;
  }

  public int getRevenue() {
    return revenue;
  }

  public void setRevenue(int revenue) {
    this.revenue = revenue;
  }

  public int getRuntime() {
    return runtime;
  }

  public void setRuntime(int runtime) {
    this.runtime = runtime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTagline() {
    return tagline;
  }

  public void setTagline(String tagline) {
    this.tagline = tagline;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isVideo() {
    return video;
  }

  public void setVideo(boolean video) {
    this.video = video;
  }

  public double getVote_average() {
    return vote_average;
  }

  public void setVote_average(double vote_average) {
    this.vote_average = vote_average;
  }

  public int getVote_count() {
    return vote_count;
  }

  public void setVote_count(int vote_count) {
    this.vote_count = vote_count;
  }

  public List<GenresBean> getGenres() {
    return genres;
  }

  public void setGenres(List<GenresBean> genres) {
    this.genres = genres;
  }

  public List<String> getOrigin_country() {
    return origin_country;
  }

  public void setOrigin_country(List<String> origin_country) {
    this.origin_country = origin_country;
  }

  public List<ProductionCompaniesBean> getProduction_companies() {
    return production_companies;
  }

  public void setProduction_companies(List<ProductionCompaniesBean> production_companies) {
    this.production_companies = production_companies;
  }

  public List<?> getProduction_countries() {
    return production_countries;
  }

  public void setProduction_countries(List<?> production_countries) {
    this.production_countries = production_countries;
  }

  public List<SpokenLanguagesBean> getSpoken_languages() {
    return spoken_languages;
  }

  public void setSpoken_languages(List<SpokenLanguagesBean> spoken_languages) {
    this.spoken_languages = spoken_languages;
  }

  public static class BelongsToCollectionBean {
    /**
     * id : 1280074
     * name : Kong Collection
     * poster_path : /lhyEUeOihbKf7ll8RCIE5CHTie3.jpg
     * backdrop_path : /qHY4ZMIDSmElhiykjhh40Q5qMJl.jpg
     */

    private int id;
    private String name;
    private String poster_path;
    private String backdrop_path;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getPoster_path() {
      return poster_path;
    }

    public void setPoster_path(String poster_path) {
      this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
      return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
      this.backdrop_path = backdrop_path;
    }
  }

  public static class GenresBean {
    /**
     * id : 878
     * name : Science Fiction
     */

    private int id;
    private String name;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  public static class ProductionCompaniesBean {
    /**
     * id : 923
     * logo_path : /8M99Dkt23MjQMTTWukq4m5XsEuo.png
     * name : Legendary Pictures
     * origin_country : US
     */

    private int id;
    private String logo_path;
    private String name;
    private String origin_country;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getLogo_path() {
      return logo_path;
    }

    public void setLogo_path(String logo_path) {
      this.logo_path = logo_path;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getOrigin_country() {
      return origin_country;
    }

    public void setOrigin_country(String origin_country) {
      this.origin_country = origin_country;
    }
  }

  public static class SpokenLanguagesBean {
    /**
     * english_name : English
     * iso_639_1 : en
     * name : English
     */

    private String english_name;
    private String iso_639_1;
    private String name;

    public String getEnglish_name() {
      return english_name;
    }

    public void setEnglish_name(String english_name) {
      this.english_name = english_name;
    }

    public String getIso_639_1() {
      return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
      this.iso_639_1 = iso_639_1;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
