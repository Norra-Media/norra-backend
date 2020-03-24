package com.norra.service;

import java.util.List;

import com.norra.entities.DisplayableContent;

public interface DisplayableContentService {

    /**
     * Add new displayable content
     * @param displayableContent
     * @return
     */
    DisplayableContent addNew(DisplayableContent displayableContent);

    /**
     * Get displayable content by key
     * @param key
     * @return
     */
    DisplayableContent getByKey(String key);

    /**
     * Get displayable contents by keys
     * @param keys
     * @return
     */
    List<DisplayableContent> getByKeys(List<String> keys);

}
