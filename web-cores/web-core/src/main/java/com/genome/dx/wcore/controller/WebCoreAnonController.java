package com.genome.dx.wcore.controller;

import com.genome.dx.wcore.config.security.WebSecurityConfigurerAdapter;
import com.genome.dx.wcore.domain.security.UserDetails;
import com.genome.dx.wcore.service.AnonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(WebCoreAnonController.URI_PREFIX)
public class WebCoreAnonController {

    public static final String URI_PREFIX 		        = WebSecurityConfigurerAdapter.ANON_PATH+"-web-core";
    public static final String LANG_CHANGE_URI          = "/langs";
    public static final String LANG_CHANGE_PARAM_NAME   = "lang";
//    @Autowired private CustomReloadableResourceBundleMessageSource customReloadableResourceBundleMessageSource;
//    @Autowired private CodeRepository codeRepository;
    @Autowired
    private AnonService anonService;

    @GetMapping(value={"/",""})
    public SecurityContext context(){
        SecurityContext context = SecurityContextHolder.getContext();
        return context;
    }

    @GetMapping(value="/userDetails")
    public Object detail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getDetails();
        if(null!=details && UserDetails.class.isAssignableFrom(details.getClass())) {
            ((UserDetails)details).setAdmLginPw(null);
        }
        return details;
    }

    //인터셉터에서 lang으로 들어오면 자동으로 변경해준다.
    @GetMapping(value=LANG_CHANGE_URI)
    public Map<String, String> i18n(@RequestParam(LANG_CHANGE_PARAM_NAME) String lang){
//
//        Map<String, String> map =  customReloadableResourceBundleMessageSource.getPropertiesMap();
//        Map<String, String> rmap = new LinkedHashMap<>();
//        rmap.putAll(map);
//        codeRepository.findAll(new Sort(Sort.Direction.ASC, "cdOrd")).stream().forEach(it -> rmap.put(it.getCd(), it.getCdNm()));
//        if ("ko_KR".equals(lang)) {
//            codeRepository.findAll().stream().forEach(it -> map.put(it.getCd(), it.getCdNm()));
//        } else if ("zh_CN".equals(lang)) {
//            codeRepository.findAll().stream().forEach(it -> map.put(it.getCd(), it.getCdNmZh()));
//        } else { //("en_US".equals(lang))
//            codeRepository.findAll().stream().forEach(it -> map.put(it.getCd(), it.getCdNmEn()));
//        }
//        return customPropertiesPersistor.getData();
////        MessageSourceAware
//        MessageResolveService
//        originMessageSource.getParentMessageSource().
//
//        messageSource.getMessage("");
//        adminService.pulseLginFailCnt(1);
//        return rmap;
        return anonService.i18n(lang);
    }

}
