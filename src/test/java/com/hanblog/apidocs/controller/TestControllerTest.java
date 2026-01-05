package com.hanblog.apidocs.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hanblog.apidocs.TestDto.TestDto;
import com.hanblog.apidocs.docs.BaseDocumentTest;

@WebMvcTest(controllers = {TestController.class})
class TestControllerTest extends BaseDocumentTest {

	TestDto response = new TestDto(1L, "Test Title", "Test Content");

	@Test
	@DisplayName("Get() 으로 Test 가져오기")
	void getTest() throws Exception {
		this.mockMvc.perform(
				get("/test/{id}", 1L)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk())
			.andDo(document("get-test",
				resource(
					builder()
						.tag("Test API")
						.summary("테스트 조회")
						.description("ID로 테스트 데이터를 조회한다")
						.pathParameters(
							parameterWithName("id").description("테스트 ID")
						)
						.responseFields(
							fieldWithPath("id").description("ID"),
							fieldWithPath("title").description("제목"),
							fieldWithPath("content").description("내용")
						)
						.build()
				)
			));
	}

	@Test
	void postTest() throws Exception {

		this.mockMvc.perform(
				post("/test")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(createJson(response))
			)
			.andExpect(status().isOk())
			.andDo(document("post-test",
				resource(
					builder()
						.tag("Test API")
						.summary("테스트 생성")
						.description("테스트 데이터를 생성한다")
						.requestFields(
							fieldWithPath("id").description("ID"),
							fieldWithPath("title").description("제목"),
							fieldWithPath("content").description("내용")
						)
						.responseFields(
							fieldWithPath("id").description("ID"),
							fieldWithPath("title").description("제목"),
							fieldWithPath("content").description("내용")
						)
						.build()
				)
			));
	}
}