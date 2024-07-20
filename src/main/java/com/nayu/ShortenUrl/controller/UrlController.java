package com.nayu.ShortenUrl.controller;

import com.nayu.ShortenUrl.domain.Url;
import com.nayu.ShortenUrl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Controller
public class UrlController {

    private final UrlService urlService;
    private static final String DOMAIN = "http://localhost:8080/";

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // URL을 SHA-256으로 해싱
    public static byte[] hashSHA256(String url) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(url.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // 해시 값을 Base62로 인코딩하고 첫 8자리 반환
    public static String encodeToBase62(String url) {
        byte[] hashBytes = hashSHA256(url);
        System.out.println("hashBytes : " + hashBytes.length);
        String base62Encoded = Base62.encode(hashBytes);
        System.out.println("base : " + base62Encoded);
        // 결과 문자열을 8자리로 제한
        return base62Encoded.length() > 8 ? base62Encoded.substring(0, 8) : base62Encoded;
    }

    // 긴 URL을 짧은 URL로 변환
    @PostMapping("/makeUrl")
    public String makeUrl(String url, Model model) {
        String shortUrl = encodeToBase62(url);
        System.out.println("shortUrl : " + shortUrl);
        Url urlObj = new Url();
        urlObj.setOriUrl(url);
        urlObj.setShtUrl(shortUrl);

        // DB에 저장
        urlService.save(urlObj);
        model.addAttribute("shortUrl", DOMAIN + shortUrl);
        return "home";  // 결과를 보여줄 뷰의 이름
    }

    @GetMapping("/{shtUrl}")
    public RedirectView redirectToUrl(@PathVariable String shtUrl) {
        String originalUrl = urlService.getOriUrl(shtUrl);

        if(originalUrl != null) {
            return new RedirectView(originalUrl);
        } else {
            return new RedirectView("/error");
        }
    }
}
