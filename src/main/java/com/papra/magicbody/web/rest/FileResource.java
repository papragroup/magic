package com.papra.magicbody.web.rest;


import com.papra.magicbody.service.MinioServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/url-generate")
public class FileResource {

    @Autowired
    private MinioServiceUtil minioServiceUtil;
    @GetMapping("/{new-url}")
    public Resource resource(@PathVariable(name = "new-url") String fileName){
        return new Resource(minioServiceUtil.getLink(fileName)) ;
    }



    public static class Resource{
        private String newUrl;

        public Resource(String newUrl) {
            this.newUrl = newUrl;
        }

        public String getNewUrl() {
            return newUrl;
        }

        public Resource setNewUrl(String newUrl) {
            this.newUrl = newUrl;
            return this;
        }
    }
}
