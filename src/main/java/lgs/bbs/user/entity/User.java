package lgs.bbs.user.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Getter
@Entity(name = "UserInfo")
@NoArgsConstructor
@AllArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    private String id;
    private String password;

    private String name;

    private String birthday;
    private String email;
    private String gender;
    private String phoneNum;

    private String address;
    private String addressDtl;

    private int rule;
    private String deleteYn;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder
    public User(String id,
                  String password,
                  String name,
                  String birthday,
                  String email,
                  String gender,
                  String phoneNum,
                  String address,
                  String addressDtl,
                  int rule,
                  String deleteYn) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.address = address;
        this.addressDtl = addressDtl;
        this.rule = rule;
        this.deleteYn = deleteYn;
    }
}
