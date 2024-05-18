package Gwp.KNUMarket.domain.comment.application;

import Gwp.KNUMarket.domain.comment.data.dto.req.CommentPostReq;
import Gwp.KNUMarket.domain.comment.data.dto.res.CommentGetRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CommentService {

    ResponseEntity<HttpStatus> post(CommentPostReq commentPostReq, Authentication authentication);
    ResponseEntity<List<CommentGetRes>> get(Integer productId);
}
