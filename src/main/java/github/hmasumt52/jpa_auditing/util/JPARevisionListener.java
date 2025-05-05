package github.hmasumt52.jpa_auditing.util;

import org.hibernate.envers.RevisionListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import github.hmasumt52.jpa_auditing.model.JPARevision;
import jakarta.servlet.http.HttpServletRequest;


public class JPARevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        JPARevision revision = (JPARevision) revisionEntity;
        
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest();

        String modifier = request.getHeader("User-Id");
        if (modifier == null) {
            modifier = "SYSTEM";
        }
        revision.setModifer(modifier);
    }
  
}
