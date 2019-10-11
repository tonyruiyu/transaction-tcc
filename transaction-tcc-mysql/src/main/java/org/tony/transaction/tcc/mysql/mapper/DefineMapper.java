package org.tony.transaction.tcc.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.tony.transaction.tcc.core.Atom;
import org.tony.transaction.tcc.core.Composite;

public interface DefineMapper {

    int updateAtomStatus(@Param("atomId") String atomId, @Param("currentStatus") String currentStatus, @Param("lastStatus") String lastStatus);

    int updateCompositeStatus(@Param("compositeId") String atomId, @Param("currentStatus") String currentStatus, @Param("lastStatus") String lastStatus);

    /**
     *
     * @param code
     * @param status
     * @param timeout 超时时间单位秒
     * @return
     */
    List<Composite> loadCompositeByStatus(@Param("code") String code, @Param("status") List<String> status, @Param("timeout") long timeout);

    List<Atom> loadAtomsByComposite(@Param("cid") String cid);

    int obtainCompositeLock(@Param("id") String id, @Param("timeout") long timeout);

    int releaseCompositeByStatus(@Param("id") String id);

    void clearCompostie(@Param("ccode") String code, @Param("saveDay") int saveDay);

    void clearAtom(@Param("saveDay") int saveDay);
}
