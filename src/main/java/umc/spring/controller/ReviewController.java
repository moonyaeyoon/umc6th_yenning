package umc.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.BaseResponse;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.code.status.SuccessStatus;
import umc.spring.dto.request.ReviewRequestDTO;
import umc.spring.dto.request.StoreRequestDTO;
import umc.spring.service.review.ReviewService;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{userId}/{storeId}")
    public BaseResponse<Void> createReview(@PathVariable Long userId, @PathVariable Long storeId, @RequestBody ReviewRequestDTO.CreateReviewDTO createReviewDTO){
        try {
            reviewService.createReview(userId,storeId,createReviewDTO);
            return BaseResponse.of(SuccessStatus.REVIEW_CREATED,null);
        } catch (NoSuchElementException e) {
            return BaseResponse.onFailure(ErrorStatus._BAD_REQUEST.getMessage(), ErrorStatus._BAD_REQUEST.getCode(), null);
        }

    }
}
