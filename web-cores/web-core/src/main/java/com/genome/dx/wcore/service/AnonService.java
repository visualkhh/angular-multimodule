package com.genome.dx.wcore.service;

import com.genome.dx.core.repository.CodeRepository;
import com.genome.dx.core.repository.UrlRepository;
import com.omnicns.web.spring.message.CustomReloadableResourceBundleMessageSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class AnonService {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private CustomReloadableResourceBundleMessageSource customReloadableResourceBundleMessageSource;
    public Map<String, String> i18n(String lang) {
        Map<String, String> map =  customReloadableResourceBundleMessageSource.getPropertiesMap();
        Map<String, String> rmap = new LinkedHashMap<>();
        rmap.putAll(map);

        if ("ko_KR".equals(lang) || "ko-KR".equals(lang)) {
            codeRepository.findAll(new Sort(Sort.Direction.ASC, "cdOrd")).stream().forEach(it -> rmap.put(it.getCd(), it.getCdNm()));
            urlRepository.findAll(new Sort(Sort.Direction.ASC, "urlSeq")).stream().forEach(it -> rmap.put(it.getI18nCd(), it.getMenuNm()));
        } else { //("en_US".equals(lang))
            codeRepository.findAll(new Sort(Sort.Direction.ASC, "cdOrd")).stream().forEach(it -> rmap.put(it.getCd(), it.getCdNmEn()));
            urlRepository.findAll(new Sort(Sort.Direction.ASC, "urlSeq")).stream().forEach(it -> rmap.put(it.getI18nCd(), it.getMenuNmEn()));
        }

        return rmap;
    }

}
