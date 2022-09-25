package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.InfoScoreDto;
import com.project.model.entity.InfoScoreEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author dsl
 * @description 针对表【info_score】的数据库操作Mapper
 * @createDate 2022-09-20 12:43:45
 * @Entity generator.domain.InfoScore
 */
@Mapper
public interface InfoScoreMapper extends BaseMapper<InfoScoreEntity> {

    /**
     * 根据条件分页查询评分表数据
     *
     * @param infoScoreDto 评分表信息
     * @return 评分表数据集合信息
     */
    IPage<InfoScoreEntity> selectInfoScore(InfoScoreDto infoScoreDto);

    /**
     * 新增评分表信息
     *
     * @param infoScoreEntity 评分表数据信息
     * @return 结果
     */
    int insertInfoScore(InfoScoreEntity infoScoreEntity);

    /**
     * 通过评分ID删除数据信息
     *
     * @param id 评分Id
     * @return 结果
     */
    int deleteInfoScoreById(Long id);

    /**
     * 通过访问申请ID批量删除数据信息
     *
     * @param visitIds 访问申请Ids
     * @return 结果
     */
    int deleteInfoScoreByVisitIds(Long[] visitIds);

    /**
     * 修改评分表信息
     *
     * @param infoScoreEntity 评分表信息
     * @return 结果
     */
    int updateInfoScore(InfoScoreEntity infoScoreEntity);

    /**
     * 通过主键查询评分表信息
     *
     * @param id 评分表id
     * @return 结果
     */
    InfoScoreEntity selectInfoScoreById(Long id);

    /**
     * 通过访问申请ID查询评分表信息
     *
     * @param visitId 访问申请Id
     * @return 结果
     */
    InfoScoreEntity selectInfoScoreByVisitId(Long visitId);
}
