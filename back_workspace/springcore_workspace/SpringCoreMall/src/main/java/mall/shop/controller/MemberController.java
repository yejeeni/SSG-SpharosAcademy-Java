package mall.shop.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Member;
import mall.exception.MemberException;
import mall.exception.MemberNotFoundException;
import mall.exception.PasswordEncryptException;
import mall.service.MemberService;
import mall.service.SnsProviderService;

@Slf4j
@Controller
public class MemberController {

	@Autowired
	@Qualifier("googleAuth20Service")
	private OAuth20Service googleAuth20Service;
	@Autowired
	@Qualifier("naverAuth20Service")
	private OAuth20Service naverAuth20Service;
	@Autowired
	@Qualifier("kakaoAuth20Service")
	private OAuth20Service kakaoAuth20Service;
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private SnsProviderService snsProviderService;

	/**
	 * 회원가입 폼 처리
	 * @return
	 */
	@GetMapping("/member/registform")
	public String getRegistForm() {
		return "shop/member/join";
	}
	
	/**
	 * 회원가입 요청 처리
	 * @param member
	 * @return
	 */
	@PostMapping("/member/regist")
	public String regist(Member member) {
		log.debug("id={}, pw={}, name={}, email={}", member.getId(), member.getPassword(), member.getName(), member.getEmail());
		memberService.regist(member);
		return "redirect:/shop/member/loginform";
	}
	
	/**
	 * 홈페이지 로그인 요청 처리
	 */
	@PostMapping("/member/login")
	public String homeLogin(Member member, HttpSession session) {
		Member loginMember = memberService.login(member);
		session.setAttribute("member", loginMember);
		return "redirect:/shop";
	}
	
	
	/**
	 * 로그인 폼 처리
	 */
	@GetMapping("/member/loginform")
	public String getForm() {
		return "shop/member/login";
	}
	
