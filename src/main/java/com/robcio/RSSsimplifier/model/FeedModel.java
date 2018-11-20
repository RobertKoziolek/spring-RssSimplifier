package com.robcio.RSSsimplifier.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeedModel {

    private final String title;
    private final String link;
    private final String imgUrl;
    private final String content;
}
