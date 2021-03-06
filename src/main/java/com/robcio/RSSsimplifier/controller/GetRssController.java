package com.robcio.RSSsimplifier.controller;


import com.robcio.RSSsimplifier.container.FeedContainer;
import com.robcio.RSSsimplifier.model.FeedModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rss")
@Api(value = "/rss", description = "Operations about RSS")
public class GetRssController {

    private final FeedContainer feedContainer;

    @Autowired
    public GetRssController(final FeedContainer feedContainer) {
        this.feedContainer = feedContainer;
    }

    @GetMapping(path = "/")
    @ApiOperation(value = "Returns current RSS feed stored in app")
    public List<FeedModel> getRss() {
        return feedContainer.getFeedList();
    }
}
