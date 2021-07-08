package com.wei.common.objectsize;

import lombok.Data;
import org.apache.lucene.util.RamUsageEstimator;

import java.math.BigDecimal;

/**
 * @author weizhenchao
 * @create 2021-07-08-19:25
 */
public class ObjectSizeCal {
    public static void main(String[] args) {
        Order order = new Order();
        order.setId(123456789);
        order.setName("面包");
        order.setMoney(new BigDecimal("99.9"));
        long size = RamUsageEstimator.sizeOf(order);
        System.out.println("对象大小(kb):"+size);

        String humanSize = RamUsageEstimator.humanSizeOf(order);
        System.out.println("对象大小(mb):"+humanSize);
    }
}

@Data
class Order{
    private Integer id;
    private String name;
    private BigDecimal money;
    private byte[] bytes = new byte[1024*1024];//1204kb = 1mb
}
