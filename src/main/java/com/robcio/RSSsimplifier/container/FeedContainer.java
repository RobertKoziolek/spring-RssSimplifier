package com.robcio.RSSsimplifier.container;

import com.robcio.RSSsimplifier.model.FeedModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class FeedContainer {

    @Getter
    @Setter
    private List<FeedModel> feedList;

    @Autowired
    public FeedContainer(){
        this.feedList = new LinkedList<>();
    }
}
