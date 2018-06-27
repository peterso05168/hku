package com.accentrix.hku.service.tag;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiVerb;

import com.accentrix.hku.vo.tag.TagVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月20日 下午4:15:32 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "tag Service", description = "tagService")
public interface TagService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/tag/get/", verb = ApiVerb.GET, description = "get tag by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    TagVo get(@ApiQueryParam(name = "id", description = "The tag id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/tag/", verb = ApiVerb.POST, description = "save tag", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    TagVo save(@ApiBodyObject TagVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/tag/save-list/", verb = ApiVerb.POST, description = "save tag list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<TagVo> save(List<TagVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/tag/{id}/", verb = ApiVerb.DELETE, description = "delete tag by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The tag Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/tag/delete-tag/", verb = ApiVerb.POST, description = "delete tag", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject TagVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/tag/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<TagVo> findList();
}
