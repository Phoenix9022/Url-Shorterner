package com.example.demo.controller;

import com.example.demo.model.Url;
import com.example.demo.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/api/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("originalUrl");
        if (originalUrl == null || originalUrl.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "URL cannot be empty"));
        }

        if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
            originalUrl = "http://" + originalUrl;
        }

        Url shortUrl = urlService.generateShortLink(originalUrl);
        return ResponseEntity.ok(Map.of("shortLink", shortUrl.getShortLink()));
    }

    @GetMapping("/{shortLink:[a-zA-Z0-9-]{8}}")
    public void redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws IOException {
        Url urlToRet = urlService.getEncodedUrl(shortLink);

        if (urlToRet != null) {
            response.sendRedirect(urlToRet.getOriginalUrl());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "URL not found or expired");
        }
    }
}
