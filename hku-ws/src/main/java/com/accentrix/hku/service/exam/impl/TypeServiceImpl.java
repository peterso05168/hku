package com.accentrix.hku.service.exam.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.exam.Type;
import com.accentrix.hku.repository.exam.TypeRepository;
import com.accentrix.hku.service.exam.TypeService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.exam.TypeVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:32:16
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("type")
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public TypeVo get(String id) {
        Type type = typeRepository.findOne(id);
        return toVo(type);
    }

    @Transactional
    @Override
    public TypeVo save(TypeVo vo) {
        Type type = toType(vo);
        vo.setId(type.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<TypeVo> save(List<TypeVo> vos) {
        List<TypeVo> typeVos = new ArrayList<TypeVo>();
        for (TypeVo typeVo : typeVos) {
            Type type = toType(typeVo);
            type = typeRepository.save(type);
            typeVo.setId(type.getId());
            typeVos.add(typeVo);
        }
        return typeVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        typeRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(TypeVo vo) {
        Type type = toType(vo);
        typeRepository.delete(type);
    }

    @Override
    public List<TypeVo> findList() {
        List<TypeVo> typeVos = new ArrayList<TypeVo>();
        List<Type> types = typeRepository.findAll();
        for (Type type : types) {
            typeVos.add(toVo(type));
        }
        return typeVos;
    }

    private Type toType(TypeVo vo) {
        Type type = new Type();
        type.setExamCd(vo.getExamCd());
        type.setExamDesc(vo.getExamDesc());
        type.setExamDescChi(vo.getExamDescChi());
        type.setYearStart(vo.getYearStart());
        type.setYearEnd(vo.getYearEnd());
        return type;
    }

    private TypeVo toVo(Type type) {
        TypeVo vo = new TypeVo();
        vo.setId(type.getId());
        vo.setExamCd(type.getExamCd());
        vo.setExamDesc(type.getExamDesc());
        vo.setExamDescChi(type.getExamDescChi());
        vo.setYearStart(type.getYearStart());
        vo.setYearEnd(type.getYearEnd());
        if (Constants.EXAM_IB_CODES.contains(type.getExamCd())) {
            vo.setIsActiveIB(true);
        } else if (Constants.EXAM_GCE_CODES.contains(type.getExamCd())) {
            vo.setIsActiveGCE(true);
        } else if (Constants.EXAM_SAT_CODES.contains(type.getExamCd())) {
            vo.setIsActiveSAT(true);
        } else if (Constants.EXAM_IT_CODES.contains(type.getExamCd())) {
            vo.setIsActiveIT(true);
        } else if (Constants.EXAM_NJCEE_CODES.contains(type.getExamCd())) {
            vo.setIsActiveNJCEE(true);
        }
        return vo;
    }

    @Override
    public List<TypeVo> findByIdNotIn(List<String> ids) {
        List<TypeVo> typeVos = new ArrayList<TypeVo>();
        List<Type> types = typeRepository.findByIdNotIn(ids);
        for (Type type : types) {
            typeVos.add(toVo(type));
        }
        return typeVos;
    }

    @Override
    public TypeVo getByExamCd(String examCd) {
        Type type = typeRepository.getByExamCd(examCd);
        return toVo(type);
    }
}
