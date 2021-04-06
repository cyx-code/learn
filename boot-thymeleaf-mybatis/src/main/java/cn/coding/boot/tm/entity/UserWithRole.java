package cn.coding.boot.tm.entity;

import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

/**
 * 用户拥有哪些角色ID
 */
@Data
public class UserWithRole extends User {
    @Transient
    private Long roleId;
    @Transient
    private List<Long> roleIds;
}
