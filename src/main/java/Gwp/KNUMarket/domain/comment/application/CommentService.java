package Gwp.KNUMarket.domain.comment.application;

import Gwp.KNUMarket.domain.comment.data.dto.req.CommentPostReq;
import Gwp.KNUMarket.domain.comment.data.dto.res.CommentGetRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.naming.NoPermissionException;
import java.util.List;

public interface CommentService {

    ResponseEntity<HttpStatus> post(CommentPostReq commentPostReq, Authentication authentication);
    ResponseEntity<List<CommentGetRes>> get(Integer productId);
    ResponseEntity<HttpStatus> patch(Integer id, String content, Authentication authentication) throws NoPermissionException;
    ResponseEntity<HttpStatus> delete(Integer id, Authentication authentication) throws NoPermissionException;
}
