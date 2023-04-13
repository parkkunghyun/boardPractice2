package boardPractice2.boardPractice2.service;

import boardPractice2.boardPractice2.entity.User;
import boardPractice2.boardPractice2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User signIn(String name, String email, String password) {
        // 온 데이터를 모아서 하나의 객체로 만들어서 repository에 던져주기!
        User savedUser = userRepository.save(name,email,password);

        //여기에 이제 역할 추가를 해주기 위해 하나의 함수를 더 사용!
        userRepository.mappingUSerRole(savedUser.getUserId());
        return savedUser;
    }
    @Transactional
    public User getUser( String email) {
        // 여기서는 찾아서 넘겨주기!
        User findUser=  userRepository.getUser(email);
        return findUser;
    }

}
