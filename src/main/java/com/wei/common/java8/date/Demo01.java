package com.wei.common.java8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/4 0004
 */
public class Demo01 {
    public static void main(String[] args) {
        //示例1:Java 8中获取今天的日期
        //method01();
        //示例2:Java 8中获取年、月、日信息
        //method02();
        //示例3:Java 8中处理特定日期
        //method03();
        //示例4:Java 8中判断两个日期是否相等
        //method04();
        //示例5:Java 8中检查像生日这种周期性事件
        //method05();
        //示例6:Java 8中获取当前时间
        //method06();
        //示例7:Java 8中计算时间加减
        //method07();
        //示例8:Java 8中计算日期加减
        //method08();
        //示例9:Java 8计算一年前或一年后的日期
        //method09();
        //示例10:Java 8的Clock时钟类
        //method10();
        //示例11:如何用Java判断日期是早于还是晚于另一个日期
        //method11();
        //示例12:Java 8中处理时区
        method12();
        //示例13:如何表示信用卡到期这类固定日期，答案就在YearMonth
        //method13();
        //示例14:如何在Java 8中检查闰年
        //method14();
        //示例15:计算两个日期之间的天数和月数
        //method15();
        //示例16:在Java 8中获取当前的时间戳
        //method16();
        //示例17:Java 8中如何使用预定义的格式化工具去解析或格式化日期
        //method17();
        //示例18:字符串互转日期类型
        //method18();

    }

    /**
     * Java 8中获取今天的日期
     */
    public static void method01(){
        //Java 8 中的 LocalDate 用于表示当天日期。和java.util.Date不同，它只有日期，不包含时间。当你仅需要表示日期时就用这个类。
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期:"+today);//2020-03-04
    }
    /**
     * Java 8中获取年、月、日信息
     */
    public static void method02(){
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        System.out.println("year:"+year);//2020
        System.out.println("month:"+month);//3
        System.out.println("day:"+day);//4
    }
    /**
     * Java 8中处理特定日期
     */
    public static void method03(){
        //可以通过静态工厂方法now()创建当天日期，还可以调用另一个有用的工厂方法LocalDate.of()创建任意日期，该方法需要传入年、月、日做参数，返回对应的LocalDate实例。
        //这个方法的好处是没再犯老API的设计错误，比如年度起始于1900，月份是从0开始等等。
        LocalDate date = LocalDate.of(2018,2,6);
        System.out.println("自定义日期:"+date);//2018-02-06
    }
    /**
     * Java 8中判断两个日期是否相等
     */
    public static void method04(){
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2018,2,5);

