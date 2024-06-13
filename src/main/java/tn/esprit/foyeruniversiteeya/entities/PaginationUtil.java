package tn.esprit.foyeruniversiteeya.entities;

import jakarta.annotation.Nonnull;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import java.net.URI;

public class PaginationUtil {
    public static final String PAGE = "?page=";
    public static final String SIZE = "&size=";

    public static HttpHeaders generatePaginationHttpHeaders(@NonNull Page<?> page, @Nonnull String baseUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + page.getTotalElements());
        String link="";
        if ((page.getNumber()+1)<page.getTotalPages()){
            link="<" +(URI.create(baseUrl + PAGE + (page.getNumber()+1) + SIZE + page.getSize()))+">; rel=\"prev\",";
        }
//prev link
        if((page.getNumber())>0){
            link +="<"+(URI.create(baseUrl + PAGE + (page.getNumber() -1) + SIZE + page.getSize()))+">; rel=\"prev\",";
        }
        //last and first link
        int lastPage=0;
        if(page.getTotalPages() >0 ){
            lastPage=page.getTotalPages() -1;
        }
        link += "<" + (URI.create(baseUrl + PAGE + lastPage + SIZE + page.getSize())) +">; rel=\"last\",";
        link += "<" + (URI.create(baseUrl + PAGE + 0 + SIZE + page.getSize())) +">; rel=\"first\"";
        headers.add(HttpHeaders.LINK,link);
        return headers;
    }
    private  PaginationUtil(){super();}
}
