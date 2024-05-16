package Gwp.KNUMarket.domain.comment.application;

import Gwp.KNUMarket.domain.comment.data.dto.req.CommentPostReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface CommentService {

    ResponseEntity<HttpStatus> post(CommentPostReq commentPostReq, Authentication authentication);
}
