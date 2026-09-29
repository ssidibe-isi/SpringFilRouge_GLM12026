package com.groupeisi.HelloSpring.filters;


import com.groupeisi.HelloSpring.entities.Audit;
import com.groupeisi.HelloSpring.repositories.AuditRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class AuditFilter extends OncePerRequestFilter {

    private final AuditRepository auditRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        //pretraitement
        logger.info("####### AuditFilter ###########");
        String uri=request.getRequestURI();
        String ip=request.getRemoteAddr();
        String method= request.getMethod();
        String queryParam=request.getQueryString();

        Audit audit=Audit.builder()
                .uri(uri)
                .ip(ip)
                .method(method)
                .queryParam(queryParam)
                .build();
        auditRepository.save(audit);

        Date debut=new Date();
        filterChain.doFilter(request, response);
        logger.info("postraitemnt");
        Date fin=new Date();
        long nbMilis=fin.getTime()-debut.getTime();

        int status= response.getStatus();
        audit.setStatus(status);
        audit.setTempsRequete(nbMilis);
        auditRepository.save(audit);

    }
}
