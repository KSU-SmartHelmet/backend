package com.example.SmartHelmet.controller;

import com.example.SmartHelmet.dto.User;
import com.example.SmartHelmet.repository.UserRepository;
import com.example.SmartHelmet.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public Controller(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // 회원가입
    @PostMapping("/signUp")
    public ResponseEntity<String> signup(@RequestBody User user) {
        if (userRepository.findByUserId(user.getUserId()) != null)
            return ResponseEntity.badRequest().body("이미 존재하는 사용자입니다.");
        userRepository.save(user);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인
    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody User loginUser) {
        User user = userRepository.findByUserId(loginUser.getUserId());
        if (user == null || !user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.status(401).body("로그인 실패: 아이디 또는 비밀번호 오류");
        }
        String token = jwtUtil.generateToken(user.getNickName());
        return ResponseEntity.ok(token);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        // 실질적 처리는 프론트에서 → 클라이언트 토큰 삭제
        return ResponseEntity.ok("로그아웃 성공 (토큰 삭제하세요)");
    }

    // 로그인 성공 후 홈 페이지
    @GetMapping("/home")
    public ResponseEntity<String> home(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            return ResponseEntity.status(401).body("토큰이 없습니다");

        String token = authHeader.substring(7);
        String nickName = jwtUtil.validateAndGetUsername(token);
        if (nickName == null)
            return ResponseEntity.status(401).body("유효하지 않은 토큰");

        return ResponseEntity.ok("홈입니다, " + nickName + "님!");
    }
}
