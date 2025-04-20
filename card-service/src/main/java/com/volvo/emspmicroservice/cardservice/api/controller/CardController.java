package com.volvo.emspmicroservice.cardservice.api.controller;

import com.volvo.emspmicroservice.cardservice.api.request.AssignCardRequest;
import com.volvo.emspmicroservice.cardservice.api.request.ChangeCardStatusRequest;
import com.volvo.emspmicroservice.cardservice.api.request.CreatCardRequest;
import com.volvo.emspmicroservice.cardservice.api.response.ApiResponse;
import com.volvo.emspmicroservice.cardservice.domain.entity.Card;
import com.volvo.emspmicroservice.cardservice.service.CardService;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.common.query.PageQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@Validated
public class CardController {

    private final CardService cardService;

    /**
     * Create Account API
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createCard(@RequestBody @Valid CreatCardRequest creatCardRequest) {
        Card card = cardService.createCard(creatCardRequest);

        return ApiResponse.success(card);
    }

    /**
     * Assign card to Account API
     */
    @PatchMapping("/allocation")
    public ApiResponse assign(@RequestBody @Valid AssignCardRequest acr) {
        Card card = cardService.assign(acr);

        return ApiResponse.success(card);
    }

    /**
     * Change Card status API
     */
    @PatchMapping("/status")
    public ApiResponse changeCardStatus(@RequestBody @Valid ChangeCardStatusRequest ccsr) {
        Card card = cardService.changeCardStatus(ccsr);

        return ApiResponse.success(card);
    }

    /**
     * Sort Card data by last_updated field in desc order, and pagination API
     */
    @GetMapping("/search")
    public PageDTO<Card> queryCardPage(@RequestBody PageQuery query) {
        return cardService.queryCardPage(query);
    }
}
