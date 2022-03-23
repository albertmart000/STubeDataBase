package com.example.stubedatabase;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class STubeDataBaseAPIRestController {

    private STubeDataBaseService sTubeDataBaseService;

    public STubeDataBaseAPIRestController(STubeDataBaseService sTubeDataBaseService) {
        this.sTubeDataBaseService = sTubeDataBaseService;
    }
}
