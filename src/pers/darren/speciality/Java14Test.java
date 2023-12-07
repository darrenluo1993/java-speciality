package pers.darren.speciality;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.concurrent.TimeUnit.SECONDS;
import static pers.darren.speciality.Java14Test.WeekEnum.MONDAY;
import static pers.darren.speciality.Java14Test.WeekEnum.SUNDAY;
import static pers.darren.speciality.Java14Test.WeekEnum.TUESDAY;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Java14Test {

    private static final Charset GBK = Charset.forName("GBK");

    public static void main(final String[] args) throws Exception {
        // instanceofTest();
        // switchTest1();
        // switchTest2();
        // switchTest3();
        // textBlocksTest();
        // newNPETest();
        // new Record("name", "desc").print();
        // httpClientSyncTest();
        // httpClientAsyncTest();
        byteArrayOutputStreamToStringTest();
    }

    @SuppressWarnings("preview")
    public static void instanceofTest() {
        final Object name = "Darren Luo";
        if (name instanceof String nameStr) {
            System.out.println(nameStr);
        }
    }

    public static void switchTest1() {
        final var weekDay = SUNDAY;
        switch (weekDay) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> System.out.println("工作日");
            case SATURDAY, SUNDAY -> System.out.println("节假日");
            default -> System.out.println("参数错误");
        }
    }

    public static void switchTest2() {
        final var weekDay = MONDAY;
        // 可获取switch表达式返回值
        final var weekDesc = switch (weekDay) {
            // -> 后的字符串，直接作为表达式的返回结果
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "工作日";
            case SATURDAY, SUNDAY -> "节假日";
            default -> "参数错误";
        };
        System.out.println(weekDesc);
    }

    public static void switchTest3() {
        final var weekDay = TUESDAY;
        final var weekDesc = switch (weekDay) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
                System.out.println("今天是工作日");
                // 通过yield指定表达式的返回结果
                yield "工作日";
            }
            case SATURDAY, SUNDAY -> {
                System.out.println("今天是节假日");
                // 通过yield指定表达式的返回结果
                yield "节假日";
            }
            default -> "参数错误";
        };
        System.out.println(weekDesc);
    }

    public static void textBlocksTest() {
        final var html = """
                <html>
                    <body>
                        <p>Hello, world</p>
                    </body>
                </html>
                """;
        System.out.println(html);
        final var query = """
                select
                    EMP_ID,
                    LAST_NAME
                from
                    EMPLOYEE_TB
                where
                    CITY = "INDIANAPOLIS"
                order by
                    EMP_ID, LAST_NAME;
                """;
        System.out.println(query);
        final var json = """
                {
                    "student": {
                        "name": "小明",
                        "age": "21",
                        "school": {
                            "local": "武汉",
                            "name": "武汉大学"
                        }
                    }
                }
                """;
        System.out.println(json);
    }

    public static void newNPETest() {
        final var testClass1 = new NPETestClass1();
        final var testClass2 = new NPETestClass2();
        final var testClass3 = new NPETestClass3();
        testClass1.setTestClass2(testClass2);
        testClass2.setTestClass3(testClass3);
        System.out.println(testClass1.getTestClass2().getTestClass3().getValue());
    }

    public static void httpClientSyncTest() throws IOException, InterruptedException {
        final var client = HttpClient.newHttpClient();
        final var requestBody = """
                {
                    "name": "Darren Luo",
                    "phone": "15188889999",
                    "email": "darrenluo8888@163.com",
                    "address": "湖南省长沙市天心区湘江中路二段210号湖南银行总部大厦"
                }
                """;
        final var request = HttpRequest.newBuilder(URI.create("http://localhost:7777/example/httpClienTest"))
                .setHeader("sign", UUID.randomUUID().toString())
                .setHeader("token", UUID.randomUUID().toString())
                .setHeader("timestamp", String.valueOf(System.currentTimeMillis()))
                .POST(BodyPublishers.ofString(requestBody.stripIndent(), UTF_8)).build();
        final var response = client.send(request, BodyHandlers.ofString(UTF_8));
        System.out.println(response.body());
    }

    public static void httpClientAsyncTest() throws InterruptedException, ExecutionException, TimeoutException {
        final var client = HttpClient.newHttpClient();
        final var requestBody = """
                {
                    "name": "Darren Luo",
                    "phone": "15188889999",
                    "email": "darrenluo8888@163.com",
                    "address": "湖南省长沙市天心区湘江中路二段210号湖南银行总部大厦"
                }
                """;
        final var request = HttpRequest.newBuilder(URI.create("http://localhost:7777/example/httpClienTest"))
                .setHeader("sign", UUID.randomUUID().toString())
                .setHeader("token", UUID.randomUUID().toString())
                .setHeader("timestamp", String.valueOf(System.currentTimeMillis()))
                .POST(BodyPublishers.ofString(requestBody.stripIndent(), UTF_8)).build();
        final var future = client.sendAsync(request, BodyHandlers.ofString(UTF_8));
        final var response = future.get(30, SECONDS);
        System.out.println(response.body());
    }

    public static void byteArrayOutputStreamToStringTest() throws IOException {
        String value = "测试 ByteArrayOutputStream.toString()";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(value.getBytes(GBK));
        System.out.println(stream.toString());
        System.out.println(stream.toString(GBK));
        System.out.println(new String(stream.toByteArray()));
        System.out.println(new String(stream.toByteArray(), "GBK"));
    }

    public enum WeekEnum {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    }

    public static class NPETestClass1 {

        private NPETestClass2 testClass2;

        public NPETestClass2 getTestClass2() {
            return this.testClass2;
        }

        public void setTestClass2(final NPETestClass2 testClass2) {
            this.testClass2 = testClass2;
        }
    }

    public static class NPETestClass2 {

        private NPETestClass3 testClass3;

        public NPETestClass3 getTestClass3() {
            return this.testClass3;
        }

        public void setTestClass3(final NPETestClass3 testClass3) {
            this.testClass3 = testClass3;
        }
    }

    public static class NPETestClass3 {

        private String value;

        public String getValue() {
            return this.value;
        }

        public void setValue(final String value) {
            this.value = value;
        }
    }

    @SuppressWarnings("preview")
    public static record Record(String name, String desc) {
        public void print() {
            System.out.println(this.name + "===" + this.desc());
        }
    }
}