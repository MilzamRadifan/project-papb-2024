package com.example.tapetrove;

import java.util.List;

public class Trailer {
  /**
   * id : 934632
   * results : [{"iso_639_1":"en","iso_3166_1":"US","name":"Official Trailer","key":"UEJuNHOd8Dw","site":"YouTube","size":1080,"type":"Trailer","official":true,"published_at":"2024-03-18T16:00:02.000Z","id":"65f871c3903c52017e2a1360"},{"iso_639_1":"en","iso_3166_1":"US","name":"Zack Snyder's Teaser Breakdown","key":"WhKOA3nUkgw","site":"YouTube","size":1080,"type":"Featurette","official":true,"published_at":"2023-12-27T18:00:00.000Z","id":"659f3d37b39e3502007bd522"},{"iso_639_1":"en","iso_3166_1":"US","name":"Cast Reacts - Rebel Moon: ﻿Part Two Teaser","key":"3YtMhJ7sJ0M","site":"YouTube","size":1080,"type":"Featurette","official":true,"published_at":"2023-12-26T16:00:00.000Z","id":"659f3d4e42d8a5014cdcb550"},{"iso_639_1":"en","iso_3166_1":"US","name":"Official Teaser","key":"Cf16jEmvJUY","site":"YouTube","size":1080,"type":"Teaser","official":true,"published_at":"2023-12-25T15:00:00.000Z","id":"65899ac75aba3266e7ba00fb"}]
   */

  private int id;
  private List<ResultsBean> results;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<ResultsBean> getResults() {
    return results;
  }

  public void setResults(List<ResultsBean> results) {
    this.results = results;
  }

  public static class ResultsBean {
    /**
     * iso_639_1 : en
     * iso_3166_1 : US
     * name : Official Trailer
     * key : UEJuNHOd8Dw
     * site : YouTube
     * size : 1080
     * type : Trailer
     * official : true
     * published_at : 2024-03-18T16:00:02.000Z
     * id : 65f871c3903c52017e2a1360
     */

    private String iso_639_1;
    private String iso_3166_1;
    private String name;
    private String key;
    private String site;
    private int size;
    private String type;
    private boolean official;
    private String published_at;
    private String id;

    public String getIso_639_1() {
      return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
      this.iso_639_1 = iso_639_1;
    }

    public String getIso_3166_1() {
      return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
      this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getSite() {
      return site;
    }

    public void setSite(String site) {
      this.site = site;
    }

    public int getSize() {
      return size;
    }

    public void setSize(int size) {
      this.size = size;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public boolean isOfficial() {
      return official;
    }

    public void setOfficial(boolean official) {
      this.official = official;
    }

    public String getPublished_at() {
      return published_at;
    }

    public void setPublished_at(String published_at) {
      this.published_at = published_at;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }
  }
}
