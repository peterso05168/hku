package com.accentrix.hku.service.campaign;

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

import com.accentrix.hku.vo.campaign.CritNjVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:31:08 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "CritNj Service", description = "critNjService")
public interface CritNjService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/critNj/get/", verb = ApiVerb.GET, description = "get critNj by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CritNjVo get(@ApiQueryParam(name = "id", description = "The critNj id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/critNj/", verb = ApiVerb.POST, description = "save critNj", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CritNjVo save(@ApiBodyObject CritNjVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/critNj/save-list/", verb = ApiVerb.POST, description = "save critNj list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<CritNjVo> save(@ApiBodyObject List<CritNjVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/critNj/{id}/", verb = ApiVerb.DELETE, description = "delete critNj by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The critNj Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/critNj/delete-critNj/", verb = ApiVerb.POST, description = "delete critNj", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject CritNjVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/critNj/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CritNjVo> findList();

    @GET
    @Path("/findByCpgnId/")
    @ApiMethod(path = "/api/rest/critNj/findByCpgnId/", verb = ApiVerb.GET, description = "findByCpgnId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CritNjVo> findByCpgnId(@ApiQueryParam(name = "cpgnId") @QueryParam("cpgnId") String cpgnId);
}
