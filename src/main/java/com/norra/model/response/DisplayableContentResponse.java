package com.norra.model.response;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import com.norra.entities.DisplayableContent;
import com.norra.service.DisplayableContentService;

import java.util.ArrayList;
import java.util.List;

@Data
public class DisplayableContentResponse extends DisplayableContent {

    private List<DisplayableContent> subInfoDetails = new ArrayList<>();

    public DisplayableContentResponse(DisplayableContent displayableContent, DisplayableContentService service) {
        BeanUtils.copyProperties(displayableContent, this);

        if (subInfo != null && subInfo.size() > 0)
            this.subInfoDetails = service.getByKeys(subInfo);
    }

}
