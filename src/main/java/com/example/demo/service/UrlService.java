package com.example.demo.service;

import com.example.demo.model.Url;
import com.example.demo.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url generateShortLink(String originalUrl) {
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortLink(getUniqueShortLink());
        url.setCreationDate(LocalDateTime.now());
        // Link expires in 30 days
        url.setExpirationDate(LocalDateTime.now().plusDays(30));
        return urlRepository.save(url);
    }

    public Url getEncodedUrl(String shortLink) {
        Optional<Url> optionalUrl = urlRepository.findByShortLink(shortLink);
        return optionalUrl.orElse(null);
    }

    private String getUniqueShortLink() {
        String shortLink = "";
        boolean isUnique = false;
        while (!isUnique) {
            shortLink = UUID.randomUUID().toString().substring(0, 8);
            if (urlRepository.findByShortLink(shortLink).isEmpty()) {
                isUnique = true;
            }
        }
        return shortLink;
    }
}
