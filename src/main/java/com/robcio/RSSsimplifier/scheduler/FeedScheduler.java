package com.robcio.RSSsimplifier.scheduler;

import com.robcio.RSSsimplifier.container.FeedContainer;
import com.robcio.RSSsimplifier.model.FeedModel;
import com.robcio.RSSsimplifier.util.FeedConverter;
import com.robcio.RSSsimplifier.util.FeedDownloader;
import com.rometools.rome.feed.synd.SyndFeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedScheduler {
    private static final Logger logger = LoggerFactory.getLogger(FeedScheduler.class);

    private final FeedDownloader feedDownloader;
    private final FeedContainer feedContainer;
    private final FeedConverter feedConverter;

    @Autowired
    public FeedScheduler(final FeedDownloader feedDownloader,
                         final FeedContainer feedContainer,
                         final FeedConverter feedConverter) {
        this.feedDownloader = feedDownloader;
        this.feedContainer = feedContainer;
        this.feedConverter = feedConverter;
    }

    @Scheduled(fixedRate = 60000)
    public void getFeed() {
//        add("RMF Kraj", "https://www.rmf24.pl/fakty/polska/feed");
//        add("RMF Świat", "https://www.rmf24.pl/fakty/swiat/feed");
//        add("RMF Ekonomia", "https://www.rmf24.pl/ekonomia/feed");
//        add("TVN24 Najważniejsze", "https://www.tvn24.pl/najwazniejsze.xml");

        final String url = "https://www.rmf24.pl/fakty/polska/feed";
        logger.debug("Collecting feed from {}", url);
        final SyndFeed syndFeed = feedDownloader.download(url);
        final List<FeedModel> feedModelList = feedConverter.convert(syndFeed);
        feedContainer.setFeedList(feedModelList);
    }
}
