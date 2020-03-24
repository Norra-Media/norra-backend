package com.norra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.norra.entities.DisplayableContent;
import com.norra.enums.DisplyableContentCategory;

import java.util.List;

@Repository
public interface DisplayableContentRepository extends JpaRepository<DisplayableContent, Long> {

    /**
     * Get Displayable Content by key
     *
     * @param key
     * @return
     */
    @Query("SELECT dpc FROM DisplayableContent dpc WHERE dpc.key = ?1")
    DisplayableContent getDisplayableContentByKey(String key);

    /**
     * Get multiple Displayable Content by keys
     *
     * @param key
     * @return
     */
    @Query("SELECT dpc FROM DisplayableContent dpc WHERE dpc.key in (?1)")
    List<DisplayableContent> getDisplayableContentByKey(String[] key);

    /**
     * Get multiple Displayable Content by category
     *
     * @param category
     * @return
     */
    @Query("SELECT dpc FROM DisplayableContent dpc WHERE dpc.category = ?1")
    List<DisplayableContent> getDisplayableContentByCategory(DisplyableContentCategory category);
}
