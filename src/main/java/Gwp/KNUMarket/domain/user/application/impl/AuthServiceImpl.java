package Gwp.KNUMarket.domain.user.application.impl;

import Gwp.KNUMarket.domain.user.application.AuthService;
import Gwp.KNUMarket.domain.user.data.dto.res.AuthLoginRes;
import Gwp.KNUMarket.global.config.jwt.JwtTokenProvider;
import Gwp.KNUMarket.global.data.entity.User;
import Gwp.KNUMarket.global.repository.UserRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Value("${kakao.auth.client_id}")
    private String clientId;
    @Value("${kakao.auth.redirect_uri}")
    private String redirectUri;
    private String accessTokenReqUrl = "https://kauth.kakao.com/oauth/token";
    private String jsonReqUrl = "https://kapi.kakao.com/v2/user/me";
    private List<String> prefixes = Arrays.asList("멋진", "착한", "귀여운", "똑똑한", "잠자는", "돈많은", "상냥한", "강한", "쩌는", "힙한");

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public ResponseEntity<AuthLoginRes> getLogin(String code) throws IOException {

        String kakaoAccessToken = getKakaoAccessToken(code);

        Integer kakaoId = getKakaoId(kakaoAccessToken);

        Optional<User> optionalUser = userRepository.findByKakaoId(kakaoId);

        if (optionalUser.isEmpty())
            return register(kakaoId);

        User user = optionalUser.get();

        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return new ResponseEntity<>(new AuthLoginRes(refreshToken, accessToken), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthLoginRes> patchLogin(String refreshToken) {

        String token = refreshToken.split(" ")[1];
        Optional<User> optionalUser = userRepository.findByRefreshToken(token);

        if (optionalUser.isEmpty())
            throw new NullPointerException();

        String accessToken = jwtTokenProvider.createAccessToken(optionalUser.get());

        return new ResponseEntity<>(new AuthLoginRes(refreshToken, accessToken), HttpStatus.OK);
    }

    private ResponseEntity<AuthLoginRes> register(Integer kakaoId) {

        User user = User.builder()
                .kakaoId(kakaoId)
                .name(randomName())
                .starScore(10000)
                .build();

        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return new ResponseEntity<>(new AuthLoginRes(refreshToken, accessToken), HttpStatus.CREATED);
    }

    private String getKakaoAccessToken(String code) throws IOException {
        String accessToken;

        URL url = new URL(accessTokenReqUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
        String stringBuilder = "grant_type=authorization_code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&code=" + code;

        bufferedWriter.write(stringBuilder);
        bufferedWriter.flush();

        httpURLConnection.getResponseCode();

        JsonElement element = getJsonElement(httpURLConnection);

        accessToken = element.getAsJsonObject().get("access_token").getAsString();

        bufferedWriter.close();

        return accessToken;
    }

    private Integer getKakaoId(String kakaoAccessToken) throws IOException {
        JsonElement element = getJsonElementByAccessToken(kakaoAccessToken);

        return element.getAsJsonObject().get("id").getAsInt();
    }

    private JsonElement getJsonElementByAccessToken(String token) throws IOException {

        URL url = new URL(jsonReqUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + token);

        return getJsonElement(httpURLConnection);
    }

    private JsonElement getJsonElement(HttpURLConnection httpURLConnection) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String line;
        StringBuilder result = new StringBuilder();

        while((line = bufferedReader.readLine()) != null){
            result.append(line);
        }

        bufferedReader.close();

        return JsonParser.parseString(result.toString());
    }

    private String randomName() {
        Random random = new Random();
        String name;

        do {
            int idx = random.nextInt(prefixes.size());
            String prefix = prefixes.get(idx);

            int num = random.nextInt(1000);

            name = prefix + " 경붕이 " + num;
        } while (userRepository.existsByName(name));

        return name;
    }
}