	/**
	 * 로그아웃 요청 처리
	 */
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		// 세션 무효화
		session.invalidate();
		return "redirect:/shop";
	}

	/**
	 * [구글] 로그인 인증 동의 화면 요청 처리
	 * 
	 * @return
	 */
	@GetMapping("/member/google/authurl")
	@ResponseBody
	public String getGoogleAuthUrl() {
		return googleAuth20Service.getAuthorizationUrl();
	}

	/**
	 * [구글] 등록해둔 콜백 주소로 전송되는 콜백 요청 처리
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@GetMapping("/callback/sns/google")
	public String googleCallback(@RequestParam("code") String code, HttpSession session)
			throws IOException, InterruptedException, ExecutionException {

		// OAuth20Service 빈 등록 시 이미 ClientId, ClientSecret을 등록해놓았으므로, 토큰 요청 시
		// Authrozation Code만 추가하면 됨
		OAuth2AccessToken accessToken = googleAuth20Service.getAccessToken(code);
		log.debug("google 권한 코드 " + code);
		log.debug("access tocken " + accessToken);

		// 토큰을 받았다면 언제든 Resource Owner의 개인정보에 접근 가능
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v2/userinfo");
		googleAuth20Service.signRequest(accessToken, request); // 요청 시 사용할 토큰 지정
		Response response = googleAuth20Service.execute(request); // 사용자 정보 요청 시도

		// json 파싱
		// 기존 코드
		JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();

		// 필요한 개인정보를 key값으로 조회하여 가져오기
		String email = json.get("email").getAsString();
		log.debug("email " + email);

		String name = json.get("name").getAsString();
		log.debug("name " + name);

		String openid = json.get("id").getAsString(); // sns provider가 회원을 구분하는 내부 식별 아이디
		log.debug("sns id " + openid);

		// 로그인 멤버 객체 생성
		Member member = null;
		member = memberService.selectById(openid);
		
		if (member == null) {
			// 없으면 가입 후 로그인

			// 회원 등록
			member = new Member();

			member.setSnsProvider(snsProviderService.selectByName("google"));
			member.setId(openid);
			member.setEmail(email);
			member.setName(name);

			memberService.regist(member);

		}
		session.setAttribute("member", member); // 세션에 member를 추가

		return "redirect:/shop/product/list";
	}
	
	/**
	 * [네이버] 로그인 인증 동의 화면 요청 처리
	 * 
	 * @return
	 */
	@GetMapping("/member/naver/authurl")
	@ResponseBody
	public String getNaverAuthUrl() {
		return naverAuth20Service.getAuthorizationUrl();
	}

	/**
	 * [네이버] 등록해둔 콜백 주소로 전송되는 콜백 요청 처리
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 * 
	 */
	@GetMapping("/callback/sns/naver")
	public String naverCallback(@RequestParam("code") String code, String status, HttpSession session) throws IOException, InterruptedException, ExecutionException {

		// IDP가 제공한 clientId, clientSecret(빈 등록 시 이미 등록됨)을 조합하여 토큰 요청
		OAuth2AccessToken accessToken = naverAuth20Service.getAccessToken(code);
		log.debug("naver 권한 코드 {}", code);
		log.debug("access tocken {}", accessToken);
		
		// 발급받은 토큰으로 회원 정보 조회
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://openapi.naver.com/v1/nid/me");
		naverAuth20Service.signRequest(accessToken, request);
		Response response = naverAuth20Service.execute(request); // 요청 결과
		// 결과를 json으로 파싱
		JsonObject responseJson = JsonParser.parseString(response.getBody()).getAsJsonObject();
		log.debug("responseJson {}", responseJson);
		
		JsonObject userJson = responseJson.getAsJsonObject("response");
		String id = userJson.get("id").getAsString();
		String email = userJson.get("email").getAsString();
		String name = userJson.get("name").getAsString();
		
		// 로그인 멤버 객체 생성
		Member member = null;
		// id로 로그인 시도
		member = memberService.selectById(id);
		
		// 정보가 없으면 가입 후 로그인
		if (member == null) {
			// 멤버 객체 생성
			member = new Member();

			member.setSnsProvider(snsProviderService.selectByName("naver"));
			member.setId(id);
			member.setEmail(email);
			member.setName(name);

			// 회원가입
			memberService.regist(member);
		}
		session.setAttribute("member", member); // 세션에 member를 추가

		return "redirect:/shop";
	}
	
	/**
	 * [카카오] 로그인 인증 동의 화면 요청 처리
	 * 
	 * @return
	 */
	@GetMapping("/member/kakao/authurl")
	@ResponseBody
	public String getKakaoAuthUrl() {
		return kakaoAuth20Service.getAuthorizationUrl();
	}

	/**
	 * [카카오] 등록해둔 콜백 주소로 전송되는 콜백 요청 처리
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 * 
	 */
	@GetMapping("/callback/sns/kakao")
	public String kakaoCallback(@RequestParam("code") String code, HttpSession session) throws IOException, InterruptedException, ExecutionException {

		log.debug("kakaoAuth20Service 확인: {}", kakaoAuth20Service);
		// IDP가 제공한 clientId, clientSecret(빈 등록 시 이미 등록됨)을 조합하여 토큰 요청
		OAuth2AccessToken accessToken = kakaoAuth20Service.getAccessToken(code);
		log.debug("kakao 권한 코드 {}", code);
		log.debug("access tocken {}", accessToken);
		
		// 발급받은 토큰으로 회원 정보 조회
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://kapi.kakao.com/v2/user/me");
		kakaoAuth20Service.signRequest(accessToken, request);
		Response response = kakaoAuth20Service.execute(request); // 요청 결과
		// 결과를 json으로 파싱
		JsonObject responseJson = JsonParser.parseString(response.getBody()).getAsJsonObject();
		log.debug("responseJson {}", responseJson);
		
		JsonObject userJson = responseJson.getAsJsonObject("response");
		String id = userJson.get("id").getAsString();
		String profile_image = userJson.get("profile_image").getAsString();
		String profile_nickname = userJson.get("profile_nickname").getAsString();
		
		// 로그인 멤버 객체 생성
		Member member = null;
		// id로 로그인 시도
		member = memberService.selectById(id);
		
		// 정보가 없으면 가입 후 로그인
		if (member == null) {
			// 멤버 객체 생성
			member = new Member();

			member.setSnsProvider(snsProviderService.selectByName("kakao"));
			member.setId(id);
//			member.setEmail(email);
			member.setName(profile_nickname);

			// 회원가입
			memberService.regist(member);
		}
		session.setAttribute("member", member); // 세션에 member를 추가

		return "redirect:/shop";
	}
	
	/*
	 *  현재 컨트롤러의 메서드에서 발생하는 예외에 대한 처리
	 */
	@ExceptionHandler(PasswordEncryptException.class)
	public ModelAndView handle(PasswordEncryptException e) {
		ModelAndView modelAndView = new ModelAndView("shop/error/result");
		
		modelAndView.addObject("msg", "회원 가입 실패");
		modelAndView.addObject("e", e);
		return modelAndView;
	}
	
	@ExceptionHandler({MemberNotFoundException.class, MemberException.class})
	public ModelAndView handle(Exception e) {
		ModelAndView modelAndView = new ModelAndView("shop/error/result");
		
		modelAndView.addObject("msg", e.getMessage());
		modelAndView.addObject("e", e);
		return modelAndView;
	}
}
