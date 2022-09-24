package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.InfoReviewDto;
import com.project.model.entity.InfoReviewEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author dsl
 * @description 针对表【info_review】的数据库操作Mapper
 * @createDate 2022-09-20 12:43:45
 * @Entity generator.domain.InfoReview
 */
@Mapper
public interface InfoReviewMapper extends BaseMapper<InfoReviewEntity> {

    /**
     * 根据条件分页查询审核表数据
     *
     * @param infoReviewDto 审核表信息
     * @return 审核表数据集合信息
     */
    IPage<InfoReviewEntity> selectInfoReview(InfoReviewDto infoReviewDto);

    /**
     * 新增审核表信息
     *
     * @param infoReviewEntity 审核表数据信息
     * @return 结果
     */
    int insertInfoReview(InfoReviewEntity infoReviewEntity);

    /**
     * 通过审核ID删除数据信息
     *
     * @param id 审核Id
     * @return 结果
     */
    int deleteInfoReviewById(Long id);

    /**
     * 通过审核ID批量删除数据信息
     *
     * @param ids 审核Id数组
     * @return 结果
     */
    int deleteInfoReviewByVisitIds(Long[] ids);

    /**
     * 修改审核表信息
     *
     * @param infoReviewEntity 审核表信息
     * @return 结果
     */
    int updateInfoReview(InfoReviewEntity infoReviewEntity);

    /**
     * 通过主键查询审核表信息
     *
     * @param id 审核表id
     * @return 结果
     */
    InfoReviewEntity selectInfoReviewById(Long id);

    /**
     * 通过访问申请查询审核表信息
     *
     * @param visitId 访问申请Id
     * @return 结果
     */
    InfoReviewEntity selectInfoReviewByVisitId(Long visitId);

}
