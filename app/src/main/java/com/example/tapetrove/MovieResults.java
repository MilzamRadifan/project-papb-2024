package com.example.tapetrove;

import java.io.Serializable;
import java.util.List;

public class MovieResults implements Serializable {

    /**
     * page : 1
     * results : [{"adult":false,"backdrop_path":"/qrGtVFxaD8c7et0jUtaYhyTzzPg.jpg","genre_ids":[28,878,12,14],"id":823464,"original_language":"en","original_title":"Godzilla x Kong: The New Empire","overview":"Following their explosive showdown, Godzilla and Kong must reunite against a colossal undiscovered threat hidden within our world, challenging their very existence \u2013 and our own.","popularity":2602.291,"poster_path":"/tMefBSflR6PGQLv7WvFPpKLZkyk.jpg","release_date":"2024-03-27","title":"Godzilla x Kong: The New Empire","video":false,"vote_average":6.673,"vote_count":685},{"adult":false,"backdrop_path":"/87IVlclAfWL6mdicU1DDuxdwXwe.jpg","genre_ids":[878,12],"id":693134,"original_language":"en","original_title":"Dune: Part Two","overview":"Follow the mythic journey of Paul Atreides as he unites with Chani and the Fremen while on a path of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, Paul endeavors to prevent a terrible future only he can foresee.","popularity":2579.546,"poster_path":"/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg","release_date":"2024-02-27","title":"Dune: Part Two","video":false,"vote_average":8.294,"vote_count":3196},{"adult":false,"backdrop_path":"/1XDDXPXGiI8id7MrUxK36ke7gkX.jpg","genre_ids":[16,28,10751,35,14],"id":1011985,"original_language":"en","original_title":"Kung Fu Panda 4","overview":"Po is gearing up to become the spiritual leader of his Valley of Peace, but also needs someone to take his place as Dragon Warrior. As such, he will train a new kung fu practitioner for the spot and will encounter a villain called the Chameleon who conjures villains from the past.","popularity":2321.715,"poster_path":"/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg","release_date":"2024-03-02","title":"Kung Fu Panda 4","video":false,"vote_average":7.155,"vote_count":1194},{"adult":false,"backdrop_path":"/2KGxQFV9Wp1MshPBf8BuqWUgVAz.jpg","genre_ids":[16,28,12,35,10751],"id":940551,"original_language":"en","original_title":"Migration","overview":"After a migrating duck family alights on their pond with thrilling tales of far-flung places, the Mallard family embarks on a family road trip, from New England, to New York City, to tropical Jamaica.","popularity":986.979,"poster_path":"/ldfCF9RhR40mppkzmftxapaHeTo.jpg","release_date":"2023-12-06","title":"Migration","video":false,"vote_average":7.536,"vote_count":1206},{"adult":false,"backdrop_path":"/9wJO4MBzkqgUZemLTGEsgUbYyP6.jpg","genre_ids":[878,9648,53,28],"id":720321,"original_language":"en","original_title":"Breathe","overview":"Air-supply is scarce in the near future, forcing a mother and daughter to fight for survival when two strangers arrive desperate for an oxygenated haven.","popularity":1094.244,"poster_path":"/wTW2t8ocWDlHns8I7vQxuqkyK58.jpg","release_date":"2024-04-04","title":"Breathe","video":false,"vote_average":5.202,"vote_count":47},{"adult":false,"backdrop_path":"/cIztAxDn3H8JylRaJwiHHpkGe53.jpg","genre_ids":[10751,35,16],"id":1239146,"original_language":"en","original_title":"Woody Woodpecker Goes to Camp","overview":"After getting kicked out of the forest, Woody thinks he's found a forever home at Camp Woo Hoo \u2014 until an inspector threatens to shut down the camp.","popularity":927.905,"poster_path":"/mMnzNYvpqLLLdgF5TMmXfuy6wzx.jpg","release_date":"2024-04-12","title":"Woody Woodpecker Goes to Camp","video":false,"vote_average":7.2,"vote_count":63},{"adult":false,"backdrop_path":"/7ZP8HtgOIDaBs12krXgUIygqEsy.jpg","genre_ids":[878,28,14,12],"id":601796,"original_language":"ko","original_title":"외계+인 1부","overview":"Gurus in the late Goryeo dynasty try to obtain a fabled, holy sword, and humans in 2022 hunt down an alien prisoner that is locked in a human's body. The two parties cross paths when a time-traveling portal opens up.","popularity":1032.337,"poster_path":"/8QVDXDiOGHRcAD4oM6MXjE0osSj.jpg","release_date":"2022-07-20","title":"Alienoid","video":false,"vote_average":6.918,"vote_count":280},{"adult":false,"backdrop_path":"/4woSOUD0equAYzvwhWBHIJDCM88.jpg","genre_ids":[28,27,53],"id":1096197,"original_language":"en","original_title":"No Way Up","overview":"Characters from different backgrounds are thrown together when the plane they're travelling on crashes into the Pacific Ocean. A nightmare fight for survival ensues with the air supply running out and dangers creeping in from all sides.","popularity":1223.319,"poster_path":"/hu40Uxp9WtpL34jv3zyWLb5zEVY.jpg","release_date":"2024-01-18","title":"No Way Up","video":false,"vote_average":6.342,"vote_count":392},{"adult":false,"backdrop_path":"/oe7mWkvYhK4PLRNAVSvonzyUXNy.jpg","genre_ids":[28,53],"id":359410,"original_language":"en","original_title":"Road House","overview":"Ex-UFC fighter Dalton takes a job as a bouncer at a Florida Keys roadhouse, only to discover that this paradise is not all it seems.","popularity":997.866,"poster_path":"/bXi6IQiQDHD00JFio5ZSZOeRSBh.jpg","release_date":"2024-03-08","title":"Road House","video":false,"vote_average":7.063,"vote_count":1511},{"adult":false,"backdrop_path":"/uv2twFGMk2qBdyJBJAVcrpRtSa9.jpg","genre_ids":[28,10752,878],"id":929590,"original_language":"en","original_title":"Civil War","overview":"In the near future, a group of war journalists attempt to survive while reporting the truth as the United States stands on the brink of civil war.","popularity":722.672,"poster_path":"/sh7Rg8Er3tFcN9BpKIPOMvALgZd.jpg","release_date":"2024-04-10","title":"Civil War","video":false,"vote_average":7.477,"vote_count":265},{"adult":false,"backdrop_path":"/pwGmXVKUgKN13psUjlhC9zBcq1o.jpg","genre_ids":[28,14],"id":634492,"original_language":"en","original_title":"Madame Web","overview":"Forced to confront revelations about her past, paramedic Cassandra Webb forges a relationship with three young women destined for powerful futures...if they can all survive a deadly present.","popularity":776.45,"poster_path":"/rULWuutDcN5NvtiZi4FRPzRYWSh.jpg","release_date":"2024-02-14","title":"Madame Web","video":false,"vote_average":5.632,"vote_count":1113},{"adult":false,"backdrop_path":"/qekky2LbtT1wtbD5MDgQvjfZQ24.jpg","genre_ids":[28,53],"id":984324,"original_language":"fr","original_title":"Le salaire de la peur","overview":"When an explosion at an oil well threatens hundreds of lives, a crack team is called upon to make a deadly desert crossing with nitroglycerine in tow.","popularity":804.626,"poster_path":"/jFK2ZLQUzo9pea0jfMCHDfvWsx7.jpg","release_date":"2024-03-28","title":"The Wages of Fear","video":false,"vote_average":5.801,"vote_count":176},{"adult":false,"backdrop_path":"/fUC5VsQcU3m6zmYMD96R7RqPuMn.jpg","genre_ids":[28,80,53],"id":1105407,"original_language":"en","original_title":"Damaged","overview":"A Chicago detective travels to Scotland after an emerging serial killer\u2019s crimes match those that he investigated five years earlier, one of which was the crime scene of his murdered girlfriend.","popularity":942.154,"poster_path":"/tMO0YLXgJZBnIAjoTSz26zE33YN.jpg","release_date":"2024-04-12","title":"Damaged","video":false,"vote_average":5.574,"vote_count":27},{"adult":false,"backdrop_path":"/qnVm19Vu2Sc14LoEj82pmqAYr3p.jpg","genre_ids":[12,28,18],"id":845111,"original_language":"fr","original_title":"Les trois mousquetaires : Milady","overview":"D'Artagnan, on a quest to rescue the abducted Constance, runs into the mysterious Milady de Winter again. The tension between the Catholics and the Protestants finally escalates, as the king declares war \u2014 forcing the now four musketeers into battle. But as the war goes on, they are tested physically, mentally and emotionally.","popularity":627.586,"poster_path":"/rtosxP5sXuoRFPH4sVbMccLIPiV.jpg","release_date":"2023-12-13","title":"The Three Musketeers: Milady","video":false,"vote_average":6.6,"vote_count":298},{"adult":false,"backdrop_path":"/dcnSWFCtk4b2aIzkhq6IDbzoIf1.jpg","genre_ids":[28,35],"id":942047,"original_language":"en","original_title":"Outsource","overview":"A police chief hires an old friend, who is an international spy, to help him search for a wanted suspect in the Philippines. When the chief dies, all evidence points towards the spy, and he must go to extremes to defend himself.","popularity":773.22,"poster_path":"/zIAF0UXtCJTJOYNYWiBfyifaaOi.jpg","release_date":"2022-01-18","title":"Outsource","video":false,"vote_average":3.5,"vote_count":2},{"adult":false,"backdrop_path":"/e3gVl1gnxEFKLTF6pn6KRqUPi9K.jpg","genre_ids":[10749,18],"id":1127166,"original_language":"it","original_title":"Fabbricante di lacrime","overview":"Adopted together after a tough childhood in an orphanage, Nica and Rigel realize that unexpected but irresistible feelings pull them together.","popularity":601.777,"poster_path":"/uoBHsxSgfc3PQsSn98RfnbePHOy.jpg","release_date":"2024-04-03","title":"The Tearsmith","video":false,"vote_average":6.599,"vote_count":411},{"adult":false,"backdrop_path":"/atPlFdUrQl2U9MtUwujrrjnQHBA.jpg","genre_ids":[28,80,18,53],"id":654739,"original_language":"ko","original_title":"발신제한","overview":"On his way to work, a bank manager receives an anonymous call claiming there's a bomb under his car seat, and if anyone exits the car, it will explode unless he can pay a ransom.","popularity":709.021,"poster_path":"/y2Aimt8isimtigec3e4kB2G9FMR.jpg","release_date":"2021-06-23","title":"Hard Hit","video":false,"vote_average":7.678,"vote_count":169},{"adult":false,"backdrop_path":"/FUnAVgaTs5xZWXcVzPJNxd9qGA.jpg","genre_ids":[878,28,18,10770],"id":934632,"original_language":"en","original_title":"Rebel Moon - Part Two: The Scargiver","overview":"The rebels gear up for battle against the Motherworld as unbreakable bonds are forged, heroes emerge \u2014 and legends are made.","popularity":574.325,"poster_path":"/cxevDYdeFkiixRShbObdwAHBZry.jpg","release_date":"2024-04-19","title":"Rebel Moon - Part Two: The Scargiver","video":false,"vote_average":6.148,"vote_count":257},{"adult":false,"backdrop_path":"/9c0lHTXRqDBxeOToVzRu0GArSne.jpg","genre_ids":[878,28],"id":935271,"original_language":"en","original_title":"After the Pandemic","overview":"Set in a post-apocalyptic world where a global airborne pandemic has wiped out 90% of the Earth's population and only the young and immune have endured as scavengers. For Ellie and Quinn, the daily challenges to stay alive are compounded when they become hunted by the merciless Stalkers.","popularity":709.146,"poster_path":"/p1LbrdJ53dGfEhRopG71akfzOVu.jpg","release_date":"2022-03-01","title":"After the Pandemic","video":false,"vote_average":4.806,"vote_count":54},{"adult":false,"backdrop_path":"/lzWHmYdfeFiMIY4JaMmtR7GEli3.jpg","genre_ids":[878,12],"id":438631,"original_language":"en","original_title":"Dune","overview":"Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive.","popularity":542.962,"poster_path":"/d5NXSklXo0qyIYkgV94XAgMIckC.jpg","release_date":"2021-09-15","title":"Dune","video":false,"vote_average":7.791,"vote_count":11353}]
     * total_pages : 43690
     * total_results : 873793
     */

    private int page;
    private int total_pages;
    private int total_results;
    private List<ResultsBean> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Serializable {
        /**
         * adult : false
         * backdrop_path : /qrGtVFxaD8c7et0jUtaYhyTzzPg.jpg
         * genre_ids : [28,878,12,14]
         * id : 823464
         * original_language : en
         * original_title : Godzilla x Kong: The New Empire
         * overview : Following their explosive showdown, Godzilla and Kong must reunite against a colossal undiscovered threat hidden within our world, challenging their very existence – and our own.
         * popularity : 2602.291
         * poster_path : /tMefBSflR6PGQLv7WvFPpKLZkyk.jpg
         * release_date : 2024-03-27
         * title : Godzilla x Kong: The New Empire
         * video : false
         * vote_average : 6.673
         * vote_count : 685
         */

        private boolean adult;
        private String backdrop_path;
        private int id;
        private String original_language;
        private String original_title;
        private String overview;
        private double popularity;
        private String poster_path;
        private String release_date;
        private String title;
        private boolean video;
        private double vote_average;
        private int vote_count;
        private List<Integer> genre_ids;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }
    }
}
