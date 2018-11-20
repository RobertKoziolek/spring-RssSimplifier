package com.robcio.RSSsimplifier.util;

import com.robcio.RSSsimplifier.model.FeedModel;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

@Component
public class FeedConverter {

    private final HtmlExtractor htmlExtractor;

    @Autowired
    public FeedConverter(final HtmlExtractor htmlExtractor) {
        this.htmlExtractor = htmlExtractor;
    }

    public List<FeedModel> convert(final SyndFeed syndFeed) {
        final LinkedList<FeedModel> feedList = new LinkedList<>();
        for (final SyndEntry entry : syndFeed.getEntries()) {
            final String entryTitle = entry.getTitle();
            if (StringUtils.isEmpty(entryTitle)) {
                continue;
            }
            final String title = entryTitle.replace("\n", "");
            final String description = entry.getDescription()
                                            .getValue();
            final String imgUrl = htmlExtractor.extractImg(description);
            final String text = htmlExtractor.text(description);
            final FeedModel feedModel = new FeedModel(title, entry.getLink(), imgUrl, text);
            feedList.add(feedModel);
        }
        return feedList;
    }
}
