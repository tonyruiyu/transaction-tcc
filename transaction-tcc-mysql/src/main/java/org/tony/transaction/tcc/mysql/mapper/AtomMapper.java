package org.tony.transaction.tcc.mysql.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tony.transaction.tcc.mysql.po.Atom;
import org.tony.transaction.tcc.mysql.po.AtomExample;

public interface AtomMapper {
    long countByExample(AtomExample example);

    int deleteByExample(AtomExample example);

    int deleteByPrimaryKey(String atomId);

    int insert(Atom record);

    int insertSelective(Atom record);

    List<Atom> selectByExampleWithBLOBs(AtomExample example);

    List<Atom> selectByExample(AtomExample example);

    Atom selectByPrimaryKey(String atomId);

    int updateByExampleSelective(@Param("record") Atom record, @Param("example") AtomExample example);

    int updateByExampleWithBLOBs(@Param("record") Atom record, @Param("example") AtomExample example);

    int updateByExample(@Param("record") Atom record, @Param("example") AtomExample example);

    int updateByPrimaryKeySelective(Atom record);

    int updateByPrimaryKeyWithBLOBs(Atom record);

    int updateByPrimaryKey(Atom record);
}