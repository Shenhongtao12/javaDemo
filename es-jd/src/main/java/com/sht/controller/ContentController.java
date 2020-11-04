package com.sht.controller;

import com.sht.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Aaron
 * @date 2020/11/2 23:37
 */
@RestController
@RequestMapping("api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/{keyword}")
    public ResponseEntity<Boolean> parse(@PathVariable("keyword") String keyword) throws Exception {
        return ResponseEntity.ok(contentService.parseContent(keyword));
    }

    @GetMapping("findByPage")
    public ResponseEntity<List<Map<String, Object>>> findByPage(@RequestParam("keyword") String keyword,
                                                                @RequestParam("page") int page,
                                                                @RequestParam("size") int size) throws IOException {

        return ResponseEntity.ok(contentService.findByPage(page, size, keyword));
    }
}
