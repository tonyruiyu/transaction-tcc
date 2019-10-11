package org.tony.transaction.tcc.mysql.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tony.transaction.tcc.mysql.po.Composite;
import org.tony.transaction.tcc.mysql.po.CompositeExample;

public interface CompositeMapper {
    long countByExample(CompositeExample example);

    int deleteByExample(CompositeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Composite record);

    int insertSelective(Composite record);

    List<Composite> selectByExample(CompositeExample example);

    Composite selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Composite record, @Param("example") CompositeExample example);

    int updateByExample(@Param("record") Composite record, @Param("example") CompositeExample example);

    int updateByPrimaryKeySelective(Composite record);

    int updateByPrimaryKey(Composite record);
}