        if(date1.equals(date2)){
            System.out.println("时间相等");
        }else{
            System.out.println("时间不等");
        }
    }
    /**
     * Java 8中检查像生日这种周期性事件
     */
    public static void method05(){
        //只要当天的日期和生日匹配，无论是哪一年都会打印出祝贺信息。你可以把程序整合进系统时钟，看看生日时是否会受到提醒，或者写一个单元测试来检测代码是否运行正确。
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(1990,3,4);
        MonthDay birthday = MonthDay.of(date2.getMonth(),date2.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(date1);

        if(currentMonthDay.equals(birthday)){
            System.out.println("是你的生日");
        }else{
            System.out.println("你的生日还没有到");
        }
    }
    /**
     * Java 8中获取当前时间
     */
    public static void method06(){
        LocalTime time = LocalTime.now();
        System.out.println("获取当前的时间,不含有日期:"+time);//14:38:33.623
    }
    /**
     * Java 8中计算时间加减
     */
    public static void method07(){
        //通过增加小时、分、秒来计算将来的时间很常见。Java 8除了不变类型和线程安全的好处之外，还提供了更好的plusHours()方法替换add()，并且是兼容的。
        //注意，这些方法返回一个全新的LocalTime实例，由于其不可变性，返回后一定要用变量赋值。
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(3);
        System.out.println("三个小时后的时间为:"+newTime);//17:41:38.247
    }
    /**
     * Java 8中计算日期加减
     */
    public static void method08(){
        //和上个例子计算3小时以后的时间类似，这个例子会计算一周后的日期。LocalDate日期不包含时间信息，它的plus()方法用来增加天、周、月，ChronoUnit类声明了这些时间单位。
        // 由于LocalDate也是不变类型，返回后一定要用变量赋值。
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期为:"+today);//2020-03-04
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后的日期为:"+nextWeek);//2020-03-11
    }
    /**
     * Java 8计算一年前或一年后的日期
     */
    public static void method09(){
        LocalDate today = LocalDate.now();
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期: " + previousYear);//2019-03-04

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("一年后的日期: "+nextYear);//2021-03-04
    }
    /**
     * Java 8的Clock时钟类
     */
    public static void method10(){
        //Java 8增加了一个Clock时钟类用于获取当时的时间戳，或当前时区下的日期时间信息。以前用到System.currentTimeInMillis()和TimeZone.getDefault()的地方都可用Clock替换。
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());//1583305595800

        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());//1583305595800
    }
    /**
     * 如何用Java判断日期是早于还是晚于另一个日期
     */
    public static void method11(){
        //在Java 8中，LocalDate类有两类方法isBefore()和isAfter()用于比较日期。
        LocalDate today = LocalDate.now();
        LocalDate day1 = LocalDate.of(2020,5,6);
        System.out.println("isAfter比较结果:"+day1.isAfter(today));

        LocalDate day2 = today.minus(1, ChronoUnit.DAYS);
        System.out.println("isBefore比较结果:"+day2.isBefore(today));
    }
    /**
     * Java 8中处理时区
     */
    public static void method12(){
        //Java 8不仅分离了日期和时间，也把时区分离出来了。现在有一系列单独的类如ZoneId来处理特定时区，ZoneDateTime类来表示某时区下的时间。
        //这在Java 8以前都是GregorianCalendar类来做的。下面这个例子展示了如何把本时区的时间转换成另一个时区的时间。
        ZonedDateTime beijingTime = ZonedDateTime.now();
        System.out.println("当前时间: "+ beijingTime);

        ZoneId america = ZoneId.of("America/New_York");
        ZonedDateTime newyorkTime = ZonedDateTime.of(beijingTime.toLocalDateTime(), america);
        System.out.println("纽约时间: " + newyorkTime);
    }
    /**
     * 如何表示信用卡到期这类固定日期，答案就在YearMonth
     */
    public static void method13(){
        //与 MonthDay检查重复事件的例子相似，YearMonth是另一个组合类，用于表示信用卡到期日、FD到期日、期货期权到期日等。
        //还可以用这个类得到 当月共有多少天，YearMonth实例的lengthOfMonth()方法可以返回当月的天数，在判断2月有28天还是29天时非常有用。
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("当前年月:%s,本月总计天数:%d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
    }
    /**
     * 如何在Java 8中检查闰年
     */
    public static void method14(){
        LocalDate today = LocalDate.now();
        if(today.isLeapYear()){
            System.out.println("今年是闰年");
        }else {
            System.out.println("今年不是闰年");
        }
    }
    /**
     * 计算两个日期之间的天数和月数
     */
    public static void method15(){
        //计算两个日期之间的天数、周数或月数。在Java 8中可以用java.time.Period类来做计算。下面这个例子中，我们计算了当天和将来某一天之间的月数。
        LocalDate today = LocalDate.now();
        LocalDate java8Release = LocalDate.of(2018, 12, 14);

        Period period = Period.between(today, java8Release);
        System.out.println("两日期年差:"+period.getYears()+",月差:"+period.getMonths()+",日差:"+period.getDays());
    }
    /**
     * 在Java 8中获取当前的时间戳
     */
    public static void method16(){
        //Instant类有一个静态工厂方法now()会返回当前的时间戳
        //时间戳信息里同时包含了日期和时间，这和java.util.Date很像。实际上Instant类确实等同于Java 8之前的Date类，你可以使用Date类和Instant类各自的转换方法互相转换
        //例如：Date.from(Instant) 将Instant转换成java.util.Date，Date.toInstant()则是将Date类转换成Instant类。
        Instant timestamp = Instant.now();
        System.out.println("当前日期时间戳: " + timestamp.toEpochMilli());
    }
    /**
     * Java 8中如何使用预定义的格式化工具去解析或格式化日期
     */
    public static void method17(){
        String dayAfterTommorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTommorrow+"  格式化后的日期为:  "+formatted);
    }
    /**
     * 字符串互转日期类型
     */
    public static void method18(){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转字符串
        String str = date.format(format1);
        System.out.println("日期转换为字符串:"+str);

        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //字符串转日期
        LocalDate date2 = LocalDate.parse(str,format2);
        System.out.println("日期类型:"+date2);
    }
}
