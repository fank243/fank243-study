import com.github.fank243.study.core.domain.enums.UserStatusEnum;

/**
 * @author FanWeiJie
 * @date 2023-09-15 21:43
 */
public class Main {

    public static void main(String[] args) {
        UserStatusEnum userStatusEnum = Enum.valueOf(UserStatusEnum.class, "NORMAL");
        System.out.println(userStatusEnum);
    }
}
