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

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class FeedScheduler {
    private static final Logger logger = LoggerFactory.getLogger(FeedScheduler.class);

    private final FeedDownloader feedDownloader;
    private final FeedContainer feedContainer;
    private final FeedConverter feedConverter;
    private final List<String> rssLinks = new LinkedList<>();

    @Autowired
    public FeedScheduler(final FeedDownloader feedDownloader,
                         final FeedContainer feedContainer,
                         final FeedConverter feedConverter) {
        this.feedDownloader = feedDownloader;
        this.feedContainer = feedContainer;
        this.feedConverter = feedConverter;
        fillRssLinks();
    }

    private void fillRssLinks() {
        rssLinks.add("https://www.rmf24.pl/fakty/polska/feed");
        rssLinks.add("https://www.rmf24.pl/fakty/swiat/feed");
        rssLinks.add("https://www.rmf24.pl/ekonomia/feed");
        rssLinks.add("https://www.rmf24.pl/nauka/feed");
        rssLinks.add("https://www.rmf24.pl/kultura/feed");
        rssLinks.add("https://www.rmf24.pl/sport/feed");
        rssLinks.add("https://www.rmf24.pl/rozrywka/ciekawostki/feed");
    }

    @Scheduled(fixedRate = 60000)
    public void getFeed() {
        final String url = rssLinks.get(new Random().nextInt(rssLinks.size()));
        logger.debug("Collecting feed from {}", url);
        final SyndFeed syndFeed = feedDownloader.download(url);
        final List<FeedModel> feedModelList = feedConverter.convert(syndFeed);
        feedContainer.setFeedList(feedModelList);
    }
}
