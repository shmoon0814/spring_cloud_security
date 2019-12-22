package m.s.h.cloudserver.service;

import m.s.h.cloudserver.model.LoginHistory;
import m.s.h.cloudserver.model.Members;
import m.s.h.cloudserver.model.Result;
import m.s.h.cloudserver.repository.LoginHistoryRepository;
import m.s.h.cloudserver.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    LoginHistoryRepository loginHistoryRepository;

    public Result join(String email, String nick_name, String password) {
        //TODO Validaiton Check

        Members members = new Members();
        passwordEncoder.encode(password);
        members.setPassword(password);
        members.setEmail(email);
        members.setNick_name(nick_name);

        return new Result(200, memberRepository.save(members), null);
    }

    public Result login(String email, String password) {
        Members members = memberRepository.loadbyemail(email);
        if (members == null) {
            return new Result(666, null, "해당 이메일은 가입하지 않았습니다.");
        }
        if (passwordEncoder.matches(password, members.getPassword())) {
            Map<String, Object> token_map = new HashMap<>();

            String access_token = jwtTokenUtil.doGenerateToken(members);

            token_map.put("access_token", access_token);

            members.setToken(access_token);
            memberRepository.save(members);

            LoginHistory loginHistory = new LoginHistory();
            loginHistory.setUser_id(members.getId());
            loginHistoryRepository.save(loginHistory);
            return new Result(200, token_map, null);
        } else {
            return new Result(427, null, "Password is Wrong");
        }

    }


}
