package com.yangchen.mybatis.dal.dao;

import com.yangchen.mybatis.dal.domain.table.Test;

public interface TestMapper {
    Test selectByPrimaryKey(Integer id);
}