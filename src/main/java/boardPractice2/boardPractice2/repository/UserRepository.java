package boardPractice2.boardPractice2.repository;

import boardPractice2.boardPractice2.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Repository
public class UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsertOperations insertUser;

    public UserRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("user2")
                .usingGeneratedKeyColumns("user_id");
    }

    @Transactional
    public User save(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRegdate(LocalDateTime.now());

        // 일단 db에 저장하기!
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        Number number =  insertUser.executeAndReturnKey(params);
        int userID = number.intValue();
        user.setUserId(userID);
        return user;
    }

    @Transactional
    public void mappingUSerRole(int userId) {
        // userId를 찾아서 role_id를 넣어주기!
        // 일단 넣어주기!
        String sql = "insert into user_role2(user_id, role_id) values (:userId,1)";
        SqlParameterSource params = new MapSqlParameterSource("userId", userId);
        jdbcTemplate.update(sql,params);
    }

    @Transactional
    public User getUser(String email) {
        String sql = "select * from user2 where email = :email";
        SqlParameterSource params = new MapSqlParameterSource("email",email);
        RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);

        User user = jdbcTemplate.queryForObject(sql,params,rowMapper);
        return user;
    }
}
