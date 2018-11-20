package com.robcio.RSSsimplifier.util;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class FeedDownloader {
    private static final Logger logger = LoggerFactory.getLogger(FeedDownloader.class);

    public SyndFeed download(final String url) {
        final SyndFeedInput input = new SyndFeedInput();
        try {
            return input.build(new XmlReader(new URL(url)));
        } catch (final FeedException e) {
            throw new RuntimeException(String.format("Could not build feed with URL: %s", url));
        } catch (final MalformedURLException e) {
            throw new RuntimeException(String.format("Could not build URL with string: %s", url));
        } catch (final IOException e) {
            throw new RuntimeException(String.format("Could not read XML from url: %s", url));
        }
    }
}
