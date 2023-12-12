package pers.darren.speciality;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.StringTemplate.RAW;

public class Java21Test {
    private static final String name = "Darren Luo 2";

    public static void main(String[] args) {
        // stringTemplates();
        // sequencedCollections();
        formatterPatternSwitch(123);
        formatterPatternSwitch(234L);
        formatterPatternSwitch(345.12F);
        formatterPatternSwitch(456.23D);
        formatterPatternSwitch("567.34");
    }

    /**
     * <pre>
     * <font color="#916dd5" size="5">String Templates (Preview)</font>
     * <a href="https://openjdk.org/jeps/430" style="color:#916dd5;font-size:12px;">JEP 430：字符串模板（预览）</a>
     *
     * <b>1. 什么是 String Templates?</b>
     * String Templates 是 Java 21 中引入的一个新特性，它允许我们在字符串中使用占位符来动态替换变量的值。它提供了一种更简洁、更直观的方式来构建字符串，而不需要使用传统的字符串拼接或格式化方法。
     *
     * <b>2. 为什么需要 String Templates?</b>
     * 在传统的 Java 中，我们通常使用字符串拼接或格式化方法来构建动态字符串。这种方式需要手动处理变量的值，并且容易出错。而且，当字符串中包含大量变量时，代码会变得冗长且难以维护。
     *
     * String Templates 的引入解决了这个问题，它提供了一种更简洁、更易读的方式来构建动态字符串。通过使用占位符，我们可以将变量的值直接嵌入到字符串中，而不需要手动处理。
     *
     * <b>3. String Templates 的实现原理?</b>
     * String Templates 的实现原理是通过在字符串中使用占位符<font color="#916dd5">\{}</font>来表示变量。在运行时，Java 编译器会将这些占位符替换为实际的变量值。
     *
     * 具体来说，当我们使用 String Templates 时，编译器会将字符串中的占位符<font color="#916dd5">\{}</font>解析为一个特殊的表达式，并将其转换为对应的变量值。这个过程是在编译时完成的，所以在运行时不会有额外的性能开销。
     *
     * <b>4. String Templates 的优点</b>
     * <font color="#916dd5">「简洁易读」</font>：使用占位符<font color="#916dd5">\{}</font>来表示变量，使得代码更加简洁、易读。
     * <font color="#916dd5">「类型安全」</font>：String Templates 在编译时会进行类型检查，确保变量的类型与占位符的类型匹配，避免了运行时的类型错误。
     * <font color="#916dd5">「性能优化」</font>：String Templates 的解析过程是在编译时完成的，所以在运行时不会有额外的性能开销。
     * <font color="#916dd5">「可扩展性」</font>：String Templates 支持自定义的格式化函数，可以根据需求进行扩展。
     *
     * <b>5. String Templates 的缺点</b>
     * <font color="#916dd5">「兼容性」</font>：String Templates 是 Java 21 中引入的新特性，需要使用 Java 21 或更高版本的 JDK 才能使用。
     * <font color="#916dd5">「可读性」</font>：当字符串中包含大量的占位符时，可能会降低代码的可读性。
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 12/6/23 4:48 PM
     * @since 21
     */
    public static void stringTemplates() {
        String name = "Darren Luo";
        int age = 18;
        String message = STR. "My name is \{ name } and I'm \{ age } years old." ;
        System.out.println(message);

        // STR
        message = STR. "Greetings \{ name }." ;
        System.out.println(message);
        // FMT
        message = STR. "Greetings %-12s\{ name }." ;
        System.out.println(message);
        // RAW
        StringTemplate st = RAW. "Greetings \{ name }." ;
        message = STR.process(st);
        System.out.println(message);

        // method
        message = STR. "Greetings \{ getName() }!" ;
        System.out.println(message);
        // field
        message = STR. "Greetings \{ Java21Test.name }!" ;
        System.out.println(message);

        int x = 10, y = 20;
        String s = STR. "\{ x } + \{ y } = \{ x + y }" ;    //"10 + 20 = 30"
        System.out.println(s);

        String time = STR. "The current time is \{
                // sample comment - current time in HH:mm:ss
                DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.now()) }." ;
        System.out.println(time);

        String title = "My Web Page";
        String text = "Hello, world";
        String html = STR. """
                <html>
                    <head>
                        <title>\{ title }</title>
                    </head>
                    <body>
                        <p>\{ text }</p>
                    </body>
                </html>
                """ ;
        System.out.println(html);
    }

    /**
     * <a href="https://openjdk.org/jeps/431" style="color:#916dd5;font-size:12px;">JEP431：序列化集合</a>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 12/8/23 5:09 PM
     */
    public static void sequencedCollections() {
        ///////////////////////////////SequencedCollection///////////////////////////////
        System.out.println("///////////////////////////////SequencedCollection///////////////////////////////");
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);   // List contains: [1]

        arrayList.addFirst(0);  // List contains: [0, 1]
        arrayList.addLast(2);   // List contains: [0, 1, 2]

        Integer firstElement = arrayList.getFirst();  // 0
        System.out.println(firstElement);
        Integer lastElement = arrayList.getLast();  // 2
        System.out.println(lastElement);

        List<Integer> reversed = arrayList.reversed();
        System.out.println(reversed); // Prints [2, 1, 0]

        ///////////////////////////////SequencedSet///////////////////////////////
        System.out.println("///////////////////////////////SequencedSet///////////////////////////////");
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(List.of(1, 2, 3));

        firstElement = linkedHashSet.getFirst();   // 1
        System.out.println(firstElement);
        lastElement = linkedHashSet.getLast();    // 3
        System.out.println(lastElement);

        linkedHashSet.addFirst(0);  // List contains: [0, 1, 2, 3]
        linkedHashSet.addLast(4);   // List contains: [0, 1, 2, 3, 4]

        System.out.println(linkedHashSet.reversed());   // Prints [4, 3, 2, 1, 0]

        linkedHashSet.addFirst(5);  // List contains: [5, 0, 1, 2, 3, 4]
        System.out.println(linkedHashSet);
        linkedHashSet.addFirst(3);  // List contains: [3, 5, 0, 1, 2, 4]
        System.out.println(linkedHashSet);

        ///////////////////////////////SequencedMap///////////////////////////////
        System.out.println("///////////////////////////////SequencedMap///////////////////////////////");
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        Map.Entry<Integer, String> firstEntry = map.firstEntry();   // 1=One
        System.out.println("firstEntry: " + firstEntry.getKey() + "===" + firstEntry.getValue());
        Map.Entry<Integer, String> lastEntry = map.lastEntry();    // 3=Three
        System.out.println(STR. "lastEntry: \{ lastEntry.getKey() }===\{ lastEntry.getValue() }" );
        System.out.println(map);  //{1=One, 2=Two, 3=Three}
        // pollFirstEntry与pollLastEntry不仅仅是获取元素，还会从Map中移除对应的元素
        firstEntry = map.pollFirstEntry();   // 1=One
        System.out.println(STR. "pollFirstEntry: \{ firstEntry.getKey() }===\{ firstEntry.getValue() }" );
        lastEntry = map.pollLastEntry();    // 3=Three
        System.out.println(STR. "pollLastEntry: \{ lastEntry.getKey() }===\{ lastEntry.getValue() }" );
        System.out.println(map);  //{2=Two}

        map.putFirst(1, "One");     //{1=One, 2=Two}
        map.putLast(3, "Three");    //{1=One, 2=Two, 3=Three}
        System.out.println(map);
        map.putFirst(3, "Three");    //{1=One, 2=Two, 3=Three}
        System.out.println(map);  //{1=One, 2=Two, 3=Three}
        System.out.println(map.reversed());   //{3=Three, 2=Two, 1=One}
    }

    /**
     * <a href="https://openjdk.org/jeps/441" style="color:#916dd5;font-size:12px;">JEP 441：switch 的模式匹配（正式版）</a>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 12/8/23 5:09 PM
     */
    public static void formatterPatternSwitch(Object o) {
        // Prior to Java 21
        String formatted;
        if (o instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (o instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (o instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (o instanceof String s) {
            formatted = String.format("String %s", s);
        } else {
            formatted = "unknown " + o;
        }
        System.out.println("Prior to Java 21: " + formatted);

        // As of Java 21
        formatted = switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> "unknown " + o;
        };
        System.out.println("As of Java 21: " + formatted);
    }

    public static String getName() {
        return "Darren Luo 1";
    }
}
