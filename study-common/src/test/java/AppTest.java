import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Builder;
import lombok.Data;

public class AppTest {

    @Data
    @Builder
    static class Tst {
        private String name;
    }

    public static void main(String[] args) {
        List<Tst> list = new ArrayList<>();

        list.add(Tst.builder().name("abc").build());
        list.add(Tst.builder().name("adc").build());
        list.add(Tst.builder().name("bac").build());

        Collections.swap(list,
            list.indexOf(list.stream().filter(tst -> tst.getName().equals("bac")).findFirst().orElse(list.get(0))), 0);

        System.out.println(list);
    }
}
