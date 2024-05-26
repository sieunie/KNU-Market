package Gwp.KNUMarket.domain.evaluation.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "평가")
@RequestMapping("/api/evaluation")
public class EvaluationController {
}
