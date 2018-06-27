package com.accentrix.hku.service.tag.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.tag.Tag;
import com.accentrix.hku.repository.tag.TagRepository;
import com.accentrix.hku.service.tag.TagService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.tag.TagVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月20日 下午4:15:46 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("tag")
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public TagVo get(String id) {
        Tag tag = tagRepository.findOne(id);
        return toVo(tag);
    }

    @Transactional
    @Override
    public TagVo save(TagVo vo) {
        Tag tag = toTag(vo);
        tag = tagRepository.save(tag);
        vo.setId(tag.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<TagVo> save(List<TagVo> vos) {
        List<TagVo> tagVos = new ArrayList<TagVo>();
        for (TagVo tagVo : vos) {
            Tag tag = toTag(tagVo);
            tag = tagRepository.save(tag);
            tagVo.setId(tag.getId());
            tagVos.add(tagVo);
        }
        return tagVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        tagRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(TagVo vo) {
        Tag tag = toTag(vo);
        tagRepository.delete(tag);
    }

    @Override
    public List<TagVo> findList() {
        List<Tag> list = tagRepository.findAll();
        List<TagVo> vos = new ArrayList<TagVo>();
        for (Tag tag : list) {
            vos.add(toVo(tag));
        }
        return vos;
    }

    private Tag toTag(TagVo vo) {
        Tag tag = new Tag();
        tag.setId(vo.getId());
        tag.setDesc(vo.getDesc());
        return tag;
    }

    private TagVo toVo(Tag tag) {
        TagVo vo = new TagVo();
        vo.setId(tag.getId());
        vo.setDesc(tag.getDesc());
        return vo;
    }

}
