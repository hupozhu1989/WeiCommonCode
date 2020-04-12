package com.wei.interview.passvalue03;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/12
 */
@NoArgsConstructor
@Getter
@Setter
public class Person{
    private Integer id;
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }
}
