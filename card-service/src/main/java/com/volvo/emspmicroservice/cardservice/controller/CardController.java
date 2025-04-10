package com.volvo.emspmicroservice.cardservice.controller;

import com.volvo.emspmicroservice.cardservice.dto.CardDTO;
import com.volvo.emspmicroservice.common.client.AccountClient;
import com.volvo.emspmicroservice.common.dto.AccountDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.common.util.CommonUtil;
import com.volvo.emspmicroservice.cardservice.domain.Card;
import com.volvo.emspmicroservice.common.domain.Result;
import com.volvo.emspmicroservice.cardservice.enumType.CardStatus;
import com.volvo.emspmicroservice.common.query.PageQuery;
import com.volvo.emspmicroservice.cardservice.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@Validated
public class CardController {

    private final CardService cardService;

    private final CommonUtil commonUtil;

    private final AccountClient accountClient;

    /**
     * Create Account API
     */
    @PostMapping("/create")
    public Result createCard(@RequestBody @Valid CardDTO cardDTO) {
        if(cardDTO.getCardNum().length() != 16) {
            throw new RuntimeException("Card number must be 16 characters!");
        }

        Card card = new Card();
        card.setCardNum(cardDTO.getCardNum());
        card.setAccountId(cardDTO.getAccountId());
        card.setContractId(commonUtil.generateRandomEMAID());
        card.setCardStatus(CardStatus.valueOf(cardDTO.getCardStatus()));
        // save card data
        cardService.save(card);

        return Result.of(200, "Card created successfully!");
    }

    /**
     * Assign card to Account API
     */
    @PatchMapping("/assign/{id}")
    public Result assign(@PathVariable int id) {
        Card card = cardService.getById(id);
        if(card == null) {
            throw new RuntimeException("Card not found with id: " + id);
        }
        AccountDTO accountDTO = accountClient.getAccountById(id);

        card.setAccountId(accountDTO.getId());
        card.setCardStatus(CardStatus.valueOf(accountDTO.getAccountStatus()));
        cardService.updateById(card);

        return Result.of(200, "Card assigned successfully!");
    }

    /**
     * Change Card status API
     */
    @PatchMapping("/changeCardStatus/{id}")
    public Result changeCardStatus(@PathVariable int id, @RequestBody @Valid CardDTO cardDTO) {
        Card card = cardService.getById(id);
        if(card == null) {
            throw new RuntimeException("Card not found with id: " + id);
        }
        card.setCardStatus(CardStatus.valueOf(cardDTO.getCardStatus()));
        cardService.updateById(card);

        return Result.of(200, "Card status changed successfully!");
    }

    /**
     * Sort Card data by last_updated field in desc order, and pagination API
     */
    @GetMapping("/queryCardPage")
    public PageDTO<CardDTO> queryCardPage(@RequestBody PageQuery query) {
        return cardService.queryCardPage(query);
    }
}
