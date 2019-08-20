package com.lcl.testdemos;

import lombok.Data;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Address
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-13 14:09
 */
@Data
public class Address {
    private String stress;
    private String place;

    public Address(String stress, String place) {
        this.stress = stress;
        this.place = place;
    }
}
