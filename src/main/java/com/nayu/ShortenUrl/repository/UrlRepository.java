package com.nayu.ShortenUrl.repository;

import com.nayu.ShortenUrl.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Url findByOriUrl(String originalUrl);
    Url findByShtUrl(String shortUrl);

//    private final EntityManager em;
//    public UrlRepository(EntityManager em) {
//        this.em = em;
//    }
//
//    public void save(Url urlObj) {
//        em.persist(urlObj);
//    }
//
//    public boolean findByOriUrl(String oriUrl) {
//    }
}
