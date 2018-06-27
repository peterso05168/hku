package com.accentrix.hku.repository.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.tag.Tag;
import com.accentrix.hku.repository.tag.custom.TagRepositoryCustom;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月20日 下午4:10:21 
 * @version 1.0 
 */

@Repository
public interface TagRepository extends JpaRepository<Tag, String>, TagRepositoryCustom {

}
