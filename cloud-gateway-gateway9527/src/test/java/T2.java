import java.time.ZonedDateTime;

public class T2 {

    public static void main(String[] args) {
        //获取spring cloud Gateway 中国区的时间格式
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
