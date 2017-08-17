package com.tonychen.trainingapp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TonyChen on 2017/8/17;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * PACKAGENAME: com.tonychen.trainingapp.model
 * Description :
 */
public class TestBean2 {

    @Override
    public String toString() {
        return "TestBean2{" +
                "page_index=" + page_index +
                ", songs=" + songs +
                ", song_sum=" + song_sum +
                ", domain='" + domain + '\'' +
                ", count=" + count +
                '}';
    }

    /**
     * page_index : 1
     * songs : {}
     * song_sum : 0
     * domain : music
     * count : 0
     */

    private int page_index;
    private SongsBean songs;
    private int song_sum;
    private String domain;
    private int count;

    public int getPage_index() {
        return page_index;
    }

    public void setPage_index(int page_index) {
        this.page_index = page_index;
    }

    public SongsBean getSongs() {
        return songs;
    }

    public void setSongs(SongsBean songs) {
        this.songs = songs;
    }

    public int getSong_sum() {
        return song_sum;
    }

    public void setSong_sum(int song_sum) {
        this.song_sum = song_sum;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static class SongsBean {
        private String artist;//歌手
        private String size;//大小
        private String id;
        private String img;//歌手头像
        private String album;//封面图名称
        private String artistPic;//封面图
        private String title;//歌曲名
        private String url;//歌曲url
        private String lrcSize;//歌词大小
        private String imgSize;//图片大小
        private String duration;//时长
        private String lrcUrl;//歌词url
        private String albumPic;//封面图
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public String getArtistPic() {
            return artistPic;
        }

        public void setArtistPic(String artistPic) {
            this.artistPic = artistPic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLrcSize() {
            return lrcSize;
        }

        public void setLrcSize(String lrcSize) {
            this.lrcSize = lrcSize;
        }

        public String getImgSize() {
            return imgSize;
        }

        public void setImgSize(String imgSize) {
            this.imgSize = imgSize;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getLrcUrl() {
            return lrcUrl;
        }

        public void setLrcUrl(String lrcUrl) {
            this.lrcUrl = lrcUrl;
        }

        public String getAlbumPic() {
            return albumPic;
        }

        public void setAlbumPic(String albumPic) {
            this.albumPic = albumPic;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            return "Song{" +
                    "artist='" + artist + '\'' +
                    ", size='" + size + '\'' +
                    ", id='" + id + '\'' +
                    ", img='" + img + '\'' +
                    ", album='" + album + '\'' +
                    ", artistPic='" + artistPic + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", lrcSize='" + lrcSize + '\'' +
                    ", imgSize='" + imgSize + '\'' +
                    ", duration='" + duration + '\'' +
                    ", lrcUrl='" + lrcUrl + '\'' +
                    ", albumPic='" + albumPic + '\'' +
                    ", additionalProperties=" + additionalProperties +
                    '}';
        }
    }
}
