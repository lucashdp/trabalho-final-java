package com.javaee.stockmarket.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shopping implements Serializable {

    private static final long serialVersionUID = 1L;

    private Float price;

    private Long buyer_id;

    private Long stock_id;
}
