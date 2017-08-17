
package com.tonychen.trainingapp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Music {

    private Integer pageIndex;
    private List<Song> songs ;//播放列表
    private Integer songSum;//列表数量
    private String domain;//领域
    private Integer count;//列表数量
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Integer getSongSum() {
        return songSum;
    }

    public void setSongSum(Integer songSum) {
        this.songSum = songSum;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Music{" +
                "pageIndex=" + pageIndex +
                ", songs=" + songs +
                ", songSum=" + songSum +
                ", domain='" + domain + '\'' +
                ", count=" + count +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
