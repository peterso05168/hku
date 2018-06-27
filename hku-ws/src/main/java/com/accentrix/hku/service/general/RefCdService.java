package com.accentrix.hku.service.general;

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

import com.accentrix.hku.vo.general.RefCdVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:32:34
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "RefCd Service", description = "refCdService")
public interface RefCdService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/refCd/get/", verb = ApiVerb.GET, description = "get refCd by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RefCdVo get(@ApiQueryParam(name = "id", description = "The refCd id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/refCd/", verb = ApiVerb.POST, description = "save refCd", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RefCdVo save(@ApiBodyObject RefCdVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/refCd/save-list/", verb = ApiVerb.POST, description = "save refCd list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<RefCdVo> save(List<RefCdVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/refCd/{id}/", verb = ApiVerb.DELETE, description = "delete refCd by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The refCd Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/refCd/delete-refCd/", verb = ApiVerb.POST, description = "delete refCd", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject RefCdVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/refCd/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<RefCdVo> findList();

    @POST
    @Path("/findListByType/")
    @ApiMethod(path = "/api/rest/refCd/findListByType/", verb = ApiVerb.POST, description = "findListByType", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })

    @ApiResponseObject
    List<RefCdVo> findListByType(@ApiQueryParam(name = "type") @QueryParam("type") String type);

    @GET
    @Path("/getByTpyeAndCd/")
    @ApiMethod(path = "/api/rest/refCd/getByTpyeAndCd/", verb = ApiVerb.GET, description = "get refCd by Type and Cd", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    RefCdVo getByTypeAndCd(@ApiQueryParam(name = "type") @QueryParam("type") String type,
            @ApiQueryParam(name = "cd") @QueryParam("cd") String cd);
}
