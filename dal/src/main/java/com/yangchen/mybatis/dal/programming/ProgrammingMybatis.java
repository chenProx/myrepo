package com.yangchen.mybatis.dal.programming;

import com.yangchen.mybatis.dal.dao.TestMapper;
import com.yangchen.mybatis.dal.domain.table.Test;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProgrammingMybatis {
    public static SqlSession getSqlSession() throws FileNotFoundException {
        InputStream inputStream = new
                FileInputStream("D:\\code\\myrepo\\dal\\src\\main\\resources\\mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        return sqlSession;
    }

    public static Test getByPrimaryKey(SqlSession sqlSession, int id) {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        long start = System.currentTimeMillis();
        Test test = mapper.selectByPrimaryKey(id);
        System.out.println(System.currentTimeMillis() - start);
        return test;
    }

    public static void main(String[] args) throws FileNotFoundException {
        SqlSession sqlSession = getSqlSession();
        try {
            Test test = getByPrimaryKey(sqlSession, 1);
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
