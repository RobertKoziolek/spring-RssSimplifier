package com.robcio.RSSsimplifier.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HtmlExtractor {

    public String extractImg(final String htmlContent) {
        final Document document = Jsoup.parse(htmlContent);
        final Element img = document.select("img")
                                    .first();
        return Objects.isNull(img) ? null : img.attr("src");
    }

    public String text(final String htmlContent) {
        return Jsoup.parse(htmlContent)
                    .text();
    }

}
