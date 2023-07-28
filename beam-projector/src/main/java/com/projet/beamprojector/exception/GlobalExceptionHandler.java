package com.projet.beamprojector.exception;

import com.projet.beamprojector.comment.exception.CommentErrorResponse;
import com.projet.beamprojector.comment.exception.CommentException;
import com.projet.beamprojector.exception.type.ErrorCode;
import com.projet.beamprojector.member.exception.MemberErrorResponse;
import com.projet.beamprojector.member.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException(
		MethodArgumentNotValidException e) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST,
			e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(
		HttpMessageNotReadableException e) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST,
			"Request Body가 비어 있습니다");
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(MemberException.class)
	public ResponseEntity<?> handleMemberException(MemberException e) {

		return ResponseEntity.badRequest()
			.body(MemberErrorResponse.builder()
				.errorCode(e.getErrorCode())
				.errorMessage(e.getErrorMessage())
				.build());

	}

	@ExceptionHandler(CommentException.class)
	public ResponseEntity<?> handlerPlaceException(CommentException e) {

		return ResponseEntity.badRequest()
			.body(CommentErrorResponse.builder()
				.commentErrorCode(e.getCommentErrorCode())
				.errorMessage(e.getErrorMessage())
				.build());
	}


}
