package com.nayu.ShortenUrl.service;

import com.nayu.ShortenUrl.domain.Url;
import com.nayu.ShortenUrl.repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UrlService {

    private UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public void save(Url urlObj) {
        //이미 저장된 데이터인지 체크하고 없을때만 insert
        if(!isSaved(urlObj)){
            urlRepository.save(urlObj);
        }
    }

    private boolean isSaved(Url urlObj) {
        return urlRepository.findByOriUrl(urlObj.getOriUrl())!=null;
    }

    public String getOriUrl(String shtUrl) {
        Url urlEntity = urlRepository.findByShtUrl(shtUrl);
        return (urlEntity != null) ? urlEntity.getOriUrl() : null;
    }
}
