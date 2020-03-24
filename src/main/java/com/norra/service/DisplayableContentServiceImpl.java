package com.norra.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.norra.config.DataSource;
import com.norra.constants.Constants;
import com.norra.entities.DisplayableContent;
import com.norra.exceptions.EntityNotFoundException;
import com.norra.repositories.DisplayableContentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DisplayableContentServiceImpl implements DisplayableContentService {

    @Autowired
    private DisplayableContentRepository repository;

    @Override
    public DisplayableContent addNew(DisplayableContent displayableContent) {
        validateSubInfo(displayableContent.getSubInfo());

        return repository.save(displayableContent);
    }

    @Override
    @DataSource(Constants.DB_READ_REPLICA_NAME)
    public DisplayableContent getByKey(String key) {
        DisplayableContent dc = repository.getDisplayableContentByKey(key);

        validateSubInfo(dc.getSubInfo()); // Required for now since there is no validation checcks on db TODO: move to converter

        return dc;
    }

    @Override
    @Transactional
    public List<DisplayableContent> getByKeys(List<String> keys) {
        List<DisplayableContent> dcs = repository.getDisplayableContentByKey(keys.toArray(new String[0]));

        dcs.forEach(d -> validateSubInfo(d.getSubInfo())); // Required for now since there is no validation checcks on db TODO: move to converter

        return dcs;
    }

    /**
     * Validate if all the subinfo keys are present in DB to ensure there isn't any violation TODO: to be handled by converter
     * @param subinfo
     */
    private void validateSubInfo(List<String> subinfo){
        if(subinfo == null || subinfo.size() == 0) return;

        List<DisplayableContent> displayableContentList =
                repository.getDisplayableContentByKey(subinfo.toArray(new String[0]));

        List<String> foundKeys = displayableContentList.stream()
                .map(DisplayableContent::getKey)
                .collect(Collectors.toList());

        List<String> notFoundStrings =
                subinfo.stream()
                        .filter(d -> !foundKeys.contains(d))
                        .collect(Collectors.toList());

        if(notFoundStrings.size() > 0){
            throw new EntityNotFoundException("key", notFoundStrings.toArray(new String[0]));
        }
    }
}
