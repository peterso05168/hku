package com.accentrix.hku.service.app;

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

import com.accentrix.hku.vo.app.ReqDocConfVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:26:34 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "ReqDocConf Service", description = "reqDocConfService")
public interface ReqDocConfService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/reqDocConf/get/", verb = ApiVerb.GET, description = "get reqDocConf by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ReqDocConfVo get(@ApiQueryParam(name = "id", description = "The reqDocConf id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/reqDocConf/", verb = ApiVerb.POST, description = "save reqDocConf", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ReqDocConfVo save(@ApiBodyObject ReqDocConfVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/reqDocConf/save-list/", verb = ApiVerb.POST, description = "save reqDocConf list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ReqDocConfVo> save(List<ReqDocConfVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/reqDocConf/{id}/", verb = ApiVerb.DELETE, description = "delete reqDocConf by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The reqDocConf Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/reqDocConf/delete-reqDocConf/", verb = ApiVerb.POST, description = "delete reqDocConf", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ReqDocConfVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/reqDocConf/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ReqDocConfVo> findList();

    @GET
    @Path("getByTypeAndCdAndName")
    @ApiMethod(path = "/api/rest/reqDocConf/getByTypeAndCdAndName/", verb = ApiVerb.GET, description = "get reqDocConf by type and cd and name", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ReqDocConfVo getByTypeAndCdAndName(
            @ApiQueryParam(name = "type", description = "req doc type") @QueryParam("type") String type,
            @ApiQueryParam(name = "cd", description = "req doc cd") @QueryParam("cd") String cd,
            @ApiQueryParam(name = "desc", description = "req doc desc") @QueryParam("desc") String desc);
}
