package com.green.greengram.application.feedComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/feed/comment")
public class FeedCommentController {
    private final FeedCommentService feedCommentService;
}
