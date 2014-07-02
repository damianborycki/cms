package com.portal.entity;

import java.util.List;

public class GalleryMetadata {

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    private String description;
    private List<Tag> tags;
}
