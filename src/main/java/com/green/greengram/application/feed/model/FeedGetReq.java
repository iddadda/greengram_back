package com.green.greengram.application.feed.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@ToString
public class FeedGetReq {
    @NotNull(message = "page값은 필수입니다.")
    @Positive
    private Integer page;

    @NotNull(message = "row_per_page 값은 필수입니다.")
    @Positive
    @Min(value = 20, message = "20 이상")
    @Max(value = 100, message = "100 이하")
    private Integer rowPerPage;


    //    key값에 대문자 없애고 @ModelAttribute 를 사용하기 위한 생성자
    public FeedGetReq(Integer page, @BindParam("row_per_page") Integer rowPerPage) {
        this.page = page;
        this.rowPerPage = rowPerPage;
    }
}
